/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.function.entity;

import com.em.common.zTree.ZTreeNode;

/**
 * @ClassName: AcFunctionTreeNode
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月28日下午3:32:25
 */
public class AuthFunctionTreeNode extends ZTreeNode {

	private String funcAction;
	private String paraInfo;

	public String getFuncAction() {
		return funcAction;
	}

	public void setFuncAction(String funcAction) {
		this.funcAction = funcAction;
	}

	public String getParaInfo() {
		return paraInfo;
	}

	public void setParaInfo(String paraInfo) {
		this.paraInfo = paraInfo;
	}

}
