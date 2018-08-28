/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.function.group.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.core.auth.function.group.entity.AuthFunctionGroup;
import com.em.core.auth.mappers.AuthFunctionGroupMapper;

@Service
public class AuthFunctionGroupServiceImpl implements IAuthFunctionGroupService {

	@Autowired
	private AuthFunctionGroupMapper AuthFunctionGroupMapper;

	@Override
	public AuthFunctionGroup getAuthFunctionGroup(String funcGroupId) {
		AuthFunctionGroup funcGroup = AuthFunctionGroupMapper.selectByPrimaryKey(funcGroupId);
		return funcGroup;
	}

	@Override
	public void addFunctionGroup(AuthFunctionGroup funcGroup) {
		AuthFunctionGroupMapper.insert(funcGroup);
	}

	@Override
	public void updateAuthFunctionGroup(AuthFunctionGroup funcGroup) {
		AuthFunctionGroupMapper.updateByPrimaryKeySelective(funcGroup);
	}

	@Override
	public int delAuthFunctionGroup(String funcGroupId) {
		return AuthFunctionGroupMapper.deleteByPrimaryKey(funcGroupId);
	}

	@Override
	public List<AuthFunctionGroup> queryAuthFunctionGroup(AuthFunctionGroup funcGroup) {
		List<AuthFunctionGroup> list = AuthFunctionGroupMapper.query(funcGroup);
		return list;
	}

	@Override
	public List<AuthFunctionGroup> queryAuthFunctionGroupByMap(Map paramMap) {
		List<AuthFunctionGroup> list = AuthFunctionGroupMapper.queryByMap(paramMap);
		return list;
	}
}
