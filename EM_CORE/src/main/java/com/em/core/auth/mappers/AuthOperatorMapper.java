package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.core.auth.operator.entity.AuthOperator;


public interface AuthOperatorMapper {
    int deleteByPrimaryKey(String operatorId);

    int insert(AuthOperator record);

    int insertSelective(AuthOperator record);

    AuthOperator selectByPrimaryKey(String operatorId);

    int updateByPrimaryKeySelective(AuthOperator record);

    int updateByPrimaryKey(AuthOperator record);
    
	List<AuthOperator> query(AuthOperator operator);
	
	List<AuthOperator> queryByMap(Map paramMap);
}