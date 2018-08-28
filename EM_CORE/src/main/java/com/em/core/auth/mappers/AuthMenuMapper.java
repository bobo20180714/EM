package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.menu.entity.AuthMenu;

public interface AuthMenuMapper {
	int deleteByPrimaryKey(String menuId);

	int insert(AuthMenu record);

	int insertSelective(AuthMenu record);

	AuthMenu selectByPrimaryKey(String menuId);

	int updateByPrimaryKeySelective(AuthMenu record);

	int updateByPrimaryKey(AuthMenu record);

	int batchDelete(Map<String, String> paramMap);

	List<AuthMenu> query(AuthMenu menu);

	List<AuthMenu> queryByMap(Map paramMap);

	List<ZTreeNode> queryChildrenTreeNodes(String pId);

	Object queryAllMenuIds(Map paramMap);

	Object queryPermittedMenuIds(Map paramMap);

	List<Map> queryPermitted(Map paramMap);

	String getParentName(String id);
	
	List<Map> queryAll(Map paramMap);

}