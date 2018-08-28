package com.em.core.org.position.entity;

public class OrgPosition {
    private String positionId;

    private String posiCode;

    private String posiName;

    private Short posiLevel;

    private String parentPosiId;

    private String orgId;

    private String positionSeq;

    private String posiType;

    private String createTime;

    private String lastUpdateTime;

    private String updator;

    private String startDate;

    private String endDate;

    private String status;

    private String isLeaf;

	private String orgName;
    
    private String parentPostName;
    
    public String getParentPostName() {
		return parentPostName;
	}

	public void setParentPostName(String parentPostName) {
		this.parentPostName = parentPostName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }

    public String getPosiCode() {
        return posiCode;
    }

    public void setPosiCode(String posiCode) {
        this.posiCode = posiCode == null ? null : posiCode.trim();
    }

    public String getPosiName() {
        return posiName;
    }

    public void setPosiName(String posiName) {
        this.posiName = posiName == null ? null : posiName.trim();
    }

    public Short getPosiLevel() {
        return posiLevel;
    }

    public void setPosiLevel(Short posiLevel) {
        this.posiLevel = posiLevel;
    }

    public String getParentPosiId() {
        return parentPosiId;
    }

    public void setParentPosiId(String parentPosiId) {
        this.parentPosiId = parentPosiId == null ? null : parentPosiId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getPositionSeq() {
        return positionSeq;
    }

    public void setPositionSeq(String positionSeq) {
        this.positionSeq = positionSeq == null ? null : positionSeq.trim();
    }

    public String getPosiType() {
        return posiType;
    }

    public void setPosiType(String posiType) {
        this.posiType = posiType == null ? null : posiType.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime == null ? null : lastUpdateTime.trim();
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }
}