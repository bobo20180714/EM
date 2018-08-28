var nodes;//节点;
var orgOrgTree;//树对象;
var offsetTree;//下拉树形;
var offzNodes;//下拉节点;
//var rMenu;//右键菜单；
var selectOrgId;//当前tree对象选中的id;
var selectOrgNode;//当前tree对象选中的节点;
var params;//需要传递的参数；
var zNodes;//树形节点;
var curDragNodes, autoExpandNode;
var curDragNodesParent;
var inShowNode;
var orgSetting = {//树形配置;
	async: {
		enable: true,//【可管理机构】本段代码涉及可管理机构，暂时注释，后续重新考虑优化设计 暂时改为false
		//url:getAsyncUrl,
		url:BaseUtils.getNowUrl("orgOrganization/queryTreeNodes.do"),
		autoParam:["id=pId"],
		dataFilter: null
	},
	edit: {
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false,
		drag: {
			isCopy: true,
			isMove: true,
			prev:false,
			inner:true,
			next:false
			}
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
			rootPId : "root"
		}
	},
	callback : {
		onClick : orgNodeClick,
		onRightClick: OnRightClickOrg,
		/*拖动回调*/
        beforeDrag: beforeDrag,
        beforeDrop: beforeDrop,
        onDrag: onDrag,
        onDrop: onDrop
        //,
//		beforeAsync:zTreeBeforeAsync,
//		onAsyncError: zTreeOnAsyncError,
//		//onAsyncSuccess:zTreeOnAsyncSuccess,
//		onRemove:zTreeOnRemove,
	}
};

//控制该节点是否可移动
function beforeDrag(treeId, treeNodes) {
	var id = treeNodes[0].id;
	if(id=='root'){
		 curDragNodes = null;
         curDragNodesParent=null;
		return false;
	}
    for (var i = 0, l = treeNodes.length; i < l; i++) {
        if (treeNodes[i].drag === false) {
            curDragNodes = null;
            curDragNodesParent=null;
            return false;
        } else if (treeNodes[i].parentId && treeNodes[i].getParent().drag === false) {
            curDragNodes = null;
            curDragNodesParent=null;
            return false;
        }
    }
    curDragNodes = treeNodes;
    curDragNodesParent = orgOrgTree.getNodeByParam("id", curDragNodes[0].pId, null);
    return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
	var id = treeNodes[0].id;
	var type=treeNodes[0].type;
	var targetType = targetNode.type;
	if(id=='root'){
		return false;
	}
	
	if(type=="ORG"){//组织机构级别;
		 if(targetType!="ORG"){//组织机构级别;
			 alert("组织机构只能移动到组织机构下!");
			 return false;
		}
	}else if(type=="POSI"){//岗位级别；
		 if(targetType=="EMP"){//人员级别;
			 alert("岗位不能移动到人员下!");
			 return false;
		}
	} else if (type=="EMP") {//人员级别
		if(targetType=="EMP"){
			 alert("人员不能移动或复制到人员下!");
			 return false;
		}
	}
	
    if(isCopy){//复制
    	if(type != "EMP"){
    		alert("机构人员以外不能复制!");
			return false;
    	}
    	if(targetType!="POSI"){
    		//暂时不允许一人多机构,同时人员不允许复制到人员下
    		alert("机构人员只能复制到岗位下!");
			return false;
    	}
    	if(targetType=="POSI"&&curDragNodesParent.type=="ORG"){
    		//暂时不允许一人多机构,同时人员不允许复制到人员下
    		alert("机构人员从机构只能移动到岗位，不能复制!");
			return false;
    	}
        if(!confirm("您确定要复制“"+treeNodes[0].name+"”到“"+targetNode.name+"”下吗？")) {
        	//不复制
        	return false;
        }
    }else{//移动
        if(!confirm("您确定要移动“"+treeNodes[0].name+"”到“"+targetNode.name+"”下吗？")) {
        	//不移动
        	return false;
        }
        if(targetType=="ORG"&&curDragNodesParent.type=="POSI"){
        	if(!confirm("人员变更机构，如果之前该人员存在多个岗位，将全部作废，确定要移动？")) {
            	//不移动
            	return false;
            }
        }
    }
    return true;
}
function onDrag(event, treeId, treeNodes) {
	
}
function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
	var id = treeNodes[0].id;
	var type=treeNodes[0].type;
	var fromId = curDragNodesParent.id;
	var fromType = curDragNodesParent.type;
	var toId = targetNode.id;
	var toType = targetNode.type;
	$.ajax({
		url: "orgOrganization/adjustByTree.do", 
		data: { 
				id: id,
				type: type,
				fromId: fromId,
				fromType: fromType,
				toId: toId,
				toType: toType,
				isCopy:isCopy
				},
		type: 'POST',
		dataType:"json",
		success: function (data) {
			//loadTree("organization/loadtree.do");//加载树形菜单;
			if(data.flag){
				//loadTree("queryTreeNodes.do?init=init");
			}else{
				alert(data.msg);
			}
			
		}
	});

}

