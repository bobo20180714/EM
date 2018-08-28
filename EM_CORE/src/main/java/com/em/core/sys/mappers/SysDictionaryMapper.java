package com.em.core.sys.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.sys.dictionary.entity.SysDictionary;


public interface SysDictionaryMapper {

	/**
	 * @Title: rightUpdate  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void rightUpdate(Map updateSysDicParam);

	/**
	 * @Title: selectByPrimaryKey  
	 * @Description:
	 * @return: SysDictionary 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	SysDictionary selectByPrimaryKey(String dictid);

	/**
	 * @Title: count  
	 * @Description:
	 * @return: int 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	int count(Map likeParamJoinedMap);

	/**
	 * @Title: queryByMap  
	 * @Description:
	 * @return: List 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysDictionary> queryPage(PageView<SysDictionary> pageView, Map paramMap);

	/**
	 * @Title: insert  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void insert(SysDictionary dict);

	/**
	 * @Title: updateByPrimaryKeySelective  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void updateByPrimaryKeySelective(SysDictionary dict);

	/**
	 * @Title: queryByMap  
	 * @Description:
	 * @return: List<SysDictionary> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysDictionary> queryByMap(Map paramMap);

	/**
	 * @Title: queryDictItem  
	 * @Description:
	 * @return: List 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List queryDictItem(Map paramMap);

	/**
	 * @Title: rightBatchDelete  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	int rightBatchDelete(Map paramMap);

	/**
	 * @Title: rightRecursiveDelete  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void rightRecursiveDelete(Map paramMap);

	/**
	 * @Title: deleteItems  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void deleteItems(Map paramMap);

	/**
	 * @Title: batchDelete  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void batchDelete(Map paramMap);

	/**
	 * @Title: queryDictItemByDefine  
	 * @Description:
	 * @return: List 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List queryDictItemByDefine(Map paramMap);

	/**
	 * @Title: queryTreeNodesByDictType  
	 * @Description:
	 * @return: List<ZTreeNode> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<ZTreeNode> queryTreeNodesByDictType(Map paramMap);
	List<Map> queryPageRight(Map paramMap);

	List rightQuery(Map paramMap);

	/**
	 * @Title: rightQueryPageCount  
	 * @Description:
	 * @return: int 
	 * @author: teng.min
	 * @Date 2018年5月25日
	 */
	int rightQueryPageCount(Map likeParamJoinedMap);

	/**
	 * @Title: rightGetByMap  
	 * @Description: map查找 字典项 
	 * @return: Map 
	 * @author: teng.min
	 * @Date 2018年5月25日
	 */
	Map rightGetByMap(String dictId);

}