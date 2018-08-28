<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="zh-cn">
<head>
<title>function check tree</title>
<%@ include file="../../../common/common_header.jsp"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/base/org/org_organization/org_organization_with_order_check_tree.js"></script>
<script type="text/javascript">
$(function(){
	//序列化查询条件
	//var param = $("#paramForm").serialize();
	//初始化选择树
	loadOrgOnlyCheckTree("${ctx}/orgOrganization/queryOnlyOrgTreeNodes.do?rootOrgId=${rootOrgId}&orgLevel=${orgLevel}",'${orgLevel}');
	//加载默认选中
	//defaultAjax("${ctx}/acOperatorRole/queryOperatorRole.do",param,acRoleCallBack);
	initOrgOrderTree();

	<c:if test="${selectedOrgIdJson!=null && selectedOrgIdJson!=''}">
		var orgOnlyCheckTree = $.fn.zTree.getZTreeObj("orgOnlyCheckTree");
		var node;
		var data = JSON.parse('${selectedOrgIdJson}');  
		for(var i=0;i<data.length;i++){
			node=orgOnlyCheckTree.getNodeByParam("id",data[i].id);
			if(node!=null){
				orgOnlyCheckTree.checkNode(node,true,true,true);
			}
		}
	</c:if>
});

function orgOnlyCheckSave(){
	var orderOrgTree = $.fn.zTree.getZTreeObj("orderOrgTree");
	var nodes = orderOrgTree.getNodes();
	var data = [];
	var temp={};
	var node;
	if(nodes!=null&&nodes.length>0){
		for(var i=0;i<nodes.length;i++){
			node = nodes[i];
			temp={};
			
			temp.id=node.orgId;
			temp.name=node.realName;
			temp.pId = node.pOrgId;
			temp.pName = node.pName;
			temp.ppId = node.ppOrgId;
			temp.ppName =  node.ppName;
			
			data.push(temp);
		}
	}
	
	if( '${callBackFuncName}'!='null' && '${callBackFuncName}'!='' && typeof parent.${callBackFuncName} === 'function'){
		parent.${callBackFuncName}(data);
	}else{
		alert('请定义回调函数来处理data:'+JSON.stringify(data));
	}
}

</script>
</head>
<body style="background-color:#EEEEEE;overflow:hidden;">
	<div class="row">
		<div class="col-md-12">
			<button type="button" id="saveButton" onclick="orgOnlyCheckSave();" class="btn blue">保存</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-${withOrderModule=='yes'?'6':'12' }">
			<div id="treeDiv"  class="portlet box blue">
				<div class="portlet-body">
					<!-- <ul id="orgOnlyCheckTree" class="ztree" style="overflow:auto;">
					</ul> -->
					<ul class="ztree" style="overflow:auto;">
						<li id="orgOnlyCheckTree"></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-md-6" style="display:${withOrderModule=='yes'?'block':'none' }">
			<div class="portlet box blue">
				<div class="portlet-body">
					<ul class="ztree" style="overflow:auto;">
						<li id="orderOrgTree">
							请选择左侧数据
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>