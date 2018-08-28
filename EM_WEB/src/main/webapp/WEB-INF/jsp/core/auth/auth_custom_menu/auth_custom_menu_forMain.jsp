<!-- 下面是一个树的样子，应该是放在右边 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/common/commonZTree.js"></script>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %><%-- JSP控制标签 --%>
<style>
#acCustomMenuTable tr:hover{
cursor:pointer;
}

</style>
<script type="text/javascript">
var operatorId = $('#OPERATOR_ID').val();
// below is for check tree
function forOpenTree(menuOperatorCustomCode){
	
	//input填充数据
	$('#MENU_OPERATOR_CUSTOM_CODE').val(menuOperatorCustomCode);
	var param = {"MENU_OPERATOR_CUSTOM_CODE" : menuOperatorCustomCode, "OPERATOR_ID" : operatorId};
	//初始化选择树
	loadTreeWithParam("${ctx}/authCustomMenu/queryTreeNodes.do", param,'CHECK');
	//加载默认选中
	defaultAjax("${ctx}/authCustomMenu/queryUserSelectedMenu.do", param, userCustomMenuCallBack);
	
}
/* 角色列表页面：给角色分配关联功能 ---start--后期考虑抽出js */
function userCustomMenuFilterPer(node) {
	return (node.type =="LEAF_MENU"&&node.checked);
}
function userCustomMenuCallBack(data){
	var node;
	for(var i=0;i<data.length;i++){
		node=mainTreeObj.getNodeByParam("id",data[i].MENU_ID);
		mainTreeObj.checkNode(node,true,true,false);
	}
}

function userCustomMenuSave(data){

	var jsonData = "[{'MENU_OPERATOR_CUSTOM_CODE' : '" + data + "', 'MENU_IDS' : '";
	var checkedMenuNodes = mainTreeObj.getNodesByFilter(userCustomMenuFilterPer);
	var jsonIdStr = "";
	if(checkedMenuNodes.length > 0) {
		for(var i=0;i<checkedMenuNodes.length;i++){
			
			if(i != checkedMenuNodes.length - 1) {
				jsonData+= checkedMenuNodes[i].id + ",";
			} else {
				jsonData+= checkedMenuNodes[i].id + "'";
			}
		}
	} else {
		jsonData += "'";
	}
	jsonData += "}]";
	var data = {"userCustomMenuJsonStr":jsonData};
	defaultAjax("${ctx}/authCustomMenu/updateSelectedMenuIds.do",data, selectedMenuCallBack);
	
}
function selectedMenuCallBack(data) {
	alert(data.msg);
}

function forCreateCustomMenu(data) {
	var param = {"menuOperatorCustom": data, "isCreate" : "Y"};
	//自定义菜单处理
	defaultAjax("${ctx}/authCustomMenu/genCustomMenu.do", param);
	//刷新页面
	window.location.reload();
}

function forDestoryCustomMenu(data) {
	var param = {"menuOperatorCustom": data, "isCreate" : "N"};
	//自定义菜单处理
	defaultAjax("${ctx}/authCustomMenu/genCustomMenu.do", param);
	//刷新页面
	window.location.reload();
}

function deleteAcCustomMenu() {
	var checkedList = $("input[name='check']:checked")
	if(checkedList.length==0) {
		BaseUtils.alert('请选择要删除的记录');
		return;
	}
	
	var ids = [];
	for (var i = 0; i < checkedList.length; i++) {
		ids.push($(checkedList[i]).val());
	}
	
	BaseUtils.defaultConfirm("确定删除选中的自定义菜单吗？删除后不可恢复！",(function(){
		BaseUtils.showWaitMsg();
		$.ajax({
			type: "post",
			url:'${ctx}/authCustomMenu/batchDelete.do',
			data : {
				ids : ids.join(',')
			},
			dataType:"json",
			success : function(data) {
				//var ret = jQuery.parseJSON(data);
				BaseUtils.hideWaitMsg();
				BaseUtils.alert(data.msg);
				if (data.flag) {
					acCustomMenu_dataTable.ajax.reload();
				}
			}
		});
	}));
}

