<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--<%@ include file="../../../common/zTree_header.jsp"%>--%>
<%@ include file="../../../common/form_editor_header.jsp"%>

<script type="text/javascript" src="${ctx}/script/core/org/org_organization/org_organization_tree.js"></script>
<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="组织管理" thdLevel="机构人员"/>
	<div class="row">
		
		<sys:treeStruct treeCaption="机构人员树" treeId="orgTree_Org" />
		
		<div class="col-md-9" id="orgRightFrame"></div>
		
	</div>
</div>	
<%@include file="org_organization_contextMenus.jsp" %>