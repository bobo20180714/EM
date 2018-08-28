/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.position.controller;

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
import com.em.core.org.position.entity.OrgPosition;
import com.em.core.org.position.service.IOrgPositionService;

/**
 * @ClassName: OmPositionController
 * @Description: 岗位
 * @author: liuyx
 * @date: 2015年9月17日下午3:30:05
 */
@Controller
@RequestMapping("/orgPosition")
public class OrgPositionController extends BaseController {
	@Autowired
	private IOrgPositionService orgPositionService;

	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String id, String orgId, String posiId, String level, String orgname) {
		ModelAndView mv = new ModelAndView();
		OrgPosition oPosition  = new OrgPosition();
		if (!StringUtils.isEmpty(id)) {
			// 修改数据			
			oPosition = orgPositionService.get(id);
		}
		if (!StringUtils.isEmpty(posiId)) {
			oPosition.setParentPosiId(posiId);
		}
		if (!StringUtils.isEmpty(orgId)) {
			oPosition.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(level)) {
			oPosition.setPosiLevel(Short.valueOf(level));
		}
		if (!StringUtils.isEmpty(orgname)) {
			oPosition.setOrgName(orgname);
		}
		mv.addObject("record", oPosition);
		mv.setViewName("core/org/org_position/org_position_forUpdate");
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
	public RetObj update(OrgPosition oPosition, HttpServletRequest request) {
		String now = DateUtils.getCurrentDate();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("POSI_CODE", oPosition.getPosiCode());
		try {
			if (StringUtils.isEmpty(oPosition.getPositionId())) {
				// 新增

				// 此处应有检测重复相关代码

				List posiList = orgPositionService.query(paramMap);
				if (!CollectionUtils.isEmpty(posiList)) {
					return new RetObj(false, "操作失败，岗位代码重复！");
				}
				oPosition.setPositionId(StringUtils.uniqueKey36());
				oPosition.setCreateTime(now);
				orgPositionService.addOrgPosition(oPosition);
			} else {
				// 修改
				paramMap.put("NOT_POSITION_ID", oPosition.getPositionId());
				List posiList = orgPositionService.query(paramMap);
				if (!CollectionUtils.isEmpty(posiList)) {
					return new RetObj(false, "操作失败，岗位代码重复！");
				}
				oPosition.setLastUpdateTime(now);
				oPosition.setUpdator(getEmpIdFromRequest(request));
				orgPositionService.updateOrgPosition(oPosition);
			}
			return new RetObj(true, request, oPosition.getPositionId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}

	}
	/**
	 * XXX
	 * @Title delete
	 * @date  2018年5月24日下午5:54:35   
	 * @Description: TODO
	 * @param  @param request
	 * @param  @param id
	 * @param  @return    
	 * @return RetObj 
	 * @author bo
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request, String id) {
		try {
			orgPositionService.delOrgPosition(id);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}

		}
		return new RetObj(false, request);
	}
}
