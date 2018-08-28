<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh"> <!--<![endif]-->
<head>
<%@ include file="../../common/common_header.jsp"%>
<title>后台管理界面</title>
</head>
<body class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid">
	
<jsp:include page="top.jsp"/>

<div class="clearfix">
</div>


<div class="page-container">
	<jsp:include page="left.jsp"/>
	<div id="main_content" class="page-content-wrapper">
	<jsp:include page="home.jsp"/>
	</div>
	<jsp:include page="rightSiderBar.jsp"/>
</div>

	<jsp:include page="footer.jsp" />

 	<!-- 弹出框模板 -->
	<!-- 模板一：加长模板   id:ajax_lg -->
	<div class="modal fade bs-modal-lg" id="ajax_lg" role="basic" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body">
					<img src="${ctx}/theme/assets/admin/layout/img/loading-spinner-grey.gif" alt="" class="loading"> <span> &nbsp;&nbsp;Loading... </span>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 模板二：中等模板   id:ajax_md -->
	<div class="modal fade" id="ajax_md" role="basic" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<img src="${ctx}/theme/assets/admin/layout/img/loading-spinner-grey.gif" alt="" class="loading"> <span> &nbsp;&nbsp;Loading... </span>
				</div>
			</div>
		</div>
	</div> 
<!-- <div style='background-color:rgba(255,255,255,0.4) !important; width:100%; height:100%;position:fixed;top:0;left:0;''>
</div> -->
</body>
</html>
