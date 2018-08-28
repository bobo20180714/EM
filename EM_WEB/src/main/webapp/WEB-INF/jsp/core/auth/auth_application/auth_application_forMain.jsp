<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %><%-- JSP控制标签 --%>

<script type="text/javascript" src="${ctx}/script/core/auth/auth_application/auth_application_tree.js"></script>
<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="权限管理" thdLevel="应用功能管理"/>

	<div class="row">
		<%@include file="auth_application_contextMenus.jsp" %>
		<sys:treeStruct treeCaption="应用功能树" treeId="applicationTree" />
		<div class="col-md-9" id="appRightFrame"></div>
	</div>
</div>
