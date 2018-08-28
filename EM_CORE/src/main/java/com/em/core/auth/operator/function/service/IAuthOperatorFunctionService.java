/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.operator.function.service;

import java.util.List;
import java.util.Map;

import com.em.core.auth.operator.function.entity.AuthOperatorFunction;

/****
 * 
 * @author yangy
 * 2018年5月21日 下午9:43:49
 * IAuthOperatorFunctionService.java
 * @Description
 */
public interface IAuthOperatorFunctionService {
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthOperatorFunction(AuthOperatorFunction operatorFunction);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public int delAuthOperatorFunction(String operatorFunctionId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthOperatorFunction(AuthOperatorFunction operatorFunction);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthOperatorFunction getAuthOperatorFunction(String operatorFunctionId);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthOperatorFunction> queryAuthOperatorFunction(AuthOperatorFunction operatorFunction);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthOperatorFunction> queryAuthOperatorFunctionByMap(Map paramMap);
	public int batchInsert(List<Map> list, String operatorId);
}
