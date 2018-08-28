package com.em.core.auth.operator.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.em.common.utils.Md5Util;
import com.em.core.auth.mappers.AuthOperatorMapper;
import com.em.core.auth.operator.entity.AuthOperator;

@Service
public class AuthOperatorServiceImpl implements IAuthOperatorService {

	@Autowired
	private AuthOperatorMapper authOperatorMapper;
	
	@Override
	public AuthOperator getAuthOperator(String operatorId) {
		return authOperatorMapper.selectByPrimaryKey(operatorId);
	}
	
	@Override
	public void addAuthOperator(AuthOperator operator) {
		authOperatorMapper.insert(operator);
	}
	
	@Override
	public void updateAuthOperator(AuthOperator operator) {
		authOperatorMapper.updateByPrimaryKeySelective(operator);
	}
	
	@Override
	public int delAuthOperator(String operatorId) {
		return authOperatorMapper.deleteByPrimaryKey(operatorId);
	}
	
	@Override
	public List<AuthOperator> queryAuthOperator(AuthOperator operator) {
		List<AuthOperator> list = authOperatorMapper.query(operator);
		return list;
	}

	@Override
	public List<AuthOperator> queryAuthOperatorByMap(Map paramMap) {
		List<AuthOperator> list = authOperatorMapper.queryByMap(paramMap);
		return list;
	}

	/**
	 * @Title: checkLogin
	 * @author：liuyx
	 * @date：2015年9月21日上午11:35:01
	 * @Description: 验证登录用户
	 * @param userId
	 * @param passWord
	 * @return
	 */
	@Override
	public AuthOperator checkLogin(String userId, String password) {
		AuthOperator authOperator = new AuthOperator();
		authOperator.setUserId(userId);
		String psw = Md5Util.string2MD5(password);
		authOperator.setPassword(psw);
		AuthOperator user = null;
		List<AuthOperator> list = queryAuthOperator(authOperator);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			user = list.get(0);
		}		
		if (psw.equals(user.getPassword())) {
			return user;
		} else {
			return null;
		}
	}











}
