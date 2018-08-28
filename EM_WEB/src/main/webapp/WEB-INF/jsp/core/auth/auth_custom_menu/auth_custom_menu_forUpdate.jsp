<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){

	//设置提交规则
	$("#${idPrefix}customMenuForm").validate({
		rules: {
			menuOperatorCustomCode: {
				required: true
			},
			operatorId: {
				required: true
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('${idPrefix}customMenuForm',function(data){
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						//$.proxy(this.hide, this)
						$("#ajax_lg").modal('hide');
						if(acCustomMenu_dataTable){
							acCustomMenu_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
						}
						
					}
				}); 
			});
		}
     });
});
</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">自定义菜单编辑</h4>
</div>
<form id="${idPrefix}customMenuForm" name="customMenuForm" action="authCustomMenu/update.do" method="post" class="form-horizontal">
	<input type="hidden" name="operatorId" value="${record.operatorId }" />
	<input type="hidden" name="orgMenuOperatorCustomCode" value="${record.menuOperatorCustomCode }" />
	<input type="hidden" name="IS_CREATE" value="${isCreate}" />
	<input type="hidden" name="csrftoken" value="${csrftoken }" />
	<div class="modal-body form">
		<div class="form-body">
			<div class="row">
				<form:input2c labelName="分组名称" required="true" name="menuOperatorCustomCode" defaultValue="${record.menuOperatorCustomCode}" />
			</div>
		</div>

	</div>
	<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal">关闭</button>
	<input type="submit" class="btn green" value="确认保存"/>
</div>
</form>
