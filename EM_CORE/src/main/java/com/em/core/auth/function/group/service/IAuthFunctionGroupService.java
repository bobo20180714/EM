/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.function.group.service;

import java.util.List;
import java.util.Map;

import com.em.core.auth.function.group.entity.AuthFunctionGroup;

public interface IAuthFunctionGroupService {
	/***
	 * insert
	 * 
	 * @param name
	 * @return
	 */
	public void addFunctionGroup(AuthFunctionGroup funcGroup);

	/****
	 * delete
	 * 
	 * @param name
	 * @return
	 */
	public int delAuthFunctionGroup(String funcGroupId);

	/***
	 * update
	 * 
	 * @param name
	 * @return
	 */
	public void updateAuthFunctionGroup(AuthFunctionGroup funcGroup);

	/***
	 * get
	 * 
	 * @param funcGroupId
	 * @return
	 */
	public AuthFunctionGroup getAuthFunctionGroup(String funcGroupId);

	/***
	 * query
	 * 
	 * @param funcGroup
	 * @return
	 */
	public List<AuthFunctionGroup> queryAuthFunctionGroup(AuthFunctionGroup funcGroup);

	/***
	 * queryByMap
	 * 
	 * @param rrole
	 * @return
	 */
	public List<AuthFunctionGroup> queryAuthFunctionGroupByMap(Map paramMap);

}
