package com.em.core.sys.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.core.sys.dictionary.type.entity.SysDictionaryType;


public interface SysDictionaryTypeMapper {

	/**
	 * @Title: selectByPrimaryKey  
	 * @Description:
	 * @return: SysDictionaryType 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	SysDictionaryType selectByPrimaryKey(String id);

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
	List queryPage(PageView<SysDictionaryType> pageView, Map paramMap);

	/**
	 * @Title: insert  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void insert(SysDictionaryType dictType);

	/**
	 * @Title: update  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void updateByPrimaryKeySelective(SysDictionaryType dictType);

	/**
	 * @Title: delete  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	int deleteByPrimaryKey(String roleId);

	/**
	 * @Title: queryByMap  
	 * @Description:
	 * @return: List<SysDictionaryType> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysDictionaryType> queryByMap(Map paramMap);

	/**
	 * @Title: getByMap  
	 * @Description:
	 * @return: Map 
	 * @author: teng.min
	 * @Date 2018年5月25日
	 */
	Map getByMap(String typeId);

}