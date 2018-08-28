package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.core.auth.operator.role.entity.AuthOperatorRole;

public interface AuthOperatorRoleMapper {
	int deleteByPrimaryKey(String operatorId);

	int deleteBySelective(Map paramMap);

	int insert(AuthOperatorRole record);

	int insertSelective(AuthOperatorRole record);

	List<AuthOperatorRole> query(AuthOperatorRole record);

	List<AuthOperatorRole> queryByMap(Map paramMap);

	int batchInsert(List<Map> list);

	int batchDelete(Map<String, String> paramMap);
}