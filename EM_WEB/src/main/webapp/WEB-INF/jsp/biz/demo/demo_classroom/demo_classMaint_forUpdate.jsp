<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	//var a=$(window).height()-155;
	//a-=96;(a=a-96)
	//$("#appDetailDiv").css("height",a);

	//设置提交规则
	$("#${idPrefix}classroomForm").validate({
		rules: {
			className: {
				required: true
			},
			classLevel: {
				required: true
			},
			classMaster: {
				required: true
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('${idPrefix}classroomForm', function(data){
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						//$.proxy(this.hide, this)
						$("#ajax_lg").modal('hide');
						if(classroom_dataTable){
							classroom_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
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
	<h4 class="modal-title">班级编辑</h4>
</div>
<form id="${idPrefix}classroomForm" name="classroomForm" action="classMaint/update.do" method="post" class="form-horizontal">
	<input type="hidden" name="classId" value="${record.classId }" />
	<input type="hidden" name="csrftoken" value="${csrftoken }" />
	<div class="modal-body form">
		<div class="form-body">
			<div class="row">
				<form:input2c labelName="班级名称" required="true" name="className" defaultValue="${record.className}" />
				<form:input2c labelName="班级级别" required="true" name="classLevel" defaultValue="${record.classLevel}" />
			</div>
			<div class="row">
				<form:input2c labelName="班级主管" required="true" name="classMaster" defaultValue="${record.classMaster}" />
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3"> 学生列表 <span class="required" aria-required="true">* </span>
						</label>
						<div class="col-md-9">
							<select class="form-control" id="stuId" name="stuId">
								<c:forEach var="key" items="${record.students}">
									<!-- <option value="">--请选择--</option> -->
									<option value="${key.stuId}">${key.stuName}</option>
								</c:forEach>
							</select> <span class="help-block"> </span>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal">关闭</button>
	<input type="submit" class="btn green" value="确认保存"/>
</div>
</form>
