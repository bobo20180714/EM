<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
function forUpdate(dicId){
	var typeId = $("#DIC_TYPE_ID").val();
	var typeCode = $("#TYPE_CODE").val();
	layer.open({
		title:'编辑字典项',
	    type: 2,
	    area: ['460px', '300px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/sysDict/rightForUpdate.do?id='+dicId+"&typeId="+typeId+'&typeCode='+typeCode
	});
}
function deleteDetail(){
	var typeCode = $("#TYPE_CODE").val();
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
			url:'${ctx}/sysDict/rightBatchDelete.do?typeCode='+typeCode,
			data : {
				ids : ids.join(',')
			},
			dataType:"json",
			success : function(data) {
				//var ret = jQuery.parseJSON(data);
				BaseUtils.hideWaitMsg();
				BaseUtils.alert(data.msg);
				if (data.flag) {
					var typeId = $("#DIC_TYPE_ID").val();
					var url = '${ctx}/sysDict/rightQueryPage.do?id='+typeId;
					dataTable_2.ajax.reload();
				}
			}
		});
	}));
}

function updateSysDictRight(id){
	top.$("#ajax_lg").modal({
	    remote: "sysDict/rightForUpdate.do?id="+id+"&typeId="+$("#DIC_TYPE_ID").val()
	});
}

var dataTable_2;
var ajaxParams = {};
$(function(){
	dataTable_2 = $('#subDictListTable').DataTable({
		  /* "bFilter":false, */
		  "bStateSave": true,
	      "processing": false,
	      "serverSide": true,
	      "autoWidth": false, 
	      "searching" : false,
	      "orderCellsTop": true,//排序按钮放在列顶端,保持正常
	      "ajax" : {
	    	    "url": "sysDict/getChildPageData.do?id="+$("#DIC_TYPE_ID").val(),
		  	     "data": function(data) { // add request parameters before submit
		              $.each(ajaxParams, function(key, value) {
		                   data[key] = value;
		               });
		           }
	      },
	      "columns": [
			null,
			null,
	        {"width": "14%","data": "TEXT", "visible" : true,"bSortable": false},
	        {"width": "14%","data": "VALUE"},
	        {"width": "14%","data": "IS_USE"},
	        {"width": "14%","data": "SEQ","defaultContent": ""}
	      ],
	      "aaSorting": [[2, "desc" ]],//此句会影响汉化，是个插件的bug，所以还得跟上下面一段代码
	      "oLanguage":{ 
	          "sLengthMenu" : "显示  _MENU_ 条 "
	      },
	      "columnDefs": [
	                     {
	                    	 "width": "200px",
	                    	 "searchable": false,
	                         "orderable": false,
	                       "targets": [6],
	                       "data": "DIC_ID",
	                       "render": function(data, type, row,meta) {
	                    	   var htmlContent = "<a href='sysDict/rightForUpdate.do?id="+data+"&typeId="+$("#DIC_TYPE_ID").val()+"' data-target='#ajax_lg' data-toggle='modal'>修改</a>";
	                    	   var htmlContent = "<a href='javascript:updateSysDictRight(\""+data+"\")'>修改</a>";
	                         return htmlContent;
	                       },
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": null,
	                         "targets": [1],
	                         "render": function(data, type, row,meta) {
		                         return meta.row+dataTable_2.page.info().start+1;
		                       }
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": "DIC_ID",
	                         "targets": [0],
	                         "render": function(data, type, row,meta) {
		                         return '<div class="subDictchecker"><span class="checkSpanSubDict"><input class="checker checkboxes" type="checkbox" name="check" value="'+data+'" /></span></div>';
		                       }
	                     }
	                   ]
	    });

	$('#subDictListTable').on('change', 'tbody tr .checkboxes', function () {
		var beforeState = this.checked;
		$(this).prop("checked",this.checked);
		$(this).attr("checked",this.checked);
 		if(!this.checked){
			$("#checkAllSubDict").prop("checked", false);
			$("#checkAllSubDict").attr("checked", false);
			$("#checkAllSubDict").parents('span').removeClass("checked");
		}
		$(this).parents('span').toggleClass("checked");
        $(this).parents('tr').toggleClass("active");
        
    });
	
	//全选择 全解除
	$("#checkAllSubDict").click(function(){
	    $(".subDictchecker :checkbox").prop("checked", this.checked);
	    $(".subDictchecker :checkbox").attr("checked", this.checked);
	    if(this.checked){
	    	 $(".checkSpanSubDict").addClass("checked");
	    	 $(".checkSpanSubDict").parents('tr').addClass("active");
	    }else{
	    	$(".checkSpanSubDict").removeClass("checked");
	    	 $(".checkSpanSubDict").parents('tr').removeClass("active");
	    }
	  
	});
});


</script>
 <div class=" tabPage"> 
<!-- <div style="margin-left:8px;margin-top: 0px;min-height: 400px;padding: 0px 20px 10px;"> -->
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<table:tableTitle title="字典子项列表"/>
				<!-- <div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>字典子项列表
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse" data-original-title="" title="">
						</a>
					</div>
				</div> -->
				<div class="portlet-body">
					<table:toolbar href="sysDict/rightForUpdate.do?typeId=${record.DIC_TYPE_ID}" target="#ajax_lg" delclick="deleteDetail()"/>
					<input type="hidden" id="DIC_TYPE_ID" name="DIC_TYPE_ID" value="${record.DIC_TYPE_ID}" />
					<input type="hidden" id="TYPE_CODE" name="TYPE_CODE" value="${record.TYPE_CODE}" />
					
					<table class="table table-striped table-bordered table-hover datatable" id="subDictListTable">
						<thead>
							<tr role="row" class="heading">
								<th><input type="checkbox" id="checkAllSubDict"  class="group-checkable" data-set="#subDictListTable .checkboxes" /></th>
								<th>编号</th>
								<th>字典项</th>
								<th>字典值</th>
								<th>是否使用</th>
								<th>顺序</th>
								<th>操作</th>
							</tr>					
						</thead>
					</table>
				</div>
				
			</div>
		</div>
	</div>
</div>
