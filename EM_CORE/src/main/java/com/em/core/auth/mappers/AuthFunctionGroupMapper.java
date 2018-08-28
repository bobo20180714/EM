package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.core.auth.function.group.entity.AuthFunctionGroup;

public interface AuthFunctionGroupMapper {
	int deleteByPrimaryKey(String funcGroupId);

	int insert(AuthFunctionGroup record);

	int insertSelective(AuthFunctionGroup record);

	AuthFunctionGroup selectByPrimaryKey(String funcGroupId);

	int updateByPrimaryKeySelective(AuthFunctionGroup record);

	int updateByPrimaryKey(AuthFunctionGroup record);
	
	List<AuthFunctionGroup> query(AuthFunctionGroup funcGroup);
	
	List<AuthFunctionGroup> queryByMap(Map paramMap);
}