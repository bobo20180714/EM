/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.group.service;

import java.util.List;

import com.em.common.zTree.ZTreeNode;
import com.em.core.org.group.entity.OrgGroup;

/**
 * @ClassName: IOmGroupService.java
 * @Description:
 * @author yuqing
 * @version 2015年9月24日 下午6:02:55
 */
public interface IOrgGroupService {
	List<ZTreeNode> queryChildrenTreeNodes(String pId);

	OrgGroup get(String id);

	void addOrgGroup(OrgGroup orgGroup);

	void updateOrgGroup(OrgGroup orgGroup);

	int deleteOrgGroup(String id);

	List<OrgGroup> queryOrgGroup(OrgGroup orgGroup);
	/**
	 * 验证输入字段是否重复：修改
	 * 
	 * @author: 于庆
	 */
	List<OrgGroup> queryUpdate(String id);
}
