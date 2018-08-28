/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.employee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.em.common.consts.CommonConsts;
import com.em.common.dao.IBaseDao;
import com.em.common.page.PageView;
import com.em.common.service.ConfigService;
import com.em.common.utils.DateUtils;
import com.em.common.utils.Md5Util;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.operator.entity.AuthOperator;
import com.em.core.auth.operator.service.IAuthOperatorService;
import com.em.core.org.employee.entity.OrgEmployee;
import com.em.core.org.employee.organization.entity.OrgEmployeeOrganization;
import com.em.core.org.employee.organization.service.IOrgEmployeeOrganizationService;
import com.em.core.org.employee.position.entity.OrgEmployeePosition;
import com.em.core.org.mappers.OrgEmployeeMapper;
import com.em.core.org.mappers.OrgEmployeeOrganizationMapper;
import com.em.core.org.mappers.OrgEmployeePositionMapper;
import com.em.core.org.mappers.OrgOrganizationMapper;

/**
 * @ClassName: OmEmployeeServiceImpl
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class OrgEmployeeServiceImpl implements IOrgEmployeeService {

	@Autowired
	private IAuthOperatorService authOperatorService;
	@Autowired
	private OrgEmployeeOrganizationMapper orgEmployeeOrganizationMapper;
	@Autowired
	private OrgEmployeeMapper orgEmployeeMapper;
	@Autowired
	private OrgOrganizationMapper orgOrganizationMapper;
	@Autowired
	private OrgEmployeePositionMapper orgEmployeePositionMapper;

	/*
	 * @Autowired private IAcApplicationService acApplicationService; public
	 * void testTransaction() { this.delete("2");
	 * acApplicationService.delete("2"); int i = 1/0; return; }
	 */

	@Override
	public OrgEmployee getOrgEmployee(String id) {
		return orgEmployeeMapper.selectByPrimaryKey(id);
	}


	/**
	 * 新增人员
	 * 
	 * @param beanMap
	 * @return -1:操作员重复
	 */
	@Override
	public int insert(OrgEmployee orgEmployee) {

		String userId = StringUtils.getTrimStr(orgEmployee.getUserId());
		String operatorId = StringUtils.uniqueKey36();
		String now = DateUtils.getCurrentDate();
		// 操作员新增
		if (!StringUtils.isEmpty(userId)) {
			// 操作员USER_ID重复检测
			Map paramMap = new HashMap();
			paramMap.put("userId", userId);
			List<AuthOperator> checkList = authOperatorService.queryAuthOperatorByMap(paramMap);
			if (!CollectionUtils.isEmpty(checkList)) {
				return CommonConsts.DATA_REPEAT_ERR;
			}
			// 设置密码
			String psw = StringUtils.getTrimStr(orgEmployee.getPassword());
			if (StringUtils.isEmpty(psw)) {
				// 空密码则设置默认密码
				psw = ConfigService.getConfig(ConfigService.DEFAULT_PASSWORD);
			}
			AuthOperator aoAuthOperator = new AuthOperator();
			aoAuthOperator.setOperatorId(operatorId);
			aoAuthOperator.setOperatorName(orgEmployee.getEmpName());
			aoAuthOperator.setUserId(userId);
			aoAuthOperator.setPassword(Md5Util.string2MD5(psw));
			aoAuthOperator.setStatus(orgEmployee.getStatus());
			aoAuthOperator.setEmail(orgEmployee.getpEmail());
			// 新增操作员数据
			authOperatorService.addAuthOperator(aoAuthOperator);
		}
		orgEmployee.setEmpId(StringUtils.uniqueKey36());
		orgEmployee.setOperatorId(operatorId);
		orgEmployee.setCreateTime(now);
		if (StringUtils.isEmpty(orgEmployee.getPosition())) {
			OrgEmployeeOrganization oeo = new OrgEmployeeOrganization();
			oeo.setEmpId(orgEmployee.getEmpId());
			oeo.setOrgId(orgEmployee.getOrgId());
			oeo.setIsMain("1");
			orgEmployeeOrganizationMapper.insert(oeo);
		} else {
			OrgEmployeePosition oep = new OrgEmployeePosition();
			oep.setEmpId(orgEmployee.getEmpId());
			oep.setPositionId(orgEmployee.getPosition());
			oep.setIsMain("1");
			orgEmployeePositionMapper.insert(oep);
		}

		return orgEmployeeMapper.insertSelective(orgEmployee);
	}

	/**
	 * 修改 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public int update(OrgEmployee orgEmployee) {

		String userId = StringUtils.getTrimStr(orgEmployee.getUserId());
		String operatorId = StringUtils.getTrimStr(orgEmployee.getOperatorId());
		String now = DateUtils.getCurrentDate();
		// 操作员新增
		if (!StringUtils.isEmpty(userId)) {
			// 操作员USER_ID重复检测
			Map paramMap = new HashMap();
			paramMap.put("USER_ID", userId);

			if (!StringUtils.isEmpty(operatorId)) {
				// 已有对应注册账号，检测时排除对应数据
				paramMap.put("NOT_OPERATOR_ID", operatorId);
			}

			List<AuthOperator> checkList = authOperatorService.queryAuthOperatorByMap(paramMap);
			if (!CollectionUtils.isEmpty(checkList)) {
				return CommonConsts.DATA_REPEAT_ERR;
			}		
			AuthOperator aoAuthOperator = new AuthOperator();
			aoAuthOperator.setOperatorName(orgEmployee.getEmpName());
			aoAuthOperator.setUserId(userId);
			String empStatus = StringUtils.getTrimStr(orgEmployee.getEmpStatus());
			if (!CommonConsts.EMP_STATUS_NORMAL.equals(empStatus)) {
				// 如果员工状态已经非正常在职，则禁用操作员，前台也应该加上限制。
				aoAuthOperator.setStatus(CommonConsts.OPERATOR_STATUS_ABNORMAL);
			} else {
				aoAuthOperator.setStatus(CommonConsts.OPERATOR_STATUS_NORMAL);
			}
			aoAuthOperator.setStatus(orgEmployee.getStatus());
			aoAuthOperator.setEmail(orgEmployee.getpEmail());

			if (StringUtils.isEmpty(operatorId)) {
				// 无已有操作员账号
				operatorId = StringUtils.uniqueKey36();
				aoAuthOperator.setOperatorId(StringUtils.uniqueKey36());
				String psw = StringUtils.getTrimStr(orgEmployee.getPassword());
				if (StringUtils.isEmpty(psw)) {
					// 空密码则设置默认密码
					psw = ConfigService.getConfig(ConfigService.DEFAULT_PASSWORD);
				}
				aoAuthOperator.setPassword(Md5Util.string2MD5(psw));
				// 新增操作员数据
				authOperatorService.addAuthOperator(aoAuthOperator);
			} else {
				// 已有操作员账号
				aoAuthOperator.setOperatorId(operatorId);
				String psw = StringUtils.getTrimStr(orgEmployee.getPassword());
				if (!StringUtils.isEmpty(psw)) {
					aoAuthOperator.setPassword(Md5Util.string2MD5(psw));
				}
				authOperatorService.updateAuthOperator(aoAuthOperator);
			}
		}
		// 暂不支持调岗调机构
		return orgEmployeeMapper.updateByPrimaryKeySelective(orgEmployee);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ZTreeNode> queryTreeNodes(Map paramMap) {
		
		List<ZTreeNode> list = orgEmployeeMapper.queryTreeNodes(paramMap);
		for (ZTreeNode n : list) {
			if (n.getpId().equals("-1")) {
				n.setOpen(true);
				break;
			}
		}
		// 设置根节点打开
		/*
		 * if(pId==null) { for(ZTreeNode node:list) {
		 * if("-1".equals(node.getpId())) { node.setOpen(true); break; } } }
		 */

		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ZTreeNode> queryOrgEmpTreeNodes(Map paramMap) {
		List<ZTreeNode> list = orgEmployeeMapper.queryOrgEmpTreeNodes(paramMap);
		for (ZTreeNode n : list) {
			if (n.getpId().equals("-1")) {
				n.setOpen(true);
				break;
			}
		}
		// 设置根节点打开
		/*
		 * if(pId==null) { for(ZTreeNode node:list) {
		 * if("-1".equals(node.getpId())) { node.setOpen(true); break; } } }
		 */

		return list;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ZTreeNode> queryMultiOrgEmpTreeNodes(Map paramMap) {
		String rootOrgId = StringUtils.getTrimStr(paramMap.get("rootOrgId"));
		if (StringUtils.isNotEmpty(rootOrgId)) {
			String orgIdChildrenList = StringUtils.getTrimStr(orgOrganizationMapper.getChildrenStringByOrgId(rootOrgId));
			paramMap.put("orgIdChildrenList", orgIdChildrenList);
		}
		List<ZTreeNode> list = orgEmployeeMapper.queryMultiOrgEmpTreeNodes(paramMap);
		for (ZTreeNode n : list) {
			if (n.getpId().equals("-1")) {
				n.setOpen(true);
				break;
			}
		}
		// 设置根节点打开
		/*
		 * if(pId==null) { for(ZTreeNode node:list) {
		 * if("-1".equals(node.getpId())) { node.setOpen(true); break; } } }
		 */

		return list;
	}


	@Override
	public void delOrgEmployee(String id) {
		orgEmployeeMapper.deleteByPrimaryKey(id);	
	}

	@Override
	public OrgEmployee get(String id) {
		return orgEmployeeMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<OrgEmployee> queryByMap(Map paramMap) {
		// TODO Auto-generated method stub
		return orgEmployeeMapper.queryByMap(paramMap);
	}

}
