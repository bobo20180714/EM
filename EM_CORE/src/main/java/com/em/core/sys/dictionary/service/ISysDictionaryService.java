/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.dictionary.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.em.common.page.PageView;
import com.em.common.service.IBaseService;
import com.em.common.zTree.ZTreeNode;
import com.em.core.sys.dictionary.entity.SysDictionary;
import com.em.core.sys.dictionary.type.entity.SysDictionaryType;

/**
 * @ClassName: ISysDictService.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:46:04
 */

public interface ISysDictionaryService  {
	/**
	 * 字典项列表
	 * 
	 * @author: 于庆
	 */
//	@SuppressWarnings("rawtypes")
//	public PageView rightQueryPage(PageView pageView, Map paramMap);

	/**
	 * 字典项验证字段重复
	 * 
	 * @author: 于庆
	 */
	public List<Map> rightQuery(Map paramMap);

	/**
	 * 字典项验证字段重复
	 * 
	 * @author: liuyx
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryDictItemsByTypeCode(String typeCode);

	/**
	 * 字典项插入记录
	 * 
	 * @author: 于庆
	 */
//	@SuppressWarnings("rawtypes")
//	public int rightInsert(Map record);

	/**
	 * 字典项修改记录
	 * 
	 * @author: 于庆
	 */
//	@SuppressWarnings("rawtypes")
//	public int rightUpdate(Map record);

	/**
	 * 字典项获取 一条记录
	 * 
	 * @author: 于庆
	 */
//	@SuppressWarnings("rawtypes")
//	public Map rightGet(String id);

	/**
	 * 字典项 批量删除
	 * 
	 * @author: 于庆
	 */
	public int rightBatchDelete(String ids);

	/**
	 * 
	 * @Title: rightRecursiveDelete
	 * @author：刘宇祥
	 * @date：2016年9月19日下午12:59:38
	 * @Description: 据PID递归删除
	 * @param ids
	 * @return
	 */
	public void rightRecursiveDelete(String ids);

	/**
	 * 字典类型 批量删除
	 * 
	 * @author: 于庆
	 */
	public void batchDelete(String ids);

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
	public List<Map> queryDictItemByDefine(String valueColName, String textColName, String tableName, String condition);

	/**
	 * 
	 * @Title: queryTreeNodesByDictType
	 * @author：刘宇祥
	 * @date：2016年9月7日上午9:06:25
	 * @Description: 根据TYPE_ID获取SYS_DIC中相应数据，并以ztree节点的形式进行转换
	 * @param paramMap
	 * @return
	 */
	public List<ZTreeNode> queryTreeNodesByDictType(Map paramMap);

	/**
	 * @Title: getSysDictionary  
	 * @Description: 字典项获取 一条记录
	 * @return: SysDictionary 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	SysDictionary getSysDictionary(String dictid);

	/**
	 * @Title: rightQueryPage  
	 * @Description:
	 * @return: PageView<SysDictionary> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	PageView rightQueryPage(PageView<Map> pageView,
			Map paramMap);

	/**
	 * @Title: insertSysDictionary  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void insertSysDictionary(SysDictionary dict);

	/**
	 * @Title: updateSysDictionary  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void updateSysDictionary(SysDictionary dict);

	/**
	 * @Title: rightGetByMap  
	 * @Description: map 查询数据字典项
	 * @return: Map 
	 * @author: teng.min
	 * @Date 2018年5月25日
	 */
	Map rightGetByMap(String id);

	/**
	 * @Title: getByMap  
	 * @Description: map 查询字典项类型
	 * @return: Map 
	 * @author: teng.min
	 * @Date 2018年5月25日
	 */
	public Map getByMap(String typeId);




}
