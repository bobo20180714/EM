var orgOnlyCheckTreeNodes;//树形节点;
var ableToCheckOrgLevel;
var orgOnlyCheckTreeSetting = {//树形配置;
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
		onCheck: orgOnlyCheckTreeOnCheck
	}
};

/*
 * 为节点：
 * 添加父信息和爷爷信息
 * 对id转换
 * 重命名
 */
function getOrgNodeWithPPPinfo(srcNode){
	var targetNode = JSON.parse(JSON.stringify(srcNode));
	targetNode.id = srcNode.tId;
	targetNode.orgId = srcNode.id;
	targetNode.realName = srcNode.name;
	//左侧树对象
	var orgOnlyCheckTree = $.fn.zTree.getZTreeObj("orgOnlyCheckTree");
	//右侧树对象
	var orderOrgTree = $.fn.zTree.getZTreeObj("orderOrgTree");
	var pNode =  orgOnlyCheckTree.getNodeByParam("id", srcNode.pId, null);  
	if(pNode==null){
		return targetNode;
	}
	targetNode.pOrgId = pNode.id;
	targetNode.pOrgName = pNode.name;
	targetNode.name = "【"+targetNode.realName+"】-【"+targetNode.pOrgName+"】";
	var ppNode = orgOnlyCheckTree.getNodeByParam("id", pNode.pId, null); 
	if(ppNode==null){
		return targetNode;
	}
	//对节点属性进行转换
	targetNode.ppOrgId = ppNode.id;
	targetNode.ppOrgName = ppNode.name;
	targetNode.name = targetNode.name+"-【"+targetNode.ppOrgName+"】";
	return targetNode;
}

function orgOnlyCheckTreeOnCheck(event, treeId, treeNode) {
	//左侧树对象
	var orgOnlyCheckTree = $.fn.zTree.getZTreeObj("orgOnlyCheckTree");
	//右侧树对象
	var orderOrgTree = $.fn.zTree.getZTreeObj("orderOrgTree");
	if(treeNode.extendAttr==ableToCheckOrgLevel){
		var node = orderOrgTree.getNodeByParam("id", treeNode.tId, null);
		if(treeNode.checked){
			if(node==null){
				node = getOrgNodeWithPPPinfo(treeNode);
				orderOrgTree.addNodes(null,node,true);
			}
		}else{
			if(node!=null){
				orderOrgTree.removeNode(node,false);
			}
		}
	}else{
		if(treeNode.checked){
			recursiveAddOrgChildrenToRight(treeNode,orderOrgTree,'add');
		}else{
			recursiveAddOrgChildrenToRight(treeNode,orderOrgTree,'remove');
		}
		
	}
    
};

/**
 * 递归所有叶子节点，添加到右侧
 */
function recursiveAddOrgChildrenToRight(treeNode,orderOrgTree,handleType){
	var childrens = treeNode.children;
	if(childrens==null||childrens.length==0){
		var nodeToRight;
		if(treeNode.extendAttr==ableToCheckOrgLevel){
			
			nodeToRight = orderOrgTree.getNodeByParam("id", treeNode.tId, null);
			if(handleType=='add'){
				if(nodeToRight==null){
					nodeToRight = getOrgNodeWithPPPinfo(treeNode);
					orderOrgTree.addNodes(null,nodeToRight,true);
				}
			}else{
				if(nodeToRight!=null){
					orderOrgTree.removeNode(nodeToRight,false);
				}
			}
		}
		return;
	}
	for(var i=0;i<childrens.length;i++){
		recursiveAddOrgChildrenToRight(childrens[i],orderOrgTree,handleType)
	}
}



function loadOrgOnlyCheckTree(url,orgLevel){
	ableToCheckOrgLevel = orgLevel;
	$.ajax({
		async : false,
		type: "post",
		url: url,
		dataType:"json",
		success: function(data) {
			orgOnlyCheckTreeNodes=data;
			$.fn.zTree.init($("#orgOnlyCheckTree"), orgOnlyCheckTreeSetting, orgOnlyCheckTreeNodes);
			var orgOnlyCheckTree = $.fn.zTree.getZTreeObj("orgOnlyCheckTree");
			orgOnlyCheckTree.expandAll(true);
		}
	});
}

//----------------------------------------------------------------------上左下右----------------------------------
var orderOrgTreeSetting = {//树形配置;
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

function initOrgOrderTree(){
	$.fn.zTree.init($("#orderOrgTree"), orderOrgTreeSetting);
}