<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>

<script type="text/javascript">

function deleteClassroom(){
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
			url:'${ctx}/classMaint/batchDelete.do',
			data : {
				ids : ids.join(',')
			},
			beforeSend: function(request) {
	            request.setRequestHeader("csrftoken", "${csrftoken}");
	        },
			dataType:"json",
			success : function(data) {
				//var ret = jQuery.parseJSON(data);
				BaseUtils.hideWaitMsg();
				BaseUtils.alert(data.msg);
				if (data.flag) {
					classroom_dataTable.ajax.reload();
				}
			}
		});
	}));
}

var classroom_dataTable;
var ajaxParams = {};
$(function(){

	//往后台传其他参数 ajaxParams["gogogogtest"] = "yesimtest";
	
	classroom_dataTable = $('#classroomListTable').DataTable({
		 /*  "bStateSave": true, */
	      "serverSide": true,
	      "ajax" : {
	    	    "url": "classMaint/getPageData.do",
		  	      "data": function(data) { // add request parameters before submit
		              $.each(ajaxParams, function(key, value) {
		                   data[key] = value;
		               });
		           }
	      },
	      /*
	      *	这里注意：如果想排序的话，data里面的值必须与数据库表中的字段保持一致，如果不一致时，就增加一个name属性，值为数据库表字段，
	      *比如 Student表中有STU_NAME字段，如果data定义为stuName时，必须增加一个name属性，值为STU_NAME。如果data定义为STU_NAME，则无需name属性
	      */
	      "columns": [
			null,
			null,
	        {"width": "15%","data": "className", "visible" : true,"bSortable": false,"defaultContent": "", "name":"CLASS_NAME"},
	        {"width": "15%","data": "classLevel","defaultContent": ""},
	        {"width": "15%","data": "classMaster","defaultContent": "", "name":"CLASS_MASTER"}
	      ],
	      "aaSorting": [[ 2, "desc" ],[4, "desc"]],//此句会影响汉化，是个插件的bug，所以还得跟上下面一段代码
	      "oLanguage":{ 
	          "sLengthMenu" : "显示  _MENU_ 条 "
	      },
	      "columnDefs": [
	                     {
	                    	 "width": "200px",
	                    	 "searchable": false,
	                         "orderable": false,
	                       "targets": [5],
	                       "data": "classId",
	                       "render": function(data, type, row,meta) {
	                    	   var htmlContent = "<a href='classMaint/forUpdate.do?id="+data+"' data-target='#ajax_lg' data-toggle='modal'>修改</a>";         	   
	                         return htmlContent;
	                       },
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": null,
	                         "targets": [1],
	                         "render": function(data, type, row,meta) {
	                        	 //每行的索引
		                         return meta.row+classroom_dataTable.page.info().start+1;
		                       }
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": "classId",
	                         "targets": [0],
	                         "render": function(data, type, row,meta) {
		                         return '<div class="classroomchecker"><span class="checkSpanClassroom"><input class="checker checkboxes" type="checkbox" name="check" value="'+data+'" /></span></div>';
		                       }
	                     }
	                     
	                     
	                   ]
	    });

	$('#classroomListTable').on('change', 'tbody tr .checkboxes', function () {
		var beforeState = this.checked;
		$(this).prop("checked",this.checked);
		$(this).attr("checked",this.checked);
 		if(!this.checked){
			$("#checkAllClassroom").prop("checked", false);
			$("#checkAllClassroom").attr("checked", false);
			$("#checkAllClassroom").parents('span').removeClass("checked");
		}
		$(this).parents('span').toggleClass("checked");
        $(this).parents('tr').toggleClass("active");
        
    });	
	//全选择 全解除
	$("#checkAllClassroom").click(function(){
	    $(".classroomchecker :checkbox").prop("checked", this.checked);
	    $(".classroomchecker :checkbox").attr("checked", this.checked);
	    if(this.checked){
	    	 $(".checkSpanClassroom").addClass("checked");
	    	 $(".checkSpanClassroom").parents('tr').addClass("active");
	    }else{
	    	$(".checkSpanClassroom").removeClass("checked");
	    	 $(".checkSpanClassroom").parents('tr').removeClass("active");
	    }
	   
	    //var list = $("input[name='check']:checkbox:checked")
	    /* var list = $("input[name='check']:checked")
	    alert($(list[0]).val()); */
	});
	
});
</script>
<div class=" tabPage">
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<table:tableTitle title="班级列表"/>
				<div class="portlet-body">
					<table:toolbar href="classMaint/forUpdate.do?id=create" target="#ajax_lg" delclick="deleteClassroom()"/>
					<table class="table table-striped table-bordered table-hover datatable" id="classroomListTable">
						<thead>
							<tr role="row" class="heading">
								<th><input type="checkbox" id="checkAllClassroom"  class="group-checkable" data-set="#classroomListTable .checkboxes" /></th>
								<th>编号</th>
							    <th>班级姓名</th>
							    <th>班级级别</th>
							    <th>班级主管</th>
							    <th>操作</th>
							</tr>
							
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>