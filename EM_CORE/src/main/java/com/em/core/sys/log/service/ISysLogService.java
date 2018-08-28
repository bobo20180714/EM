/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.log.service;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.common.service.IBaseService;
import com.em.core.auth.role.entity.AuthRole;
import com.em.core.sys.log.entity.SysLog;

/**
 * @ClassName: ISysLogService.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:46:04
 */

public interface ISysLogService {
	public PageView<SysLog> queryPage(PageView<SysLog> pageView, Map<String, Object> params);
	
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addSysLog(SysLog sysLog);
	
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateSysLog(SysLog sysLog);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public SysLog getSysLog(String id);
//
//	/**
//	 * @Title: querySysLog  
//	 * @Description:
//	 * @return: List<SysLog> 
//	 * @author: teng.min
//	 * @Date 2018年5月22日
//	 */
//	List<SysLog> query(SysLog sysLog);

	/**
	 * @Title: querySysLogByMap  
	 * @Description:
	 * @return: List<SysLog> 
	 * @author: teng.min
	 * @Date 2018年5月22日
	 */
	List<SysLog> queryByMap(Map paramMap);
	
	
	int batchInsert(List list);
	
}
