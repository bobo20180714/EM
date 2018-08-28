<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
function forUpdate(id){
	layer.open({
		title:'字典项新增',
	    type: 2,
	    area: ['700px', '300px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/sysDict/forUpdate.do?id='+id+''
	});
}
function deleteDict(){
	var checkedList = $("input[name='check']:checked")
	if(checkedList.length==0){
		BaseUtils.alert('请选择要删除的记录');
		return;
	}
	
	var ids = [];
	for (var i = 0; i < checkedList.length; i++) {
		ids.push($(checkedList[i]).val());
	}
	BaseUtils.defaultConfirm("你确定要删除吗？该字典的子项也一起删除，删除后不可恢复！",(function(){
		BaseUtils.showWaitMsg();
		$.ajax({
			type: "post",
			url:'${ctx}/sysDict/batchDelete.do',
			data : {
				ids : ids.join(',')
			},
			dataType:"json",
			success : function(data) {
				//var ret = jQuery.parseJSON(data);
				BaseUtils.hideWaitMsg();
				BaseUtils.alert(data.msg);
				if (data.flag) {
					dict_dataTable_.ajax.reload();
					document.getElementById("dictRightFrame").style.display="none";
				}
			}
		});
	}));
}

var dict_dataTable_;
var ajaxParams = {};
$(function(){
	dict_dataTable_ = $('#dictListTable').DataTable({
		  /* "bFilter":false, */
		  "bStateSave": true,
	      "processing": false,
	      "serverSide": true,
	      "autoWidth": false, 
	      "orderCellsTop": true,//排序按钮放在列顶端,保持正常
	      "ajax" : {
	    	    "url": "sysDict/getPageData.do",
		  	     "data": function(data) { // add request parameters before submit
		              $.each(ajaxParams, function(key, value) {
		                   data[key] = value;
		               });
		           }
	      },
	      "columns": [
			null,
			null,
	        {"width": "20%","data": "typeName", "visible" : true,"bSortable": false, "name": "TYPE_NAME"},
	        {"width": "15%","data": "typeCode"}
	      ],
	      "aaSorting": [[2, "desc" ]],//此句会影响汉化，是个插件的bug，所以还得跟上下面一段代码
	      "oLanguage":{ 
	          "sLengthMenu" : "显示  _MENU_ 条 "
	      },
	      "columnDefs": [
	                     {
	                    "width": "150px",
	                    "searchable": false,
	                    "orderable": false,
	                    "targets": [4],
	                    "data": "dicTypeId",
	                    "render": function(data, type, row,meta) {
	                    	 var htmlContent = "<a href='sysDict/forUpdate.do?id="+data+"' data-target='#ajax_lg' data-toggle='modal'>修改</a>";
	                    	 htmlContent = htmlContent + " <a href='javascript:forDetail(\""+data+"\",\""+row.typeCode+"\");'>查看子项</a>";
	                    	 htmlContent = htmlContent + " <a href='javascript:;' onclick='forTreeDetail(\""+data+"\",\""+row.typeCode+"\");' >树形编辑</a>";
	                         return htmlContent;
	                       },
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": null,
	                         "targets": [1],
	                         "render": function(data, type, row,meta) {
		                         return meta.row+dict_dataTable_.page.info().start+1;
		                       }
	                     },{
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": "dicTypeId",
	                         "targets": [0],
	                         "render": function(data, type, row,meta) {
		                         return '<div class="dictChecker"><span class="checkSpanDict"><input class="checker checkboxes" type="checkbox" name="check" value="'+data+'" /></span></div>';
		                       }
	                     }
	                     
	                     
	                   ]
	    });

	
	
	$('#dictListTable').on('change', 'tbody tr .checkboxes', function () {
		var beforeState = this.checked;
		$(this).prop("checked",this.checked);
		$(this).attr("checked",this.checked);
 		if(!this.checked){
			$("#checkAllDict").prop("checked", false);
			$("#checkAllDict").attr("checked", false);
			$("#checkAllDict").parents('span').removeClass("checked");
		}
		$(this).parents('span').toggleClass("checked");
        $(this).parents('tr').toggleClass("active");
        
    });
	
	//全选择 全解除
	$("#checkAllDict").click(function(){
	    $(".dictChecker :checkbox").prop("checked", this.checked);
	    $(".dictChecker :checkbox").attr("checked", this.checked);
	    if(this.checked){
	    	 $(".checkSpanDict").addClass("checked");
	    	 $(".checkSpanDict").parents('tr').addClass("active");
	    }else{
	    	$(".checkSpanDict").removeClass("checked");
	    	 $(".checkSpanDict").parents('tr').removeClass("active");
	    }
	    //var list = $("input[name='check']:checkbox:checked")
	    /* var list = $("input[name='check']:checked")
	    alert($(list[0]).val()); */
	});
});

function forDetail(id,typeCode){
	document.getElementById("dictRightFrame").style.display="block";
	var url = 'sysDict/rightQueryPage.do?id='+id+"&typeCode="+typeCode;
	BaseUtils.load('dictRightFrame',url);
}

function forTreeDetail(id,typeCode){
	//document.getElementById("dictRightFrame").style.display="block";
	//var url = 'sysDict/rightQueryPage.do?id='+id;
	//BaseUtils.load('dictRightFrame',url);
	//top.BaseUtils.redirectCurrentTab("sysDict/forTreeMain.do?dictTypeId="+id); 
	top.BaseUtils.addTab("字典编辑","sysDict/forTreeMain.do?dictTypeId="+id+"&typeCode="+typeCode); 
	
}
</script>
<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="其他管理" thdLevel="字典管理"/>
	<div class="row">
		<div class="col-md-6">
			<div class="portlet box blue">
				<table:tableTitle title="字典列表"/>
				
				<div class="portlet-body">
					<table:toolbar href="sysDict/forUpdate.do?id=create" target="#ajax_lg" delclick="deleteDict()"/>
					
					<table class="table table-striped table-bordered table-hover datatable" id="dictListTable">
						<thead>
							<tr role="row" class="heading">
								<th><input type="checkbox" id="checkAllDict"  class="group-checkable" data-set="#dictListTable .checkboxes" /></th>
								<th>编号</th>
								<th>字典名称</th>
								<th>字典编码</th>
								<th>操作</th>
							</tr>					
						</thead>
					</table>
				</div>
			</div>
		</div>
		
		<div class="col-md-6" id="dictRightFrame">
		</div>
	</div>
</div>


