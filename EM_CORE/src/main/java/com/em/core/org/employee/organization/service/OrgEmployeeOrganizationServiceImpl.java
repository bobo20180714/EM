package com.em.core.org.employee.organization.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.dao.IBaseDao;
import com.em.common.page.PageView;
import com.em.core.org.employee.organization.entity.OrgEmployeeOrganization;
import com.em.core.org.mappers.OrgEmployeeOrganizationMapper;

@Service
public class OrgEmployeeOrganizationServiceImpl implements IOrgEmployeeOrganizationService {

	@Autowired
	private OrgEmployeeOrganizationMapper orgEmployeeOrganizationMapper;

	@Override
	public OrgEmployeeOrganization get(String id) {
		return orgEmployeeOrganizationMapper.selectByPrimaryKey(id);
	}


	/**
	 * 插入 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void addOrgEmployeeOrganization(OrgEmployeeOrganization orgEmployeeOrganization) {
		orgEmployeeOrganizationMapper.insertSelective(orgEmployeeOrganization);
	}

	/**
	 * 修改 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void updateOrgEmployeeOrganization(OrgEmployeeOrganization orgEmployeeOrganization) {
		orgEmployeeOrganizationMapper.updateByPrimaryKeySelective(orgEmployeeOrganization);
	}

	/**
	 * 删除 一条记录
	 * 
	 * @param
	 */
	@Override
	public void deleteOrgEmployeeOrganization(OrgEmployeeOrganization orgEmployeeOrganization) {
		orgEmployeeOrganizationMapper.deleteByPrimaryKey(orgEmployeeOrganization);
	}


}
