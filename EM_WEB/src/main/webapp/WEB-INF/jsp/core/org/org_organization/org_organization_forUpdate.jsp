<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	//设置提交规则
	$("#orgForm").validate({
		rules: {
			'orgCode': "required",//写法1
			orgName: {//写法2
				required: true
			},
			status:{//写法2
				required: true
			},
			sortNo:{//写法2
				required: true
			},
			email:{//写法2
				email: true
			}
		},
		messages: {
			orgCode: "机构代码不能为空！",
			orgName: {
				required: "机构名称不能为空！"
			},
			status: {
				required: "机构状态不能为空！"
			},
			sortNo: {
				required: "排列顺序必须为正整数！"
			},
			email: {
				email: "电子邮箱格式不正确！"
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('orgForm',function(data){
				//BaseUtils.alert(data.msg); 
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						var orgTree_Org = $.fn.zTree.getZTreeObj("orgTree_Org");
		            	//var pnode=orgTree_Org.getNodeByParam("id","root");
						var pid ="${record.parentOrgId }";
						if(pid==""){
							pid ="-1";
						}
		            	var pnode=orgTree_Org.getNodeByParam("id",pid);
		            	//pnode.isParent = true;
		            	orgTree_Org.reAsyncChildNodes(pnode, "refresh");
						BaseUtils.load("orgRightFrame","orgOrganization/forUpdate.do?id="+data.obj);
					}
				}); 
				
				//不阻滞写法
				/* BaseUtils.alert(data.msg); 
				if (data.flag) {
					var omTree_Org = $.fn.zTree.getZTreeObj("omTree_Org");
	            	var pnode=omTree_Org.getNodeByParam("id","root");
	            	pnode.isParent = true;
	            	omTree_Org.reAsyncChildNodes(pnode, "refresh");
					BaseUtils.load("orgRightFrame","acApplication/forUpdate.do?id="+data.obj);
				} */
			});
		}
     });
});
	
</script>
<div id="appDetailDiv" class="portlet box yellow">
	<form:formTitle title="机构详情" />
	<div class="portlet-body form">
		<form id="orgForm" name="orgForm" action="orgOrganization/update.do" method="post" class="form-horizontal">
			<input type="hidden" name="orgId" value="${record.orgId }" />
			<input type="hidden" name="orgLevel" value="${record.orgLevel }" />
			<input type="hidden" name="csrftoken" value="${csrftoken }" />
			<div class="form-body">
				<div class="row">
					<form:input2c labelName="机构代码" required="true" name="orgCode" defaultValue="${record.orgCode}"/>
					<form:input2c labelName="机构名称" required="true" name="orgName" defaultValue="${record.orgName}"/>
				</div>
				<div class="row">
					<input type="hidden" name="parentOrgId" value="${record.parentOrgId }" />
					<form:input2c labelName="上级机构" required="false" name="parentOrgName" readonly="readonly" defaultValue="${record.parentOrgName}"/>
					<form:dictSelect2c labelName="机构状态" required="true" name="status" cls="form-control" typeCode="STATUS" defaultValue="${record.status}" />
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">
							机构状态（待做成字典）
							<span class="required" aria-required="true">* </span>
							</label>
							<div class="col-md-7">
								<select class="form-control" id="STATUS" name="STATUS">
									<option value='0' ${record.STATUS=='0'?'selected':''}>默认</option>
								</select> <span class="help-block">  </span>
							</div>
						</div>
					</div> --%>
				</div>
				<div class="row">
					<form:input2c labelName="排列顺序" required="true" name="sortNo" defaultValue="${record.sortNo}"/>
					
				</div>
				<div class="row">
					<form:input2c labelName="机构地址" name="orgAddr" defaultValue="${record.orgAddr}"/>
					<form:input2c labelName="邮编" name="zipCode" defaultValue="${record.zipCode}"/>
				</div>
				<div class="row">
					<form:input2c labelName="联系人" name="linkMan" defaultValue="${record.linkMan}"/>
					<form:input2c labelName="联系电话" name="linkTel" defaultValue="${record.linkTel}"/>
				</div>
				<div class="row">
					<form:input2c labelName="电子邮箱" name="email" defaultValue="${record.email}"/>
					<form:input2c labelName="网站地址" name="webUrl" defaultValue="${record.webUrl}"/>
				</div>	
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-3 control-label">备注</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<div class="col-md-12">
								<textarea class="form-control" name="remark" row="1">${record.remark}</textarea> 
							</div>
						</div>
					</div>
				</div>		
			</div>
			<form:formSaveButton />
		</form>
	</div>
</div>
				
