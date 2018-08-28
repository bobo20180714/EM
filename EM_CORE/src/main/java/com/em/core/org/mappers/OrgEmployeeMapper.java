package com.em.core.org.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.zTree.ZTreeNode;
import com.em.core.org.employee.entity.OrgEmployee;


public interface OrgEmployeeMapper {
    int deleteByPrimaryKey(String empId);

    int insertSelective(OrgEmployee record);

    OrgEmployee selectByPrimaryKey(String empId);

    int updateByPrimaryKeySelective(OrgEmployee record);

    int updateByPrimaryKey(OrgEmployee record);

	List<ZTreeNode> queryMultiOrgEmpTreeNodes(Map<String, Object> paramMap);

	List<ZTreeNode> queryOrgEmpTreeNodes(Map<String, Object> paramMap);

	List<ZTreeNode> queryTreeNodes(Map<String, Object> paramMap);

	int insertByMap(Map record);

	int updateByMap(Map record);

	List<OrgEmployee> queryByMap(Map paramMap);

}