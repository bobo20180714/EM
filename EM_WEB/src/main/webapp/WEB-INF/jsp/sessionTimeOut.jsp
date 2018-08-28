<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8"/>
<title>开发框架</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${ctx}/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<%--2016年6月12日  刘宇祥 删除 <link href="${ctx}/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/> --%>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>


<%--2016年6月12日  刘宇祥 删除  <link href="${ctx}/theme/assets/global/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/> --%>
<link href="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/global/plugins/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
<%-- 2016年6月12日 刘宇祥 删除 <link href="${ctx}/theme/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/> --%>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="${ctx}/theme/assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->

<link href="${ctx}/theme/assets/admin/layout/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/admin/layout/css/plugins.css" rel="stylesheet" type="text/css"/><!-- 该css用到了较多插件中的css 删除时需要注意 -->

<!-- 改样式入手此处 -->
<link href="${ctx}/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>

<link href="${ctx}/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>


<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${ctx}/theme/assets/global/plugins/respond.min.js"></script>
<script src="${ctx}/theme/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${ctx}/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script><!-- 1.11.2版 -->
<script src="${ctx}/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctx}/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>

<script src="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>

<!-- SELF JS -->
<script src="${ctx}/theme/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>   <!-- http://bootboxjs.com/documentation.html -->
<script src="${ctx}/theme/assets/global/plugins/bootstrap-contextmenu/bootstrap-contextmenu.js"></script>

<%--
经过试验 这两段会和bootbox冲突
 <script src="${ctx}/theme/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${ctx}/theme/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script> --%>

<!-- 自己引入的工具放在component中,component中的插件是因为觉得bootstrap不好用,不够用，才引入的，请不要随便引入 -->
<!-- 页面dialog\Tip工具 -->
<script type="text/javascript" src="${ctx}/script/common/commonUtils.js"></script>
<title>会话超时</title>
<style>
.bgray{
	background-color:#3b6176 !important;
	z-index:1000;
}
.bgray .modal-body{
	background-color:#f2dede;
	border:solid 1px #ebccd1;
}
.bgray h2{
	font-size:16px;
	z-index:1000;
	margin-bottom:20px !important;
	text-align:center;
}
.bgray .modal-content{
	border-radius:0;
}
.bgray .btn{
	border-radius:0;
}
<%-- <%@ include file="public/header.jsp"%> --%>
</head>
</style>
<body class="bgray">

	<script type="text/javascript">
	function submitLoginForm(name,pwd){
		var use
		$.ajax({
			type: "post",
			async:true,
			global:false,
			url: "loginCheck.do",
			dataType:"json",
			data:{"userId":name,"password":pwd,"remember":true},
			success: function(data) {
				//BaseUtils.hideWaitMsg();
				
				if(data.flag){
					//BaseUtils.showWaitMsg();
					window.location.href="main.do";
					if(window.top != window && document.referrer){
						 top.location.href = '${ctx }/main.do';
					}else{
						window.location = '${ctx }/main.do';
					}
				}else{
					
					if(window.top != window && document.referrer){
						  BaseUtils.alertWithFunc('您的会话已超时，请重新登录！',function(){
							  top.location = '${ctx }/login.do';
						  });
					}else{
						BaseUtils.alertWithFunc('您的会话已超时，请重新登录！',function(){
							window.location = '${ctx }/login.do';
						});
					}
				}
				
			}
		});
	}
	var cookie = $.cookie('user'); 
	var username='';
	var pass='';
	if(cookie !='' && cookie != undefined){
		username=cookie.split('-')[0];
		pass=cookie.split('-')[1];
		submitLoginForm(username,pass);
	}else{
		/* BaseUtils.alertWithFunc('您的会话已超时，请重新登录！',function(){
			window.location = '${ctx }/login.do';
		}); */
		
		if(window.top != window && document.referrer){
			  BaseUtils.alertWithFunc('您的会话已超时，请重新登录！',function(){
				  top.location.href = '${ctx }/login.do';
			  });
		}else{
			BaseUtils.alertWithFunc('您的会话已超时，请重新登录！',function(){
				window.location = '${ctx }/login.do';
			});
		}
		
	}

	
	
	</script>
</body>
</html>




