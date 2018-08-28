<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>

<script id="sysDictForTreeMainScript" type="text/javascript">
var ${idPrefix}sysDictTree;//树对象;
var ${idPrefix}selectDictNode;
var ${idPrefix}dictSetting = {//树形配置;
	async: {
		enable: true,
		//url:getAsyncUrl,
		url:"${ctx}/sysDict/queryTreeNodesByDictType.do?dictTypeId=${dictTypeId}",
		autoParam:["id=pId"],
		dataFilter: null
	},	
	view : {
		showTitle : false,
		fontCss : {
			color : "#104E8B"
		}
	},
	data : {
		key : {
			name : "name",
			title : ""
		},
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : "-1"
		}
	},
	callback : {
		onClick : ${idPrefix}dictTreeNodeClick,
		onRightClick: ${idPrefix}dictTreeRightClick
	}
};



//单击菜单方法;
function ${idPrefix}dictTreeNodeClick(event, treeId, treeNode) {
	${idPrefix}selectDictNode = treeNode;
	var url= "";
	if(treeNode.id=="root"){//根节点
		return;
	}else{
		 url = "sysDict/forTreeNodeUpdate.do?id="+treeNode.id;
	}
	BaseUtils.load('${idPrefix}dictItemNodeDetail',url);
}


//右键单击菜单执行方法;
function ${idPrefix}dictTreeRightClick(event, treeId, treeNode){
	event.preventDefault();
	if(!treeNode){
		return;
	}
	
	${idPrefix}selectDictNode=treeNode;
	${idPrefix}sysDictTree.selectNode(treeNode);
	
	${idPrefix}showDictTreeContextMenu(event.clientX,event.clientY,treeNode);
}

//绑定右键方法
function ${idPrefix}showDictTreeContextMenu(x,y,treeNode){
	if(!treeNode){
		return;
	}
	var type = treeNode.type;
	var tId = treeNode.tId;
	var dictContextMenuId="";
	if(treeNode.id=='root'){
		dictContextMenuId = '${idPrefix}sysDictRootContextMenus';
	}else{
		dictContextMenuId = '${idPrefix}sysDictChildrenContextMenus';
	}
	$('#'+dictContextMenuId).contextMenu({x: x, y: y});
}

//初始化左边树形菜单;
function ${idPrefix}loadDictTree(url){
	$.ajax({
		async:false,
		type: "post",
		global:false,
		url: url,
		dataType:"json",
		success: function(data) {
			$.fn.zTree.init($("#${idPrefix}dictItemTree"), ${idPrefix}dictSetting, data);
			${idPrefix}sysDictTree = $.fn.zTree.getZTreeObj("${idPrefix}dictItemTree");
		}
	});
}
/* var inter;
//窗口大小改变监听方法;
window.onresize=function(){
	inter = setInterval("setSize()","1");
}
function setSize(){
	$(window.parent.document.body).find("#dictItemNodeDetail").attr("style",$(window.parent.document.body).find("#treeDiv").attr("style"));
	clearInterval(inter);
} */
$(document).ready(function() {
	/* var scrollHeight = window.screen.height;
	var screenWidth = window.screen.width; */
	${idPrefix}loadDictTree("${ctx}/sysDict/queryTreeNodesByDictType.do?dictTypeId=${dictTypeId}");//加载树形菜单;
	//全部展开
	${idPrefix}sysDictTree.expandAll(true);
});


function ${idPrefix}forCreateDictItem(){
	var pId="";
	var selectId = ${idPrefix}sysDictTree.getSelectedNodes()[0].id;
	var selectTypeId = ${idPrefix}sysDictTree.getSelectedNodes()[0].type;
	if(selectId=="root"){
	}else{
		pId = selectId;
	}
	 var url = "sysDict/forTreeNodeUpdate.do?dictTypeId="+selectTypeId+"&pId="+pId;
	 BaseUtils.load('${idPrefix}dictItemNodeDetail',url);
}


//删除
function ${idPrefix}forDeleteDictItem(){
	var id=${idPrefix}selectDictNode.id;
	url="sysDict/rightRecursiveDelete.do?ids="+id+"&typeCode=${typeCode}";

	BaseUtils.defaultConfirm("确定要删除？<font color='red'>该操作不可逆，并删除所有关联项！</font> ",(function(){
		defaultAjax(url, "", ${idPrefix}deleteDIctItemCallBackFunction);
	}));
	
}


function ${idPrefix}deleteDIctItemCallBackFunction(data){
	BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
		if (data.flag) {
			var pnode=${idPrefix}sysDictTree.getNodeByParam("id",${idPrefix}selectDictNode.pId);
			${idPrefix}sysDictTree.reAsyncChildNodes(pnode, "refresh");
		}
	});
}
//重新刷新节点；
function ${idPrefix}reloadDictNodes(){
	${idPrefix}sysDictTree.reAsyncChildNodes(${idPrefix}selectDictNode, "refresh");
}

</script>

<div class="tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="组织管理" thdLevel="机构人员"/>
	<div class="row">
		<%@include file="sys_dict_forTreeContextMenu.jsp" %>
		<sys:treeStruct treeCaption="树形结构编辑" treeId="${idPrefix}dictItemTree" />
		
		<div class="col-md-9" id="${idPrefix}dictItemNodeDetail"></div>
	</div>
</div>	