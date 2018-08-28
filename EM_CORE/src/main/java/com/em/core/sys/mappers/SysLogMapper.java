/**
 * 
 */
package com.em.core.sys.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.core.auth.role.entity.AuthRole;
import com.em.core.sys.log.entity.SysLog;

/**
 * @title 
 * @ClassName SysLogMapper
 * @return TODO
 * @Author min.teng
 * @Date 2018年5月22日 下午6:19:07
 */
public interface SysLogMapper {

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
	 * @return: List<SysLog> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysLog> queryPage(PageView<SysLog> pageView,
			Map<String, Object> queryPageMap);

	/**
	 * @Title: selectByPrimaryKey  
	 * @Description:
	 * @return: SysLog 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	SysLog selectByPrimaryKey(String sysLogId);

	/**
	 * @Title: insert  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void insert(SysLog sysLog);

	/**
	 * @Title: updateByPrimaryKey  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void updateByPrimaryKeySelect(SysLog sysLog);

	/**
	 * @Title: queryByMap  
	 * @Description:
	 * @return: List<SysLog> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysLog> queryByMap(Map paramMap);
	
//	List<SysLog> queryPage(PageView<SysLog> pageView, Map<String, Object> paramMap);
//	int count(Map<String, String> params);
//	/**
//	 * @Title: selectByPrimaryKey  
//	 * @Description:
//	 * @return: SysLog 
//	 * @author: teng.min
//	 * @Date 2018年5月22日
//	 */
//	SysLog selectByPrimaryKey(String sysLogId);
//	int deleteByPrimaryKey(String sysLogId);
//
//	int insert(SysLog record);
//
//	int insertSelective(SysLog record);
//
//	int updateByPrimaryKeySelective(SysLog record);
//
//	int updateByPrimaryKey(SysLog record);
//
//	int batchDelete(Map<String, String> paramMap);
//	
//	List<SysLog> query(SysLog sysLog);
//	
//	List<SysLog> queryByMap(Map paramMap);
//	
//	//批量插入  待添加

}
