/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.function.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.dao.IBaseDao;
import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.function.entity.AuthFunction;
import com.em.core.auth.mappers.AuthFunctionMapper;
import com.em.core.auth.mappers.AuthRoleMapper;
import com.em.core.auth.role.entity.AuthRole;

/**
 * @ClassName: AcFunctionServiceImpl
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class AuthFunctionServiceImpl implements IAuthFunctionService {

	@Autowired
	public AuthFunctionMapper authFunctionMapper;
	
	@Override
	public AuthFunction getAuthFunction(String funcId) {
		AuthFunction function = authFunctionMapper.selectByPrimaryKey(funcId);
		return function;
	}
	
	@Override
	public void addAuthFunction(AuthFunction function) {
		authFunctionMapper.insert(function);
	}

	@Override
	public void updateAuthFunction(AuthFunction function) {
		authFunctionMapper.updateByPrimaryKeySelective(function);
	}
	
	@Override
	public int delAuthFunction(String funcId) {
		return authFunctionMapper.deleteByPrimaryKey(funcId);
	}
	
	@Override
	public List<AuthFunction> queryAuthFunction(AuthFunction function) {
		List<AuthFunction> list = authFunctionMapper.query(function);
		return list;
	}
	
	/*****
	 * 登陆时获取资源列表，判断资源是否需要保护
	 * XXX
	 */
	@Override
	public List<AuthFunction> queryAuthFunctionByMap(Map paramMap) {
		List<AuthFunction> list = authFunctionMapper.queryByMap(paramMap);
		return list;
	}

	@Override
	public List<ZTreeNode> queryTreeNodes(Map paramMap) {
		List<ZTreeNode> list = authFunctionMapper.queryTreeNodes(paramMap);
		for (ZTreeNode n : list) {
			if (n.getpId().equals("root")) {
				n.setOpen(true);
				break;
			}
		}
		// 设置根节点打开
		/*
		 * if(pId==null) { for(ZTreeNode node:list) {
		 * if("-1".equals(node.getpId())) { node.setOpen(true); break; } } }
		 */
		return list;
	}

	@Override
	public List<Map> queryPermitted(Map paramMap) {
		return authFunctionMapper.queryPermitted(paramMap);
	}

	@Override
	public int batchDelete(String ids) {
		// TODO Auto-generated method stub
		return 0;
	}


}
