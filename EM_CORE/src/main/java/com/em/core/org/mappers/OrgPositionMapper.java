package com.em.core.org.mappers;

import java.util.List;
import java.util.Map;

import com.em.core.org.position.entity.OrgPosition;

public interface OrgPositionMapper {
    int deleteByPrimaryKey(String positionId);

    int insert(OrgPosition record);

    int insertSelective(OrgPosition record);

    OrgPosition selectByPrimaryKey(String positionId);

    int updateByPrimaryKeySelective(OrgPosition record);

    int updateByPrimaryKey(OrgPosition record);

	List<OrgPosition> query(Map<String, String> map);
}