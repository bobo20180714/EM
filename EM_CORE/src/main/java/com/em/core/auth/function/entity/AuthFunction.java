package com.em.core.auth.function.entity;

public class AuthFunction {
    private String funcId;

    private String funcGroupId;

    private String funcName;

    private String funcDesc;

    private String funcAction;

    private String paraInfo;

    private String isCheck;

    private String funcType;

    private String isMenu;

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId == null ? null : funcId.trim();
    }

    public String getFuncGroupId() {
        return funcGroupId;
    }

    public void setFuncGroupId(String funcGroupId) {
        this.funcGroupId = funcGroupId == null ? null : funcGroupId.trim();
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc == null ? null : funcDesc.trim();
    }

    public String getFuncAction() {
        return funcAction;
    }

    public void setFuncAction(String funcAction) {
        this.funcAction = funcAction == null ? null : funcAction.trim();
    }

    public String getParaInfo() {
        return paraInfo;
    }

    public void setParaInfo(String paraInfo) {
        this.paraInfo = paraInfo == null ? null : paraInfo.trim();
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck == null ? null : isCheck.trim();
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType == null ? null : funcType.trim();
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu == null ? null : isMenu.trim();
    }
}