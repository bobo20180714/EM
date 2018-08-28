package com.em.core.org.mappers;

import java.util.List;
import java.util.Map;

import com.em.common.zTree.ZTreeNode;
import com.em.core.org.organization.entity.OrgOrganization;

public interface OrgOrganizationMapper {
    int deleteByPrimaryKey(String orgId);

    int insert(OrgOrganization record);

    int insertSelective(OrgOrganization record);

    OrgOrganization selectByPrimaryKey(String orgId);

    int updateByPrimaryKeySelective(OrgOrganization record);

    int updateByPrimaryKey(OrgOrganization record);

	String getChildrenStringByOrgId(String rootOrgId);

	List<OrgOrganization> query(Map map);

	List<ZTreeNode> queryOnlyOrgTreeNodes(Map<String, Object> paramMap);

	List<ZTreeNode> queryChildrenTreeNodes(Map<String, Object> paramMap);

	List<ZTreeNode> queryOnlyOrgChildrenTreeNodes(Map<String, Object> paramMap);
}