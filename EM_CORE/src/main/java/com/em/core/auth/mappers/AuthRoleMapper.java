package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.page.PageView;
import com.em.core.auth.role.entity.AuthRole;

public interface AuthRoleMapper {
	
	int deleteByPrimaryKey(String roleId);

	int insert(AuthRole record);

	int insertSelective(AuthRole record);

	AuthRole selectByPrimaryKey(String roleId);

	int updateByPrimaryKeySelective(AuthRole record);

	int updateByPrimaryKey(AuthRole record);
	
	int count(Map<String, String> params);

	List<AuthRole> queryPage(PageView<AuthRole> pageView, Map<String, Object> paramMap);

	int batchDelete(Map<String, String> paramMap);
	
	List<AuthRole> query(AuthRole authRole);
	
	List<AuthRole> queryByMap(Map paramMap);
}