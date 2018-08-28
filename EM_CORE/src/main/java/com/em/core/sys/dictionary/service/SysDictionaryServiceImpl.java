/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.dictionary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.em.common.dao.IBaseDao;
import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.sys.dictionary.entity.SysDictionary;
import com.em.core.sys.dictionary.type.entity.SysDictionaryType;
import com.em.core.sys.mappers.SysDictionaryMapper;
import com.em.core.sys.mappers.SysDictionaryTypeMapper;

/**
 * @ClassName: SysDictServiceImpl.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:48:14
 */
@Service
public class SysDictionaryServiceImpl implements ISysDictionaryService,ISysDictionaryTypeService {

	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;
	@Autowired
	private SysDictionaryTypeMapper sysDictionaryTypeMapper;
	
	
//----------------------------------------------------------------------
	//-------------------
	//--------------SysDictionaryType 方法
	//--------------------
	@Override
	public SysDictionaryType getSysDictionaryType(String id) {
		SysDictionaryType dictType = sysDictionaryTypeMapper.selectByPrimaryKey(id);
		return dictType;
	}

	/**
	 * 查询 一页记录
	 * XXX: count方法  空指针,已解决
	 * FIXME : start 20
	 * @param rowSelection
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public PageView<SysDictionaryType> queryPage(PageView<SysDictionaryType> pageView, Map<String, Object> paramMap) {
		Map<String, Object> queryPageMap = new HashMap<String, Object>();
		
		int count = sysDictionaryTypeMapper.count(pageView.getLikeParamJoinedMap(paramMap));
		pageView.setRowCountAndPreventAutoCount(count);
		queryPageMap.put("paging", pageView);
		queryPageMap.put("t", paramMap);
		List<SysDictionaryType> list = sysDictionaryTypeMapper.queryPage(pageView, queryPageMap);
		pageView.setRecords(list);
		return pageView;
	}


	/**
	 * 插入 一条记录
	 * XXX
	 * @param beanMap
	 * @return
	 */
	@Override
	public void insertSysDictionaryType(SysDictionaryType dictType) {
		sysDictionaryTypeMapper.insert(dictType);
	}
	
	/**
	 * 修改 一条记录
	 * XXX
	 * @param beanMap
	 * @return
	 */
	@Override
	public void updateSysDictionaryType(SysDictionaryType dictType) {
		String dicTypeId = dictType.getDicTypeId();
		SysDictionaryType oldDictType = sysDictionaryTypeMapper.selectByPrimaryKey(dicTypeId);
//		Map oldRecord = (Map) dao.get("SysDictMapper.get", dicTypeId);
		String oldTypeCode = oldDictType.getTypeCode();
		String newTypeCode = dictType.getTypeCode();
		if (!oldTypeCode.equals(newTypeCode)) {
			Map updateSysDicParam = new HashMap();
			updateSysDicParam.put("TYPE_CODE", newTypeCode);
			updateSysDicParam.put("OLD_TYPE_CODE", oldTypeCode);
			sysDictionaryMapper.rightUpdate(updateSysDicParam);//mapper不用修改
		}
		sysDictionaryTypeMapper.updateByPrimaryKeySelective(dictType);
	}

	/**
	 * 删除 一条记录
	 * 
	 * @param
	 */
	@Override
	public void delSysDictionaryType(String dictTypeId) {
		sysDictionaryTypeMapper.deleteByPrimaryKey(dictTypeId);
	}

