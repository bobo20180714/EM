<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %><%-- ${fns:xxxx}方法标签 --%>
<!-- 自定义首页内容 BEGIN -->
<style>
.home-bac{
	background: url(${ctx}/theme/assets/admin/${layout}/img/shouye.png) no-repeat 200px 20px; 
	background-size:265px 340px;
	height:360px;
}
@media (max-width: 768px){
	.home-bac{
		background-position:center 20px;
	}
}
</style>
<script type="text/javascript">
/* $(function(){
	BaseUtils.defaultMaxLayerModule("ifram超时测试","${ctx}/test.do");
}); */
$(function(){
	//var winFeatures = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,"+"width="+screen.width+"px,height="+screen.height;
	//window.open("${ctx}/ntkoDemo/forOfficeDemo.do","",winFeatures);
	//BaseUtils.defaultMaxLayerModule("office测试","${ctx}/ntkoDemo/forOfficeDemo.do");
	//BaseUtils.addTabByFrame('机构人员','sysDict/forQueryPage.do');
	//BaseUtils.addTab('胡乱命名','jsDemo/forMultiOrgCheckTreeUseDemo.do');
});
</script>
<div class="page-content">
	<div id="tabs">
		<ul id="myTab" class="nav nav-tabs" role="tablist">
			<li role="presentation" id="tab_主页" class="active">
			<a href="#home" aria-controls="home" role="tab" data-toggle="tab" style='padding-left:0;overflow: hidden;'>
			<img src="${ctx}/theme/assets/admin/${layout}/img/tab_bac0.png" style='padding:0 20px 0 0; margin-top:-2px;'/>
			 主页</a>
			 
			</li>
		</ul>
		<div id="tab-content" class="tab-content">
		    <!-- <a href="javascript:BaseUtils.addTabByFrame('hahahe','omOrganization/forMain.do');">click me</a> -->
			<%-- <a class="btn" href="${ctx}/testUeditorByModal.do" data-target="#ajax_lg" data-toggle="modal"> 打开modal</a> --%>
			<div class="tab-pane active home-bac" id="home" role="tabpanel">
				
				<%-- <div class=" tabPage" >
					<div class="row">
				<!-- <div class=" tabPage" style='height:800px; background-image: url(${ctx}/theme/assets/admin/${layout}/img/shouye.png); background-size:cover; backgrond-attachment:fixed; background-position:center top;'>-->
						<img class="img-responsive col-xs-5 col-sm-3 col-md-3 col-lg-2" style='margin-top:30px;margin-bottom:10px; margin-left:30px;' src="${ctx}/theme/assets/admin/${layout}/img/welcome.png"/>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-xs-2 col-sm-3 col-md-3" ></div>
						<img class="img-responsive  col-xs-8 col-sm-6 col-md-5" style='margin-top:20px;' src="${ctx}/theme/assets/admin/${layout}/img/shouye2.png"/>
					</div>
				</div> --%>
			</div>
		</div>
	</div>
</div>