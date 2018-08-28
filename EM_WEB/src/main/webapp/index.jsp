<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- BEGIN CORE PLUGINS -->
<script src="${ctx}/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script><!-- 1.11.2版 -->
<script src="${ctx}/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<script type="text/javascript">
	
	jQuery(document).ready(function() {     
		autoLogin();
		});
	
	function autoLogin(){
		var cookie = $.cookie('user'); 
		var username='';
		var pass='';
		var validCode='';
		if(cookie !='' && cookie != undefined){
			username=cookie.split('-')[0];
			pass=cookie.split('-')[1];
			validCode="hasCookie";
			submitLoginForm(username,pass,validCode);
		}else{
			if(parent){
				parent.window.location.href="login.do";
			}else{
				window.location.href="login.do";
			}
		}
	}
	
	function submitLoginForm(name,pwd,validC){
		var use
		$.ajax({
			type: "post",
			async:true,
			global:false,
			url: "loginCheck.do",
			dataType:"json",
			data:{"userId":name,"password":pwd,"remember":true,"validCode":validC},
			success: function(data) {
				//BaseUtils.hideWaitMsg();
				
				if(data.flag){
					//BaseUtils.showWaitMsg();
					window.location.href="main.do";
				}else{
					$.cookie('user',null);
					top.location = '${ctx }/login.do';
				}
				
			}
		});
	}	
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在跳转...</title>
</head>
<%-- <body>
<s:message code="welcome"></s:message>
<br/>
<s:message code="welcome" arguments="刘宇祥"/>
<br/>
欢迎
</body> --%>
</html>