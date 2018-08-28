package com.em.core.auth.home.module.entity;

public class AuthHomeModuleWithBLOBs extends AuthHomeModule {
    private String htmlContent;

    private String paramJson;

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }

    public String getParamJson() {
        return paramJson;
    }

    public void setParamJson(String paramJson) {
        this.paramJson = paramJson == null ? null : paramJson.trim();
    }
}