package com.em.core.sys.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.core.sys.file.entity.SysFile;

public interface SysFileMapper{

	/**
	 * @Title: count  
	 * @Description:
	 * @return: int 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	int count(Map likeParamJoinedMap);

	/**
	 * @Title: queryPage  
	 * @Description:
	 * @return: List<SysFile> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysFile> queryPage(PageView<SysFile> pageView,
			Map<String, Object> queryPageMap);

	/**
	 * @Title: selectByPrimaryKey  
	 * @Description:
	 * @return: SysFile 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	SysFile selectByPrimaryKey(String sysLogId);

	/**
	 * @Title: insert  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void insert(SysFile sysLog);

	/**
	 * @Title: deleteByPrimaryKey  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void deleteByPrimaryKey(String sysLogId);

	/**
	 * @Title: updateByPrimaryKeySelective  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void updateByPrimaryKeySelective(SysFile sysFile);

	/**
	 * @Title: query  
	 * @Description:
	 * @return: List<SysFile> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysFile> query(SysFile sysLog);

	/**
	 * @Title: queryByMap  
	 * @Description:
	 * @return: List<SysFile> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysFile> queryByMap(Map paramMap);
  
}