<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="../../../common/common_header.jsp"%> --%>
<%--<%@ include file="../../../common/zTree_header.jsp"%>--%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %><%-- JSP控制标签 --%>
<script type="text/javascript" src="${ctx}/script/base/org/org_group/org_group_tree.js"></script>
<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="组织管理" thdLevel="工作组"/>
	<div class="row">
		<%@include file="org_group_contextMenus.jsp" %>
		<sys:treeStruct treeCaption="工作组树" treeId="groupTree" />
		<!-- <div class="col-md-3">  style="BORDER-RIGHT: #999999 1px dashed;height:100%"
			<div id="treeDiv" class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<span class="caption-subject bold uppercase"><i class="fa fa-cogs"></i>工作组树</span>
						<span class="caption-helper"></span>
					</div>
				</div>
				<div class="portlet-body">
					<ul class="ztree" style="overflow:auto;">
						<li id="groupTree"></li>
					</ul>
				</div>
			</div>				
		</div> -->
		<div class="col-md-9" id="groupRightFrame"></div>
	</div>
</div>