<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	//var a=$(window).height()-155;
	//a-=96;(a=a-96)
	//$("#appDetailDiv").css("height",a);

	//设置提交规则
	$("#funcGroupForm").validate({
		rules: {
			'funcGroupName': "required"
		},
		messages: {
			funcGroupName: "功能组名称不能为空！"
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('funcGroupForm',function(data){
				var data = data;
				//BaseUtils.alert(data.msg); 
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						var groupTree = $.fn.zTree.getZTreeObj("applicationTree");
						var pid ="${record.parentGroup }";
						if(pid==""){
							pid ="${record.appId }";
						}
		            	var pnode=groupTree.getNodeByParam("id",pid);
		            	pnode.isParent = true;
		            	groupTree.reAsyncChildNodes(pnode, "refresh");
						BaseUtils.load("appRightFrame","authFuncGroup/forUpdate.do?id="+data.obj);
					}
				}); 
				
			});
		}
     });
});
</script>
<div id="funcGroupDetailDiv" class="portlet box yellow">
	<form:formTitle title="功能组详情" />
	<div class="portlet-body form">
		<form id="funcGroupForm" name="funcGroupForm" action="authFuncGroup/update.do" method="post" class="form-horizontal">
			<input type="hidden" name="appId" id="appId" value="${record.appId}"/>
	        <input type="hidden" name="funcGroupId" id="funcGroupId" value="${record.funcGroupId}"/>
	        <input type="hidden" name="parentGroup" id="parentGroup" value="${record.parentGroup}"/>
	        <input type="hidden" name="csrftoken" value="${csrftoken }" />
			<div class="form-body">
				<div class="row">
					<form:input2c cls="col-md-11" labelName="功能组名称" required="true" name="funcGroupName" defaultValue="${record.funcGroupName}"/>
				</div>
			</div>
			<form:formSaveButton />
		</form>
	</div>
</div>

