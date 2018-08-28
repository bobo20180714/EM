package com.em.core.auth.operator.function.entity;

public class AuthOperatorFunction {
	private String authType;

	private String funcId;

	private String operatorId;

	private String appId;

	private String funcGroupId;

	private String startDate;

	private String endDate;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getFuncGroupId() {
		return funcGroupId;
	}

	public void setFuncGroupId(String funcGroupId) {
		this.funcGroupId = funcGroupId == null ? null : funcGroupId.trim();
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate == null ? null : startDate.trim();
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate == null ? null : endDate.trim();
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType == null ? null : authType.trim();
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId == null ? null : funcId.trim();
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId == null ? null : operatorId.trim();
	}
}