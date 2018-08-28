<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh"> <!--<![endif]-->
<head>
<%@ include file="../../common/common_header.jsp"%>
<title>后台管理界面</title>
<script type="text/javascript">
var loadingDivForAjaxModal = '<div class="modal-dialog modal-lg"><div class="modal-content"><div class="modal-body"><img src="${ctx}/theme/assets/admin/layout/img/loading-spinner-grey.gif" alt="" class="loading"> <span> &nbsp;&nbsp;Loading... </span></div></div></div>';
$(function(){
	/* bootstrap v2写法，打开模态框之前清除之前的内容，如果不清楚则不会重新加载 */
	/* $("#ajax_md").on("hidden", function() {
		alert("ok");
	    $(this).removeData("modal");
	});
	$("#ajax_lg").on("hidden", function() {
		alert("ok");
	    $(this).removeData("modal");
	}); */
	
	/* js方式的写法，和HTML一样，事实上是调用了jquery的load方法
	$("#ajax_lg").modal({
	    remote: "xxx.do"
	}); */
	/* bootstrap v3写法 */
	$("#ajax_md,#ajax_lg").on("hidden.bs.modal", function() {
		$(this).html(loadingDivForAjaxModal);
	});
});

</script>
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

</body>
</html>
