/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.organization.service;

import java.util.List;
import java.util.Map;

import com.em.common.service.IBaseService;
import com.em.common.zTree.ZTreeNode;
import com.em.core.org.organization.entity.OrgOrganization;

/**
 * @ClassName: IOmOrganizationService
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午3:39:36
 */
public interface IOrgOrganizationService {

	List<ZTreeNode> queryChildrenTreeNodes(Map<String, Object> paramMap);

	int adjust(String id, String type, String fromId, String fromType, String toId, String toType, String isCopy);

	public List<ZTreeNode> queryOnlyOrgTreeNodes(Map<String, Object> paramMap);

	OrgOrganization get(String id);

	void addOrgOrganization(OrgOrganization organization);

	void updateOrgOrganization(OrgOrganization organization);

	void delOrgOrganization(String id);

	List<OrgOrganization> query(Map<String, String> map);
}
