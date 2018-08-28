/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.employee.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.em.common.consts.CommonConsts;
import com.em.common.consts.GlobMessageKeys;
import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.DateUtils;
import com.em.common.utils.HttpUtils;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.operator.entity.AuthOperator;
import com.em.core.auth.operator.service.IAuthOperatorService;
import com.em.core.org.employee.entity.OrgEmployee;
import com.em.core.org.employee.service.IOrgEmployeeService;

/*****
 * 
 * @author yangy
 * 2018年5月24日 下午3:37:20
 * OrgEmployeeController.java
 * @Description
 */
@Controller
@RequestMapping("/orgEmployee")
public class OrgEmployeeController extends BaseController {
	@Autowired
	private IOrgEmployeeService orgEmployeeService;
	@Autowired
	private IAuthOperatorService authOperatorService;

	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		return orgEmployeeService.queryTreeNodes(paramMap);
	}

	/**
	 * XXX
	 * @Title: queryOrgEmpTreeNodes
	 * @author：liuyx
	 * @date：2015年11月19日上午8:40:35
	 * @Description: 查询主机构人员树（不包含岗位）供选择树
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryOrgEmpTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryOrgEmpTreeNodes(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		// 任何人选人只能看到自己可以管理的机构下的人员
		// 【可管理机构】本段代码涉及可管理机构，暂时注释，后续重新考虑优化设计,参见OmOrganizationController中的queryTreeNodes
		// paramMap.put("orgIdList",getAttributeFromEmpSession(request,
		// "ORG_ID_LIST") );
		return orgEmployeeService.queryOrgEmpTreeNodes(paramMap);
	}

	/**
	 * 
	 * @Title: queryMultiOrgEmpTreeNodes
	 * @author：liuyx
	 * @date：2016年10月10日
	 * @Description: 查询多机构关系人员树（不包含岗位、root机构节点和 与root直接关联的人员）供选择树
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryMultiOrgEmpTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryMultiOrgEmpTreeNodes(HttpServletRequest request, String rootOrgId) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		return orgEmployeeService.queryMultiOrgEmpTreeNodes(paramMap);
	}

	/**
	 * 
	 * @Title: forCheckTree
	 * @author：liuyx
	 * @date：2015年9月28日上午11:12:53
	 * @Description: 跳转到选择树
	 * @param request
	 * @return
	 */
	@RequestMapping("/forMultiOrgEmpCheckTree")
	public ModelAndView forMultiOrgEmpCheckTree(HttpServletRequest request, String withOrderModule, String callBackFuncName, String rootOrgId, String selectedEmpWithPidJson) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("rootOrgId", rootOrgId);
		mv.addObject("withOrderModule", withOrderModule);
		mv.addObject("callBackFuncName", callBackFuncName);
		mv.addObject("selectedEmpWithPidJson", selectedEmpWithPidJson);
		mv.setViewName("core/org/org_employee/org_employee_forCheckTree");
		return mv;

	}

	/****
	 * 关联人员树生成页面
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/forCheckTree")
	public ModelAndView forCheckTree(HttpServletRequest request) {
		Map<String, Object> paramMap = HttpUtils.getRequestMap(request);
		ModelAndView mv = new ModelAndView();
		mv.addObject("record", paramMap);
		mv.setViewName("core/org/org_employee/org_employee_forCheckTree");
		return mv;
	}

	/**
	 * XXX
	 * @Title: forUpdate
	 * @author：liuyx
	 * @date：2015年9月20日下午12:11:02
	 * @Description: 进入修改/新增页面
	 * @param request
	 * @param id
	 *            修改则必须
	 * @param orgId
	 *            新增则必须
	 * @param posiId
	 *            新增则必须
	 * @return
	 */
	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String id, String orgId, String posiId) {
		ModelAndView mv = new ModelAndView();		
		OrgEmployee oe = new OrgEmployee();
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			oe = orgEmployeeService.getOrgEmployee(id);

			String operatorId = StringUtils.getTrimStr(oe.getOperatorId());
			AuthOperator oper = authOperatorService.getAuthOperator(operatorId);
			mv.addObject("oper", oper);

		} else {
			// 新增数据
			if (!StringUtils.isEmpty(posiId)) {
				oe.setPosition(posiId);
			}
			if (!StringUtils.isEmpty(orgId)) {
				oe.setOrgId(orgId);
			}
		}
		mv.addObject("record", oe);
		mv.setViewName("core/org/org_employee/org_employee_forUpdate");
		return mv;
	}

	@RequestMapping("/forDetail")
	public ModelAndView forDetail(HttpServletRequest request, String id, String orgId, String posiId) {

		ModelAndView mv = new ModelAndView();		
		OrgEmployee oe = new OrgEmployee();
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			oe = orgEmployeeService.getOrgEmployee(id);

			String operatorId = StringUtils.getTrimStr(oe.getOperatorId());
			AuthOperator oper = authOperatorService.getAuthOperator(operatorId);
			mv.addObject("oper", oper);

		} else {
			// 新增数据
			if (!StringUtils.isEmpty(posiId)) {
				oe.setPosition(posiId);
			}
			if (!StringUtils.isEmpty(orgId)) {
				oe.setOrgId(orgId);
			}
		}
		mv.addObject("record", oe);
		mv.setViewName("core/org/org_employee/org_employee_forDetail");
		return mv;
	}

	/**
	 * XXX
	 * @Title: update
	 * @author：liuyx
	 * @date：2015年9月15日下午3:15:30
	 * @Description: 新增/修改 数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(OrgEmployee orgEmployee) {
		String now = DateUtils.getCurrentDate();
		try {
			if (StringUtils.isEmpty(orgEmployee.getEmpId())) {
				/*
				 * if(StringUtils.isEmpty(record.get("PASSWORD"))) {
				 * record.put("PASSWORD","111222"); }
				 */
				// 新增
				int ret = orgEmployeeService.insert(orgEmployee);
				if (ret == CommonConsts.DATA_REPEAT_ERR) {
					return new RetObj(false, GlobMessageKeys.OPER_USER_ID_REPEAT_ERR, null);
				}
			} else {
				// 修改
				orgEmployee.setLastUpdateTime(now);
				int ret = orgEmployeeService.update(orgEmployee);
				if (ret == CommonConsts.DATA_REPEAT_ERR) {
					return new RetObj(false, GlobMessageKeys.OPER_USER_ID_REPEAT_ERR, null);
				}
			}
			return new RetObj(true, "操作成功", orgEmployee.getEmpId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}

	}

	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request, String id, String pId) {
		try {
			// pId：暂只支持一人一岗，一人多岗需要判断是只删除关系还是完全删除,根据是否主岗。
			orgEmployeeService.delOrgEmployee(id);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}

		}
		return new RetObj(false, request);
	}

	/** 用户中心 相关 start **/
	/**
	 * 
	 * @Title: forUpdate
	 * @author：liuyx
	 * @date：2015年9月20日下午12:11:02
	 * @Description: 进入修改/新增页面
	 * @param request
	 * @param id
	 *            修改则必须
	 * @param orgId
	 *            新增则必须
	 * @param posiId
	 *            新增则必须
	 * @return
	 */
	@RequestMapping("/forUserCenter")
	public ModelAndView forUserCenter(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String id = getEmpIdFromRequest(request);

		// 修改数据
		OrgEmployee orgEmployee = orgEmployeeService.get(id);
		
		String operatorId = orgEmployee.getOperatorId();
		AuthOperator oper = authOperatorService.getAuthOperator(operatorId);
		mv.addObject("oper", oper);
		mv.addObject("record", orgEmployee);
		mv.setViewName("core/org/org_employee/org_employee_forUserCenter");
		return mv;
	}
	/** 用户中心 相关 end **/
	
}
