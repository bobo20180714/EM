/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.function.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.HttpUtils;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.function.entity.AuthFunction;
import com.em.core.auth.function.service.IAuthFunctionService;

@Controller
@RequestMapping("/authFunction")
public class AuthFunctionController extends BaseController {
	@Autowired
	private IAuthFunctionService authFunctionService;

	/****
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/forCheckTree")
	public String forCheckTree(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		return "core/auth/auth_function/auth_function_forCheckTree";
	}

	/***
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		return authFunctionService.queryTreeNodes(paramMap);
	}

	/**
	 * XXX
	 * @param request
	 * @param funcId
	 * @param funcGroupId
	 * @return
	 */
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id, String funcGroupId) {
		AuthFunction function = new AuthFunction();
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			function = authFunctionService.getAuthFunction(id);
		}
		if (!StringUtils.isEmpty(funcGroupId)) {
			function.setFuncGroupId(funcGroupId);
		}
		request.setAttribute("record", function);
		return "core/auth/auth_function/auth_function_forUpdate";
	}

	/***
	 * XXX
	 * @param function
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(AuthFunction function) {
		try {
			if (StringUtils.isEmpty(function.getFuncId())) {
				// 新增
				function.setFuncId(StringUtils.uniqueKey36());
				authFunctionService.addAuthFunction(function);
			} else {
				// 修改
				authFunctionService.updateAuthFunction(function);
			}
			return new RetObj(true, "操作成功", function.getFuncId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}
	}

	/***
	 * XXX
	 * delete the function
	 * @param funcId
	 * @param response
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id, HttpServletResponse response) {
		int status = 0;
		try {

			if (!StringUtils.isEmpty(id)) {
				status = authFunctionService.delAuthFunction(id);
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
