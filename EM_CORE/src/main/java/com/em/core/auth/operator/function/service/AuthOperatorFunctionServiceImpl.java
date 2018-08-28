/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.operator.function.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.utils.MiscUtils;
import com.em.core.auth.mappers.AuthOperatorFunctionMapper;
import com.em.core.auth.operator.function.entity.AuthOperatorFunction;

/****
 * 
 * @author yangy 2018年5月21日 下午9:44:09 AuthOperatorFunctionServiceImpl.java
 * @Description
 */
@Service
public class AuthOperatorFunctionServiceImpl implements IAuthOperatorFunctionService {

	@Autowired
	private AuthOperatorFunctionMapper authOperatorFunctionMapper;

	@Override
	public AuthOperatorFunction getAuthOperatorFunction(String operatorId) {
		return authOperatorFunctionMapper.selectByPrimaryKey(operatorId);
	}

	@Override
	public void addAuthOperatorFunction(AuthOperatorFunction operatorFunction) {
		authOperatorFunctionMapper.insert(operatorFunction);
	}

	@Override
	public void updateAuthOperatorFunction(AuthOperatorFunction operatorFunction) {
		authOperatorFunctionMapper.updateByPrimaryKey(operatorFunction);
	}

	@Override
	public int delAuthOperatorFunction(String operatorId) {
		return authOperatorFunctionMapper.deleteByPrimaryKey(operatorId);
	}

	@Override
	public List<AuthOperatorFunction> queryAuthOperatorFunction(AuthOperatorFunction operatorFunction) {
		return authOperatorFunctionMapper.query(operatorFunction);
	}

	@Override
	public List<AuthOperatorFunction> queryAuthOperatorFunctionByMap(Map paramMap) {
		return authOperatorFunctionMapper.queryByMap(paramMap);
	}

	//TODO 还需要调试
	@Override
	public int batchInsert(List<Map> list, String operatorId) {
		authOperatorFunctionMapper.deleteByPrimaryKey(operatorId);
		for(int i = 0; i < list.size(); i ++) {
			Map map = list.get(i);
			authOperatorFunctionMapper.insertByMap(map);
		}
		return 0;
	}
}
