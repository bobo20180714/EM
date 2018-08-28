package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.core.auth.operator.function.entity.AuthOperatorFunction;


public interface AuthOperatorFunctionMapper {
    int deleteByPrimaryKey(String key);

    int insert(AuthOperatorFunction record);

    int insertSelective(AuthOperatorFunction record);

    AuthOperatorFunction selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(AuthOperatorFunction record);

    int updateByPrimaryKey(AuthOperatorFunction record);
    
	List<AuthOperatorFunction> query(AuthOperatorFunction authOperatorFunction);
	
	List<AuthOperatorFunction> queryByMap(Map paramMap);

	void insertByMap(Map map);
}