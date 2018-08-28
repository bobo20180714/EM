<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 4.1.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>欢迎</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/> -->
<link
	href="${ctx}/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx}/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx}/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx}/theme/assets/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<%-- <link href="${ctx}/theme/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/> --%>
<link href="${ctx}/theme/assets/admin/pages/css/login3.css"
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<%-- <link href="${ctx}/theme/assets/admin/layout/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/admin/layout/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/> --%>
<%-- <link href="${ctx}/theme/assets/admin/layout/css/imgValidate.css" rel="stylesheet" type="text/css"/> --%>
<%-- <link href="${ctx}/theme/assets/admin/layout2/css/drag.css" rel="stylesheet" type="text/css"/> --%>
<link href="${ctx}/theme/assets/admin/layout/css/themes/darkblue.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link href="${ctx}/theme/assets/admin/layout/css/custom.css"
	rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->
<!-- <link rel="shortcut icon" href="favicon.ico"/> -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
	<div class="loginBox">

		<!-- BEGIN LOGO <div style="background-color: black;position: absolute; top:50%; left:50%; margin-top:-300px; margin-left: -220px; padding: 20px; width:440px; height:440px; background-color: rgba(0,0,0,0.4); overflow: hidden;">-->

		<!-- END LOGO -->
		<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
		<div class="menu-toggler sidebar-toggler"></div>
		<!-- END SIDEBAR TOGGLER BUTTON -->

		<%-- 
	String name = ""; //用户名
	String passward = ""; //密码
	Cookie[] cookies = request.getCookies();
	if (cookies.length>1) {
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("user")) {
				String[] s=cookies[i].getValue().split("-");
				if(s.length>2){
				name = cookies[i].getValue().split("-")[0];
				passward = cookies[i].getValue().split("-")[1];
				request.setAttribute("userId", name); //存用户名
				request.setAttribute("password", passward); //存密码
				}
			}
		}
	}
--%>
		<!-- BEGIN LOGIN -->
		<div class="logobox text-center">
			<a href="javascript:;"> <img class="logo"
				src="${ctx}/theme/assets/admin/layout/img/logo-gx.png" alt="" /> <img
				class="logoText"
				src="${ctx}/theme/assets/admin/layout/img/logo3.png" alt="" />
			</a>
		</div>
		<div class="content">
			<div class="loginbox">
				<div class="loginContent">
					<div>
						<!-- BEGIN LOGIN FORM -->
						<h4 class="admin">
							<!-- <i class="glyphicon glyphicon-leaf"></i> -->
							<i class="fa fa-coffee"></i> 管理员登录
						</h4>
						<form class="login-form" action="" method="post">
							<div class="alert alert-danger display-hide">
								<button class="close" data-close="alert"></button>
								<span id="dangerMessage">. </span>
							</div>
							<div class="form-group mt-15">
								<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
								<!-- <label class="control-label visible-ie8 visible-ie9">用户名</label> -->
								<div class="input-icon">
									<i class="fa fa-user"></i> <input
										class="form-control placeholder-no-fix" type="text"
										autocomplete="off" placeholder="Username" name="username"
										value="${userId}" />
								</div>
							</div>
							<div class="form-group">
								<!-- <label class="control-label visible-ie8 visible-ie9">密码</label> -->
								<div class="input-icon">
									<i class="fa fa-lock"></i> <input
										class="form-control placeholder-no-fix" type="password"
										autocomplete="off" placeholder="Password" name="password"
										value="${password}" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-icon yzmbox">
									<input class="form-control placeholder-no-fix pull-left yzm"
										type="text" autocomplete="off" placeholder="验证码"
										name="validateCode" /> <img src="${ctx}/validateImg.do"
										onclick="changeValidateCode(this)" title="点击图片刷新验证码"
										class="pull-right" />
									<%-- <img id="captcha_img" alt="点击更换" title="点击更换" style='width:40%;' src="${ctx}/theme/assets/admin/layout/img/captcha.png" class="pull-right"> --%>
								</div>
							</div>
							<div class="form-actions subbox">
								<label class="remember"> <input type="checkbox"
									name="remember" id="remember" /> 记住密码
								</label>
								<button type="submit" class="btn pull-right btn-primary">
									<i class="fa fa-key"></i> 登录 <i
										class="m-icon-swapright m-icon-white"></i>
							</div>
						</form>
					</div>
				</div>
				<!-- <div class="toolbar clearfix">
			<div class="pull-right admin">
				<a href="#" data-target="#signup-box" class="user-signup-link">
					切换到API用户登录界面
					<i class="ace-icon fa fa-arrow-right"></i>
				</a>
			</div>
		</div> -->
			</div>
		</div>
		<!-- END LOGIN FORM -->
		<!-- END LOGIN -->

		<!-- BEGIN COPYRIGHT -->
		<div class="copyright text-center">2018 &copy; **有限公司.</div>
	</div>
	<!-- END COPYRIGHT -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src="${ctx}/theme/assets/global/plugins/respond.min.js"></script>
<script src="${ctx}/theme/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
	<script src="${ctx}/theme/assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="${ctx}/theme/assets/global/plugins/jquery-migrate.min.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="${ctx}/theme/assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/theme/assets/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script src="${ctx}/theme/assets/global/plugins/jquery.cokie.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script
		src="${ctx}/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
		type="text/javascript"></script>
	<%-- <script type="text/javascript" src="${ctx}/theme/assets/global/plugins/select2/select2.min.js"></script> --%>
	<%-- <script src="${ctx}/theme/assets/global/plugins/drag.js" type="text/javascript"></script> --%>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${ctx}/theme/assets/global/scripts/metronic.js"
		type="text/javascript"></script>
	<script src="${ctx}/theme/assets/admin/layout/scripts/layout.js"
		type="text/javascript"></script>
	<script src="${ctx}/theme/assets/admin/layout/scripts/demo.js"
		type="text/javascript"></script>
	<script src="${ctx}/theme/assets/admin/pages/scripts/login.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script type="text/javascript" src="${ctx}/theme/assets/global/scripts/RSA.js"></script>  
    <script type="text/javascript" src="${ctx}/theme/assets/global/scripts/BigInt.js"></script>  
    <script type="text/javascript" src="${ctx}/theme/assets/global/scripts/Barrett.js"></script>  
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core components
			Layout.init(); // init current layout
			Login.init();
			Demo.init();
		});

		//$('#drag').drag();
		function changeValidateCode(obj) {

			var timenow = new Date().getTime();

			obj.src = "${ctx}/validateImg.do?d=" + timenow;
		}
		/*** 
		 *   每次请求需要一个不同的参数，获取当前的时间作为参数，否则可能会返回同样的验证码     
		 *   这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
		 */
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
