package com.em.core.org.mappers;

import com.em.core.org.employee.organization.entity.OrgEmployeeOrganization;


public interface OrgEmployeeOrganizationMapper {
    int deleteByPrimaryKey(OrgEmployeeOrganization orgEmployeeOrganization);

    int insert(OrgEmployeeOrganization record);

    int insertSelective(OrgEmployeeOrganization record);

    int updateByPrimaryKeySelective(OrgEmployeeOrganization record);

    int updateByPrimaryKey(OrgEmployeeOrganization record);

	OrgEmployeeOrganization selectByPrimaryKey(String id);
}