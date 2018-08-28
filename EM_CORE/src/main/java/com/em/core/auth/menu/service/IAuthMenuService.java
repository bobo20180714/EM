/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.menu.service;

import java.util.List;
import java.util.Map;

import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.menu.entity.AuthMenu;

@SuppressWarnings("rawtypes")
public interface IAuthMenuService {
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthMenu(AuthMenu menu);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public int delAuthMenu(String menuId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthMenu(AuthMenu menu);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthMenu getAuthMenu(String menuId);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthMenu> queryAuthMenu(AuthMenu menu);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthMenu> queryAuthMenuByMap(Map paramMap);
	
	List<ZTreeNode> queryChildrenTreeNodes(String pId);

	/**
	 * 
	 * @Title: queryPermitted
	 * @author：liuyx
	 * @date：2015年10月4日上午11:58:32
	 * @Description: 根据OPERATOR_ID、PARENT_ID获取所有有访问权限的数据
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryPermitted(Map paramMap);

	/**
	 * 新增页面根据父ID显示父节点名称
	 * 
	 * @param id
	 * @return
	 */
	public String getParentName(String id);
}
