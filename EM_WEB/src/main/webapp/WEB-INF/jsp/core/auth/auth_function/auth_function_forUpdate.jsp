<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	//var a=$(window).height()-155;
	//a-=96;(a=a-96)
	//$("#appDetailDiv").css("height",a);

	//设置提交规则
	$("#functionForm").validate({
		rules: {
			funcName: {//写法2
				required: true
			},
			isMenu: {
				required: true
			},
			funcAction: {
				required: true
			}
		},
		messages: {
			funcName: {
				required: "功能名称不能为空！"
			},
			isMenu: {
				required: "是否定义为菜单不能为空！"
			},
			funcAction: {
				required: "功能调用入口不能为空！"
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('functionForm',function(data){
				//不阻滞写法
				BaseUtils.alert(data.msg); 
				if (data.flag) {
					var omtree = $.fn.zTree.getZTreeObj("applicationTree");
					var pid ="${record.funcGroupId }";
	            	var pnode=omtree.getNodeByParam("id",pid);
	            	pnode.isParent = true;
	            	omtree.reAsyncChildNodes(pnode, "refresh");
	            	BaseUtils.load("appRightFrame","authFunction/forUpdate.do?id="+data.obj);
				} 
			});
		}
     });
});
</script>
<div id="appDetailDiv" class="portlet box yellow">
	<form:formTitle title="资源详情" />
	<div class="portlet-body form">
		<form id="functionForm" name="functionForm" action="authFunction/update.do" method="post" class="form-horizontal">
			<input type="hidden" name="funcId" value="${record.funcId }" />
			<input type="hidden" name="funcGroupId" value="${record.funcGroupId }" />
			<input type="hidden" name="csrftoken" value="${csrftoken }" />
			<div class="form-body">
				<div class="row">
					<form:input2c labelName="功能名称" required="true" name="funcName" defaultValue="${record.funcName}"/>
					<form:dictSelect2c labelName="功能类型" name="funcType" typeCode="FUNC_TYPE" cls="form-control" defaultValue="${record.funcType }" />
				</div>
				<div class="row">
					<form:dictSelect2c labelName="是否定义为菜单" required="true" name="isMenu" cls="form-control" typeCode="YN" defaultValue="${record.isMenu }" />
					<form:input2c labelName="功能调用入口" required="true" name="funcAction" defaultValue="${record.funcAction}"/>
				</div>
				<div class="row">
					<form:input2c labelName="输入参数" name="paraInfo" defaultValue="${record.paraInfo}"/>
					<form:input2c labelName="功能描述" name="funcDesc" defaultValue="${record.funcDesc}"/>
				</div>
				
			</div>
			<form:formSaveButton />
		</form>
	</div>
	
</div>

