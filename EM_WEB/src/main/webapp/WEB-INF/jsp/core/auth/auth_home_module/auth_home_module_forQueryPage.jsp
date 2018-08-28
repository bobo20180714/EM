<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
function forUpdate(id){
	var index = layer.open({
		title:'编辑模块内容',
	    type: 2,
	    area: ['700px', '300px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/authHomeModule/forUpdate.do?id='+id+''
	});
	//layer.full(index);
}


function deleteModule(){
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
			url:'${ctx}/authHomeModule/batchDelete.do',
			data : {
				ids : ids.join(',')
			},
			dataType:"json",
			success : function(data) {
				//var ret = jQuery.parseJSON(data);
				BaseUtils.hideWaitMsg();
				BaseUtils.alert(data.msg);
				if (data.flag) {
					//$('#queryForm').submit();
					module_dataTable.ajax.reload();
				}
			}
		});
	}));
}

var module_dataTable;
var ajaxParams = {};
$(function(){
//往后台传其他参数 ajaxParams["gogogogtest"] = "yesimtest";
	
	module_dataTable = $('#moduleListTable').DataTable({
		  "serverSide": true,
	      "ajax" : {
	    	    "url": "authHomeModule/getPageData.do"
	      },
	      "columns": [
			null,
			null,
	        {"width": "15%","data": "moduleName", "visible" : true,"bSortable": false},
	        {"width": "15%","data": "moduleType", "name":"MODULE_TYPE"},
	        {"width": "15%","data": "code"},
	        {"width": "15%","data": "inUse"}
	      ],
	      "aaSorting": [[ 3, "desc" ]],//此句会影响汉化，是个插件的bug，所以还得跟上下面一段代码
	      "oLanguage":{ 
	          "sLengthMenu" : "显示  _MENU_ 条 "
	      },
	      "columnDefs": [
	                     {
	                    	 "width": "200px",
	                    	 "searchable": false,
	                         "orderable": false,
	                       "targets": [6],
	                       "data": "homeModuleId",
	                       "render": function(data, type, row,meta) {
	                    	   var htmlContent = "<a href='authHomeModule/forUpdate.do?id="+data+"' data-target='#ajax_lg' data-toggle='modal'>修改</a>";
	                         return htmlContent;
	                       },
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": null,
	                         "targets": [1],
	                         "render": function(data, type, row,meta) {
		                         return meta.row+module_dataTable.page.info().start+1;
		                       }
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": "homeModuleId",
	                         "targets": [0],
	                         "render": function(data, type, row,meta) {
		                         return '<div class="homeModulechecker"><span class="checkSpanHomeModule"><input class="checker checkboxes" type="checkbox" name="check" value="'+data+'" /></span></div>';
		                       }
	                     }
	                     
	                     
	                   ]
	    });

	$('#moduleListTable').on('change', 'tbody tr .checkboxes', function () {
		var beforeState = this.checked;
		$(this).prop("checked",this.checked);
		$(this).attr("checked",this.checked);
 		if(!this.checked){
			$("#checkAllHomeModule").prop("checked", false);
			$("#checkAllHomeModule").attr("checked", false);
			$("#checkAllHomeModule").parents('span').removeClass("checked");
		}
		$(this).parents('span').toggleClass("checked");
        $(this).parents('tr').toggleClass("active");
        
    });
	
	//全选择 全解除
	$("#checkAllHomeModule").click(function(){
	    $(".homeModulechecker :checkbox").prop("checked", this.checked);
	    $(".homeModulechecker :checkbox").attr("checked", this.checked);
	    if(this.checked){
	    	 $(".checkSpanHomeModule").addClass("checked");
	    	 $(".checkSpanHomeModule").parents('tr').addClass("active");
	    }else{
	    	$(".checkSpanHomeModule").removeClass("checked");
	    	 $(".checkSpanHomeModule").parents('tr').removeClass("active");
	    }
	    //var list = $("input[name='check']:checkbox:checked")
	    /* var list = $("input[name='check']:checked")
	    alert($(list[0]).val()); */
	});
});
</script>

<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="其他管理" thdLevel="首页模块管理"/>
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<table:tableTitle title="模块列表"/>
				<div class="portlet-body">
					<table:toolbar href="authHomeModule/forUpdate.do?id=create" target="#ajax_lg" delclick="deleteModule()"/>
					<table class="table table-striped table-bordered table-hover datatable" id="moduleListTable">
						<thead>
							<tr role="row" class="heading">
								<th><input type="checkbox" id="checkAllHomeModule"  class="group-checkable" data-set="#moduleListTable .checkboxes" /></th>
								<th>编号</th>
				 				<th>模块标题</th>
								<th>模块类型</th>
								<th>模块编号</th>
								<th>是否在用</th>
								<th>操作</th>
							</tr>					
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
	
