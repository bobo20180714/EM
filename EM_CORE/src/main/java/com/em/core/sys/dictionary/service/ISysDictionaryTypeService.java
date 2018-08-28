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
import com.em.core.sys.dictionary.type.entity.SysDictionaryType;

/**
 * @ClassName: ISysDictService.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:46:04
 */

public interface ISysDictionaryTypeService  {
	/**
	 * @Title: getSysDictionaryType  
	 * @Description:
	 * @return: SysDictionaryType 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	SysDictionaryType getSysDictionaryType(String id);

	/**
	 * @Title: queryPage  
	 * @Description:
	 * @return: PageView<SysDictionaryType> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	PageView<SysDictionaryType> queryPage(PageView<SysDictionaryType> pageView,
			Map<String, Object> paramMap);

	/**
	 * @Title: insertSysDictionaryType  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void insertSysDictionaryType(SysDictionaryType dictType);

	/**
	 * @Title: updateSysDictionaryType  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void updateSysDictionaryType(SysDictionaryType dictType);

	/**
	 * @Title: deleteSysDictionaryType  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void delSysDictionaryType(String dictTypeId);

	/**
	 * @Title: queryByMap  
	 * @Description:
	 * @return: List<SysDictionaryType> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysDictionaryType> queryByMap(Map paramMap);
}
