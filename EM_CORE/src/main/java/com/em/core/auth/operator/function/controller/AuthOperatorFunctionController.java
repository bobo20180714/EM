/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.operator.function.controller;

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
import com.em.core.auth.operator.function.entity.AuthOperatorFunction;
import com.em.core.auth.operator.function.service.IAuthOperatorFunctionService;

/****
 * 操作员对应功能控制器，权限中功能与用户绑定
 * 
 * @author yangy 2018年5月21日 下午5:51:02 AuthOperatorFunctionController.java
 * @Description
 */
@Controller
@RequestMapping("/authOperFunc")
public class AuthOperatorFunctionController extends BaseController {

	@Autowired
	private IAuthOperatorFunctionService authOperFuncService;

	/***
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryOperFunc")
	@ResponseBody
	public List<AuthOperatorFunction> forCheckTreeForOper(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		List<AuthOperatorFunction> list = authOperFuncService.queryAuthOperatorFunctionByMap(paramMap);
		return list;
	}

	@RequestMapping("/batchInsert")
	@ResponseBody
	public RetObj batchInsert(HttpServletRequest request, String operFuncJsonStr, String operatorId) {
		List<Map> dataList = JSON.parseArray(operFuncJsonStr, Map.class);
		try {
			authOperFuncService.batchInsert(dataList, operatorId);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}

	}
}
