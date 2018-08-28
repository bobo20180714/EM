/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.role.function.service;

import java.util.List;
import java.util.Map;

import com.em.core.auth.role.function.entity.AuthRoleFunction;

/****
 * 
 * @author yangy
 * 2018年5月21日 下午11:57:02
 * IAuthRoleFunctionService.java
 * @Description
 */
public interface IAuthRoleFunctionService {
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthRoleFunction(AuthRoleFunction roleFunction);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public void delAuthRoleFunction(String roleId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthRoleFunction(AuthRoleFunction roleFunction);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthRoleFunction> queryAuthRoleFunction(AuthRoleFunction roleFunction);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthRoleFunction> queryAuthRoleFunctionByMap(Map paramMap);
	public int batchInsert(List<Map> list, String roleId);
}
