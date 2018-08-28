package com.em.core.org.mappers;

import java.util.List;
import java.util.Map;

import com.em.core.org.employee.group.entity.OrgEmployeeGroup;


public interface OrgEmployeeGroupMapper {
    int deleteByEntity(OrgEmployeeGroup key);

    void deleteByPrimaryKey(OrgEmployeeGroup orgEmployeeGroup);
    
    int insert(OrgEmployeeGroup record);

    int insertSelective(OrgEmployeeGroup record);

	List<OrgEmployeeGroup> query(OrgEmployeeGroup orgEmployeeGroup);

	void deleteByMap(Map deleteParam);

	void batchInsert(List<Map> list);
}