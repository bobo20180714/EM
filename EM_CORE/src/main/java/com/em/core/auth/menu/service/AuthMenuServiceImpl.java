package com.em.core.auth.menu.service;

/**
 * Copyright2015-2019 em All Rights Reserved.
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.mappers.AuthApplicationMapper;
import com.em.core.auth.mappers.AuthMenuMapper;
import com.em.core.auth.menu.entity.AuthMenu;

/***
 * 
 * @author yangy 2018年5月21日 下午3:26:36 AuthMenuServiceImpl.java
 * @Description
 */
@Service
public class AuthMenuServiceImpl implements IAuthMenuService {

	@Autowired
	private AuthMenuMapper authMenuMapper;
	
	@Autowired
	private AuthApplicationMapper authApplicationMapper;

	@Override
	public AuthMenu getAuthMenu(String menuId) {
		AuthMenu menu = authMenuMapper.selectByPrimaryKey(menuId);
		return menu;
	}

	@Override
	public void addAuthMenu(AuthMenu menu) {
		authMenuMapper.insert(menu);
	}

	@Override
	public void updateAuthMenu(AuthMenu menu) {
		authMenuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public int delAuthMenu(String menuId) {
		return authMenuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public List<AuthMenu> queryAuthMenu(AuthMenu menu) {
		List<AuthMenu> list = authMenuMapper.query(menu);
		return list;
	}

	@Override
	public List<AuthMenu> queryAuthMenuByMap(Map paramMap) {
		List<AuthMenu> list = authMenuMapper.queryByMap(paramMap);
		return list;
	}

	/**
	 * XXX
	 * @Title: queryChildrenTreeNodes
	 * @author：liuyx
	 * @date：2015年9月7日下午4:56:36
	 * @Description: 构建 机构 岗位 人员树
	 * @param pid
	 * @return
	 */
	@Override
	public List<ZTreeNode> queryChildrenTreeNodes(String pId) {
		List<ZTreeNode> list = authMenuMapper.queryChildrenTreeNodes(pId);
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

	/******
	 * 查询权限
	 */
	@Override
	public List<Map> queryPermitted(Map paramMap) {
		if ("superadmin".equals(paramMap.get("OPERATOR_ID"))) {
			// 超级管理员查询出所有
			String menuIds = StringUtils.getTrimStr(authMenuMapper.queryAllMenuIds(paramMap));
			paramMap.put("MENU_IDS", menuIds);
			return authMenuMapper.queryAll(paramMap);
		}
		String menuIds = StringUtils.getTrimStr(authMenuMapper.queryPermittedMenuIds(paramMap));
		paramMap.put("MENU_IDS", menuIds);
		return authMenuMapper.queryPermitted(paramMap);
	}

	/**
	 * 用于编辑页面显示父节点名称
	 */
	@Override
	public String getParentName(String id) {
		String name = authMenuMapper.getParentName(id);
		if (StringUtils.isEmpty(name)) {
			name = authApplicationMapper.getTopMenuParent(id);
		}
		return name;
	}

}
