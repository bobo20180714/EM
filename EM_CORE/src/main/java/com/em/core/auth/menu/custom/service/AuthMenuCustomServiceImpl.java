/**
 * Copyright2015-2019 em All Rights Reserved.
 */

package com.em.core.auth.menu.custom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.page.PageView;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.mappers.AuthMenuCustomMapper;
import com.em.core.auth.mappers.AuthMenuMapper;
import com.em.core.auth.menu.custom.entity.AuthMenuCustom;

/**
 * 
 * @author yangy 2017年3月20日 下午4:59:30 AcCustomMenuServiceImpl.java
 * @Description 自定义菜单service实现
 */
@Service
public class AuthMenuCustomServiceImpl implements IAuthMenuCustomService {

	@Autowired
	private AuthMenuCustomMapper authMenuCustomMapper;
	
	@Autowired
	private AuthMenuMapper authMenuMapper;

	@Override
	public AuthMenuCustom getAuthMenuCustom(String menuId) {
		return authMenuCustomMapper.selectByPrimaryKey(menuId);
	}

	/**
	 * 查询 一页记录
	 * 
	 * @param rowSelection
	 * @param map
	 * @return
	 */
	@Override
	public PageView<AuthMenuCustom> queryPage(PageView<AuthMenuCustom> pageView, Map<String, Object> paramMap) {
		Map<String, Object> queryPageMap = new HashMap<String, Object>();
		int count = authMenuCustomMapper.count(pageView.getLikeParamJoinedMap(paramMap));
		pageView.setRowCountAndPreventAutoCount(count);
		queryPageMap.put("paging", pageView);
		queryPageMap.put("t", paramMap);
		List<AuthMenuCustom> list = authMenuCustomMapper.queryPage(pageView, queryPageMap);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public void addAuthMenuCustom(AuthMenuCustom menuCustom) {
		authMenuCustomMapper.insert(menuCustom);
	}

	@Override
	public void updateAuthMenuCustom(AuthMenuCustom menuCustom) {
		authMenuCustomMapper.updateByPrimaryKeySelective(menuCustom);
	}
	
	@Override
	public void updateAuthMenuCustomByMap(Map<String, String> paramMap) {
		authMenuCustomMapper.updateByMap(paramMap);
	}

	@Override
	public void delAuthMenuCustom(String operatorId) {
		authMenuCustomMapper.deleteByPrimaryKey(operatorId);

	}

	@Override
	public List<AuthMenuCustom> queryAuthMenuCustom(AuthMenuCustom menuCustom) {
		return authMenuCustomMapper.query(menuCustom);
	}

	@Override
	public List<AuthMenuCustom> queryAuthMenuCustomByMap(Map paramMap) {
		return authMenuCustomMapper.queryByMap(paramMap);
	}

	/**
	 * @description 获取所有有权限的菜单节点
	 * 
	 */
	@Override
	public List<ZTreeNode> queryPermittedTreeNodes(String menuIds) {
		List<ZTreeNode> list = authMenuCustomMapper.queryPermittedTreeNodes(menuIds);
		// 设置根节点打开

		for (ZTreeNode node : list) {
			if ("-1".equals(node.getpId())) {
				node.setOpen(true);
				break;
			}
		}

		return list;
	}

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

	@Override
	public int batchDelete(String ids, String operatorId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("MENU_CUSTOM_CODES", ids);
		paramMap.put("OPERATOR_ID", operatorId);
		int ret = authMenuCustomMapper.batchDelete(paramMap);
		return ret;
	}



}
