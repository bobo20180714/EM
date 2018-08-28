<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
//设置提交规则
$("#dictForm").validate({
	rules: {
		TYPE_NAME: {
			required: true
		},
		TYPE_CODE: {
			required: true
		},
		IS_USE: {
			required: true
		}
	},
	//改变默认的提交方法
	submitHandler:function(form){
		defaultAjaxSubmit('dictForm',function(data){
			//阻滞写法
			BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
				if (data.flag) {
					$("#ajax_lg").modal('hide');
					if(dict_dataTable_){
						dict_dataTable_.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
						document.getElementById("dictRightFrame").style.display="none";
					}
					
				}
			}); 
		});
	}

});

</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">字典项编辑</h4>
</div>
<form id="dictForm" name="dictForm" action="sysDict/update.do" method="post" class="form-horizontal">
	<input type="hidden" name="dicTypeId" value="${record.dicTypeId}" />
	<input type="hidden" name="csrftoken" value="${csrftoken }" />
	<div class="modal-body form">
		<div class="form-body">
			<div class="row">
				<form:input2c labelName="字典名称" required="true" name="typeName" defaultValue="${record.typeName}" />
				<form:input2c labelName="字典编码" required="true" name="typeCode" defaultValue="${record.typeCode}" />
			</div>
			<div class="row">
				<form:dictSelect2c labelName="是否使用" required="true" name="isUse" cls="form-control" typeCode="YN" defaultValue="${record.isUse }" />
			</div>
		</div>
	</div> 
	<div class="modal-footer">
		<button type="button" class="btn default" data-dismiss="modal">关闭</button>
		<input type="submit" class="btn green" value="确认保存"/>
	</div>
</form>
