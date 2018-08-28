/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.application.service;

import java.util.List;
import java.util.Map;

import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.application.entity.AuthApplication;

public interface IAuthApplicationService {
	
	List<ZTreeNode> queryChildrenTreeNodes(String pId);
	
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthApplication(AuthApplication application);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public int delAuthApplication(String appId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthApplication(AuthApplication application);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthApplication getAuthApplication(String appId);
	
	/**
	 * query
	 */
	public List<AuthApplication> queryAuthApplication(AuthApplication application);
	
	/***
	 * query by map
	 */
	public List<AuthApplication> queryAuthApplicationByMap(Map<String, Object> paramMap);

}
