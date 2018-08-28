<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
var log_dataTable;
var ajaxParams = {};
$(function(){
//往后台传其他参数 ajaxParams["gogogogtest"] = "yesimtest";
	log_dataTable = $('#logListTable').DataTable({
	      "serverSide": true,
	      "ajax" : {
	    	    "url": "sysLog/getPageData.do",
		  	     "data": function(data) { // add request parameters before submit
		              $.each(ajaxParams, function(key, value) {
		                   data[key] = value;
		               });
		           }
	      },
	      "columns": [
			null,
	        {"width": "15%","data": "createBy", "visible" : true,"bSortable": false,"defaultContent": ""},
	        {"width": "15%","data": "createTime","defaultContent": "","name": "CREATE_TIME"},
	        {"width": "10%","data": "remoteAddr","defaultContent": ""},
	        {"width": "20%","data": "requestUri","defaultContent": ""}//,
	        //{"width": "30%","data": "PARAMS"}//overflow:hidden; white-space:nowrap;text-overflow:ellipsis;
	      ],
	      "aaSorting": [[2, "desc" ]],//此句会影响汉化，是个插件的bug，所以还得跟上下面一段代码
	      "oLanguage":{ 
	          "sLengthMenu" : "显示  _MENU_ 条 "
	      },
	      "columnDefs": [
	                    {
	                    	 "width": "5%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": null,
	                         "targets": [0],
	                         "render": function(data, type, row,meta) {
		                         return meta.row+log_dataTable.page.info().start+1;
		                       }
	                     }/* ,
	                    {
	                    	 "width": "15%",
	                         "searchable": false,
	                         "orderable": false,
	                         "data": "PARAMS",
	                         "targets": [5],
	                         "render": function(data, type, row,meta) {
	                        	 var len=data.toString().length;
	                        	 var displayData=data;
	                        	 if(len>50){
	                        		 displayData=displayData.substring(0,50)+"...";
	                        	 }
		                         return displayData;
	                         }
	                     } */
	                   ]
	    	});
	/* $("table td").css("overflow", "hidden");
	$("table td").css("white-space", "nowrap");
	$("table td").css("text-overflow", "ellipsis"); */
});
</script>
<style type="text/css">
	td 
	{ 
	    word-break:break-all; 
	} 
</style>

<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="其他管理" thdLevel="系统操作日志"/>
	<div class="row">
		 <div class="col-md-12">
			<div class="portlet box blue">
				<table:tableTitle title="操作日志列表"/>
				<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover datatable" id="logListTable">
						<thead>
							<tr role="row" class="heading">
								<th>序号</th>
								<th>操作用户</th>
								<th>操作时间</th>
								<th>操作IP</th>
								<th>访问链接</th>
								<!-- <th>访问参数</th> -->
							</tr>					
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
	
