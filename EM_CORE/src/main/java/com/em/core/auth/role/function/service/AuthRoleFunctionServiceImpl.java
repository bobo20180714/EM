/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.role.function.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.utils.MiscUtils;
import com.em.core.auth.mappers.AuthRoleFunctionMapper;
import com.em.core.auth.role.function.entity.AuthRoleFunction;

/***
 * 
 * @author yangy 2018年5月21日 下午11:59:08 AuthRoleFunctionServiceImpl.java
 * @Description
 */
@Service
public class AuthRoleFunctionServiceImpl implements IAuthRoleFunctionService {

	@Autowired
	private AuthRoleFunctionMapper authRoleFunctionMapper;

	@Override
	public void addAuthRoleFunction(AuthRoleFunction roleFunction) {
		authRoleFunctionMapper.insert(roleFunction);
	}

	@Override
	public void updateAuthRoleFunction(AuthRoleFunction roleFunction) {
		authRoleFunctionMapper.updateByPrimaryKey(roleFunction);
	}

	@Override
	public void delAuthRoleFunction(String roleId) {
		authRoleFunctionMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public List<AuthRoleFunction> queryAuthRoleFunction(AuthRoleFunction roleFunction) {
		return authRoleFunctionMapper.query(roleFunction);
	}

	@Override
	public List<AuthRoleFunction> queryAuthRoleFunctionByMap(Map paramMap) {
		return authRoleFunctionMapper.queryByMap(paramMap);
	}

	@Override
	public int batchInsert(List<Map> list, String roleId) {
		delAuthRoleFunction(roleId);
		for(int i = 0; i < list.size(); i ++) {
			Map map = list.get(i);
			authRoleFunctionMapper.insertSelective(map);
		}
		return 0;
	}

}