//单击菜单方法;
function orgNodeClick(event, treeId, treeNode) {
	/*var nodes = omOrgTree.getSelectedNodes();*/
	selectOrgNode = treeNode;
	inShowNode = treeNode;
	var url= "";
	if(treeNode.id=="root"){//根节点；
		return;
	}else if(treeNode.type=="ORG"){//组织机构级别;
		url="orgOrganization/forUpdate.do";
	}else if(treeNode.type=="POSI"){//岗位级别；
		url="orgPosition/forUpdate.do";
	}else{//人员级别；
		url="orgEmployee/forUpdate.do";
	}
	
	if(treeNode.id!="root"){
		url+="?id="+ treeNode.id;
	}
//	window.frames.rightFrame.location.href = url;
	BaseUtils.load('orgRightFrame',url);
}


//初始化左边树形菜单;
function loadTree(url){
	windowresize();
	/*zNodes = [
	        { "id":"1", pId:0, name:"父1", open:true,iconSkin:""},
			{ id:11, pId:1, name:"父11", iconSkin:"OpIcon02"},
			{ id:111, pId:11, name:"叶111", iconSkin:"OpIcon02"},
			{ id:112, pId:11, name:"叶112", iconSkin:"OpIcon02"},
			{ id:113, pId:11, name:"叶113", iconSkin:"OpIcon02"},
			{ id:114, pId:11, name:"叶114", type:"parent", iconSkin:"OpIcon02"},
			{ id:12, pId:1, name:"叶12"}
	  	];*/
	/*zNodes = [{"id":"root","pId":"-1","name":"机构人员树","type":"ORG","checked":false,"iconSkin":"OpIcon02","attributes":null,"parent":true,open:true},
	          {"id": "000B9F7C-0000-0000-0000-00004D545C34","pId": "root","name": "山东省科学院","type": "ORG","checked": false,"iconSkin": "OpIcon02","attributes": null,"parent": true},
	          {"id": "51ab80a0-96c8-4b8d-af19-184a08a55f59","pId": "root","name": "测试1","type": "ORG","checked": false,"iconSkin": "OpIcon02","attributes": null,"parent": true}];
	*/
	$.ajax({
		async:false,
		type: "post",
		global:false,
		url: url,
		dataType:"json",
		success: function(data) {
			zNodes=data;
			windowresize();//加载窗口大小高度;
			$.fn.zTree.init($("#orgTree_Org"), orgSetting, zNodes);
			orgOrgTree = $.fn.zTree.getZTreeObj("orgTree_Org");

		}
	});
//	furl="organization/showOrgEmp.do";//右侧加载页面
//	document.getElementById('orgRightFrame').src=furl;
//	//window.frames.rightFrame.location.href = furl;
//	rMenu = $("#rMenu");
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
	$(window.parent.document.body).find("#orgRightFrame").attr("style",$(window.parent.document.body).find("#treeDiv").attr("style"));
	clearInterval(inter);
}
$(document).ready(function() {
	var scrollHeight = window.screen.height;
	var screenWidth = window.screen.width;
	/*if(navigator.userAgent.indexOf("MSIE")>0)
	{ 
	    if(navigator.userAgent.indexOf("MSIE 6.0")>0)
	    { 
	    	flag = false;
	    } 
	    if(navigator.userAgent.indexOf("MSIE 7.0")>0)
	    {
	    	flag = false;
	    } 
	    if(navigator.userAgent.indexOf("MSIE 8.0")>0)
	    {
	    	scrollHeight-=201;
	    } 
	    if(navigator.userAgent.indexOf("MSIE 9.0")>0)
	    {
	    	scrollHeight-=201;
	    } 
	 }else{
	     flag = false;
	 } */
	//$("#ac_menu_guid_jsp1").parents("table:first").css({"margin-top":"-10px","margin-left":"-10px"});
//	$("#omTree").css({"height":$(window.parent.rightFrame).innerHeight()-50,"width":210});
//	$("#orgRightFrame").css({"height":$(window.parent.rightFrame).innerHeight()-50,"width":window.screen.width-$(window.parent.leftFrame).innerWidth() - 235});
	loadTree("orgOrganization/queryTreeNodes.do?init=init");//加载树形菜单;
	
});


//右键单击菜单执行方法;
function OnRightClickOrg(event, treeId, treeNode)
{
	event.preventDefault();
	if(!treeNode){
		return;
	}
	selectOrgId=treeNode.id;
  	selectOrgNode=treeNode;
  	orgOrgTree.selectNode(treeNode);
	
	showOrgContextMenu(BaseUtils.pointerX(event),BaseUtils.pointerY(event),treeNode);
}

//绑定右键方法
function showOrgContextMenu(x,y,treeNode){
	if(!treeNode){
		return;
	}
	var type = treeNode.type;
	var tId = treeNode.tId;
	var orgContextMenuId="";
	if(treeNode.id=='root'){
		orgContextMenuId = 'orgOrgRootContextMenus';
	}else if(type=='ORG'){
		orgContextMenuId = 'orgOrgOrgContextMenus';
	}else if(type=='POSI'){
		orgContextMenuId = 'orgOrgPosiContextMenus';
	}else if(type=='EMP'){
		orgContextMenuId = 'orgOrgEmpContextMenus';
	}
	$('#'+orgContextMenuId).contextMenu({x: x, y: y});
}

