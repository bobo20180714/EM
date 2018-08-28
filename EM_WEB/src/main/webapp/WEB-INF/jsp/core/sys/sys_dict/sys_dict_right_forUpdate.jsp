<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
//设置提交规则
$("#childDictForm").validate({
	rules: {
		text: {
			required: true
		},
		value: {
			required: true
		},
		isUse: {
			required: true
		},
		seq: {
			required: true,
			digits: true
		}
	},
	//改变默认的提交方法
	submitHandler:function(form){
		defaultAjaxSubmit('childDictForm',function(data){
			//阻滞写法
			BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
				if (data.flag) {
					$("#ajax_lg").modal('hide');
					if(dataTable_2){
						dataTable_2.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
					}
					
				}
			}); 
		});
	}

});

</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">字典子项编辑</h4>
</div>
<form id="childDictForm" name="childDictForm" action="sysDict/rightUpdate.do" method="post" class="form-horizontal">
	<input type="hidden" id="TYPE_ID" name="typeId" value="${record.typeId}" />
	<input type="hidden" id="TYPE_CODE" name="typeCode" value="${record.typeCode}" />
	<input type="hidden" name="dicId" value="${record.dicId }" />
	<input type="hidden" name="pId" value="${record.pId }" />
	<input type="hidden" name="csrftoken" value="${csrftoken }" />
	<div class="modal-body form">
		<div class="form-body">
			<div class="row">
				<form:input2c labelName="字典项" required="true" name="text" defaultValue="${record.text}" />
				<form:input2c labelName="字典值" required="true" name="value" defaultValue="${record.value}" />
			</div>
			<div class="row">
				<form:dictSelect2c labelName="是否使用" required="true" name="isUse" cls="form-control" typeCode="YN" defaultValue="${record.isUse }" />
				<form:input2c labelName="字典顺序" required="true" name="seq" defaultValue="${record.seq}" placeholder="填入整数" />
			</div>
		</div>
	</div> 
	<div class="modal-footer">
		<button type="button" class="btn default" data-dismiss="modal">关闭</button>
		<input type="submit" class="btn green" value="确认保存"/>
	</div>
</form>

