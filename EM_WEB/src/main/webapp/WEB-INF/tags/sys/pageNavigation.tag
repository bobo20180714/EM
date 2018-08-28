<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ attribute name="firstLevel" type="java.lang.String" description="第一级菜单"%><!--后期改成逗号隔开字符串，格式如:权限管理,应用功能管理 -->
<%@ attribute name="secLevel" type="java.lang.String" description="第二级菜单"%>
<%@ attribute name="thdLevel" type="java.lang.String" description="第三级菜单"%>
<%-- <% 
	list<String> desclist=${name}.split("\\,");

%> --%>

<%-- <div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a href="${ctx}/main.do">${firstLevel}</a>
			<i class="fa fa-angle-right"></i></li>
		<li><a href="#">${secLevel}</a> <c:if test="${not empty thdLevel}"><i class="fa fa-angle-right"></i></c:if></li>
		<c:if test="${not empty thdLevel}">
			<li><a href="#">${thdLevel}</a></li>
		</c:if>
	</ul>
</div> --%>