//右键增加下级机构方法;
function forCreateChildOrg(){
	
	var pId=orgOrgTree.getSelectedNodes()[0].id;
	var pname=orgOrgTree.getSelectedNodes()[0].name;
	var level = orgOrgTree.getSelectedNodes()[0].level;
	 var url = "orgOrganization/forUpdate.do?pId="+pId+"&level="+level+"&pname="+pname;
	 var height = 500;
	 var width = 800;
//	 window.frames.rightFrame.location.href = url;
//	 document.getElementById('orgRightFrame').src=url;
	 BaseUtils.load('orgRightFrame',url);
//	 readStatus(url,"添加下级机构",width,height);
}


function forCreateChildPosi(type){
	var orgId = orgOrgTree.getSelectedNodes()[0].extendAttr;
	var posiId= orgOrgTree.getSelectedNodes()[0].extendAttr1;
	var level = orgOrgTree.getSelectedNodes()[0].level;
	var orgname=orgOrgTree.getSelectedNodes()[0].name;
	 var url = "orgPosition/forUpdate.do?orgId="+orgId+"&posiId="+posiId+"&level="+level+"&orgname="+orgname;
	 var height = 500;
	 var width = 800;
	 BaseUtils.load('orgRightFrame',url);
}

function forCreateChildEmp(type){
	var orgId = orgOrgTree.getSelectedNodes()[0].extendAttr;
	var posiId= orgOrgTree.getSelectedNodes()[0].extendAttr1;
	var level = orgOrgTree.getSelectedNodes()[0].level;
	 var url = "orgEmployee/forUpdate.do?orgId="+orgId+"&posiId="+posiId+"&level="+level;
	 var height = 500;
	 var width = 800;
	 BaseUtils.load('orgRightFrame',url);
}

//维护人员角色
function forOperatorRole(){
	
	var id=orgOrgTree.getSelectedNodes()[0].id;
	var url="authOperatorRole/forUpdate.do?empId="+id;
	defaultDialog('角色分配',url,'650px','480px');
	
//	layer.open({
//	title:'角色分配',
//    type: 2,
//    area: ['650px', '480px'],
//    fix: false, //不固定
//    maxmin: true,
//    content: "${ctx}/acOperatorRole/forUpdate.do?empId="+id
//});
	
	var nodes = orgOrgTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}
}
//维护特殊权限
function forOperFunc(){
	var id=orgOrgTree.getSelectedNodes()[0].id;
	defaultDialog('功能分配','authFunction/forCheckTree.do?TYPE=AUTH_OPERATOR&OPERATOR_ID='+id,'300px','400px');
//	layer.open({
//		title:'功能分配',
//	    type: 2,
//	    area: ['300px', '400px'],
//	    fix: false, //不固定
//	    maxmin: true,
//	    content: '${ctx}/acFunction/forCheckTree.do?TYPE=AC_OPERATOR&OPERATOR_ID='+id
//	});
}

//删除
function forDelete(type){
	var id=selectOrgNode.id;
	var pId=selectOrgNode.pId;
	var url = "";
	if(type=='ORG'){
		url="orgOrganization/delete.do?id="+id;
	}else if(type=='POSI'){
		url="orgPosition/delete.do?id="+id;
	}else if(type=='EMP'){
		url="orgEmployee/delete.do?id="+id+"&pId="+pId;
	}
	//var name = omOrgTree.getSelectedNodes()[0].name;
//	if(type=='PARENT_MENU'){
	//BaseUtils.defaultConfirm("您确认要删除选中的岗位: "+name+"? 注意：将删除所有关联项。",okHandlerParent(url));
	
	BaseUtils.defaultConfirm("确定要删除？<font color='red'>该操作不可逆，并删除所有关联项！</font> ",(function(){
		defaultAjax(url, "", deleteCallBackFunction);
	}));
	
}
/*function okHandlerParent(urlParam){
	var nodes = omOrgTree.getSelectedNodes();
	alert(urlParam);
	$.ajax({
		type: "post",
		global:false,
		url:urlParam,
		data:{id:nodes[0].id},
		dataType:"json",
		success: function(data) {
			status=data.status;
			if(status!=0){
				alert(" 操作成功！");
				var pnode=omOrgTree.getNodeByParam("id",nodes[0].pId);
				omOrgTree.reAsyncChildNodes(pnode, "refresh");
			}
		}
	});
}*/

function deleteCallBackFunction(data){
	BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
		if (data.flag) {
			var pnode=orgOrgTree.getNodeByParam("id",selectOrgNode.pId);
			orgOrgTree.reAsyncChildNodes(pnode, "refresh");
		}
	});
}
//重新刷新节点；
function reloadNode(){
	orgOrgTree.reAsyncChildNodes(selectOrgNode, "refresh");
}

//---------------------------------------------------------分界线---------------------------------------------------------------------
