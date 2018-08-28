package com.em.common.spring.interceptor.schedules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.em.common.spring.interceptor.LogInterceptor;
import com.em.common.utils.cache.ICacheService;
import com.em.core.sys.log.service.ISysLogService;

@Service
public class RequestLogSchedule {

	@Autowired
	@Qualifier("ehcacheService")
	private ICacheService cacheService;

	@Autowired
	private ISysLogService sysLogService;

	public final Logger logger = Logger.getLogger(this.getClass());

	@Scheduled(cron = "0 0/1 * * * ? ")
	@PreDestroy
	public void insert() {
		try {
			Set<String> set = cacheService.getAllKeys(LogInterceptor.LOG_KEY_PRIFIX + ".*");
			List recordList = new ArrayList();
			for (String apiLogKey : set) {
				Map temp = cacheService.getMap(apiLogKey);
				if (temp != null) {
					recordList.add(temp);
				}
			}
			if (recordList.size() > 0) {
				// 防止多线程时temp为空的情况。 判断只有temp不为空的时候才插入数据库
				sysLogService.batchInsert(recordList);
				for (String apiLogKey : set) {
					cacheService.remove(apiLogKey);
				}
				if (logger.isDebugEnabled()) {
					logger.debug("日志数据:" + JSON.toJSONString(set) + "插入数据库中成功");
				}
			}

		} catch (Exception e) {
			logger.error("日志数据插入数据库失败 " + e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("日志信息定时操作已完成");
		}
	}
}
