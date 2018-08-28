package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.home.module.entity.AuthHomeModule;
import com.em.core.auth.home.module.entity.AuthHomeModuleWithBLOBs;

public interface AuthHomeModuleMapper {
	int deleteByPrimaryKey(String homeModuleId);

	int insert(AuthHomeModule record);

	int insertSelective(AuthHomeModuleWithBLOBs record);

	AuthHomeModule selectByPrimaryKey(String homeModuleId);

	int updateByPrimaryKeySelective(AuthHomeModule record);

	int updateByPrimaryKeyWithBLOBs(AuthHomeModule record);

	int updateByPrimaryKey(AuthHomeModule record);

	int batchDelete(Map<String, String> paramMap);

	List<AuthHomeModule> query(AuthHomeModule homeModule);

	List<AuthHomeModule> queryByMap(Map paramMap);
	
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
	int batchInsertRoleHomeModule(List<Map> list);

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
	 * 分页
	 */
	int count(Map<String, String> params);

	List<AuthHomeModule> queryPage(PageView<AuthHomeModule> pageView, Map<String, Object> paramMap);
	
	int deleteRoleHomeModule(Map paramMap);

}