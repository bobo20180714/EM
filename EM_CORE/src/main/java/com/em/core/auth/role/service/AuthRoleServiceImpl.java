/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.page.PageView;
import com.em.core.auth.mappers.AuthOperatorRoleMapper;
import com.em.core.auth.mappers.AuthRoleFunctionMapper;
import com.em.core.auth.mappers.AuthRoleMapper;
import com.em.core.auth.role.entity.AuthRole;

/***
 * service方法实现
 * @author yangy
 * 2018年5月16日 下午11:37:35
 * AuthRoleServiceImpl.java
 * @Description
 */
@Service
public class AuthRoleServiceImpl implements IAuthRoleService {

	@Autowired
	public AuthRoleMapper authRoleMapper;
	
	@Autowired
	public AuthRoleFunctionMapper authRoleFunctionMapper;
	
	@Autowired
	public AuthOperatorRoleMapper authOperatorRoleMapper;

	
	/**
	 * 查询 一页记录
	 * 
	 * @param rowSelection
	 * @param map
	 * @return
	 */
	@Override
	public PageView<AuthRole> queryPage(PageView<AuthRole> pageView, Map<String, Object> paramMap) {
		Map<String, Object> queryPageMap = new HashMap<String, Object>();
		int count = authRoleMapper.count(pageView.getLikeParamJoinedMap(paramMap));
		pageView.setRowCountAndPreventAutoCount(count);
		queryPageMap.put("paging", pageView);
		queryPageMap.put("t", paramMap);
		List<AuthRole> list = authRoleMapper.queryPage(pageView, queryPageMap);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public int batchDelete(String ids) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("ROLE_IDS", ids);
		authRoleFunctionMapper.batchDelete(paramMap);
		authOperatorRoleMapper.batchDelete(paramMap);
		int ret = authRoleMapper.batchDelete(paramMap);
		return ret;
	}

	@Override
	public void addAuthRole(AuthRole role) {
		authRoleMapper.insert(role);
	}

	@Override
	public void delAuthRole(String roleId) {
		authRoleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public void updateAuthRole(AuthRole role) {
		authRoleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public AuthRole getAuthRole(String roleId) {
		AuthRole role = authRoleMapper.selectByPrimaryKey(roleId);
		return role;
	}

	@Override
	public List<AuthRole> queryAuthRole(AuthRole role) {
		List<AuthRole> list = authRoleMapper.query(role);
		return list;
	}

	@Override
	public List<AuthRole> queryAuthRoleByMap(Map paramMap) {
		List<AuthRole> list = authRoleMapper.queryByMap(paramMap);
		return list;
	}
}
