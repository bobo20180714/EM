/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.operator.role.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.HttpUtils;
import com.em.common.utils.StringUtils;
import com.em.core.auth.operator.role.entity.AuthOperatorRole;
import com.em.core.auth.operator.role.service.IAuthOperatorRoleService;
import com.em.core.auth.role.entity.AuthRole;
import com.em.core.auth.role.service.IAuthRoleService;
import com.em.core.org.employee.entity.OrgEmployee;
import com.em.core.org.employee.service.IOrgEmployeeService;

/****
 * 
 * @author yangy 2018年5月21日 下午11:32:20 AuthOperatorRoleController.java
 * @Description
 */
@Controller
@RequestMapping("/authOperatorRole")
public class AuthOperatorRoleController extends BaseController {

	@Autowired
	private IAuthOperatorRoleService authOperatorRoleService;
	@Autowired
	private IOrgEmployeeService orgEmployeeService;
	@Autowired(required = false)
	private IAuthRoleService authRoleService;

	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String empId, String operatorId) {
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isEmpty(operatorId)) {
			OrgEmployee emp = orgEmployeeService.getOrgEmployee(empId);
			operatorId = emp.getOperatorId();
		}
		// 获取当前应用的所有角色
		Map paramMap = new HashMap();
		// 取出所有角色
		List<AuthRole> roleList = authRoleService.queryAuthRoleByMap(paramMap);
		paramMap.put("OPERATOR_ID", operatorId);
		// 取出已经授权的角色
		List<AuthOperatorRole> operatorRoleList = authOperatorRoleService.queryAuthOperatorRoleByMap(paramMap);

		// 删除所有已授权的角色
		for (AuthOperatorRole operatorRole : operatorRoleList) {
			for (AuthRole role : roleList) {
				if (role.getRoleId().equals(operatorRole.getRoleId())) {
					roleList.remove(role);
					break;
				}
			}
		}
		mv.addObject("roleList", roleList);
		mv.addObject("operatorRoleList", operatorRoleList);
		mv.addObject("operatorId", operatorId);
		mv.setViewName("core/auth/auth_operator_role/auth_operator_role_forUpdate");
		return mv;
	}

	/****
	 * 查询角色关联的人员
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryOperatorRole")
	@ResponseBody
	public List<AuthOperatorRole> queryOperatorRole(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		List<AuthOperatorRole> list = authOperatorRoleService.queryAuthOperatorRoleByMap(paramMap);
		return list;
	}

	/***
	 * 关联人员功能中，将人员与角色进行关联
	 * XXX
	 * @param request
	 * @param dataJsonStr
	 * @param roleId
	 * @param notInOperatorIds
	 * @return
	 */
	@RequestMapping("/batchInsertByJson")
	@ResponseBody
	public RetObj batchInsertByJson(HttpServletRequest request, String dataJsonStr, String roleId, String notInOperatorIds) {
		List<Map> dataList = JSON.parseArray(dataJsonStr, Map.class);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("ROLE_ID", roleId);
		paramMap.put("notInOperatorIds", notInOperatorIds);
		try {
			authOperatorRoleService.batchInsert(dataList, paramMap);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	@RequestMapping("/batchInsertByArray")
	@ResponseBody
	public RetObj batchInsertByArray(HttpServletRequest request, String[] selectright, String operatorId) {

		List<Map> dataList = new ArrayList();
		if (selectright != null && selectright.length > 0) {
			for (int i = 0; i < selectright.length; i++) {
				Map operRole = new HashMap();
				operRole.put("OPERATOR_ID", operatorId);
				operRole.put("ROLE_ID", selectright[i]);
				dataList.add(operRole);
			}
		}

		try {
			authOperatorRoleService.batchInsert(dataList, null);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}
}
