package com.em.core.auth.home.module.entity;

public class AuthHomeModule {
    private String homeModuleId;

    private String moduleType;

    private String moduleName;

    private String appId;

    private String inUse;

    private String code;

    private String dataCol;

    private String dataRow;

    private String dataSizex;

    private String dataSizey;
    
    private String htmlContent;
    
    private String paramJson;

    public String getHomeModuleId() {
        return homeModuleId;
    }

    public void setHomeModuleId(String homeModuleId) {
        this.homeModuleId = homeModuleId == null ? null : homeModuleId.trim();
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getInUse() {
        return inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse == null ? null : inUse.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDataCol() {
        return dataCol;
    }

    public void setDataCol(String dataCol) {
        this.dataCol = dataCol == null ? null : dataCol.trim();
    }

    public String getDataRow() {
        return dataRow;
    }

    public void setDataRow(String dataRow) {
        this.dataRow = dataRow == null ? null : dataRow.trim();
    }

    public String getDataSizex() {
        return dataSizex;
    }

    public void setDataSizex(String dataSizex) {
        this.dataSizex = dataSizex == null ? null : dataSizex.trim();
    }

    public String getDataSizey() {
        return dataSizey;
    }

    public void setDataSizey(String dataSizey) {
        this.dataSizey = dataSizey == null ? null : dataSizey.trim();
    }

	/**
	 * @return the htmlContent
	 */
	public String getHtmlContent() {
		return htmlContent;
	}

	/**
	 * @param htmlContent the htmlContent to set
	 */
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	/**
	 * @return the paramJson
	 */
	public String getParamJson() {
		return paramJson;
	}

	/**
	 * @param paramJson the paramJson to set
	 */
	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}
    
}