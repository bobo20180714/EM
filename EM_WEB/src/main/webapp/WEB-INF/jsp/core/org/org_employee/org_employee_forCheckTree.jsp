<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>function check tree</title>
<%@ include file="../../../common/common_header.jsp"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<%--<%@ include file="../../../common/zTree_header.jsp"%>--%>
<script type="text/javascript" src="${ctx}/script/core/org/org_employee/org_employee_check_tree.js"></script>
<script type="text/javascript">
function authRoleFilterPer(node) {
	return (node.type =="EMP"&&node.checked);
}
//提交保存时不应删除的操作员id,已废，现在只要在角色里面分配，那么原来的角色关联关系就会作废，原本是为了机构分级做的
var notInOperatorIds = "";
function authRoleCallBack(data){
	var node;
	for(var i=0;i<data.length;i++){
		node=funcZTree.getNodeByParam("extendAttr",data[i].operatorId);
		if(node==null){
			notInOperatorIds = notInOperatorIds+data[i].operatorId+",";
			continue;
		}
		funcZTree.checkNode(node,true,true,false);
	}
}
function authOperatorRoleSaveCallBack(data){
	//var ret = jQuery.parseJSON(data);
	BaseUtils.hideWaitMsg();
	//layer.close(load);
	parent.BaseUtils.alert(data.msg);
	if (data.flag) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
		parent.pageNow('${record.pageNow}');
	} 
}

var load;
function authOperatorRoleSave(){
	//等待状态
	//load =  layer.load(0, 0);
	//BaseUtils.showWaitMsg();
	var checkedFuncNodes = funcZTree.getNodesByFilter(authRoleFilterPer);
	var jsonIdStr = "";
	for(var i=0;i<checkedFuncNodes.length;i++){
		var operatorId = checkedFuncNodes[i].extendAttr;
		//去重复
		if(jsonIdStr.indexOf(operatorId) != -1){
			continue;
		}
		jsonIdStr+="{ROLE_ID:'${record.ROLE_ID}',OPERATOR_ID:'"+checkedFuncNodes[i].extendAttr+"'}";
		if(i<checkedFuncNodes.length-1){
			jsonIdStr+=",";
		}
	}
	var jsonDataPer="["+jsonIdStr+"]";
	var jsonData = {"dataJsonStr":jsonDataPer,'roleId':'${record.ROLE_ID}'};//,'notInOperatorIds':notInOperatorIds};
	//alert(jsonIdStr+"");
	defaultAjax("${ctx}/authOperatorRole/batchInsertByJson.do",jsonData,authOperatorRoleSaveCallBack);
}

//保存时不可删除的empid
var notInEmpIds = "";
function orgGroupCallBack(data){
	var node;
	for(var i=0;i<data.length;i++){
		node=funcZTree.getNodeByParam("id",data[i].empId);
		if(node==null){
			notInEmpIds = notInOperatorIds+data[i].empId+",";
			continue;
		}
		funcZTree.checkNode(node,true,true,false);
	}
}

function orgEmpGroupSave(){
	//等待状态
	//load =  layer.load(0, 0);
	//BaseUtils.showWaitMsg();
	var checkedFuncNodes = funcZTree.getNodesByFilter(authRoleFilterPer);
	var jsonIdStr = "";
	for(var i=0;i<checkedFuncNodes.length;i++){
		var operatorId = checkedFuncNodes[i].extendAttr;
		//去重复
		if(jsonIdStr.indexOf(operatorId) != -1){
			continue;
		}
		jsonIdStr+="{GROUP_ID:'${record.GROUP_ID}',EMP_ID:'"+checkedFuncNodes[i].id+"'}";
		if(i<checkedFuncNodes.length-1){
			jsonIdStr+=",";
		}
	}
	var jsonDataPer="["+jsonIdStr+"]";
	var jsonData = {"dataJsonStr":jsonDataPer,'groupId':'${record.GROUP_ID}','notInEmpIds':notInEmpIds};
	defaultAjax("${ctx}/orgEmpGroup/batchInsertByJson.do",jsonData,orgEmpGroupSaveCallBack);
}

function orgEmpGroupSaveCallBack(data){
	//var ret = jQuery.parseJSON(data);
	BaseUtils.hideWaitMsg();
	//layer.close(load);
	BaseUtils.alert(data.msg);
	if (data.flag) {
		
		var orgtreeEmp = parent.window.$.fn.zTree.getZTreeObj("orgTree_Org");
		var pnode=orgtreeEmp.getNodeByParam("id","${record.GROUP_ID}");
		pnode.isParent = true;
		orgtreeEmp.reAsyncChildNodes(pnode, "refresh");
		
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	}
	
}

$(function(){
	//序列化查询条件
	var param = $("#paramForm").serialize();
	//初始化选择树
	loadOrgEmployeeCheckTreeWithParam("${ctx}/orgEmployee/queryOrgEmpTreeNodes.do");
	var type="${record.TYPE}";
	//根据TYPE参数来断定是哪个页面调用选择树
	if(type=='AUTH_ROLE'){
		//加载默认选中
		defaultAjax("${ctx}/authOperatorRole/queryOperatorRole.do",param,authRoleCallBack);
		$("#saveButton").click(function(){
			authOperatorRoleSave();
		});
	}else if(type=='ORG_GROUP'){
		//加载默认选中
		defaultAjax("${ctx}/orgEmpGroup/queryEmpGroup.do",param,orgGroupCallBack);
		$("#saveButton").click(function(){
			orgEmpGroupSave();
		});
	}
	funcZTree.expandAll(true);
	//alert(funcZTree.getCheckedNodes());
	
});
</script>
</head>
<body style="background-color:#EEEEEE;overflow:hidden;">
<form id="paramForm">
	<input type="hidden" name="ROLE_ID" id="ROLE_ID" value="${record.ROLE_ID}"/>
	<input type="hidden" name="GROUP_ID" id="GROUP_ID" value="${record.GROUP_ID}"/>
</form>
<table border="0" id="rolefunctreetable" cellpadding="0" cellspacing="0" width="100%" height="100%" style="background-color:#f3f4f5;" align="left">
	<tr>
		<td style="padding:15px 0px 0px 10px;">
			<button type="button" id="saveButton" class="btn blue">保存</button>
			<!-- <input id="saveButton" type="button" value="保存" style="width: 15%;height:60%" class="formbtn1" /> -->
		</td>
	</tr>
	<tr>
		<td width="260px" align="left" valign="top">
			<div id="treeDiv"
				style="background-color:#f3f4f5;padding:10px 0px 0px 0px;height:400px;overflow-y:auto">
				<ul id="zTree" class="ztree" style="overflow:auto;">
				</ul>
			</div>
		</td>
	</tr>
</table>
</body>
</html>