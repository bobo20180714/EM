/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.application.entity.AuthApplication;
import com.em.core.auth.mappers.AuthApplicationMapper;

/***
 * 
 * @author yangy 2018年5月16日 下午11:50:07 AuthApplicationServiceImpl.java
 * @Description
 */
@Service
public class AuthApplicationServiceImpl implements IAuthApplicationService {

	@Autowired
	public AuthApplicationMapper authApplicationMapper;

	@Override
	public List<ZTreeNode> queryChildrenTreeNodes(String pId) {
		List<ZTreeNode> list = authApplicationMapper.queryChildrenTreeNodes(pId);
		// 设置根节点打开
		if (pId == null) {
			for (ZTreeNode node : list) {
				if ("-1".equals(node.getpId())) {
					node.setOpen(true);
					break;
				}
			}
		}

		return list;
	}

	@Override
	public void addAuthApplication(AuthApplication application) {
		authApplicationMapper.insert(application);
	}

	@Override
	public int delAuthApplication(String appId) {
		return authApplicationMapper.deleteByPrimaryKey(appId);

	}

	@Override
	public void updateAuthApplication(AuthApplication application) {
		authApplicationMapper.updateByPrimaryKeySelective(application);
	}

	@Override
	public AuthApplication getAuthApplication(String appId) {
		AuthApplication application = authApplicationMapper.selectByPrimaryKey(appId);
		return application;
	}

	@Override
	public List<AuthApplication> queryAuthApplicationByMap(Map<String, Object> paramMap) {
		List<AuthApplication> applicationList = authApplicationMapper.queryByParamMap(paramMap);
		return applicationList;
	}

	@Override
	public List<AuthApplication> queryAuthApplication(AuthApplication application) {
		List<AuthApplication> applicationList = authApplicationMapper.query(application);
		return applicationList;
	}

}
