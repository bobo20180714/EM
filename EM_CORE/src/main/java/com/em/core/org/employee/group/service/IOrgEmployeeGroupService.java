/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.employee.group.service;

import java.util.List;
import java.util.Map;

import com.em.common.service.IBaseService;
import com.em.core.org.employee.group.entity.OrgEmployeeGroup;

/**
 * @ClassName: IOmEmpGroupService
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午3:39:36
 */
public interface IOrgEmployeeGroupService {

	void addOrgEmployeeGroup(OrgEmployeeGroup orgEmployeeGroup);

	List<OrgEmployeeGroup> queryOrgEmployeeGroup(
			OrgEmployeeGroup orgEmployeeGroup);

	void delOrgEmployeeGroup(OrgEmployeeGroup orgEmployeeGroup);

	int batchInsert(List<Map> dataList, Map<String, Object> deleteParam);
}
