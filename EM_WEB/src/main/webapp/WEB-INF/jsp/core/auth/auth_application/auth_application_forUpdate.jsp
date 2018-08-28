<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	//var a=$(window).height()-155;
	//a-=96;(a=a-96)
	//$("#appDetailDiv").css("height",a);

	//设置提交规则
	$("#appForm").validate({
		rules: {
			'appName': "required",//写法1
			appCode: {//写法2
				required: true
			},
			appType: {
				required: true
			},
			isOpen: {
				required: true
			}
		},
		messages: {
			appName: "应用名称不能为空！",
			appCode: {
				required: "应用代码不能为空！"
			},
			appType: {
				required: "应用类别不能为空！"
			},
			isOpen: {
				required: "是否开通不能为空！"
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('appForm',function(data){
				//BaseUtils.alert(data.msg); 
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						var omtree = $.fn.zTree.getZTreeObj("applicationTree");
		            	var pnode=omtree.getNodeByParam("id","root");
		            	pnode.isParent = true;
		            	omtree.reAsyncChildNodes(pnode, "refresh");
						BaseUtils.load("appRightFrame","authApplication/forUpdate.do?id="+data.obj);
					}
				}); 
			});
		}
     });
});
</script>
<div id="appDetailDiv" class="portlet box yellow">
	<form:formTitle title="应用详情" />
	<div class="portlet-body form">
		<form id="appForm" name="appForm" action="authApplication/update.do" method="post" class="form-horizontal">
		<input type="hidden" name="csrftoken" value="${csrftoken }" />
			<div class="form-body">
				<div class="row">
					<form:input2c labelName="应用名称" required="true" name="appName" defaultValue="${record.appName}" />
					<form:input2c labelName="应用代码" required="true" name="appCode" defaultValue="${record.appCode}" />
				</div>
				<div class="row">
					<form:dictSelect2c labelName="应用类别" required="true" name="appType" cls="form-control" typeCode="APP_TYPE"
						defaultValue="${record.appType }" />
					<form:dictSelect2c labelName="是否开通" required="true" name="isOpen" cls="form-control" typeCode="YN" defaultValue="${record.isOpen }" />
				</div>
			</div>
			<div class="row">
				<form:datePicker2c labelName="开通日期" name="openDate"  rules="{dateFmt:'yyyy年MM月'}" defaultValue="${record.openDate}" />
				<form:input2c labelName="访问地址" cls="form-control" name="url" defaultValue="${record.url}" />
			</div>
			<div class="row">
				<form:input2c labelName="IP地址" name="ipAddr" defaultValue="${record.ipAddr}" />
				<form:input2c labelName="端口" cls="form-control" name="ipPort" defaultValue="${record.ipPort}" />
			</div>
			<div class="row">
				<form:input2c labelName="应用描述" name="appDesc" defaultValue="${record.appDesc}" />
			</div>
			<input type="hidden" name="appId" value="${record.appId }" />
			<form:formSaveButton />
	</div>
	
	</form>
</div>


