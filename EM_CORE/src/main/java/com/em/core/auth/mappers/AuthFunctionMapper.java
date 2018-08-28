package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.function.entity.AuthFunction;

public interface AuthFunctionMapper {
    int deleteByPrimaryKey(String funcId);

    int insert(AuthFunction record);

    int insertSelective(AuthFunction record);

    AuthFunction selectByPrimaryKey(String funcId);

    int updateByPrimaryKeySelective(AuthFunction record);

    int updateByPrimaryKey(AuthFunction record);
    
    List<AuthFunction> query(AuthFunction record);
    
    List<AuthFunction> queryByMap(Map paramMap);
    
    List<ZTreeNode> queryTreeNodes(Map paramMap);
    
    List<Map> queryPermitted(Map paramMap);
}