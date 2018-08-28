<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	//var a=$(window).height()-155;
	//a-=96;(a=a-96)
	//$("#appDetailDiv").css("height",a);

	//设置提交规则
	$("#${idPrefix}stuForm").validate({
		rules: {
			stuName: {
				required: true
			},
			stuAge: {
				required: true
			},
			classId: {
				required: true
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('${idPrefix}stuForm', function(data){
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						//$.proxy(this.hide, this)
						$("#ajax_lg").modal('hide');
						if(student_dataTable){
							student_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
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
	<h4 class="modal-title">学生编辑</h4>
</div>
<form id="${idPrefix}stuForm" name="stuForm" action="stuMaint/update.do" method="post" class="form-horizontal">
	<input type="hidden" name="stuId" value="${record.stuId }" />
	<input type="hidden" name="csrftoken" value="${csrftoken }" />
	<div class="modal-body form">
		<div class="form-body">
			<div class="row">
				<form:input2c labelName="学生姓名" required="true" name="stuName" defaultValue="${record.stuName}" />
				<form:dictSelect2c labelName="学生性别" required="true" name="stuSex" cls="form-control" typeCode="GENDER" defaultValue="${record.stuSex }" />
			</div>
			<div class="row">
				<form:input2c labelName="学生年龄" required="true" name="stuAge" defaultValue="${record.stuAge}" />
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3"> 学生班级 <span class="required" aria-required="true">* </span>
						</label>
						<div class="col-md-9">
							<select class="form-control" id="classId" name="classId">
								<c:forEach var="key" items="${classRoomList}">
									<!-- <option value="">--请选择--</option> -->
									<option value="${key.classId}" ${record.classRoom.classId==key.classId?'selected':''}>${key.className}</option>
								</c:forEach>
							</select> <span class="help-block"> </span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<form:input2c labelName="学位" required="true" name="stuDegree" defaultValue="${record.stuDegree}" />
				<form:input2c labelName="家庭住址" required="true" name="stuAddress" defaultValue="${record.stuAddress}" />
			</div>
		</div>

	</div>
	<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal">关闭</button>
	<input type="submit" class="btn green" value="确认保存"/>
</div>
</form>
