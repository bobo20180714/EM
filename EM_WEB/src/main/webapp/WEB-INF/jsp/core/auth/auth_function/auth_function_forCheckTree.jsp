<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>function check tree</title>
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/common/commonZTree.js"></script>
<script type="text/javascript">
/* 角色列表页面：给角色分配关联功能 ---start--后期考虑抽出js */
function authRoleFilterPer(node) {
	return (node.type =="FUNCTION"&&node.checked);
}
function authRoleCallBack(data){
	var node;
	for(var i=0;i<data.length;i++){
		node=mainTreeObj.getNodeByParam("id",data[i].funcId);
		mainTreeObj.checkNode(node,true,true,false);
	}
}
function authRoleFuncSaveCallBack(data){
	//var ret = jQuery.parseJSON(data);
	BaseUtils.hideWaitMsg();
	if (data.flag) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
		parent.pageNow('${record.pageNow}');
		
	}
}
function authRoleFuncSave(){
	var checkedFuncNodes = mainTreeObj.getNodesByFilter(authRoleFilterPer);
	var jsonIdStr = "";
	debugger
	for(var i=0;i<checkedFuncNodes.length;i++){
		jsonIdStr+="{ROLE_ID:'${record.ROLE_ID}',FUNC_ID:'"+checkedFuncNodes[i].id+"',FUNC_GROUP_ID:'"+checkedFuncNodes[i].pId+"',APP_ID:'"+checkedFuncNodes[i].extendAttr+"'}";
		if(i<checkedFuncNodes.length-1){
			jsonIdStr+=",";
		}
	}
	var jsonDataPer="["+jsonIdStr+"]";
	var jsonData = {"roleFuncJsonStr":jsonDataPer,"roleId":"${record.ROLE_ID}"};
	defaultAjax("${ctx}/authRoleFunc/batchInsert.do",jsonData,authRoleFuncSaveCallBack);
	
}
/* 角色列表页面：给角色分配关联功能 ---end */

/* 菜单编辑页面：给菜单分配功能 ---start--后期考虑抽出js */
function authMenuCallBack(data){
	var node;
	for(var i=0;i<data.length;i++){
		node=mainTreeObj.getNodeByParam("id",data[i].funcId);
		if(node!=null&&node!=''){
			mainTreeObj.checkNode(node,true,true,false);
		}
		
	}
}
function authMenuSave(){
	debugger
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var nodes = mainTreeObj.getCheckedNodes(true);
	if(nodes.length==0){
		BaseUtils.alert('尚未选择');
		return;
	}
	
	parent.$('#funcId').val(nodes[0].id);
	parent.$('#funcName').val(nodes[0].name);
	parent.$('#menuAction').val(nodes[0].funcAction);
	parent.$('#parameter').val(nodes[0].paraInfo);
	parent.$('#menuUrl').text(nodes[0].funcAction+""+nodes[0].paraInfo);
	
	parent.layer.close(index);
}
/* 菜单编辑页面：给菜单分配功能 ---end */
  
 /* 机构树页面：给人员分配特殊权限 ---start--后期考虑抽出js */
function authOperatorCallBack(data){
	var node;
	for(var i=0;i<data.length;i++){
		node=mainTreeObj.getNodeByParam("id",data[i].funcId);
		mainTreeObj.checkNode(node,true,true,false);
	}
}
function authOperFuncSave(){
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var checkedFuncNodes = mainTreeObj.getNodesByFilter(authRoleFilterPer);
	var jsonIdStr = "";
	for(var i=0;i<checkedFuncNodes.length;i++){
		jsonIdStr+="{AUTH_TYPE:'1',OPERATOR_ID:'${record.OPERATOR_ID}',FUNC_ID:'"+checkedFuncNodes[i].id+"',FUNC_GROUP_ID:'"+checkedFuncNodes[i].pId+"',APP_ID:'"+checkedFuncNodes[i].extendAttr+"'}";
		if(i<checkedFuncNodes.length-1){
			jsonIdStr+=",";
		}
	}
	var jsonDataPer="["+jsonIdStr+"]";
	var jsonData = {"operFuncJsonStr":jsonDataPer,"operatorId":"${record.OPERATOR_ID}"};
	defaultAjax("${ctx}/authOperFunc/batchInsert.do",jsonData,authOperFuncSaveCallBack);
	
	
}

function authOperFuncSaveCallBack(data){
	//var ret = jQuery.parseJSON(data);
	BaseUtils.hideWaitMsg();
	parent.BaseUtils.alert(data.msg);
	if (data.flag) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	} 
}
/* 机构树页面：给人员分配特殊权限---end */
 
$(function(){
	//序列化查询条件
	var param = $("#paramForm").serialize();
	
	var type="${record.TYPE}";
	
	//根据TYPE参数来断定是哪个页面调用选择树
	if(type=='AUTH_ROLE'){
		//初始化选择树
		loadTreeWithParam("${ctx}/authFunction/queryTreeNodes.do",param,'CHECK');
		//加载默认选中
		defaultAjax("${ctx}/authRoleFunc/queryRoleFunc.do",param,authRoleCallBack);
		$("#saveButton").click(function(){
			authRoleFuncSave();
		});
	}else if(type=='AUTH_MENU'){
		//初始化选择树
		loadTreeWithParam("${ctx}/authFunction/queryTreeNodes.do",param,'RADIO');
		//加载默认选中
		defaultAjax("${ctx}/authMenu/query.do",param,authMenuCallBack);
		$("#saveButton").click(function(){
			authMenuSave();
		});
	}else if(type=='AUTH_OPERATOR'){
		//初始化选择树
		loadTreeWithParam("${ctx}/authFunction/queryTreeNodes.do",param,'CHECK');
		//加载默认选中
		defaultAjax("${ctx}/authOperFunc/queryOperFunc.do",param,authOperatorCallBack);
		$("#saveButton").click(function(){
			authOperFuncSave();
		});
	}
	
});


</script>
</head>
<body style="background-color:#EEEEEE;overflow:hidden;">
<form id="paramForm">
	<input type="hidden" name="APP_ID" id="APP_ID" value="${record.APP_ID}"/>
	<input type="hidden" name="ROLE_ID" id="ROLE_ID" value="${record.ROLE_ID}"/>
	<input type="hidden" name="MENU_ID" id="MENU_ID" value="${record.MENU_ID}"/>
	<input type="hidden" name="IS_MENU" id="IS_MENU" value="${record.IS_MENU}"/>
	<input type="hidden"  name="OPERATOR_ID" id="OPERATOR_ID" value="${record.OPERATOR_ID}"/>
</form>
<table border="0" id="rolefunctreetable" cellpadding="0" cellspacing="0" width="100%" height="100%" style="background-color:#f3f4f5;" align="left">
	<tr>
		<td style="padding:15px 0px 0px 10px;">
			<button type="button" id="saveButton" class="btn blue">确定</button>
		</td>
	</tr>
	<tr>
		<td width="260px" align="left" valign="top">
			<div id="treeDiv"
				style="background-color:#f3f4f5;padding:10px 0px 0px 0px;height:300px;overflow-y:auto">
				<ul id="zTree" class="ztree" style="overflow:auto;">
					<%-- <li>
						<img src="XXXXXX/component/ztree/css/zTreeStyle/img/diy/loading.gif"></img>
						<font size="4">正在加载菜单项，请稍候...</font>
					</li> --%>
				</ul>
			</div>
		</td>
	</tr>
</table>
</body>
</html>