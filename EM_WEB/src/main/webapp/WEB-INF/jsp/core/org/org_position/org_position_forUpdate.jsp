<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	//设置提交规则
	$("#posiForm").validate({
		rules: {
			'posiCode': "required",//写法1
			posiName: {//写法2
				required: true
			}
		},
		messages: {
			posiCode: "岗位代码不能为空！",
			posiName: {
				required: "岗位名称不能为空！"
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			if(!checkdate())
				return;
			 defaultAjaxSubmit('posiForm',function(data){
				//BaseUtils.alert(data.msg); 
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						var orgtreePosi = $.fn.zTree.getZTreeObj("orgTree_Org");
						var pid ="${record.parentPosiId }";
						if(pid==""){
							pid ="${record.orgId }";
						}
		            	var pnode=orgtreePosi.getNodeByParam("id",pid);
		            	pnode.isParent = true;
		            	orgtreePosi.reAsyncChildNodes(pnode, "refresh");
						BaseUtils.load("orgRightFrame","orgPosition/forUpdate.do?id="+data.obj);
					}
				}); 
				
			}); 
		}
     });
}); 
function checkdate()
{   
//得到日期值并转化成日期格式，replace(/\-/g, "\/")是根据验证表达式把日期转化成长日期格式，这样
//再进行判断就好判断了
    var sDate = new Date(document.getElementById("startDate").value.replace(/\-/g, "\/"));
	 var eDate = new Date(document.getElementById("endDate").value.replace(/\-/g, "\/"));
	 if(sDate > eDate)
	 {
	 	alert("失效日期不能小于生效日期！");
	   	return false;
	}
	 return true;
}  
</script>
<div id="appDetailDiv" class="portlet box yellow">
	<form:formTitle title="岗位详情" />
	<div class="portlet-body form">
		<form id="posiForm" name="posiForm" action="orgPosition/update.do" method="post" class="form-horizontal">
			<input type="hidden" name="positionId" value="${record.positionId }" />
			<input type="hidden" name="posiLevel" value="${record.posiLevel }" />
			<input type="hidden" name="parentPosiId" value="${record.parentPosiId }" />
			<input type="hidden" name="orgId" value="${record.orgId }" />
			<input type="hidden" name="csrftoken" value="${csrftoken }" />
			<div class="form-body">
				<div class="row">
					<form:input2c labelName="岗位代码" required="true" name="posiCode" defaultValue="${record.posiCode}"/>
					<form:input2c labelName="岗位名称" required="true" name="posiName" defaultValue="${record.posiName}"/>
				</div>
				<div class="row">
					<form:input2c labelName="隶属机构" readonly="readonly" required="true" name="orgName" defaultValue="${record.orgName}"/>
				</div>
				<div class="row">
					<form:dictSelect2c labelName="岗位类别" name="posiType" cls="form-control" typeCode="POSI_TYPE" defaultValue="${record.posiType }" />
					<form:dictSelect2c labelName="岗位状态" name="status" cls="form-control" typeCode="STATUS" defaultValue="${record.status }" />
				</div>
				<div class="row">
					<form:input2c labelName="生效日期" required="false" cls="form-control date date-picker" name="startDate" dateFmt="yyyy-mm-dd" defaultValue="${record.startDate}"/>
					<form:input2c labelName="失效日期" required="false" cls="form-control date date-picker" name="endDate" dateFmt="yyyy-mm-dd" defaultValue="${record.endDate}"/>
				</div>
			</div>
			<form:formSaveButton />
		</form>
	</div>
</div>