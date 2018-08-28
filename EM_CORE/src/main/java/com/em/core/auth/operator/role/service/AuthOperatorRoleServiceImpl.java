/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.operator.role.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.em.common.utils.MiscUtils;
import com.em.core.auth.mappers.AuthOperatorRoleMapper;
import com.em.core.auth.operator.role.entity.AuthOperatorRole;

/*****
 * 
 * @author yangy
 * 2018年5月21日 下午11:17:00
 * AuthOperatorRoleServiceImpl.java
 * @Description
 */
@Service
public class AuthOperatorRoleServiceImpl implements IAuthOperatorRoleService {

	@Autowired
	private AuthOperatorRoleMapper authOperatorRoleMapper;
	
	@Override
	public AuthOperatorRole getAuthOperatorRole(String operatorId) {
		return null;
	}
	
	@Override
	public void addAuthOperatorRole(AuthOperatorRole operatorRole) {
		authOperatorRoleMapper.insert(operatorRole);
	}
	
	@Override
	public int updateAuthOperatorRole(AuthOperatorRole operatorRole) {
		return 0;
	}
	
	@Override
	public void delAuthOperatorRole(String operatorId) {
		authOperatorRoleMapper.deleteByPrimaryKey(operatorId);
	}
	
	@Override
	public void delAuthOperatorRoleSelective(Map paramMap) {
		authOperatorRoleMapper.deleteBySelective(paramMap);
	}
	
	@Override
	public List<AuthOperatorRole> queryAuthOperatorRole(AuthOperatorRole operatorRole) {
		return authOperatorRoleMapper.query(operatorRole);
	}

	@Override
	public List<AuthOperatorRole> queryAuthOperatorRoleByMap(Map paramMap) {
		return authOperatorRoleMapper.queryByMap(paramMap);
	}

	//TODO 这里的delete传入的应该是object
	@Override
	public int batchInsert(List<Map> list, Map deleteParam) {
		delAuthOperatorRoleSelective(deleteParam);
		if (!CollectionUtils.isEmpty(list)) {
			return authOperatorRoleMapper.batchInsert(list);
		}
		return 0;

	}








}
