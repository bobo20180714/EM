<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>function check tree</title>
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/common/commonZTree.js"></script>
<script type="text/javascript">
var load;
function confirmClick(){
	//等待状态
	load =  layer.load(0, 0);
	var checkedFuncNodes = mainTreeObj.getCheckedNodes(true)
	var jsonIdStr = "";
	for(var i=0;i<checkedFuncNodes.length;i++){
		var operatorId = checkedFuncNodes[i].extendAttr;
		//去重复
		if(jsonIdStr.indexOf(operatorId) != -1){
			continue;
		}
		jsonIdStr+="{ROLE_ID:'${record.ROLE_ID}',HOME_MODULE_ID:'"+checkedFuncNodes[i].id+"'}";
		if(i<checkedFuncNodes.length-1){
			jsonIdStr+=",";
		}
	}
	var jsonDataPer="["+jsonIdStr+"]";
	var jsonData = {"dataJsonStr":jsonDataPer,'roleId':'${record.ROLE_ID}'};
	defaultAjax("${ctx}/authHomeModule/batchInsertRoleHomeModule.do",jsonData,saveCallBack);
}

function saveCallBack(data){
	//var ret = jQuery.parseJSON(data);
	BaseUtils.hideWaitMsg();
	BaseUtils.alert(data.msg);
	if (data.flag) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		//parent.pageNow('${record.pageNow}');
		parent.layer.close(index);
	}
}

$(function(){
	//序列化查询条件
	var param = $("#paramForm").serialize();
	//初始化选择树
	loadTreeWithParam("${ctx}/authHomeModule/queryNodesForRole.do",param,'CHECK');
	$("#confirmButton").click(function(){
		confirmClick();
	});

	mainTreeObj.expandAll(true);
	
});


</script>
</head>
<body style="background-color:#EEEEEE;overflow:hidden;">
<form id="paramForm">
	<input type="hidden" name="ROLE_ID" id="ROLE_ID" value="${record.ROLE_ID}"/>
	<input type="hidden" name="APP_ID" id="APP_ID" value="${record.APP_ID}"/>
</form>
<table border="0" id="treetable" cellpadding="0" cellspacing="0" width="100%" height="100%" style="background-color:#f3f4f5;" align="left">
	<tr>
		<td style="padding:15px 0px 0px 10px;">
			<input id="confirmButton" type="button" value="确定"  class="btn btn-primary" />
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