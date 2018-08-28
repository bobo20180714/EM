<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="zh-cn">
<head>
<title>function check tree</title>
<%@ include file="../../../common/common_header.jsp"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/base/org/org_employee/org_employee_multi_org_check_tree.js"></script>
<script type="text/javascript">
$(function(){
	//序列化查询条件
	//var param = $("#paramForm").serialize();
	//初始化选择树
	loadMultiOrgEmployeeCheckTree("${ctx}/orgEmployee/queryMultiOrgEmpTreeNodes.do?rootOrgId=${rootOrgId}");
	//加载默认选中
	//defaultAjax("${ctx}/acOperatorRole/queryOperatorRole.do",param,acRoleCallBack);
	initOrderTree();

	<c:if test="${selectedEmpWithPidJson!=null && selectedEmpWithPidJson!=''}">
		var multiOrgEmpCheckTree = $.fn.zTree.getZTreeObj("multiOrgEmployeeCheckTree");
		var node;
		var data = JSON.parse('${selectedEmpWithPidJson}');  
		for(var i=0;i<data.length;i++){
			node=multiOrgEmpCheckTree.getNodeByParam("id",data[i].id);
			if(node!=null&&node.pId==data[i].pId){
				multiOrgEmpCheckTree.checkNode(node,true,true,true);
			}
		}
	</c:if>
});

function multiOrgEmpCheckSave(){
	var orderEmpTree = $.fn.zTree.getZTreeObj("orderEmpTree");
	var nodes = orderEmpTree.getNodes();
	var data = [];
	var temp={};
	var node;
	if(nodes!=null&&nodes.length>0){
		for(var i=0;i<nodes.length;i++){
			node = nodes[i];
			temp={};
			/* temp.EMP_ID=node.id;
			temp.EMP_NAME=node.name;
			temp.P_ORG_NAME = node.pname;
			temp.P_P_ORG_NAME =  node.ppName; */
			
			temp.id=node.empId;
			temp.name=node.realName;
			temp.pId = node.pOrgId;
			temp.pName = node.pOrgName;
			temp.ppId = node.ppOrgId;
			temp.ppName =  node.ppOrgName;
			
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
			<button type="button" id="saveButton" onclick="multiOrgEmpCheckSave();" class="btn blue">保存</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-${withOrderModule=='yes'?'6':'12' }">
			<div id="treeDiv"  class="portlet box blue">
				<div class="portlet-body">
					<!-- <ul id="multiOrgEmployeeCheckTree" class="ztree" style="overflow:auto;">
					</ul> -->
					<ul class="ztree" style="overflow:auto;">
						<li id="multiOrgEmployeeCheckTree"></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-md-6" style="display:${withOrderModule=='yes'?'block':'none' }">
			<div class="portlet box blue">
				<div class="portlet-body">
					<ul class="ztree" style="overflow:auto;">
						<li id="orderEmpTree">
							请选择左侧数据
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>