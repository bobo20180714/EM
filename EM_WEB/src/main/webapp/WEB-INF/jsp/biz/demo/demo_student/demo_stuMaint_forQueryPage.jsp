<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>

<script type="text/javascript">

function deleteStudent(){
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
			url:'${ctx}/stuMaint/batchDelete.do',
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
					student_dataTable.ajax.reload();
				}
			}
		});
	}));
}

var student_dataTable;
var ajaxParams = {};
$(function(){

	//往后台传其他参数 ajaxParams["gogogogtest"] = "yesimtest";
	
	student_dataTable = $('#stuListTable').DataTable({
		 /*  "bStateSave": true, */
	      "serverSide": true,
	      "ajax" : {
	    	    "url": "stuMaint/getPageData.do",
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
	        {"width": "15%","data": "stuName", "visible" : true,"bSortable": false,"defaultContent": "", "name":"STU_NAME"},
	        {"width": "15%","data": "stuSexText","defaultContent": ""},
	        {"width": "15%","data": "stuAge","defaultContent": "", "name":"STU_AGE"},
	        {"width": "15%","data": "classRoom.className","defaultContent": ""},
	        {"width": "15%","data": "stuDegree","defaultContent": ""},
	        {"width": "15%","data": "stuAddress","defaultContent": ""}
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
	                       "targets": [8],
	                       "data": "stuId",
	                       "render": function(data, type, row,meta) {
	                    	   var htmlContent = "<a href='stuMaint/forUpdate.do?id="+data+"' data-target='#ajax_lg' data-toggle='modal'>修改</a>";         	   
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
		                         return meta.row+student_dataTable.page.info().start+1;
		                       }
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": "stuId",
	                         "targets": [0],
	                         "render": function(data, type, row,meta) {
		                         return '<div class="studentchecker"><span class="checkSpanStudent"><input class="checker checkboxes" type="checkbox" name="check" value="'+data+'" /></span></div>';
		                       }
	                     }
	                     
	                     
	                   ]
	    });

	$('#stuListTable').on('change', 'tbody tr .checkboxes', function () {
		var beforeState = this.checked;
		$(this).prop("checked",this.checked);
		$(this).attr("checked",this.checked);
 		if(!this.checked){
			$("#checkAllStudent").prop("checked", false);
			$("#checkAllStudent").attr("checked", false);
			$("#checkAllStudent").parents('span').removeClass("checked");
		}
		$(this).parents('span').toggleClass("checked");
        $(this).parents('tr').toggleClass("active");
        
    });	
	//全选择 全解除
	$("#checkAllStudent").click(function(){
	    $(".studentchecker :checkbox").prop("checked", this.checked);
	    $(".studentchecker :checkbox").attr("checked", this.checked);
	    if(this.checked){
	    	 $(".checkSpanStudent").addClass("checked");
	    	 $(".checkSpanStudent").parents('tr').addClass("active");
	    }else{
	    	$(".checkSpanStudent").removeClass("checked");
	    	 $(".checkSpanStudent").parents('tr').removeClass("active");
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
						<table:tableTitle title="学生列表"/>
						<div class="portlet-body">
							<table:toolbar href="stuMaint/forUpdate.do?id=create" target="#ajax_lg" delclick="deleteStudent()"/>
							<table class="table table-striped table-bordered table-hover datatable" id="stuListTable">
								<thead>
									<tr role="row" class="heading">
										<th><input type="checkbox" id="checkAllStudent"  class="group-checkable" data-set="#stuListTable .checkboxes" /></th>
										<th>编号</th>
									    <th>学生姓名</th>
									    <th>性别</th>
									    <th>年龄</th>
									    <th>所属班级</th>
									    <th>学位</th>
									    <th>家庭住址</th>
									    <th>操作</th>
									</tr>
									
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
</div>