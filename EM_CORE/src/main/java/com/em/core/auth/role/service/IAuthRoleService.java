/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.role.service;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.core.auth.role.entity.AuthRole;

/***
 * service接口方法
 * @author yangy
 * 2018年5月16日 下午11:30:50
 * IAuthRoleService.java
 * @Description
 */
public interface IAuthRoleService {
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthRole(AuthRole role);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public void delAuthRole(String roleId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthRole(AuthRole role);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthRole getAuthRole(String roleId);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthRole> queryAuthRole(AuthRole role);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthRole> queryAuthRoleByMap(Map paramMap);
	
	/***
	 * batch delete	
	 * @param ids
	 * @return
	 */
	public int batchDelete(String ids);
	
	/***
	 * quiry by page
	 */
	public PageView<AuthRole> queryPage(PageView<AuthRole> pageView, Map<String, Object> params);
	
}
