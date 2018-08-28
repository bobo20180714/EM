package com.em.core.auth.menu.custom.entity;

public class AuthMenuCustom {
	private String operatorId;
	private String menuOperatorCustomCode;
	private String menuIds;
	private String isUse;
	private String orgMenuOperatorCustomCode;
	private String isCreate;

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getMenuOperatorCustomCode() {
		return menuOperatorCustomCode;
	}

	public void setMenuOperatorCustomCode(String menuOperatorCustomCode) {
		this.menuOperatorCustomCode = menuOperatorCustomCode;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getOrgMenuOperatorCustomCode() {
		return orgMenuOperatorCustomCode;
	}
	

	public String getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(String isCreate) {
		this.isCreate = isCreate;
	}

	public void setOrgMenuOperatorCustomCode(String orgMenuOperatorCustomCode) {
		this.orgMenuOperatorCustomCode = orgMenuOperatorCustomCode;
	}

}