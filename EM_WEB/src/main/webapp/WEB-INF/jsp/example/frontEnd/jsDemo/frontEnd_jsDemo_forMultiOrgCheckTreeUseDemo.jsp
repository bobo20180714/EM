<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
	<script type="text/javascript">
	function forMultiOrgEmpCheck(withOrderModule,callBackFuncName,rootOrgId){
		CommonBizUtils.loadMultiOrgEmpCheckTree({
			width: '320px',
			height: '400px',
			max: false,
			withOrderModule: withOrderModule,
			callBackFuncName: callBackFuncName,
			rootOrgId: rootOrgId,
			selectedEmpWithPidJson : '[{"id":"1cbcd22f-f36d-410d-952b-a5281fe9a858","pId":"088d6e74-ee49-4f63-96b0-a351a4090cec"}]'
		});
		
	}
	
	function forOrgOnlyCheckTree(withOrderModule,callBackFuncName,rootOrgId){
		CommonBizUtils.loadOrgOnlyCheckTree({
			width: '320px',
			height: '400px',
			max: false,
			withOrderModule: withOrderModule,
			callBackFuncName: callBackFuncName,
			rootOrgId: rootOrgId,
			selectedOrgIdJson:'[{"id":"088d6e74-ee49-4f63-96b0-a351a4090cec"}]',
			orgLevel: '1'
		});
		
	}
	
	function callBackFunc1(data){
		alert('callBackFunc1:'+JSON.stringify(data));
	}
	
	function callBackFunc2(data){
		alert('callBackFunc2'+JSON.stringify(data));
	}
	
	$(function(){
		$('#multiOrgTestButtonId').click(function(){
			BaseUtils.refreshCurrentTab();
			//BaseUtils.redirectCurrentTab('sysDict/forQueryPage.do');
		});
	});
	</script>
  <div class="tabPage">
    <div class="portlet-body form">
		<form id="test" name="test" action="" method="post" class="form-horizontal">
			<div class="row">
				<form:input2c required="true" labelName="测试1" name="testCheck1" defaultValue="" onclick="forMultiOrgEmpCheck('no','callBackFunc1','2bcb5427-0c4d-40df-9a52-27290524a89a')"/>
				<form:input2c required="true" labelName="测试2" name="testCheck2" defaultValue="" onclick="forMultiOrgEmpCheck('yes','callBackFunc2','2bcb5427-0c4d-40df-9a52-27290524a89a')"/>
			</div>
			<div class="row">
				<input type="button" id="multiOrgTestButtonId" class="btn green" value="刷新当前tab"/>
				<a href="javascript:BaseUtils.redirectCurrentTab('sysDict/forQueryPage.do');" >a标签刷新tab1</a>
				<a href="javascript:void(0);" onclick="BaseUtils.redirectCurrentTab('sysDict/forQueryPage.do');" >a标签刷新tab2</a> 
			</div>
			<div class="row">
				<form:input2c required="true" labelName="测试3" name="testCheck1" defaultValue="" onclick="forOrgOnlyCheckTree('no','callBackFunc1','2bcb5427-0c4d-40df-9a52-27290524a89a')"/>
				<form:input2c required="true" labelName="测试4" name="testCheck2" defaultValue="" onclick="forOrgOnlyCheckTree('yes','callBackFunc2','2bcb5427-0c4d-40df-9a52-27290524a89a')"/>
			</div>
		</form>
	</div>
  </div>
