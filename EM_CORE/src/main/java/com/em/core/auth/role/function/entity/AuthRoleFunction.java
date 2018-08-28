package com.em.core.auth.role.function.entity;

public class AuthRoleFunction {
    
    private String funcId;

    private String roleId;
    
	private String appId;

    private String funcGroupId;

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

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId == null ? null : funcId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}