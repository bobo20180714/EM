/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.menu.custom.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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
import com.em.core.auth.menu.custom.entity.AuthMenuCustom;
import com.em.core.auth.menu.custom.service.IAuthMenuCustomService;
import com.em.core.auth.menu.service.IAuthMenuService;

/**
 * 
 * @author yangy 2017年3月20日 下午4:55:50 AcCustomMenuController
 * @Description 自定义菜单
 */
@Controller
@RequestMapping("/authCustomMenu")
public class AuthMenuCustomController extends BaseController {

	@Autowired
	private IAuthMenuCustomService authCustomMenuService;

	@Autowired
	private IAuthMenuService authMenuService;

	/**
	 * 主要展示页面
	 * 
	 * @return
	 */
	@RequestMapping("/forMain")
	public String forMain(HttpServletRequest request) {
		Map record = new HashMap();
		record.put("OPERATOR_ID", getOperatorIdFromRequest(request));
		request.setAttribute("record", record);
		return "core/auth/auth_custom_menu/auth_custom_menu_forMain";
	}

	/**
	 * @description 获取当前登录用户的所有菜单（有访问权限的）
	 * @param request
	 * @return List<ZTreeNode>
	 */
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		String menuIds = new String();
		String operatorId = getOperatorIdFromRequest(request);
		paramMap.put("OPERATOR_ID", operatorId);
		if ("superadmin".equals(getUserIdFromRequest(request))) {
			menuIds = null;
		} else {
			List<Map> permList = authCustomMenuService.queryPermitted(paramMap);
			for (int i = 0; i < permList.size(); i++) {
				Map map = permList.get(i);
				String menuId = (String) map.get("MENU_ID");
				if (i != permList.size() - 1) {
					menuIds = menuIds + menuId + ",";
				} else {
					menuIds = menuIds + menuId;
				}

			}
			menuIds = StringUtils.getStrJoinQuotes(menuIds);
		}
		List<ZTreeNode> list = authCustomMenuService.queryPermittedTreeNodes(menuIds);
		return list;
	}

	@RequestMapping("/query")
	@ResponseBody
	public List<AuthMenuCustom> query(AuthMenuCustom menuCustom) {
		return authCustomMenuService.queryAuthMenuCustom(menuCustom);
	}

	@RequestMapping("/getPageData")
	@ResponseBody
	public PageView<AuthMenuCustom> getPageData(HttpServletRequest request) {
		PageView<AuthMenuCustom> pageView = new PageView<AuthMenuCustom>(request);
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		try {
			pageView = authCustomMenuService.queryPage(pageView, record);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String acCustomMenuCode) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		AuthMenuCustom record = null;
		List<AuthMenuCustom> list = null;
		if (StringUtils.isEmpty(acCustomMenuCode)) {
			// 新增用户
			request.setAttribute("isCreate", "Y");
		} else {
			// 修改用户
			// 如果是中文，则进行转码
			acCustomMenuCode = new String(acCustomMenuCode.getBytes("iso8859-1"), "utf-8");
			Map params = new HashMap();
			params.put("MENU_OPERATOR_CUSTOM_CODE", acCustomMenuCode);
			params.put("OPERATOR_ID", getOperatorIdFromRequest(request));
			list = authCustomMenuService.queryAuthMenuCustomByMap(params);
			record = list.get(0);
			request.setAttribute("isCreate", "N");
		}
		record.setOperatorId(getOperatorIdFromRequest(request));
		mv.addObject("record", record);
		mv.setViewName("core/auth/auth_custom_menu/auth_custom_menu_forUpdate");
		return mv;
	}

	/**
	 * 
	 * @author yangy
	 * @Description 编辑自定义菜单基础信息
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(AuthMenuCustom menuCustom) {
		List<AuthMenuCustom> list = null;
		String isCreate = menuCustom.getIsCreate();

		try {
			if (isCreate.equals("Y")) {
				// 新增
				menuCustom.setIsUse("0");
				// 检测名称是否已经存在
				list = authCustomMenuService.queryAuthMenuCustom(menuCustom);
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "分组名称已经存在！");
				}

				authCustomMenuService.addAuthMenuCustom(menuCustom);
			} else {
				// 修改
				list = authCustomMenuService.queryAuthMenuCustom(menuCustom);
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "分组名称已经存在！");
				}
				authCustomMenuService.updateAuthMenuCustom(menuCustom);
			}
			return new RetObj(true, "操作成功", menuCustom.getMenuOperatorCustomCode());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateSelectedMenuIds")
	@ResponseBody
	public RetObj updateSelectedMenuIds(HttpServletRequest request, String userCustomMenuJsonStr) {
		List<Map> dataList = JSON.parseArray(userCustomMenuJsonStr, Map.class);
		Map map = dataList.get(0);
		String menuIds = (String) map.get("MENU_IDS");
		String menuOperatorCustomCode = (String) map.get("MENU_OPERATOR_CUSTOM_CODE");
		Map<String, String> paramMap = new HashMap<String, String>();
		try {
			// params存入operator_id, 存入MENU_OPERATOR_CUSTOM_CODE，存入menu_ids
			paramMap.put("OPERATOR_ID", getOperatorIdFromRequest(request));
			paramMap.put("MENU_IDS", menuIds);
			paramMap.put("MENU_OPERATOR_CUSTOM_CODE", menuOperatorCustomCode);
			authCustomMenuService.updateAuthMenuCustomByMap(paramMap);
			return new RetObj(true, request);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RetObj(false, request);
		}
	}

	/**
	 * @Description 根据operator_id与MENU_OPERATOR_CUSTOM_CODE获取用户选择的menu_ids
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryUserSelectedMenu")
	@ResponseBody
	public List queryUserSelectedMenu(HttpServletRequest request) {
		List returnList = new ArrayList();
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);

		List<AuthMenuCustom> list = authCustomMenuService.queryAuthMenuCustomByMap(paramMap);
		AuthMenuCustom record = list.get(0);
		// 获取MENU_IDS字段
		String menuIds = record.getMenuIds();
		if (StringUtils.isEmpty(menuIds)) {
			return returnList;
		} else {
			// string convert to list
			String[] text = menuIds.split(",");
			for (String str : text) {
				Map temp = new HashMap();
				temp.put("MENU_ID", str);
				returnList.add(temp);
			}
		}

		return returnList;
	}

	@RequestMapping("/batchDelete")
	@ResponseBody
	public RetObj batchDelete(HttpServletRequest request, String ids) {
		String operatorId = getOperatorIdFromRequest(request);
		try {
			// decoupleService.callLocalOrRemoteService("acRoleServiceImpl",
			// "batchDelete",int.class, StringUtils.getStrJoinQuotes(ids));
			authCustomMenuService.batchDelete(StringUtils.getStrJoinQuotes(ids), operatorId);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	@RequestMapping("/genCustomMenu")
	@ResponseBody
	public RetObj genCustomMenu(HttpServletRequest request, String menuOperatorCustom, String isCreate) {
		Map<String, String> paramMap = new HashMap<String, String>();
		try {
			paramMap.put("OPERATOR_ID", getOperatorIdFromRequest(request));
			paramMap.put("MENU_OPERATOR_CUSTOM_CODE", menuOperatorCustom);
			if (StringUtils.equals("Y", isCreate)) {
				paramMap.put("IS_USE", "1");
			} else if (StringUtils.equals("N", isCreate)) {
				paramMap.put("IS_USE", "0");
			}
			authCustomMenuService.updateAuthMenuCustomByMap(paramMap);
			return new RetObj(true, request);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RetObj(false, request);
		}
	}

}
