/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.menu.custom.service;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.menu.custom.entity.AuthMenuCustom;

/**
 * 
 * @author yangy 2017年3月20日 下午4:58:30 IAcCustomMenuService.java
 * @Description 自定义菜单service
 */
public interface IAuthMenuCustomService {
	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthMenuCustom(AuthMenuCustom menuCustom);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public void delAuthMenuCustom(String operatorId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthMenuCustom(AuthMenuCustom menuCustom);
	
	public void updateAuthMenuCustomByMap(Map<String, String> paramMap);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthMenuCustom getAuthMenuCustom(String menuId);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthMenuCustom> queryAuthMenuCustom(AuthMenuCustom menuCustom);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthMenuCustom> queryAuthMenuCustomByMap(Map paramMap);
	
	/***
	 * quiry by page
	 */
	public PageView<AuthMenuCustom> queryPage(PageView<AuthMenuCustom> pageView, Map<String, Object> params);
	List<ZTreeNode> queryPermittedTreeNodes(String pId);

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
	public int batchDelete(String ids, String operatorId);

}
