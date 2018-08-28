package com.em.core.auth.function.group.entity;

public class AuthFunctionGroup {
    private String funcGroupId;

    private String appId;

    private String funcGroupName;

    private String parentGroup;

    private Integer groupLevel;

    private String funcGroupSeq;

    private String isLeaf;

    public String getFuncGroupId() {
        return funcGroupId;
    }

    public void setFuncGroupId(String funcGroupId) {
        this.funcGroupId = funcGroupId == null ? null : funcGroupId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getFuncGroupName() {
        return funcGroupName;
    }

    public void setFuncGroupName(String funcGroupName) {
        this.funcGroupName = funcGroupName == null ? null : funcGroupName.trim();
    }

    public String getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(String parentGroup) {
        this.parentGroup = parentGroup == null ? null : parentGroup.trim();
    }

    public Integer getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(Integer groupLevel) {
        this.groupLevel = groupLevel;
    }

    public String getFuncGroupSeq() {
        return funcGroupSeq;
    }

    public void setFuncGroupSeq(String funcGroupSeq) {
        this.funcGroupSeq = funcGroupSeq == null ? null : funcGroupSeq.trim();
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }
}