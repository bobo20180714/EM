<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	
	//设置提交规则
	$("#${idPrefix}roleForm").validate({
		rules: {
			roleName: {
				required: true
			},
			roleType: {
				required: true
			},
			appId: {
				required: true
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('${idPrefix}roleForm', function(data){
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						//$.proxy(this.hide, this)
						$("#ajax_lg").modal('hide');
						if(role_dataTable){
							role_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
						}
						
					}
				}); 
			}, true);
		}
     });
});
</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">角色编辑</h4>
</div>
<form id="${idPrefix}roleForm" name="roleForm" action="authRole/update.do" method="post" class="form-horizontal">
	<input type="hidden" name="roleId" value="${record.roleId }" />
	<input type="hidden" name="csrftoken" value="${csrftoken }" />
	<div class="modal-body form">
		<div class="form-body">
			<div class="row">
				<form:input2c labelName="角色名称" required="true" name="roleName" defaultValue="${record.roleName}" />
				<form:dictSelect2c labelName="角色类型" required="true" name="roleType" cls="form-control" typeCode="ROLE_TYPE" defaultValue="${record.roleType }" />
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3"> 应用权限 <span class="required" aria-required="true">* </span>
						</label>
						<div class="col-md-9">
							<select class="form-control" id="appId" name="appId">
								<c:forEach var="key" items="${appList}">
									<!-- <option value="">--请选择--</option> -->
									<option value="${key.appId}" ${record.appId==key.appId?'selected':''}>${key.appName}</option>
								</c:forEach>
							</select> <span class="help-block"> </span>
						</div>
					</div>
				</div>
				<form:input2c labelName="角色描述" required="false" name="roleDesc" defaultValue="${record.roleDesc}" />
			</div>
		</div>

	</div>
	<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal">关闭</button>
	<input type="submit" class="btn green" value="确认保存"/>
</div>
</form>
