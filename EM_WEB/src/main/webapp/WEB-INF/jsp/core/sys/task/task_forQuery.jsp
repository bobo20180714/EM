<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
 
<script>
var jobTask_dataTable;
var jobTaskAjaxParams = {};
$(function(){
	//往后台传其他参数 jobTaskAjaxParams["gogogogtest"] = "yesimtest";
	jobTask_dataTable = $('#jobTaskListTable').DataTable({
		  "paging": false,
	      "serverSide": true,
	      "searching": false,
	      "ajax" : {
	    	    "url": "sysJobTask/getQueryData.do",
		  	      "data": function(data) { 
		              $.each(jobTaskAjaxParams, function(key, value) {
		                   data[key] = value;
		               });
		           }
	      },
	      "columns": [
			null,
			{"data": "jobId", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"data": "jobName", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"data": "jobGroup", "visible" : true,"bSortable": false,"defaultContent": ""},
	        null,
	        {"data": "cronExpression", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"data": "description", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"data": "isConcurrent", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"data": "beanClass", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"data": "springId", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"data": "methodName", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"data": "caozuo", "visible" : true,"bSortable": false,"defaultContent": ""},
	      ],
	      "columnDefs": [
	                     {
	                         "searchable": false,
	                         "orderable": false,
	                         "data": "jobId",
	                         "targets": [0],
	                         "render": function(data, type, row,meta) {
		                         return '<div class="taskJobChecker"><span class="checkSpanJobTask"><input class="checker checkboxes" type="checkbox" name="check" value="'+data+'" /></span></div>';
		                       }
	                     },{
	                         "searchable": false,
	                         "orderable": false,
	                         "data": "jobStatus",
	                         "targets": [4],
	                         "render": function(data, type, row,meta) {
	                        	 if(data=='1'){
	                        		 return '<a href="javascript:;" onclick="changeJobStatus(\''+row.jobId+'\',\'stop\')">停止</a>'
	                        	 }else{
	                        		 return '<a href="javascript:;" onclick="changeJobStatus(\''+row.jobId+'\',\'start\')">开启</a>'
	                        	 }
		                       }
	                     },{
	                    	 "searchable": false,
	                         "orderable": false,
	                       "targets": [11],
	                       "data": "jobId",
	                       "render": function(data, type, row,meta) {
	                    	   //var htmlContent = "<a href='acRole/forUpdate.do?id="+data+"' data-target='#ajax_lg' data-toggle='modal'>修改</a>";
	                    	   
	                         return '<a href="javascript:;" onclick="updateCron(\''+row.jobId+'\')">更新cron</a>';
	                       }
	                     }
	                     
	                     
	                   ]
	    });

	$('#jobTaskListTable').on('change', 'tbody tr .checkboxes', function () {
		var beforeState = this.checked;
		$(this).prop("checked",this.checked);
		$(this).attr("checked",this.checked);
 		if(!this.checked){
			$("#checkAllJobTasks").prop("checked", false);
			$("#checkAllJobTasks").attr("checked", false);
			$("#checkAllJobTasks").parents('span').removeClass("checked");
		}
		$(this).parents('span').toggleClass("checked");
        $(this).parents('tr').toggleClass("active");
        
    });	
	//全选择 全解除
	$("#checkAllJobTasks").click(function(){
	    $(".taskJobChecker :checkbox").prop("checked", this.checked);
	    $(".taskJobChecker :checkbox").attr("checked", this.checked);
	    if(this.checked){
	    	 $(".checkSpanJobTask").addClass("checked");
	    	 $(".checkSpanJobTask").parents('tr').addClass("active");
	    }else{
	    	$(".checkSpanJobTask").removeClass("checked");
	    	 $(".checkSpanJobTask").parents('tr').removeClass("active");
	    }
	});
	
	$("#addJobTaskForm").validate({
		rules: {
			jobName: {
				required: true
			},
			jobGroup: {
				required: true
			},
			cronExpression: {
				required: true
			},
			methodName: {
				required: true
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			if ($.trim($('#beanClass').val()) == '' && $.trim($('#springId').val()) == '') {
				$('#springId').focus();
				alert('类路径和spring id至少填写一个');
				return false;
			}
			
			defaultAjaxSubmit('addJobTaskForm',function(data){
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						if(jobTask_dataTable){
							jobTask_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
						}
						
					}
				}); 
			});
		}
     });
});