var acCustomMenu_dataTable;
var ajaxParams = {"OPERATOR_ID":operatorId};
$(function(){

	$("#saveMenuButton").click(function(){
		var data = $('#MENU_OPERATOR_CUSTOM_CODE').val();
		userCustomMenuSave(data);
	});
	
	acCustomMenu_dataTable = $('#acCustomMenuTable').DataTable({
		/*  "bStateSave": true, */
		"searching" : false,
		"serverSide": true,
		"ajax" : {
					"url": "authCustomMenu/getPageData.do",
					"data": function(data) { // add request parameters before submit
						$.each(ajaxParams, function(key, value) {
							data[key] = value;
						});
					}
				},
		"columns" : [
			null,
			null,
			{"width": "30%","data": "menuOperatorCustomCode", "visible" : true,"bSortable": true, "defaultContent": "", "name":"MENU_OPERATOR_CUSTOM_CODE"}
		],
		"aaSorting" : [[ 2, "desc" ]],//此句会影响汉化，是个插件的bug，所以还得跟上下面一段代码
		"oLanguage" : { 
			"sLengthMenu" : "显示  _MENU_ 条 "
		},
		"columnDefs" : [
						{
							"width": "10%",
							"searchable": false,
							"orderable": false,
							"data": null,
							"targets": [1],
							"render": function(data, type, row,meta) {
								//每行的索引
								return meta.row + acCustomMenu_dataTable.page.info().start + 1;
							}
	                     },{
							"width": "5%",
							"searchable": false,
							"orderable": false,
							"data": "menuOperatorCustomCode",
							"targets": [0],
							"render": function(data, type, row,meta) {
								return '<div class="acCustomMenuchecker"><span class="checkSpanAcCustomchecker"><input class="checker checkboxes" type="checkbox" name="check" value="'+data+'" /></span></div>';
							}
						},{
	                    	 "width": "500px",
	                    	 "searchable": false,
	                         "orderable": false,
	                       "targets": [3],
	                       "data": "menuOperatorCustomCode",
	                       "render": function(data, type, row,meta) {
	                    	   var customMenuStatus = row.isUse;
	                    	   var htmlContent = "<a href='authCustomMenu/forUpdate.do?authCustomMenuCode="+data+"' data-target='#ajax_lg' data-toggle='modal'>修改分组名称</a>";
	                    	   htmlContent = htmlContent + "<a href='javascript:forOpenTree(\""+data+"\");'> 设置自定义菜单</a>";
	                    	   if(customMenuStatus == '1') {
									htmlContent = htmlContent + "<a href='javascript:forDestoryCustomMenu(\""+data+"\");'> 删除自定义菜单</a>";
								} else {
									htmlContent = htmlContent + "<a href='javascript:forCreateCustomMenu(\""+data+"\");'> 生成自定义菜单</a>";
								}  
	                    	   
	                    	   return htmlContent;
	                       },
	                     }
					]
		});

	$('#acCustomMenuTable').on('change', 'tbody tr .checkboxes', function () {
		var beforeState = this.checked;
		$(this).prop("checked",this.checked);
		$(this).attr("checked",this.checked);
 		if(!this.checked) {
			$("#checkAllAcCustomMenu").prop("checked", false);
			$("#checkAllAcCustomMenu").attr("checked", false);
			$("#checkAllAcCustomMenu").parents('span').removeClass("checked");
		}
		$(this).parents('span').toggleClass("checked");
        $(this).parents('tr').toggleClass("active");
        
    });	
	//全选择 全解除
	$("#checkAllAcCustomMenu").click(function() {
		$(".acCustomMenuchecker :checkbox").prop("checked", this.checked);
		$(".acCustomMenuchecker :checkbox").attr("checked", this.checked);
		if(this.checked){
			$(".checkSpanAcCustomchecker").addClass("checked");
			$(".checkSpanAcCustomchecker").parents('tr').addClass("active");
		}else{
			$(".checkSpanAcCustomchecker").removeClass("checked");
			$(".checkSpanAcCustomchecker").parents('tr').removeClass("active");
		}
	});	

});
</script>

<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="其它管理" thdLevel="自定义菜单管理"/>
	<div class="row">
		<div class="col-md-6">
			<div class="portlet box blue">
				<table:tableTitle title="用户自定义菜单列表"/>
				<div class="portlet-body">
					<table:toolbar href="authCustomMenu/forUpdate.do" target="#ajax_lg" delclick="deleteAcCustomMenu()"/>
					<table class="table table-striped table-bordered table-hover datatable" id="acCustomMenuTable">
						<thead>
							<tr role="row" class="heading">
								<th><input type="checkbox" id="checkAllAcCustomMenu"  class="group-checkable" data-set="acCustomMenuTable .checkboxes" /></th>
								<th>编号</th>
							    <th>分组名称</th>
							    <th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="portlet box blue">
			<input type="hidden" name="OPERATOR_ID" id="OPERATOR_ID" value="${record.OPERATOR_ID}"/>
				<div class="portlet-title"></div>
				<div class="portlet-body">
					<div class="row">
						<div class="col-md-2">
							<button type="button" id="saveMenuButton" class="btn blue">确定</button>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-md-6" style="text-align:left;">当前选中分组名称</label>
								<div class="col-md-6">
									<input type="text" class="form-control" name="MENU_OPERATOR_CUSTOM_CODE" id="MENU_OPERATOR_CUSTOM_CODE" value="${record.MENU_OPERATOR_CUSTOM_CODE}" readonly/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div id="treeDiv" style="padding:10px 0px 0px 0px;height:300px;overflow-y:auto">
							<ul id="zTree" class="ztree" style="overflow:auto;"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
