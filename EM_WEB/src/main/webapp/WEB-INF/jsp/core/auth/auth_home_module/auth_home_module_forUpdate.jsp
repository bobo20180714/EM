<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
//设置提交规则
$("#moduleForm").validate({
	rules: {
		moduleName: {
			required: true
		},
		moduleType: {
			required: true
		},
		code: {
			required: true
		},
		inUse: {
			required: true
		},
		dataCol: {
			required: true
		},
		dataRow: {
			required: true
		},
		dataSizex: {
			required: true
		},
		dataSizey: {
			required: true
		}
	},
	//改变默认的提交方法
	submitHandler:function(form){
		defaultAjaxSubmit('moduleForm',function(data){
			//阻滞写法
			BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
				if (data.flag) {
					$("#ajax_lg").modal('hide');
					if(dataTable_){
						module_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
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
	<h4 class="modal-title">首页模块编辑</h4>
</div>
<form id="moduleForm" name="moduleForm" action="authHomeModule/update.do" method="post" class="form-horizontal">
	<input type="hidden" name="homeModuleId" value="${record.homeModuleId}" />
	<input type="hidden" name="csrftoken" value="${csrftoken }" />
	<div class="modal-body form">
		<div class="form-body">
			<div class="row">
				<form:input2c labelName="模块标题" required="true" name="moduleName" defaultValue="${record.moduleName}" />
				<form:dictSelect2c labelName="模块类型" required="true" name="moduleType" cls="form-control" typeCode="MODULE_TYPE" defaultValue="${record.moduleType }" />
			</div>
			<div class="row">
				<form:input2c labelName="编号" required="true" name="code" defaultValue="${record.code}" />
				<form:dictSelect2c labelName="是否使用" required="true" name="inUse" cls="form-control" typeCode="YN" defaultValue="${record.inUse }" />
			</div>
			<div class="row">
				<form:input2c labelName="坐标列" required="true" name="dataCol" defaultValue="${record.dataCol}" />
				<form:input2c labelName="坐标行" required="true" name="dataRow" defaultValue="${record.dataRow}" />
			</div>
			<div class="row">
				<form:input2c labelName="占用列" required="true" name="dataSizex" defaultValue="${record.dataSizex}" />
				<form:input2c labelName="占用行" required="true" name="dataSizey" defaultValue="${record.dataSizey}" />
			</div> 
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-md-2">HTML内容
						</label>
						<div class="col-md-10">
							<%-- <textarea class="formtextarea" type="text" id="HTML_CONTENT" name="HTML_CONTENT">${record.HTML_CONTENT}</textarea>  --%>
							<textarea type="text" style="width:100%;" id="htmlContent" name="htmlContent">${record.htmlContent}</textarea> 
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-md-2">参数（json格式）
						</label>
						<div class="col-md-10">
							<textarea type="text" style="width:100%;" id="paramJson" name="paramJson">${record.paramJson}</textarea>
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
			

