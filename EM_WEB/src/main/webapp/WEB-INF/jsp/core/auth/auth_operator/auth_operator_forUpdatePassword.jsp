<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){

	//设置提交规则
	$("#changePassword").validate({
		rules: {
			oldPassword: {//写法2
				required: true
			},
			newPassword: {
				required: true
			},
			repeatPassword: {
				required: true
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			if($('#newPassword').val()!=$('#repeatPassword').val()){
				BaseUtils.hideWaitMsg();
				BaseUtils.alert("重复密码错误，请重新输入");
				$('#repeatPassword').val('');
				$('#repeatPassword').focus();
				return;
			}
			
			defaultAjaxSubmit('changePassword',function(data){
				//BaseUtils.alert(data.msg); 
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						$("#ajax_lg").modal('hide');
					}
				}); 
				
				//不阻滞写法
				/* BaseUtils.alert(data.msg); 
				if (data.flag) {
					var omtree = $.fn.zTree.getZTreeObj("omTree");
	            	var pnode=omtree.getNodeByParam("id","root");
	            	pnode.isParent = true;
	            	omtree.reAsyncChildNodes(pnode, "refresh");
					BaseUtils.load("orgRightFrame","acApplication/forUpdate.do?id="+data.obj);
				} */
			});
		}
     });
});
</script>
<div class="modal-header" >
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true" ></button>
	<h4 class="modal-title">密码修改</h4>
</div>
<form action="authOperator/updatePassword.do" method="post" id="changePassword" class="form-horizontal">
	<input type="hidden" name="csrftoken" value="${csrftoken }" />
	<div class="modal-body form">
		<div class="form-body">
			<div class="row">
				<form:input2c labelName="原密码" type="password" name="oldPassword"/>
			</div>
			<div class="row">
				<form:input2c labelName="新密码" type="password" name="newPassword" />
			</div>
			<div class="row">
				<form:input2c labelName="重复密码" type="password" name="repeatPassword" />
			</div>
		</div>

	</div>
	<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal">关闭</button>
	<input type="submit" class="btn green" value="确认保存"/>
</div>
</form>



