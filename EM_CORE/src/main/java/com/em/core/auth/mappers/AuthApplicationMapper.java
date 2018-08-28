package com.em.core.auth.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.application.entity.AuthApplication;

public interface AuthApplicationMapper {
	
    int deleteByPrimaryKey(String appId);

    int insert(AuthApplication record);

    int insertSelective(AuthApplication record);

    AuthApplication selectByPrimaryKey(String appId);

    int updateByPrimaryKeySelective(AuthApplication record);

    int updateByPrimaryKey(AuthApplication record);
    
    List<AuthApplication> query(AuthApplication record);
    
    List<AuthApplication> queryByParamMap(Map<String, Object> paramMap);
    
    String getTopMenuParent(String menuId);
    
    List<ZTreeNode> queryChildrenTreeNodes(String pId);
}