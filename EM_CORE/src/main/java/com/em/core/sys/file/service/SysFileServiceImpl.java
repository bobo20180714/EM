/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.file.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.dao.IBaseDao;
import com.em.common.page.PageView;
import com.em.core.sys.file.entity.SysFile;
import com.em.core.sys.mappers.SysFileMapper;

/**
 * @ClassName: SysFileServiceImpl.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:48:14
 */
@Service
public class SysFileServiceImpl implements ISysFileService {

	@Autowired
	private SysFileMapper SysFileMapper;

	/*@SuppressWarnings("rawtypes")
	@Override
	public Map get(String id) {
		return (Map) dao.get("SysFileMapper.get", id);
	}

	*//**
	 * 查询 一页记录
	 * 
	 * @param rowSelection
	 * @param map
	 * @return
	 *//*
	@SuppressWarnings("rawtypes")
	@Override
	public PageView queryPage(PageView pageView, Map paramMap) {
		List list = dao.queryPage("SysFileMapper.queryPage", pageView, paramMap);
		pageView.setRecords(list);
		return pageView;
	}

	*//**
	 * 插入 一条记录
	 * 
	 * @param beanMap
	 * @return
	 *//*
	@SuppressWarnings("rawtypes")
	@Override
	public int insert(Map record) {
		return dao.insert_("SysFileMapper.insert", record);
	}

	*//**
	 * 修改 一条记录
	 * 
	 * @param beanMap
	 * @return
	 *//*
	@SuppressWarnings("rawtypes")
	@Override
	public int update(Map record) {
		return dao.update_("SysFileMapper.update", record);
	}

	*//**
	 * 删除 一条记录
	 * 
	 * @param
	 *//*
	@Override
	public int delete(String id) {
		return dao.delete("SysFileMapper.delete", id);
	}

	*//**
	 * @Title: 查询所有的记录（左侧列表）
	 * @author：liuyx
	 * @param paramMap
	 * @return
	 *//*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> query(Map paramMap) {
		List list = (List) dao.query("SysFileMapper.query", paramMap);
		return list;
	}

	public int batchInsert(List list) {
		if (!CollectionUtils.isEmpty(list)) {
			// return dao.batchInsert("SysFileMapper.insert", list);
			return dao.insert_("SysFileMapper.batchInsert", list);
		}
		return 0;

	}*/
	/**
	 * 查询 一页记录
	 * 
	 * @param rowSelection
	 * @param map
	 * @return
	 */
	@Override
	public PageView<SysFile> queryPage(PageView<SysFile> pageView, Map<String, Object> paramMap) {
		Map<String, Object> queryPageMap = new HashMap<String, Object>();
		int count = SysFileMapper.count(pageView.getLikeParamJoinedMap(paramMap));
		pageView.setRowCountAndPreventAutoCount(count);
		queryPageMap.put("paging", pageView);
		queryPageMap.put("t", paramMap);
		List<SysFile> list = SysFileMapper.queryPage(pageView, queryPageMap);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public SysFile getSysFile(String sysLogId){
		SysFile sysLog = SysFileMapper.selectByPrimaryKey(sysLogId);
		return sysLog;
	}

	@Override
	public void addSysFile(SysFile sysLog) {
		SysFileMapper.insert(sysLog);
	}
/**
 * 没用到 delSysFile
 */
	@Override
	public void delSysFile(String sysLogId) {
		SysFileMapper.deleteByPrimaryKey(sysLogId);
	}

	@Override
	public void updateSysFile(SysFile sysFile) {
		SysFileMapper.updateByPrimaryKeySelective(sysFile);
	}

	
	/**
	 * @Title: 查询所有的字典记录（左侧列表）
	 * @author：yuqing
	 * @param paramMap
	 * @return
	 */
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> query(Map paramMap) {
		List list = (List) dao.query("SysFileMapper.query", paramMap);
		return list;
	}*/
	
	@Override
	public List<SysFile> querySysFile(SysFile sysLog) {
		List<SysFile> list = SysFileMapper.query(sysLog);
		return list;
	}

	@Override
	public List<SysFile> querySysFileByMap(Map paramMap) {
		List<SysFile> list = SysFileMapper.queryByMap(paramMap);
		return list;
	}

	public int batchInsert(List list) {
		if (!CollectionUtils.isEmpty(list)) {
//			return dao.insert_("SysFileMapper.batchInsert", list);
		}
		return 0;

	}
}
