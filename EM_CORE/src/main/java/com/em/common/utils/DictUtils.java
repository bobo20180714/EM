package com.em.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.em.common.spring.ComponentFactory;
import com.em.common.utils.CacheUtils;
import com.em.core.sys.dictionary.service.ISysDictionaryService;
/*import org.apache.commons.lang3.StringUtils;
 */
import com.google.common.collect.Lists;

/**
 * 字典工具类
 */
@Service
public class DictUtils {
	private final Logger logger = Logger.getLogger(this.getClass());
	public static final String CACHE_DICT_MAP = "dictMap";
	/**
	 * 是每次将所有字典项 还是 只将对应typeCode类型的字典项 加入缓存
	 */
	private static final String INIT_ALL_DICT_CACHE_SWITCH = "on";

	public static List<Map> getDictList(String typeCode) {
		if (INIT_ALL_DICT_CACHE_SWITCH.equals("off")) {
			/*
			 * Cache cache =
			 * CacheUtils.getCacheManager().getCache(CacheUtils.DICT_CACHE); //
			 * 获取所有的缓存对象 for (Object key : cache.getKeys()) {
			 * System.out.println(key); }
			 */
			ISysDictionaryService sysDictService = ComponentFactory.getBean(ISysDictionaryService.class);
			return sysDictService.queryDictItemsByTypeCode(typeCode);
		}
		@SuppressWarnings("unchecked")
		Map<String, List<Map>> dictMap = (Map<String, List<Map>>) CacheUtils.get(CacheUtils.DICT_CACHE, CACHE_DICT_MAP);
		// 如果缓存中没有东西，则初始化所有字典项到缓存中
		if (dictMap == null) {
			dictMap = new HashMap<String, List<Map>>();
			ISysDictionaryService sysDictService = ComponentFactory.getBean(ISysDictionaryService.class);
			List<Map> dictTempList = sysDictService.rightQuery(null);
			for (Map dict : dictTempList) {
				List<Map> dictList = dictMap.get(dict.get("TYPE_CODE"));
				if (dictList != null) {
					dictList.add(dict);
				} else {
					dictMap.put(dict.get("TYPE_CODE").toString(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CacheUtils.DICT_CACHE, CACHE_DICT_MAP, dictMap);
		}
		List<Map> dictList = dictMap.get(typeCode);
		if (dictList == null) {
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
//XXX
	// 考虑到排序问题，直接刷新缓存中相应typeCode的item即可,精细的增改操作太麻烦。
	// 关于删除，删除之后再去获取typeCode显然不合适，所以还是放在service中处理
	public static void refreshCachedDicItemsByTypeCodeOfItem(Map dictItemRecord) {
		String typeCode = dictItemRecord.get("typeCode").toString();//对应JSP 参数
		refreshCachedDicItemsByTypeCode(typeCode);
	}

	//XXX
	public static void refreshCachedDicItemsByTypeCode(final String typeCode) {
		new Thread() {
			public void run() {
				// 取缓存
				Map<String, List<Map>> dictMap = (Map<String, List<Map>>) CacheUtils.get(CacheUtils.DICT_CACHE, CACHE_DICT_MAP);
				if (dictMap == null) {
					dictMap = new HashMap<String, List<Map>>();
				}

				// 取缓存当前typeCode对应字典项列表
				List<Map> dictList = dictMap.get(typeCode);
				ISysDictionaryService sysDictService = ComponentFactory.getBean(ISysDictionaryService.class);
				dictList = sysDictService.queryDictItemsByTypeCode(typeCode);
				if (dictList == null) {
					dictList = Lists.newArrayList();
				}
				dictMap.put(typeCode, dictList);

				CacheUtils.put(CacheUtils.DICT_CACHE, CACHE_DICT_MAP, dictMap);
			}
		}.start();
	}

	/**
	 * 
	 * @Title: getDictList
	 * @author：刘宇祥
	 * @date：2016年8月23日下午3:34:58
	 * @Description: 从指定表中获取指定字段作为字典内容
	 * @param keyColName
	 * @param valueColName
	 * @param tableName
	 * @param condition
	 * @return
	 */
	public static List<Map> getDictList(String valueColName, String textColName, String tableName, String condition) {
		ISysDictionaryService sysDictService = ComponentFactory.getBean(ISysDictionaryService.class);
		return sysDictService.queryDictItemByDefine(valueColName, textColName, tableName, condition);
	}

	@Scheduled(cron = "0 0/30 * * * ? ")
	public void refreshDictCache() {
		logger.info("定时器执行：com.sdyy.base.sys.utils.DictUtils——refreshDictCache");
		@SuppressWarnings("unchecked")
		Map<String, List<Map>> dictMap = (Map<String, List<Map>>) CacheUtils.get(CacheUtils.DICT_CACHE, CACHE_DICT_MAP);
		// 如果缓存中没有东西，则初始化所有字典项到缓存中
		if (dictMap == null) {
			dictMap = new HashMap<String, List<Map>>();
			ISysDictionaryService sysDictService = ComponentFactory.getBean(ISysDictionaryService.class);
			List<Map> dictTempList = sysDictService.rightQuery(null);
			for (Map dict : dictTempList) {
				List<Map> dictList = dictMap.get(dict.get("TYPE_CODE"));
				if (dictList != null) {
					dictList.add(dict);
				} else {
					dictMap.put(dict.get("TYPE_CODE").toString(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
	}

	/**
	 * 返回字典列表（JSON）
	 * 
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type) {
		return JSON.toJSONString(getDictList(type));
	}

}
