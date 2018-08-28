/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.operator.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.Md5Util;
import com.em.core.auth.operator.entity.AuthOperator;
import com.em.core.auth.operator.service.IAuthOperatorService;

/***
 * 操作员控制
 * 
 * @author yangy 2018年5月21日 下午5:18:21 AuthOperatorController.java
 * @Description
 */
@Controller
@RequestMapping("/authOperator")
public class AuthOperatorController extends BaseController {
	@Autowired
	private IAuthOperatorService authOperatorService;

	@RequestMapping("/forUpdatePassword")
	public String forUpdatePassword(HttpServletRequest request) {
		return "core/auth/auth_operator/auth_operator_forUpdatePassword";
	}

	/**
	 * 
	 * @Title: updatePassword
	 * @author：liuyx
	 * @date：2015年9月15日下午3:15:30
	 * @Description: 修改密码
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public RetObj updatePassword(HttpServletRequest request) {
		AuthOperator operator = null;
		try {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String psw = Md5Util.string2MD5(oldPassword);
			String newpassword_md5 = Md5Util.string2MD5(newPassword);
			operator = (AuthOperator) request.getSession().getAttribute(LOGIN_IN_OPERATOR_SESSION);
			if (operator != null) {
				String operatorId = operator.getOperatorId();
				operator = authOperatorService.getAuthOperator(operatorId);
				if (!StringUtils.equals(operator.getPassword(), psw)) {
					return new RetObj(false, "修改失败，旧密码不正确!");
				} else {
					operator.setOperatorId(operatorId);
					operator.setPassword(newpassword_md5);
					authOperatorService.updateAuthOperator(operator);
				}
			} else {
				return new RetObj(false, "修改失败，该用户不存在！");
			}
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}

			return new RetObj(false, request);
		}

	}

}
