/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.operator.role.service;

import java.util.List;
import java.util.Map;

import com.em.core.auth.operator.role.entity.AuthOperatorRole;

/******
 * 
 * @author yangy
 * 2018年5月21日 下午11:11:20
 * IAuthOperatorRoleService.java
 * @Description
 */
public interface IAuthOperatorRoleService {
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthOperatorRole(AuthOperatorRole operatorRole);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public int updateAuthOperatorRole(AuthOperatorRole operatorRole);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthOperatorRole getAuthOperatorRole(String operatorId);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthOperatorRole> queryAuthOperatorRole(AuthOperatorRole operatorRole);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthOperatorRole> queryAuthOperatorRoleByMap(Map paramMap);
	public int batchInsert(List<Map> list, Map map);

	void delAuthOperatorRole(String operatorId);

	void delAuthOperatorRoleSelective(Map paramMap);
}