	/**
	 * XXX
	 * @Title: 查询所有的字典记录（左侧列表）
	 * @author：yuqing
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@Override
	public List<SysDictionaryType> queryByMap(Map paramMap) {
		List<SysDictionaryType> list = sysDictionaryTypeMapper.queryByMap(paramMap);
		return list;
	}

	
	//---------------------------------------------------------
	//------------------------------       ----------------------------
	//-------------------------- SysDict 的方法 ---------------
	//--------------------------           ---------------
	//------------------------------------
	@Override
	public SysDictionary getSysDictionary(String dictid) {
		SysDictionary dict = sysDictionaryMapper.selectByPrimaryKey(dictid);
		return dict;
	}
	/**
	 * 右侧查询 一页记录
	 * XXX 重点+最应注意方法！！
	 * @param rowSelection
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageView rightQueryPage(PageView pageView, Map paramMap) {
		Map<String, Object> queryPageMap = new HashMap<String, Object>();
//		int count = sysDictionaryMapper.rightQueryPageCount(pageView.getLikeParamJoinedMap(paramMap));
//		pageView.setRowCountAndPreventAutoCount(count);
		queryPageMap.put("paging", pageView);
		queryPageMap.put("t", paramMap);
		List<Map> list = sysDictionaryMapper.queryPageRight(queryPageMap);
		pageView.setRecords(list);
		return pageView;
	}
	

	/**
	 * 右侧插入 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void insertSysDictionary(SysDictionary dict) {
		sysDictionaryMapper.insert(dict);
	}
	

	
	/**
	 * 修改 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateSysDictionary(SysDictionary dict) {
		sysDictionaryMapper.updateByPrimaryKeySelective(dict);
	}
	
	/**
	 * @Title: 查询所有的字典记录（右侧列表）
	 * @author：yuqing
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Map> rightQuery(Map paramMap) {
//		List list = (List) dao.query("SysDictMapper.rightQuery", paramMap);
		List<Map> list = sysDictionaryMapper.rightQuery(paramMap);
		return list;
	} 
	
	//---------------------------------------------------------
	
	/**
	 * 
	 * @Title: queryDictItemsByTypeCode
	 * @author：liuyx
	 * @date：2015年11月16日下午4:59:47
	 * @Description: TODO
	 * @param typeCode
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/*
	 * @Cacheable(value =
	 * "dictCache",key="#root.targetClass +#root.methodName + #typeCode")
	 */
	// key可以不填，@Cacheable(value = "dictCache")默认就是[类路径]-[方法名]_[参数值]
	public List<Map> queryDictItemsByTypeCode(String typeCode) {
		Map paramMap = new HashMap();
		paramMap.put("TYPE_CODE", typeCode);
		List list = (List) sysDictionaryMapper.queryDictItem(paramMap);
		return list;
	}


	/**
	 * @Title: 字典项列表
	 * @author：yuqing
	 * @date：2015年10月20日上午10:13:58
	 * @Description: 批量删除
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int rightBatchDelete(String ids) {
		Map paramMap = new HashMap();
		paramMap.put("DIC_IDS", ids);
		int ret= sysDictionaryMapper.rightBatchDelete(paramMap);
		return ret;
	}

	/**
	 * @Title: 字典项列表
	 * @Description: 递归删除
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void rightRecursiveDelete(String ids) {
		Map paramMap = new HashMap();
		paramMap.put("DIC_IDS", ids);
		sysDictionaryMapper.rightRecursiveDelete(paramMap);
	}

	/**
	 * XXX
	 * @Title: 字典类型列表
	 * @author：yuqing
	 * @date：2015年10月20日上午10:13:58
	 * @Description: 批量删除
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void batchDelete(String ids) {
		Map paramMap = new HashMap();
		paramMap.put("DIC_IDS", ids);
		sysDictionaryMapper.deleteItems(paramMap);
		sysDictionaryMapper.batchDelete(paramMap);
	}

	/**
	 * 
	 * @Title: queryDictItemByDefine
	 * @author：刘宇祥
	 * @date：2016年9月1日下午3:12:38
	 * @Description: 自定义字典
	 * @param valueColName
	 *            用作字典值的字段
	 * @param textColName
	 *            用作字典文本的字段
	 * @param tableName
	 *            用作构建字典的表
	 * @param condition
	 *            查询条件字符串 放在SQL中，WHERE 1=1 AND 之后的内容
	 * @return
	 */
	public List<Map> queryDictItemByDefine(String valueColName, String textColName, String tableName, String condition) {
		Map paramMap = new HashMap();
		paramMap.put("valueColName", valueColName);
		paramMap.put("textColName", textColName);
		paramMap.put("tableName", tableName);
		paramMap.put("condition", condition);
		List list = (List) sysDictionaryMapper.queryDictItemByDefine(paramMap);
		return list;
	}

	public List<ZTreeNode> queryTreeNodesByDictType(Map paramMap) {
		List<ZTreeNode> list = (List<ZTreeNode>) sysDictionaryMapper.queryTreeNodesByDictType(paramMap);
		return list;
	}

	@Override
	public Map rightGetByMap(String dictId) {
		// TODO Auto-generated method stub
		return sysDictionaryMapper.rightGetByMap(dictId);
	}

	@Override
	public Map getByMap(String typeId) {
		// TODO Auto-generated method stub
		Map map = sysDictionaryTypeMapper.getByMap(typeId);
		return map;
	}

//	@Override
//	public Map getByMap(String id) {
//		return (Map) dao.get("SysDictMapper.get", id);  
//	}
//	@SuppressWarnings("rawtypes")
//	@Override
//	public Map rightGet(String id) {
//		return (Map) dao.get("SysDictMapper.rightGet", id);  
//	}
}
