<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%><%-- ${fns:xxxx}方法标签 --%>
<!-- 自定义首页内容 BEGIN -->
<div class="page-content">
	<div id="tabs">
		<ul id="myTab" class="nav nav-tabs" role="tablist">
			<li role="presentation" id="tab_主页" class="active"><a
				href="#home" aria-controls="home" role="tab" data-toggle="tab"
				style='border-left: solid 1px #c5d0dc; color: #999999; margin-left: 10px;'>
					<i class="fa fa-home"
					style="color: #8dbd72; line-height: 34px; float: left; font-size: 20px; /* margin: 5px 0px 0 -7px; */ overflow: hidden;"></i>
					主页
			</a></li>
		</ul>
		<div id="tab-content" class="tab-content">

			<script>
				/* 全选示例
				$(function(){
					$('#checkAllGloutInfo').click(function(){
						if(this.checked){
						 $("input[name='checkbox']").each( function() {
						        $(this).attr('checked', true);
						        $(this).parents('.checker').find('span').addClass('checked');
						    });
						}else{
							$("input[name='checkbox']").each( function() {
						        $(this).attr('checked', false);
						        $(this).parents('.checker').find('span').removeClass('checked');
						    });
						}
					});
					
				    $('.page-sidebar-menu > li:eq(2) > a').click();
				}); */
			</script>

			<!-- 全选示例
					 <div class="table-scrollable">
						<table class="table table-responsive table-striped table-bordered table-hover datatable" id="iteminfoListTable">
							<tr>
								<th width="1%">
								<input type="checkbox" id="checkAllGloutInfo" />
								</th>
								<th width="3%">信息项编号</th>
								<th width="6%">信息项名称</th>
								<th width="6%">信息项描述</th>
								<th width="6%">是否权威</th>
							</tr>
			
								<tr>
									<td>
									<input type="checkbox" name="checkbox" value="hahaha" />
									</td>
									<td>ccc</td>
									<td>mmm</td>
									<td>dddd</td>
									<td>aaaa</td>
								</tr>
						</table>
					</div> -->


			<div class="tab-pane active" id="home" role="tabpanel"
				style="background: url(${ctx}/theme/assets/admin/${layout}/img/shouye_bac.png) no-repeat left center; background-size:500px 340px; height:360px;">
				<%-- <img class='shouyeimg' style='margin-top:20px;display:block;' src="${ctx}/theme/assets/admin/${layout}/img/shouye_bac.png"/> --%>
				<!-- <div class='' >
							<div class='indexbox' style='padding-top:50px; margin-left:auto;margin-right:auto;width:400px;'>
								<div class='' style='margin-left:-50px;'>
									<img style='width:150px; margin-left:auto;margin-right:auto;display:block;' src="${ctx}/theme/assets/admin/${layout}/img/logo3.png"/>
									<p style='font-size:26px; font-weight:bold; color:#c86139; margin-top:20px; text-align:center;'>欢迎进入易捷框架！</p>
									<p style='font-size:20px; font-weight:bold; color:#438eb9;text-align:center;'>Welcome to easy fast platform!</p>
									<input type="button" value='点击进入' style='border-radius:10px !important; display:block; margin-top:20px; margin-left:auto;margin-right:auto;'>
									
								</div>
							</div> -->
				

			</div>
		</div>
		<!-- 
						<div class=" tabPage" style='background-image:url(${ctx}/theme/assets/admin/${layout}/img/shouye.png);'>
							<div class="row">
						<!-- <div class=" tabPage" style='height:800px; background-image: url(${ctx}/theme/assets/admin/${layout}/img/shouye.png); background-size:cover; backgrond-attachment:fixed; background-position:center top;'>-->
		<!--<img class="img-responsive col-xs-6 col-sm-4 col-md-2" style='margin-top:10px;margin-bottom:10px;' src="${ctx}/theme/assets/admin/${layout}/img/welcome.png"/>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-xs-2 col-sm-3 col-md-3" ></div>
								<img class="img-responsive  col-xs-8 col-sm-6 col-md-5" style='margin-top:20px;' src="${ctx}/theme/assets/admin/${layout}/img/shouye2.png"/>
							</div>
						</div>
					</div>
					 -->
	</div>
</div>