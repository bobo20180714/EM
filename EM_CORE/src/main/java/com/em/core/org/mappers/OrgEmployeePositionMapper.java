package com.em.core.org.mappers;

import com.em.core.org.employee.position.entity.OrgEmployeePosition;

public interface OrgEmployeePositionMapper {
    int deleteByPrimaryKey(OrgEmployeePosition key);

    int insert(OrgEmployeePosition record);

    int insertSelective(OrgEmployeePosition record);

    OrgEmployeePosition selectByPrimaryKey(OrgEmployeePosition key);

    int updateByPrimaryKeySelective(OrgEmployeePosition record);

    int updateByPrimaryKey(OrgEmployeePosition record);
}