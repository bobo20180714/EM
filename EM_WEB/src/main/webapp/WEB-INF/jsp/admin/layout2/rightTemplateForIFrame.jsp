<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh"> <!--<![endif]-->
<head>
<%@ include file="../../common/common_header.jsp"%>
<script type="text/javascript" src="${ctx}/component/jQuery-resize/jquery.ba-resize.min.js"></script>
<title>后台页面</title>
<script type="text/javascript">
	var loadingDivForAjaxModal = '<div class="modal-dialog modal-lg"><div class="modal-content"><div class="modal-body"><img src="${ctx}/theme/assets/admin/layout/img/loading-spinner-grey.gif" alt="" class="loading"> <span> &nbsp;&nbsp;Loading... </span></div></div></div>';
	$(function() {
		$('body').resize(function(){
			var iframe=window.frameElement;
			var con = iframe.contentWindow;
			var height = con.document.body.clientHeight;
			if(height==0){
				height = $(document).height();
			}
			$(iframe).height(height);
		});
		/* bootstrap v3写法 */
		$("#ajax_md,#ajax_lg").on("hidden.bs.modal", function() {
			$(this).html(loadingDivForAjaxModal);
		});
		$('#main_content').load(top.Addtabs.tempIFrameUrl);
		parent.Metronic.unblockUI();
	});

	/* $("body").bind('resize',function(){
	 alert('change');
	 }); */
</script>
<style type="text/css">
	
body {
    background-color: #FDFDFD;
}
</style>
</head>
<body class="page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid">

<div class="clearfix">
</div>


<div class="page-container">
	<div id="main_content" class="page-content-wrapper">
	</div>
</div>

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

</body>
</html>
