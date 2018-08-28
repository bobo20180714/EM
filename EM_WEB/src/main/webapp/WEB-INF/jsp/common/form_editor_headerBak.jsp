<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %><%-- ${fns:xxxx}方法标签 --%>
<%@ taglib prefix="bgt" uri="/WEB-INF/tlds/bgt.tld" %><%-- 后台Java控制标签 --%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %><%-- JSP控制标签 --%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %><%-- form元素标签 --%>
<%@ taglib prefix="table" tagdir="/WEB-INF/tags/table" %><%-- form元素标签 --%>

<!-- BEGIN PAGE LEVEL STYLES -->
<%-- <link rel="stylesheet" type="text/css" href="${ctx}/theme/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/theme/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="${ctx}/theme/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/> --%>
<link rel="stylesheet" type="text/css" href="${ctx}/theme/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<!-- END PAGE LEVEL STYLES -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<%-- <script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js">
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/theme/assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script> --%>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${ctx}/theme/assets/admin/pages/scripts/components-pickers.js"></script>
<!-- END PAGE LEVEL PLUGINS -->

<!-- 验证 -->
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/jquery-validation/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
<style>  
<!--  
/*jquery validate 前端验证提示信息的字体颜色.*/  
label.error{margin-left: 10px; color: red;}  
-->  
</style>
<%-- <script type="text/javascript" src="${ctx}/script/common/formValidation.js"></script> --%>
<script type="text/javascript">
$(function(){
	 ComponentsPickers.init();
});
</script>