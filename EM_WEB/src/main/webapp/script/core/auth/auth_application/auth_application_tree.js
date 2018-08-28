var nodes;//节点;
var applicationTree;//树对象;
var offsetTree;//下拉树形;
var offzNodes;//下拉节点;
//var ac_RMenu;//右键菜单；
var selectAppId;//当前tree对象选中的id;
var selectAppNode;//当前tree对象选中的节点;
var params;//需要传递的参数；
var zNodes;//树形节点;
var curDragNodes, autoExpandNode;

var inShowNode;
var appSetting = {//树形配置;
	async: {
		enable: true,
		//url:getAsyncUrl,
		url:"authApplication/queryTreeNodes.do",
		autoParam:["id=pId"],
		dataFilter: null
	},
	/*async : {
		autoParam : ["id"],
		enable : true,
		type : "post",
		otherParam : {
			"name" : "name",
			"pwd" : ""
		},
		url :getAsyncUrl
	},*/
	view : {
		showTitle : false,
		fontCss : {
			color : "#104E8B"
		}
	},
	data : {
		key : {
			children : "children",
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
		onClick : meunClick,
		//onAsyncSuccess: zTreeOnAsyncSuccess,
		//beforeRightClick : beforeRightClick,
		onRightClick: OnRightClickAPP//,
//		beforeAsync:zTreeBeforeAsync,
//		onAsyncError: zTreeOnAsyncError,
//		//onAsyncSuccess:zTreeOnAsyncSuccess,
//		onRemove:zTreeOnRemove,
	}
};


//单击菜单方法;
function meunClick(event, treeId, treeNode) {
	/*var nodes = applicationTree.getSelectedNodes();*/
	selectAppNode = treeNode;
	inShowNode = treeNode;
	var url= "";
	if(treeNode.type=="ROOT"){//根节点；
		return;
	}else if(treeNode.type=="APP"){
		url="authApplication/forUpdate.do";
	}else if(treeNode.type=="FUNC_GROUP"){
		url="authFuncGroup/forUpdate.do";
	}else{
		url="authFunction/forUpdate.do";
	}
	
	if(treeNode.id!="root"){
		url+="?id="+ treeNode.id;
	}
//	window.frames.rightFrame.location.href = url;
	BaseUtils.load('appRightFrame',url);
	//document.getElementById('appRightFrame').src=url;
}


//初始化左边树形菜单;
function loadTree(url){
	windowresize();
	$.ajax({
		type: "post",
		global:false,
		url: url,
		dataType:"json",
		success: function(data) {
			zNodes=data;
			windowresize();//加载窗口大小高度;
			$.fn.zTree.init($("#applicationTree"), appSetting, zNodes);
			applicationTree = $.fn.zTree.getZTreeObj("applicationTree");
			//initContextMenu();
		}
	});
}



var inter;
//窗口大小改变监听方法;
window.onresize=function(){
	windowresize();
	inter = setInterval("setSize()","1");
}
//窗口大小改变时执行的方法;
function windowresize(){
	var height = document.documentElement.clientHeight;
}
function setSize(){
	$(window.parent.document.body).find("#appRightFrame").attr("style",$(window.parent.document.body).find("#treeDiv").attr("style"));
	clearInterval(inter);
}
$(document).ready(function() {
	var scrollHeight = window.screen.height;
	var screenWidth = window.screen.width;
	loadTree("authApplication/queryTreeNodes.do");//加载树形菜单;
});

//右键单击菜单执行方法;
function OnRightClickAPP(event, treeId, treeNode)
{
	event.preventDefault();
	if(!treeNode){
		return;
	}
	selectAppId=treeNode.id;
  	selectAppNode=treeNode;
  	applicationTree.selectNode(treeNode);
	showContextMenuForAcApp(BaseUtils.pointerX(event),BaseUtils.pointerY(event),treeNode);
}

//绑定右键方法
function showContextMenuForAcApp(x,y,treeNode){
	if(!treeNode){
		return;
	}
	var type = treeNode.type;
	var tId = treeNode.tId;
	var applicMenuId="";
	if(type=='ROOT'){
		applicMenuId = 'acAppRootContextMenu';
	}else if(type=='APP'){
		applicMenuId = 'acAppAppContextMenu';
	}else if(type=='FUNC_GROUP'){
		applicMenuId = 'acAppFuncGroupContextMenu';
	}else if(type=='FUNCTION'){
		applicMenuId = 'acAppFunctionContextMenu';
	}
	$('#'+applicMenuId).contextMenu({x: x, y: y});
}


//右键增加应用;
function forCreateApplication(){
	var pId=applicationTree.getSelectedNodes()[0].id;
	//var pname=applicationTree.getSelectedNodes()[0].name;
	var level = applicationTree.getSelectedNodes()[0].level;
	 var url = "authApplication/forUpdate.do";
	 var height = 500;
	 var width = 800;
//	 window.frames.rightFrame.location.href = url;
	 //document.getElementById('appRightFrame').src=url;
	 BaseUtils.load('appRightFrame',url);
//	 readStatus(url,"添加下级机构",width,height);
}


function forCreateFuncGroup(type){
	var appId = applicationTree.getSelectedNodes()[0].extendAttr;
	var funcGroupId= applicationTree.getSelectedNodes()[0].extendAttr1;
	var level = applicationTree.getSelectedNodes()[0].level;
	 var url = "authFuncGroup/forUpdate.do?funcGroupId="+funcGroupId+"&appId="+appId+"&level="+level;
	 var height = 500;
	 var width = 800;
	 //document.getElementById('appRightFrame').src=url;
	 BaseUtils.load('appRightFrame',url);
}

function forCreateFunction(type){
	 var funcGroupId= applicationTree.getSelectedNodes()[0].extendAttr1;
	 var url = "authFunction/forUpdate.do?funcGroupId="+funcGroupId;
	 var height = 500;
	 var width = 800;
	 //document.getElementById('appRightFrame').src=url;
	 BaseUtils.load('appRightFrame',url);
}

//重新刷新节点；
function reloadAppNode(){
	applicationTree.reAsyncChildNodes(selectAppNode, "refresh");
}


//右键删除应用；
function forDeleteApp(type){
	var name = applicationTree.getSelectedNodes()[0].name;
	if(type=='APP'){
		BaseUtils.defaultConfirm("您确认要删除选中的应用: "+name+"? <font color='red'>注意：将删除所有子项及其关联关系,且该操作不可逆。</font>",okHandlerApplication);
	}else if(type=='FUNC_GROUP'){
		BaseUtils.defaultConfirm("您确认要删除选中的功能组: "+name+"? <font color='red'>注意：将删除所有子项及其关联关系,且该操作不可逆。</font>",okHandlerFuncGroup);
	}else if(type=='FUNCTION'){
		BaseUtils.defaultConfirm("您确认要删除选中的功能: "+name+"? <font color='red'>注意：将删除所有关联关系,且该操作不可逆。</font>",okHandlerFunction);
	}
	
}
function okHandlerApplication(){
	var nodes = applicationTree.getSelectedNodes();
//	var id = nodes[0].id;
	$.ajax({
		type: "post",
		global:false,
		url:"authApplication/delete.do",
		data:{id:nodes[0].id},
		dataType:"json",
		success: function(data) {
			status=data.status;
			if(status!=0){
				BaseUtils.alert(" 操作成功！");
				var pnode=applicationTree.getNodeByParam("id",nodes[0].pId);
				applicationTree.reAsyncChildNodes(pnode, "refresh");
			}
		}
	});
}
function okHandlerFuncGroup(){
	var nodes = applicationTree.getSelectedNodes();
	$.ajax({
		type: "post",
		global:false,
		url:"authFuncGroup/delete.do",
		data:{id:nodes[0].id},
		dataType:"json",
		success: function(data) {
			status=data.status;
			if(status!=0){
				BaseUtils.alert(" 操作成功！");
				var pnode=applicationTree.getNodeByParam("id",nodes[0].pId);
				applicationTree.reAsyncChildNodes(pnode, "refresh");
			}
		}
	});
}
function okHandlerFunction(){
	var nodes = applicationTree.getSelectedNodes();
	$.ajax({
		type: "post",
		global:false,
		url:"authFunction/delete.do",
		data:{id:nodes[0].id},
		dataType:"json",
		success: function(data) {
			status=data.status;
			if(status!=0){
				BaseUtils.alert(" 操作成功！");
				var pnode=applicationTree.getNodeByParam("id",nodes[0].pId);
				applicationTree.reAsyncChildNodes(pnode, "refresh");
			}
		}
	});
}
//---------------------------------------------------------分界线---------------------------------------------------------------------
