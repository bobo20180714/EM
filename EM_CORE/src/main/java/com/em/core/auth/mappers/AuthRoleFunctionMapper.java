package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.core.auth.role.function.entity.AuthRoleFunction;


public interface AuthRoleFunctionMapper {
    int deleteByPrimaryKey(String key);

    int insert(AuthRoleFunction record);

    int insertSelective(Map record);
    
    AuthRoleFunction selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(AuthRoleFunction record);

    int updateByPrimaryKey(AuthRoleFunction record);
    
    List<AuthRoleFunction> query(AuthRoleFunction roleFunction);
    List<AuthRoleFunction> queryByMap(Map paramMap);
    
    int batchDelete(Map<String, String> paramMap);
}