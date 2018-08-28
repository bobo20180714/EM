package com.em.core.org.mappers;

import java.util.List;

import com.em.common.zTree.ZTreeNode;
import com.em.core.org.group.entity.OrgGroup;

public interface OrgGroupMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(OrgGroup record);

    int insertSelective(OrgGroup record);

    OrgGroup selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(OrgGroup record);

    int updateByPrimaryKey(OrgGroup record);

	List<OrgGroup> query(OrgGroup orgGroup);

	List<OrgGroup> queryUpdate(String id);

	List<ZTreeNode> queryChildrenTreeNodes(String pId);
}