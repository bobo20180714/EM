/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.role.function.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.HttpUtils;
import com.em.core.auth.role.function.entity.AuthRoleFunction;
import com.em.core.auth.role.function.service.IAuthRoleFunctionService;

/*****
 * 
 * @author yangy
 * 2018年5月22日 上午12:12:44
 * AuthRoleFunctionController.java
 * @Description
 */
@Controller
@RequestMapping("/authRoleFunc")
public class AuthRoleFunctionController extends BaseController {

	@Autowired
	private IAuthRoleFunctionService authRoleFuncService;

	/****
	 * XXX
	 * 查看该角色所拥有的功能
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryRoleFunc")
	@ResponseBody
	public List<AuthRoleFunction> forCheckTreeForRole(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		List<AuthRoleFunction> list = authRoleFuncService.queryAuthRoleFunctionByMap(paramMap);
		return list;
	}

	/****
	 * XXX
	 * 给角色增加相应的功能
	 * @param request
	 * @param roleFuncJsonStr
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/batchInsert")
	@ResponseBody
	public RetObj batchInsert(HttpServletRequest request, String roleFuncJsonStr, String roleId) {
		List<Map> dataList = JSON.parseArray(roleFuncJsonStr, Map.class);
		try {
			authRoleFuncService.batchInsert(dataList, roleId);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}

	}
}
