/**
k * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.function.group.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.StringUtils;
import com.em.core.auth.function.group.entity.AuthFunctionGroup;
import com.em.core.auth.function.group.service.IAuthFunctionGroupService;

/****
 * 功能组控制器
 * 
 * @author yangy 2018年5月18日 下午1:54:30 AuthFunctionGroupController.java
 * @Description
 */
@Controller
@RequestMapping("/authFuncGroup")
public class AuthFunctionGroupController extends BaseController {

	@Autowired
	private IAuthFunctionGroupService authFuncGroupService;

	/****
	 * id也是funcgroupid
	 * XXX
	 * @param request
	 * @param id
	 * @param funcGroupId
	 * @param appId
	 * @param level
	 * @return
	 */
	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String id, String funcGroupId, String appId, String level) {
		ModelAndView mv = new ModelAndView();
		AuthFunctionGroup authFunctionGroup = new AuthFunctionGroup();
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			authFunctionGroup = authFuncGroupService.getAuthFunctionGroup(id);
		}
		if (!StringUtils.isEmpty(funcGroupId)) {
			authFunctionGroup.setParentGroup(funcGroupId);
		}
		if (!StringUtils.isEmpty(appId)) {
			authFunctionGroup.setAppId(appId);
		}
		if (!StringUtils.isEmpty(level)) {
			authFunctionGroup.setGroupLevel(Integer.valueOf(level));
		}
		mv.setViewName("core/auth/auth_function_group/auth_function_group_forUpdate");
		mv.addObject("record", authFunctionGroup);
		return mv;
	}

	/****
	 * 修改update
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(AuthFunctionGroup authFunctionGroup) {
		try {
			if (StringUtils.isEmpty(authFunctionGroup.getFuncGroupId())) {
				// 新增
				authFunctionGroup.setFuncGroupId(StringUtils.uniqueKey36());
				authFuncGroupService.addFunctionGroup(authFunctionGroup);
			} else {
				// 修改
				authFuncGroupService.updateAuthFunctionGroup(authFunctionGroup);
			}
			return new RetObj(true, "操作成功", authFunctionGroup.getFuncGroupId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}
	}

	/***
	 * XXX
	 * delete the function group for application maint
	 * @param request
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request, String id, HttpServletResponse response) {
		int status = 0;
		try {
			if (!StringUtils.isEmpty(id)) {
				status = authFuncGroupService.delAuthFunctionGroup(id);
			}
			StringBuffer jsonStr = new StringBuffer("{\"status\":\"" + status + "\"}");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonStr.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
