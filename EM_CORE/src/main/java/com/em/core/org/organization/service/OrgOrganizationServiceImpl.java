/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.organization.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.org.employee.entity.OrgEmployee;
import com.em.core.org.employee.organization.entity.OrgEmployeeOrganization;
import com.em.core.org.employee.position.entity.OrgEmployeePosition;
import com.em.core.org.mappers.OrgEmployeeMapper;
import com.em.core.org.mappers.OrgEmployeeOrganizationMapper;
import com.em.core.org.mappers.OrgEmployeePositionMapper;
import com.em.core.org.mappers.OrgOrganizationMapper;
import com.em.core.org.mappers.OrgPositionMapper;
import com.em.core.org.organization.entity.OrgOrganization;
import com.em.core.org.position.entity.OrgPosition;

/**
 * @ClassName: OmOrganizationServiceImpl
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class OrgOrganizationServiceImpl implements IOrgOrganizationService {

	@Autowired
	private OrgOrganizationMapper organizationMapper;
	@Autowired
	private OrgPositionMapper orgPositionMapper;
	@Autowired
	private OrgEmployeePositionMapper orgEmployeePositionMapper;
	@Autowired
	private OrgEmployeeOrganizationMapper orgEmployeeOrganizationMapper;
	@Autowired
	private OrgEmployeeMapper orgEmployeeMapper;

	@Override
	public OrgOrganization get(String id) {
		return organizationMapper.selectByPrimaryKey(id);
	}

	/**
	 * 插入 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void addOrgOrganization(OrgOrganization organization) {
		organizationMapper.insertSelective(organization);
	}

	/**
	 * 修改 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void updateOrgOrganization(OrgOrganization organization) {
		organizationMapper.updateByPrimaryKeySelective(organization);
	}

	/**
	 * 删除 一条记录
	 * 
	 * @param
	 */
	@Override
	public void delOrgOrganization(String id) {
		organizationMapper.deleteByPrimaryKey(id);
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
	//用MAP不用实体类
	public List<OrgOrganization> query(Map<String, String> map) {
		List<OrgOrganization> list = organizationMapper.query(map);
		return list;
	}

	public List<ZTreeNode> queryOnlyOrgTreeNodes(Map<String, Object> paramMap) {
		String rootOrgId = StringUtils.getTrimStr(paramMap.get("rootOrgId"));
		if (StringUtils.isNotEmpty(rootOrgId)) {
			String orgIdChildrenList = StringUtils.getTrimStr(organizationMapper.getChildrenStringByOrgId(rootOrgId));
			paramMap.put("orgIdChildrenList", orgIdChildrenList);
		}

		List<ZTreeNode> list = organizationMapper.queryOnlyOrgTreeNodes(paramMap);
		return list;
	}

	/**
	 * @Title: queryChildrenTreeNodes
	 * @author：liuyx
	 * @date：2015年9月7日下午4:56:36
	 * @Description: 构建 机构 岗位 人员树
	 * @param paramMap
	 * @return
	 */
	public List<ZTreeNode> queryChildrenTreeNodes(Map<String, Object> paramMap) {
		List<ZTreeNode> list = null;
		if (StringUtils.isEmpty(paramMap.get("onlyOrg"))) {
			list = organizationMapper.queryChildrenTreeNodes(paramMap);
		} else {
			list = organizationMapper.queryOnlyOrgChildrenTreeNodes(paramMap);
		}

		String pId = paramMap.get("pId") == null ? null : paramMap.get("pId").toString();
		// 设置根节点打开
		if (pId == null || "-1".equals(pId)) {
			for (ZTreeNode node : list) {
				if ("-1".equals(node.getpId())) {
					node.setOpen(true);
					break;
				}
			}
		} else {
			for (ZTreeNode n : list) {
				if (n.getId().equals(pId)) {
					n.setOpen(true);
					break;
				}
			}
		}

		String checkedOrgIdList = paramMap.get("checkedOrgIdList") == null ? null : paramMap.get("checkedOrgIdList").toString();
		if (!StringUtils.isEmpty(checkedOrgIdList)) {
			String[] checkedArray = checkedOrgIdList.split(",");
			for (String corg : checkedArray) {
				for (ZTreeNode ztn : list) {
					if (ztn.getId().equals(corg)) {
						ztn.setChecked(true);
						break;
					}
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: adjustByTree
	 * @author：liuyx
	 * @date：2015年12月29日上午11:25:39
	 * @Description: 机构岗位人员结构调整
	 * @param id
	 * @param type
	 * @param fromId
	 * @param fromType
	 * @param toId
	 * @param toType
	 * @param isCopy
	 * @return
	 */
	public int adjust(String id, String type, String fromId, String fromType, String toId, String toType, String isCopy) {
		
		if ("ORG".equals(type)) {
			OrgOrganization organization = new OrgOrganization();
			// 机构只会调整到机构下,只需将机构表父节点修改即可
			organization.setOrgId(id);
			organization.setParentOrgId(toId);
			return organizationMapper.updateByPrimaryKeySelective(organization);
		} else if ("POSI".equals(type)) {
			// 岗位可以调整到机构或者岗位下
			// 如果调整到岗位下，只要修改所属岗位
			OrgPosition orgPosition = new OrgPosition();
			orgPosition.setPositionId(id);
			if ("POSI".equals(toType)) {
				orgPosition.setParentPosiId(toId);
			}
			// 如果调整到机构下，则清空所属岗位，将机构调整
			else if ("ORG".equals(toType)) {
				orgPosition.setParentPosiId("");
				orgPosition.setOrgId(toId);
			}
			return orgPositionMapper.updateByPrimaryKeySelective(orgPosition);
		} else if ("EMP".equals(type)) {
			// 人员可以调整到机构或者岗位下
			OrgEmployeePosition orgEmployeePosition = new OrgEmployeePosition();
			orgEmployeePosition.setEmpId(id);
			// 如果是复制到岗位下，则插入关联表，主岗设成否
			if ("true".equals(isCopy)) {
				if ("POSI".equals(toType)) {
					orgEmployeePosition.setPositionId(toId);
					orgEmployeePosition.setIsMain("0");
					return orgEmployeePositionMapper.insert(orgEmployeePosition);
				}
			}
			// 如果移动到岗位下，
			// 1先删除之前的人员岗位关联表，2以及人员机构关联表，3然后插入岗位关联关系,4再修改emp主表中的岗位,以及机构改为岗位所属机构
			if ("POSI".equals(toType)) {
				orgEmployeePosition.setPositionId(fromId);
				// 先删除之前的人员岗位关联表
				orgEmployeePositionMapper.deleteByPrimaryKey(orgEmployeePosition);
				// 删除人员机构关联表
				OrgEmployeeOrganization orgEmployeeOrganization = new OrgEmployeeOrganization();
				orgEmployeeOrganization.setEmpId(id);
				orgEmployeeOrganizationMapper.deleteByPrimaryKey(orgEmployeeOrganization);
				orgEmployeePosition.setPositionId(toId);
				orgEmployeePosition.setIsMain("1");
				// 插入岗位关联关系
				orgEmployeePositionMapper.insert(orgEmployeePosition);
				OrgPosition op = orgPositionMapper.selectByPrimaryKey(toId);
				OrgEmployee oe = new OrgEmployee();
				oe.setOrgId(op.getOrgId());
				return orgEmployeeMapper.updateByPrimaryKeySelective(oe);
			}

			// 如果是移动到机构下，1先删除之前的该人员和岗位的所有关联关系，2以及人员机构关联表,3插入人员机构关联关系，4再清空emp主表中的岗位,并且修改机构
			// 注 如果移动到机构下，则之前的多个岗位全部清空
			else if ("ORG".equals(toType)) {
				// 先删除之前的人员岗位关联表
				orgEmployeePositionMapper.deleteByPrimaryKey(orgEmployeePosition);
				// 删除人员机构关联表
				OrgEmployeeOrganization orgEmployeeOrganization = new OrgEmployeeOrganization();
				orgEmployeeOrganization.setEmpId(id);
				orgEmployeeOrganizationMapper.deleteByPrimaryKey(orgEmployeeOrganization);
				orgEmployeeOrganization.setOrgId(toId);
				orgEmployeeOrganization.setIsMain("1");
				orgEmployeeOrganizationMapper.insert(orgEmployeeOrganization);
				OrgEmployee oe = new OrgEmployee();
				oe.setOrgId(toId);
				oe.setPosition("");
				return orgEmployeeMapper.updateByPrimaryKey(oe);
			}
		}

		return 0;
	}
}
