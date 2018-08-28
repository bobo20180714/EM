/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.employee.organization.service;

import com.em.core.org.employee.organization.entity.OrgEmployeeOrganization;

/**
 * @ClassName: IOmEmpOrgService
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月20日下午1:32:13
 */
public interface IOrgEmployeeOrganizationService {

	OrgEmployeeOrganization get(String id);

	void addOrgEmployeeOrganization(
			OrgEmployeeOrganization orgEmployeeOrganization);

	void updateOrgEmployeeOrganization(
			OrgEmployeeOrganization orgEmployeeOrganization);

	void deleteOrgEmployeeOrganization(
			OrgEmployeeOrganization orgEmployeeOrganization);

}
