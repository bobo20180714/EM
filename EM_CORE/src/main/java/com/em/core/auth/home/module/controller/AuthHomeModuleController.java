/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.home.module.controller;

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
import com.em.common.page.PageView;
import com.em.common.retobj.RetObj;
import com.em.common.utils.HttpUtils;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.home.module.entity.AuthHomeModule;
import com.em.core.auth.home.module.service.IAuthHomeModuleService;

/**
 * @ClassName: AcHomeModuleController
 * @Description: 首页模块管理
 * @author: liuyx
 * @date: 2015年12月2日09:52:05
 */
@Controller
@RequestMapping("/authHomeModule")
public class AuthHomeModuleController extends BaseController {

	@Autowired
	private IAuthHomeModuleService authHomeModuleService;

	//XXX
	@RequestMapping("/getPageData")
	@ResponseBody
	public PageView<AuthHomeModule> getPageData(HttpServletRequest request) {
		PageView<AuthHomeModule> pageView = new PageView<AuthHomeModule>(request);
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		try {
			pageView = authHomeModuleService.queryPage(pageView, record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}

	//XXX
	@RequestMapping("/forQueryPage")
	public String forQueryPage() {
		return "core/auth/auth_home_module/auth_home_module_forQueryPage";
	}

	//XXX
	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(String id, String appId) {
		ModelAndView mv = new ModelAndView();
		AuthHomeModule authHomeModule = new AuthHomeModule();
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			authHomeModule = authHomeModuleService.getAuthHomeModule(id);

		}
		if (!StringUtils.isEmpty(appId)) {
			authHomeModule.setAppId(appId);
		}
		mv.addObject("record", authHomeModule);
		mv.setViewName("core/auth/auth_home_module/auth_home_module_forUpdate");
		return mv;
	}

	//XXX 
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(AuthHomeModule authHomeModule) {
		try {
			if (StringUtils.isEmpty(authHomeModule.getHomeModuleId())) {
				// 新增
				authHomeModule.setHomeModuleId(StringUtils.uniqueKey36());
				authHomeModuleService.addAuthHomeModule(authHomeModule);
			} else {
				// 修改
				authHomeModuleService.updateAuthHomeModule(authHomeModule);
			}
			return new RetObj(true, "操作成功", authHomeModule.getHomeModuleId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}
	}

	//XXX:  没用到
	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request, String id) {
		try {
			authHomeModuleService.delAuthHomeModule(id);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}

		}
		return new RetObj(false, request);
	}

	/**
	 * XXX
	 * @Title: 批量删除
	 * @author：yuqing
	 * @Description: 字典类型批量删除数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public RetObj batchDelete(HttpServletRequest request, String ids) {
		try {
			authHomeModuleService.batchDelete(StringUtils.getStrJoinQuotes(ids));
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	/**
	 * XXX
	 * @param ROLE_ID
	 * @param APP_ID
	 * @return
	 */
	@RequestMapping("/forCheckTree")
	public ModelAndView forCheckTree(String ROLE_ID, String APP_ID) {
		ModelAndView mv = new ModelAndView();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("ROLE_ID", ROLE_ID);
		paramMap.put("APP_ID", APP_ID);
		mv.addObject("record", paramMap);
		mv.setViewName("core/auth/auth_home_module/auth_home_module_forCheckTree");
		return mv;
	}

	/****
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryNodesForRole")
	@ResponseBody
	public List<ZTreeNode> queryNodesForRole(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		return authHomeModuleService.queryNodesForRole(paramMap);
	}

	/***
	 * XXX
	 * @param request
	 * @param dataJsonStr
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/batchInsertRoleHomeModule")
	@ResponseBody
	public RetObj batchInsertRoleHomeModule(HttpServletRequest request, String dataJsonStr, String roleId) {
		List<Map> dataList = JSON.parseArray(dataJsonStr, Map.class);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("ROLE_ID", roleId);
		try {
			authHomeModuleService.batchInsertRoleHomeModule(dataList, paramMap);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	@RequestMapping("/batchInsertOperHomeModule")
	@ResponseBody
	public RetObj batchInsertOperHomeModule(HttpServletRequest request, String dataJsonStr, String operatorId) {
		List<Map> dataList = JSON.parseArray(dataJsonStr, Map.class);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("OPERATOR_ID", operatorId);
		try {
			authHomeModuleService.batchInsertOperHomeModule(dataList, paramMap);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}
}
