/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.application.entity.AuthApplication;
import com.em.core.auth.application.service.IAuthApplicationService;

/*****
 * 应用管理
 * @author yangy
 * 2018年5月17日 下午2:08:00
 * AuthApplicationController.java
 * @Description
 */
@Controller
@RequestMapping("/authApplication")
public class AuthApplicationController extends BaseController {

	@Autowired
	private IAuthApplicationService authApplicationService;

	/***
	 * 应用功能管理主页 XXX
	 * @return
	 */
	@RequestMapping("/forMain")
	public String forMain() {
		return "core/auth/auth_application/auth_application_forMain";
	}

	/****
	 * XXX
	 * @param pId
	 * @return
	 */
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(String pId) {
		return authApplicationService.queryChildrenTreeNodes(pId);
	}

	/****
	 * XXX
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String id) {
		ModelAndView mv = new ModelAndView();
		AuthApplication authApplication = new AuthApplication();
		if (!StringUtils.isEmpty(id)) {
			authApplication = authApplicationService.getAuthApplication(id);
		}
		mv.setViewName("core/auth/auth_application/auth_application_forUpdate");
		mv.addObject("record", authApplication);
		return mv;
	}

	/****
	 * 编辑操作 XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(AuthApplication application) {
		List<AuthApplication> list = null;
		try {
			if (StringUtils.isEmpty(application.getAppId())) {
				// 新增
				list = authApplicationService.queryAuthApplication(application);
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "应用代码已经存在！");
				}
				application.setAppId(StringUtils.uniqueKey36());
				authApplicationService.addAuthApplication(application);
			} else {
				// 修改
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("appCode", application.getAppCode());
				paramMap.put("appId", application.getAppId());
				list = authApplicationService.queryAuthApplicationByMap(paramMap);
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "应用代码已经存在！");
				}
				authApplicationService.updateAuthApplication(application);
			}
			return new RetObj(true, "操作成功", application.getAppId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}
	}

	/****
	 * XXX
	 * 删除应用、功能组、功能
	 * @param request
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id, HttpServletResponse response) {
		int status = 0;
		try {
			if (!StringUtils.isEmpty(id)) {
				status = authApplicationService.delAuthApplication(id);
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
