<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %><%-- ${fns:xxxx}方法标签 --%>
<%@ taglib prefix="bgt" uri="/WEB-INF/tlds/bgt.tld" %><%-- 后台Java控制标签 --%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %><%-- JSP控制标签 --%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %><%-- form元素标签 --%>
<%@ taglib prefix="table" tagdir="/WEB-INF/tags/table" %><%-- form元素标签 --%>
<%
/* 防止修改页自动在IE下缓存 */
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
response.setHeader("Pragma","no-cache"); //HTTP 1.0 
%>
<script type="text/javascript">
$(function(){
	 if(typeof(BaseUtils.getCurrentTabContentObj().attr("tabIdPrefix"))=="undefined"){
		 BaseUtils.getCurrentTabContentObj().attr("tabIdPrefix","${idPrefix}");
	 }
});
</script>

