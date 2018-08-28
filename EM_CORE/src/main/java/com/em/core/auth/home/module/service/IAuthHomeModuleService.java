/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.home.module.service;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.home.module.entity.AuthHomeModule;
import com.em.core.auth.role.entity.AuthRole;

public interface IAuthHomeModuleService {

	/***
	 * insert
	 * @param name
	 * @return
	 */
	public void addAuthHomeModule(AuthHomeModule homeModule);
	
	/****
	 * delete
	 * @param name
	 * @return
	 */
	public int delAuthHomeModule(String homeModuleId);
	
	/***
	 * update
	 * @param name
	 * @return
	 */
	public void updateAuthHomeModule(AuthHomeModule homeModule);
	
	/***
	 * get
	 * @param roleId
	 * @return
	 */
	public AuthHomeModule getAuthHomeModule(String homeModuleId);
	
	/***
	 * query
	 * @param rrole
	 * @return
	 */
	public List<AuthHomeModule> queryAuthHomeModule(AuthHomeModule homeModule);
	
	/***
	 * queryByMap
	 * @param rrole
	 * @return
	 */
	public List<AuthHomeModule> queryAuthHomeModuleByMap(Map paramMap);
	
	/***
	 * batch delete	
	 * @param ids
	 * @return
	 */
	public int batchDelete(String ids);

	/**
	 * 
	 * @Title: queryNodesForRole
	 * @author：flying706
	 * @date：2015年12月3日下午3:06:54
	 * @Description: 获取角色和首页模块选择列表，包含已选项目的初始化
	 * @param paramMap
	 * @return
	 */
	List<ZTreeNode> queryNodesForRole(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: batchInsertRoleHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日下午3:06:29
	 * @Description: 批量插入角色和首页模块的关系
	 * @param list
	 * @param deleteParam
	 * @return
	 */
	int batchInsertRoleHomeModule(List<Map> list, Map deleteParam);

	// ------------------------------------------------------------------

	/**
	 * 
	 * @Title: queryOperHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日下午3:05:40
	 * @Description: 获取操作员自定义主页模块列表
	 * @param paramMap
	 * @return
	 */
	List<Map> queryOperHomeModule(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: queryOperHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日下午3:05:40
	 * @Description: 获取操作员拥有角色的所有模块列表
	 * @param paramMap
	 * @return
	 */
	List<Map> queryOperRoleHomeModule(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: batchInsertOperHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日下午3:06:29
	 * @Description: 批量插入角色和首页模块的关系
	 * @param list
	 * @param deleteParam
	 * @return
	 */
	int batchInsertOperHomeModule(List<Map> list, Map deleteParam);
	
	/***
	 * quiry by page
	 */
	public PageView<AuthHomeModule> queryPage(PageView<AuthHomeModule> pageView, Map<String, Object> params);
}
