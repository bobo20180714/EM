package com.em.core.auth.operator.service;

import java.util.List;
import java.util.Map;

import com.em.core.auth.operator.entity.AuthOperator;

public interface IAuthOperatorService {
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthOperator(AuthOperator operator);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public int delAuthOperator(String operatorId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthOperator(AuthOperator operator);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthOperator getAuthOperator(String operatorId);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthOperator> queryAuthOperator(AuthOperator operator);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthOperator> queryAuthOperatorByMap(Map paramMap);
	public AuthOperator checkLogin(String userId, String passWord);
}
