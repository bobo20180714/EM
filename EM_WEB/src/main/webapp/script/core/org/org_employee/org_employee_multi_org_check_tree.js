var multiOrgEmpCheckNodes;//节点;
var multiOrgEmpCheckSelectNode;//当前tree对象选中的节点;
var multiOrgEmpCheckTreeNodes;//树形节点;
var multiOrgEmpCheckTreeSetting = {//树形配置;
	check : {
		enable : true,
		chkboxType : {
			"Y" : "s",
			"N" : "s"
		}
	},
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
			pIdKey : "pId"//,
			//rootPId : "-1"
		}
	},
	callback: {
		onCheck: multiOrgEmpTreeOnCheck
	}
};

/*
 * 为节点：
 * 添加父信息和爷爷信息
 * 对id转换
 * 重命名
 */
function getNodeWithPPPinfo(srcNode){
	var targetNode = JSON.parse(JSON.stringify(srcNode));
	targetNode.id = srcNode.tId;
	targetNode.empId = srcNode.id;
	targetNode.realName = srcNode.name;
	//左侧树对象
	var multiOrgEmpCheckTree = $.fn.zTree.getZTreeObj("multiOrgEmployeeCheckTree");
	//右侧树对象
	var orderEmpTree = $.fn.zTree.getZTreeObj("orderEmpTree");
	var pNode =  multiOrgEmpCheckTree.getNodeByParam("id", srcNode.pId, null);  
	if(pNode==null){
		return targetNode;
	}
	targetNode.pOrgId = pNode.id;
	targetNode.pOrgName = pNode.name;
	targetNode.name = "【"+targetNode.realName+"】-【"+targetNode.pOrgName+"】";
	var ppNode = multiOrgEmpCheckTree.getNodeByParam("id", pNode.pId, null); 
	if(ppNode==null){
		return targetNode;
	}
	//对节点属性进行转换
	targetNode.ppOrgId = ppNode.id;
	targetNode.ppOrgName = ppNode.name;
	targetNode.name = targetNode.name+"-【"+targetNode.ppOrgName+"】";
	return targetNode;
}

function multiOrgEmpTreeOnCheck(event, treeId, treeNode) {
	//左侧树对象
	var multiOrgEmpCheckTree = $.fn.zTree.getZTreeObj("multiOrgEmployeeCheckTree");
	//右侧树对象
	var orderEmpTree = $.fn.zTree.getZTreeObj("orderEmpTree");
	if(treeNode.type =="EMP"){
		var node = orderEmpTree.getNodeByParam("id", treeNode.tId, null);
		if(treeNode.checked){
			if(node==null){
				node = getNodeWithPPPinfo(treeNode);
				orderEmpTree.addNodes(null,node,true);
			}
		}else{
			if(node!=null){
				orderEmpTree.removeNode(node,false);
			}
		}
	}else{
		if(treeNode.checked){
			recursiveAddChildren(treeNode,orderEmpTree,'add');
		}else{
			recursiveAddChildren(treeNode,orderEmpTree,'remove');
		}
		
	}
    
};

/**
 * 递归所有叶子节点，添加到右侧
 */
function recursiveAddChildren(treeNode,orderEmpTree,handleType){
	var childrens = treeNode.children;
	if(childrens==null||childrens.length==0){
		var nodeToRight;
		if(treeNode.type=='EMP'){
			
			nodeToRight = orderEmpTree.getNodeByParam("id", treeNode.tId, null);
			if(handleType=='add'){
				if(nodeToRight==null){
					nodeToRight = getNodeWithPPPinfo(treeNode);
					orderEmpTree.addNodes(null,nodeToRight,true);
				}
			}else{
				if(nodeToRight!=null){
					orderEmpTree.removeNode(nodeToRight,false);
				}
			}
		}
		return;
	}
	for(var i=0;i<childrens.length;i++){
		recursiveAddChildren(childrens[i],orderEmpTree,handleType)
	}
}



function loadMultiOrgEmployeeCheckTree(url){
	$.ajax({
		async : false,
		type: "post",
		url: url,
		dataType:"json",
		success: function(data) {
			multiOrgEmpCheckTreeNodes=data;
			$.fn.zTree.init($("#multiOrgEmployeeCheckTree"), multiOrgEmpCheckTreeSetting, multiOrgEmpCheckTreeNodes);
			var multiOrgEmpCheckTree = $.fn.zTree.getZTreeObj("multiOrgEmployeeCheckTree");
			multiOrgEmpCheckTree.expandAll(true);
		}
	});
}

//----------------------------------------------------------------------上左下右----------------------------------
var orderEmpTreeSetting = {//树形配置;
		edit: {
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false,
			drag:{
				isCopy:false,
				inner:false
			}
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			//beforeDrag: beforeDrag,
			//beforeDrop: beforeDrop
		}
	};

function initOrderTree(){
	$.fn.zTree.init($("#orderEmpTree"), orderEmpTreeSetting);
}