function changeJobStatus(jobId, cmd) {
	
	BaseUtils.showWaitMsg();
	$.ajax({
		url: "sysJobTask/changeJobStatus.do",
		async : false,
		type:"post",
		cache : false,
		data:  {
			jobId : jobId,
			cmd : cmd
		},
		dataType:"json",
		success: function(data) {
			BaseUtils.hideWaitMsg();
			BaseUtils.ifNeedToSessionOut(data);
			BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
				if (data.flag) {
					if(jobTask_dataTable){
						jobTask_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
					}
					
				}
			});
		}
	});
}
function updateCron(jobId) {
	var cron = prompt("输入cron表达式！", "");
	if (cron) {
		BaseUtils.showWaitMsg();
		$.ajax({
			url: "sysJobTask/updateCron.do",
			async : false,
			type:"post",
			cache : false,
			data : {
				jobId : jobId,
				cron : cron
			},
			dataType:"json",
			success: function(data) {
				BaseUtils.hideWaitMsg();
				BaseUtils.ifNeedToSessionOut(data);
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						if(jobTask_dataTable){
							jobTask_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
						}
						
					}
				});
			}
		});
	}
}

function deleteTaskJobs(){
	var checkedList = $("input[name='check']:checked")
	if(checkedList.length==0){
		BaseUtils.alert('请选择要删除的记录');
		return;
	}
	
	var ids = [];
	for (var i = 0; i < checkedList.length; i++) {
		ids.push($(checkedList[i]).val());
	}
	
	BaseUtils.defaultConfirm("你确定要删除吗？删除后不可恢复！",(function(){
		BaseUtils.showWaitMsg();
		$.ajax({
			type: "post",
			url:'${ctx}/sysJobTask/batchDelete.do',
			data : {
				ids : ids.join(',')
			},
			dataType:"json",
			success : function(data) {
				//var ret = jQuery.parseJSON(data);
				BaseUtils.hideWaitMsg();
				BaseUtils.ifNeedToSessionOut(data);
				BaseUtils.alert(data.msg);
				if (data.flag) {
					jobTask_dataTable.ajax.reload();
				}
			}
		});
	}));
}

</script>
<div class=" tabPage">
	<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue">
						<table:tableTitle title="计划任务管理"/>
						<form id="addJobTaskForm" method="post" action="sysJobTask/add.do">
						<input type="hidden" name="csrftoken" value="${csrftoken }" />
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="clearfix">
											<input type="button"  onclick="deleteTaskJobs()" value="删除" id="delete" class="btn btn-danger"/>
										</div>
									</div>									
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover datatable" id="jobTaskListTable">
								<thead>
									<tr role="row" class="heading">
										<th width="5%"><input type="checkbox" id="checkAllJobTasks"  class="group-checkable" data-set="#jobTaskListTable .checkboxes" /></th>
										<th width="5%">id</th>
									    <th width="10%">名称</th>
									    <th width="10%">分组</th>
									    <th width="5%">状态</th>
									    <th width="15%">cron表达式</th>
									    <th width="15%">描述</th>
									    <th width="5%">同步否</th>
									    <th width="15%">类路径</th>
									    <th width="5%">spring id</th>
									    <th width="5%">方法名</th>
									    <th width="5%">操作</th>
									</tr>
									<tr role="row">
										<td></td>
										<td></td>
										<td><input style="width:90%" class="form-control" type="text" name="jobName" id="jobName"></input></td>
										<td><input style="width:90%" class="form-control" type="text" name="jobGroup" id="jobGroup"></input></td>
										<td><input type="hidden" name="jobStatus" value="0"></input></td>
										<td>
											<input style="width:90%" class="form-control" type="text" name="cronExpression"	id="cronExpression"/>
										</td>
										<td><input style="width:90%" class="form-control" type="text" name="description" id="description"></input></td>
										<td>
											<select style="width:90%" class="form-control" name="isConcurrent" id="isConcurrent">
													<option value="1">1</option>
													<option value="0">0</option>
											</select>
										</td>
										<td><input style="width:90%" class="form-control" type="text" name="beanClass" id="beanClass"></input></td>
										<td><input style="width:90%" class="form-control" type="text" name="springId" id="springId"></input></td>
										<td><input style="width:90%" class="form-control" type="text" name="methodName" id="methodName"></input></td>
										<td><input type="submit" class="btn green" value="保存" /></td>
									</tr>
								</thead>
							</table>
						</div>
						</form>
					</div>
				</div>
			</div>
</div>




