/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.employee.group.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.em.core.org.employee.group.entity.OrgEmployeeGroup;
import com.em.core.org.mappers.OrgEmployeeGroupMapper;

/**
 * @ClassName: OmEmpGroupServiceImpl
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class OrgEmployeeGroupServiceImpl implements IOrgEmployeeGroupService {

	@Autowired
	private OrgEmployeeGroupMapper orgEmployeeGroupMapper;

	/**
	 * 插入 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void addOrgEmployeeGroup(OrgEmployeeGroup orgEmployeeGroup) {
		orgEmployeeGroupMapper.insert(orgEmployeeGroup);
	}


	/**
	 * 删除 一条记录
	 * 
	 * @param
	 */
	@Override
	public void delOrgEmployeeGroup(OrgEmployeeGroup orgEmployeeGroup) {
		orgEmployeeGroupMapper.deleteByPrimaryKey(orgEmployeeGroup);
	}

	/**
	 * @Title: query
	 * @author：liuyx
	 * @date：2015年9月5日下午3:51:13
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<OrgEmployeeGroup> queryOrgEmployeeGroup(OrgEmployeeGroup orgEmployeeGroup) {
		List<OrgEmployeeGroup> list = orgEmployeeGroupMapper.query(orgEmployeeGroup);
		return list;
	}

	/**
	 * @Title: batchInsert
	 * @author：liuyx
	 * @date：2015年9月27日下午2:06:18
	 * @Description: 批量插入
	 * @param list
	 * @return
	 */
	@Override
	public int batchInsert(List<Map> list, Map<String, Object> deleteParam) {
		if (deleteParam != null) {
			orgEmployeeGroupMapper.deleteByMap(deleteParam);
		}
		if (!CollectionUtils.isEmpty(list)) {
			orgEmployeeGroupMapper.batchInsert(list); 
		}
		return 0;

	}

}
