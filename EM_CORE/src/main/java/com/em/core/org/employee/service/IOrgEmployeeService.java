/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.employee.service;

import java.util.List;
import java.util.Map;

import com.em.common.service.IBaseService;
import com.em.common.zTree.ZTreeNode;
import com.em.core.org.employee.entity.OrgEmployee;

/**
 * @ClassName: IOmEmployeeService
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午3:39:36
 */
public interface IOrgEmployeeService {
	
	OrgEmployee getOrgEmployee(String id);
	
	List<ZTreeNode> queryTreeNodes(Map paramMap);

	/**
	 * 
	 * @Title: queryOrgEmpTreeNodes
	 * @author：liuyx
	 * @date：2015年11月19日上午8:37:28
	 * @Description: 仅查询机构人员树，无岗位
	 * @param paramMap
	 * @return
	 */
	List<ZTreeNode> queryOrgEmpTreeNodes(Map paramMap);

	List<ZTreeNode> queryMultiOrgEmpTreeNodes(Map paramMap);

	void delOrgEmployee(String id);

	OrgEmployee get(String id);

	int insert(OrgEmployee orgEmployee);

	int update(OrgEmployee orgEmployee);

	List<OrgEmployee> queryByMap(Map paramMap);
}
