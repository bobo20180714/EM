/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.function.service;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.function.entity.AuthFunction;


public interface IAuthFunctionService {
	
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthFunction(AuthFunction function);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public int delAuthFunction(String funcId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthFunction(AuthFunction function);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthFunction getAuthFunction(String funcId);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthFunction> queryAuthFunction(AuthFunction function);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthFunction> queryAuthFunctionByMap(Map paramMap);
	
	/***
	 * batch delete	
	 * @param ids
	 * @return
	 */
	public int batchDelete(String ids);
	
	List<ZTreeNode> queryTreeNodes(Map paramMap);

	/****
	 * 根据OPERATOR_ID获取所有有访问权限的数据
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryPermitted(Map paramMap);
}
