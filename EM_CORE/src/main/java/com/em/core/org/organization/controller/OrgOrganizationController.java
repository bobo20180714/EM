/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.organization.controller;

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

import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.DateUtils;
import com.em.common.utils.HttpUtils;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.org.organization.entity.OrgOrganization;
import com.em.core.org.organization.service.IOrgOrganizationService;

/**
 * @ClassName: OmOrganizationController
 * @Description: 机构
 * @author: liuyx
 * @date: 2015年9月2日上午9:07:10
 */
@Controller
@RequestMapping("/orgOrganization")
public class OrgOrganizationController extends BaseController {

	@Autowired
	private IOrgOrganizationService orgOrganizationService;

	@RequestMapping("/forMain")
	public String forMain() {
		return "core/org/org_organization/org_organization_forMain";
	}

	/*
	 * @RequestMapping("/queryTreeNodes")
	 * 
	 * @ResponseBody public List<ZTreeNode> queryTreeNodes(String pId) { Map
	 * paraMap = new HashMap(); paraMap.put("pId", pId); return
	 * orgOrganizationService.queryChildrenTreeNodes(paraMap); }
	 */
	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String id, String pId, String level, String pname) {
		/*
		 * OmOrganization org = new OmOrganization(); OmOrganization porg = new
		 * OmOrganization(); org.setOrgid(id); porg.setOrgid(pid); try {
		 * List<OmEmployee>emps= omEmployeeService.query(org);
		 * model.addAttribute("emps", emps); List<OmEmployee>pemps=
		 * omEmployeeService.query(porg); model.addAttribute("pemps", pemps);
		 * org = (OmOrganization) orgOrganizationService.get(org);
		 * model.addAttribute("t", org); model.addAttribute("parentname",
		 * parentname); List<XtDict> dictList =
		 * xtDictService.querySysDictByDictcode("JGZT","","","1");
		 * model.addAttribute("dictList", dictList); } catch (Exception e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * model.addAttribute("pid", id);
		 */
		ModelAndView mv = new ModelAndView();
		OrgOrganization orgOrganization = new OrgOrganization();
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			orgOrganization = orgOrganizationService.get(id);

		}
		if (!StringUtils.isEmpty(pId)) {
			orgOrganization.setParentOrgId(pId);
		}
		if (!StringUtils.isEmpty(level)) {
			orgOrganization.setOrgLevel(Short.valueOf(level));
		}
		if (!StringUtils.isEmpty(pname)) {
			orgOrganization.setParentOrgName(pname);
		}
		mv.addObject("record", orgOrganization);
		mv.setViewName("core/org/org_organization/org_organization_forUpdate");
		return mv;
	}

	/**
	 * 
	 * @Title: update
	 * @author：liuyx
	 * @date：2015年9月15日下午3:15:30
	 * @Description: 新增/修改 数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(OrgOrganization orgOrganization, HttpServletRequest request) {
		String now = DateUtils.getCurrentDate();
		try {
			if (StringUtils.isEmpty(orgOrganization.getOrgId())) {
				// 新增
				// 此处应有检测重复相关代码
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("ORG_CODE", orgOrganization.getOrgCode());
				List<OrgOrganization> orgList = orgOrganizationService.query(paramMap);
				if (!CollectionUtils.isEmpty(orgList)) {
					return new RetObj(false, "操作失败，机构代码重复！");
				}
				orgOrganization.setOrgId(StringUtils.uniqueKey36());
				orgOrganization.setCreateTime(now);
				orgOrganizationService.addOrgOrganization(orgOrganization);
			} else {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("ORG_CODE", orgOrganization.getOrgCode());
				paramMap.put("NOT_ORG_ID", orgOrganization.getOrgId());
				List<OrgOrganization> orgList = orgOrganizationService.query(paramMap);
				if (!CollectionUtils.isEmpty(orgList)) {
					return new RetObj(false, "操作失败，机构代码重复！");
				}
				// 修改
				orgOrganization.setLastUpdateTime(now);
				orgOrganization.setUpdator(getEmpIdFromRequest(request));
				orgOrganizationService.updateOrgOrganization(orgOrganization);
			}
			return new RetObj(true, request, orgOrganization.getOrgId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}

	}

	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request, String id) {
		try {
			orgOrganizationService.delOrgOrganization(id);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}

		}
		return new RetObj(false, request);
	}

	/**
	 * 
	 * @Title: forCheckTree
	 * @author：liuyx
	 * @date：2015年11月18日15:51:19
	 * @Description: 跳转到选择树
	 * @param request
	 * @return
	 */
	@RequestMapping("/forCheckTree")
	public ModelAndView forCheckTree(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		mv.addObject("record", paramMap);
		mv.setViewName("core/org/org_organization/org_organization_forCheckTree");
		return mv;
	}

	/**
	 * 
	 * @Title: queryTreeNodes
	 * @author：liuyx
	 * @date：2015年12月24日下午3:34:19
	 * @Description: 获取机构岗位人员节点,旧版，待改进
	 * @param request
	 *            有init则查出所有可管理的节点 有pId则只查出pId下的所有节点 init和pId不可同时存在
	 * @return
	 */
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		/*
		 * if(paramMap.get("pId")==null) { paramMap.put("pId",
		 * getAttributeFromEmpSession(request, "ORG_ID")); }
		 */

		/*
		 * 【可管理机构】本段代码涉及可管理机构，暂时注释，后续重新考虑优化设计，现会导致自己新增的机构自己看不到，
		 * 因为自己还没有分配管理自己刚才新增的机构的权限 if(paramMap.get("init")!=null) {
		 * paramMap.put("orgIdList",getAttributeFromEmpSession(request,
		 * "ORG_ID_LIST") ); }
		 */
		return orgOrganizationService.queryChildrenTreeNodes(paramMap);
	}

	/**
	 * 
	 * @Title: adjust
	 * @author：liuyx
	 * @date：2015年12月29日上午10:49:14
	 * @Description: 组织机构结构调整
	 * @param request
	 * @param id
	 * @param type
	 * @param fromId
	 *            原父节点id
	 * @param fromType
	 *            原父节点类型
	 * @param toId
	 *            目标父节点id
	 * @param toType
	 *            目标父节点类型
	 * @return
	 */
	@RequestMapping("/adjustByTree")
	@ResponseBody
	public RetObj adjustByTree(HttpServletRequest request, String id, String type, String fromId, String fromType, String toId, String toType, String isCopy) {
		try {

			orgOrganizationService.adjust(id, type, fromId, fromType, toId, toType, isCopy);

			return new RetObj(true);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false);
		}

	}

	/**
	 * 
	 * @Title: forOnlyOrgNodesCheckTree
	 * @author：刘宇祥
	 * @date：2016年10月18日下午6:30:40
	 * @Description: 跳转到机构选择选择树
	 * @param request
	 * @param withOrderModule
	 * @param callBackFuncName
	 * @param rootOrgId
	 * @param selectedOrgIdJson
	 * @param orgLevel
	 * @return
	 */
	@RequestMapping("/forOnlyOrgNodesCheckTree")
	public ModelAndView forOnlyOrgNodesCheckTree(HttpServletRequest request, String withOrderModule, String callBackFuncName, String rootOrgId, String selectedOrgIdJson, String orgLevel) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("rootOrgId", rootOrgId);
		mv.addObject("withOrderModule", withOrderModule);
		mv.addObject("callBackFuncName", callBackFuncName);
		mv.addObject("selectedOrgIdJson", selectedOrgIdJson);
		mv.addObject("orgLevel", orgLevel);
		mv.setViewName("core/org/org_organization/org_organization_forCheckTree");
		return mv;
	}

	/**
	 * 
	 * @Title: queryOnlyOrgTreeNodes
	 * @author：刘宇祥
	 * @date：2016年10月18日下午5:20:54
	 * @Description: 仅查询除root节点外的机构节点树数据
	 * @param request
	 *            rootOrgId可选，如果传入则只查询相应根节点下的所有机构节点 orgLevel可选，最末级机构节点的level最大值
	 * @return
	 */
	@RequestMapping("/queryOnlyOrgTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryOnlyOrgTreeNodes(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		return orgOrganizationService.queryOnlyOrgTreeNodes(paramMap);
	}
}
