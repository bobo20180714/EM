/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.log.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.dao.IBaseDao;
import com.em.common.page.PageView;
import com.em.core.auth.role.entity.AuthRole;
import com.em.core.sys.log.entity.SysLog;
import com.em.core.sys.mappers.SysLogMapper;

/**
 * @ClassName: SysLogServiceImpl.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:48:14
 */
@Service
public class SysLogServiceImpl implements ISysLogService {

	@Autowired
//	private IBaseDao dao;
	private SysLogMapper sysLogMapper;
	
	/**
	 * 查询 一页记录
	 * XXX queryPage: sql语法错误  传参大小写, jsp页面 为字段添加name属性
	 * @param rowSelection
	 * @param map
	 * @return
	 */
	@Override
	public PageView<SysLog> queryPage(PageView<SysLog> pageView, Map<String, Object> paramMap) {
		Map<String, Object> queryPageMap = new HashMap<String, Object>();
		int count = sysLogMapper.count(pageView.getLikeParamJoinedMap(paramMap));
		pageView.setRowCountAndPreventAutoCount(count);
		queryPageMap.put("paging", pageView);
		queryPageMap.put("t", paramMap);
		List<SysLog> list = sysLogMapper.queryPage(pageView, queryPageMap);
		pageView.setRecords(list);
		return pageView;
	}
/*
 * TODO 没用到
 */
	@Override
	public SysLog getSysLog(String sysLogId){
		SysLog sysLog = sysLogMapper.selectByPrimaryKey(sysLogId);
		return sysLog;
	}
	/*
	 * TODO 没用到
	 */
	@Override
	public void addSysLog(SysLog sysLog) {
		sysLogMapper.insert(sysLog);
	}

	/*
	 * TODO 没用到
	 */
	@Override
	public void updateSysLog(SysLog sysLog) {
		sysLogMapper.updateByPrimaryKeySelect(sysLog);
	}

//	@Override
//	public List<SysLog> querySysLog(SysLog sysLog) {
//		List<SysLog> list = sysLogMapper.query(sysLog);
//		return list;
//	}
	
	/**
	 * TODO 没用到
	 * @Title: 查询所有的字典记录（左侧列表）
	 * @author：yuqing
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<SysLog> queryByMap(Map paramMap) {
		List<SysLog> list = sysLogMapper.queryByMap(paramMap);
		return list;
	}
	/*
	 * TODO 没用
	 */
	public int batchInsert(List list) {
		if (!CollectionUtils.isEmpty(list)) {
//			return dao.insert_("SysLogMapper.batchInsert", list);
		}
		return 0;

	}

}
