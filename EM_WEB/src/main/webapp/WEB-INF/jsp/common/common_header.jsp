<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %><%-- ${fns:xxxx}方法标签 --%>
<%@ taglib prefix="bgt" uri="/WEB-INF/tlds/bgt.tld" %><%-- 后台Java控制标签 --%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %><%-- JSP控制标签 --%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %><%-- form元素标签 --%>
<meta charset="utf-8"/>
<title>公司管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${ctx}/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/theme/assets/global/plugins/datatables/media/css/dataTables.bootstrap.css"/>


<link href="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/global/plugins/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="${ctx}/theme/assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->

<link href="${ctx}/theme/assets/admin/${layout==null?'layout':layout}/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/admin/${layout==null?'layout':layout}/css/plugins.css" rel="stylesheet" type="text/css"/><!-- 该css用到了较多插件中的css 删除时需要注意 -->

<!-- 改样式入手此处 -->
<link href="${ctx}/theme/assets/admin/${layout==null?'layout':layout}/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/admin/${layout==null?'layout':layout}/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>

<link href="${ctx}/theme/assets/admin/${layout==null?'layout':layout}/css/custom.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" href="${ctx}/theme/assets/global/plugins/bootstrap-tabdrop/css/bootstrap-addtabs.css" type="text/css" media="screen" />
<!-- END THEME STYLES -->
<!-- <link rel="shortcut icon" href="favicon.ico"/> -->


<script>
var _projectName_="${ctx}";
//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
var _curWwwPath_=window.document.location.href;
//获取主机地址之后的目录，如： uimcardprj/meun.jsp
var _pathName_=window.document.location.pathname;
var _pos_=_curWwwPath_.indexOf(_pathName_);
//获取主机地址，如： http://localhost:8083
var _localhostPaht_=_curWwwPath_.substring(0,_pos_);
//获取带"/"的项目名，如：/uimcardprj
var _rootPath_=_localhostPaht_+"/";
if(_projectName_!=''){
	_rootPath_=_localhostPaht_+_projectName_+"/";
}
</script>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${ctx}/theme/assets/global/plugins/respond.min.js"></script>
<script src="${ctx}/theme/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${ctx}/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script><!-- 1.11.2版 -->
<script src="${ctx}/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctx}/theme/assets/global/plugins/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery.blockui.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="${ctx}/theme/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/admin/${layout==null?'layout':layout}/scripts/layout.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/admin/${layout==null?'layout':layout}/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>

<!-- SELF JS -->
<script src="${ctx}/theme/assets/global/plugins/bootbox/bootbox.js" type="text/javascript"></script>   <!-- http://bootboxjs.com/documentation.html -->

<%-- <script src="${ctx}/theme/assets/global/plugins/bootstrap-contextmenu/bootstrap-contextmenu.js"></script> --%>
<%-- 项目中所有上述右键菜单插件应被下面的替换 --%>
<link href="${ctx}/component/jQuery-contextMenu/jquery.contextMenu.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/component/jQuery-contextMenu/jquery.contextMenu.js" type="text/javascript"></script>
<script src="${ctx}/component/jQuery-contextMenu/jquery.ui.position.min.js" type="text/javascript"></script>
<!-- 自己引入的工具放在component中,component中的插件是因为觉得bootstrap不好用,不够用，才引入的，请不要随便引入 -->
<script type="text/javascript" src="${ctx}/component/My97DatePicker/WdatePicker.js"></script>
<!-- 页面dialog\Tip工具 -->
<script type="text/javascript" src="${ctx}/component/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/component/layer/extend/layer.ext.js"></script>
<script type="text/javascript" src="${ctx}/component/pushlet/ajax-pushlet-client.js"></script> 
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/datatables/media/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/datatables/media/js/dataTables.language.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/datatables/media/js/datatables.basicsetting.js" ></script>
<script src="${ctx}/theme/assets/global/plugins/bootstrap-tabdrop/js/bootstrap-addtabs.js" type="text/javascript"></script>


<script type="text/javascript" src="${ctx}/script/common/commonUtils.js"></script>

<%@ include file="zTree_header.jsp"%>

<!-- END PAGE LEVEL SCRIPTS -->
<script>

/* PL.leave(); */

jQuery(document).ready(function() {
   $('#tabs').addtabs({monitor:'.topbar'});
/* 	alert(PL.state);
	if(PL.state != PL.STATE_NULL){
		PL.leave();//退出所有的pushlet监听
	} */
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   QuickSidebar.init() // init quick sidebar
   Tasks.initDashboardWidget();
});
/* $.fn.dataTable.ext.errMode = function(s,h,m){} */
</script>
<!-- END JAVASCRIPTS -->


<!-- form_editor_header.jsp start-->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/theme/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
<!-- END PAGE LEVEL STYLES -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<!-- datepicker对中文支持不是很全面，但是为了兼容过去的写法保留了，现在引入My97 放在common_header.jsp中-->

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
	if (jQuery().datepicker) {
        $('.date-picker').datepicker({
            rtl: Metronic.isRTL(),
            orientation: "left",
            autoclose: true,
            language:  'zh-CN'
        });
        //$('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
    }
});
</script>
<!-- form_editor_header.jsp end-->

<!-- zTree_header.jsp start -->
<!-- 树形目录 -->
<link rel="stylesheet" href="${ctx}/component/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${ctx}/component/zTree/css/zTreeStyle/tree_icon.css" type="text/css">
<script type="text/javascript" src="${ctx}/component/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/component/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/component/zTree/js/jquery.ztree.exedit-3.5.js"></script>
<!-- zTree_header.jsp end -->

