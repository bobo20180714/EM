package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.menu.custom.entity.AuthMenuCustom;

public interface AuthMenuCustomMapper {
	int deleteByPrimaryKey(String operatorId);

	int insert(AuthMenuCustom record);

	AuthMenuCustom selectByPrimaryKey(String menuId);

	int updateByPrimaryKeySelective(AuthMenuCustom record);

	int batchDelete(Map<String, String> paramMap);

	List<AuthMenuCustom> query(AuthMenuCustom menu);

	List<AuthMenuCustom> queryByMap(Map paramMap);

	List<ZTreeNode> queryPermittedTreeNodes(String pId);

	List<Map> queryPermitted(Map paramMap);
	
	int count(Map<String, String> params);

	List<AuthMenuCustom> queryPage(PageView<AuthMenuCustom> pageView, Map<String, Object> paramMap);

	void updateByMap(Map<String, String> paramMap);

}