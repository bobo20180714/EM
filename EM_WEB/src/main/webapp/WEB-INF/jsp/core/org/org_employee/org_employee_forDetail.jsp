<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<%-- <script type="text/javascript" src="${ctx}/script/common/formValidation.js"></script> --%>
<script type="text/javascript">

function forOrgOrganCheckTree(pId){
	var checkedOrgIdList = $('#orgIdList').val();
	layer.open({
		title:'关联人员',
	    type: 2,
	    area: ['300px', '400px'],
	    fix: false, //不固定
	    maxmin: true,
	    //content: '${ctx}/omOrganization/forCheckTree.do?pId='+pId+'&checkedOrgIdList='+checkedOrgIdList+'&onlyOrg=onlyOrg'
	    content: '${ctx}/orgOrganization/forCheckTree.do?pId=${loginEmp.orgIdList}&checkedOrgIdList='+checkedOrgIdList+'&onlyOrg=onlyOrg'
	});
}
</script>
<div id="appDetailDiv" class="portlet box yellow">
	<form:formTitle title="人员详情" />
	<div class="portlet-body form">
		<form id="detailForm" name="detailForm" action="orgEmployee/update.do" method="post" class="form-horizontal">
			<input type="hidden" name="empId" value="${record.empId }" />
			<input type="hidden" name="operatorId" value="${record.operatorId }" />
			<input type="hidden" name="orgId" value="${record.orgId }" />
			<div class="form-body">
				<div class="row">
					<input type="hidden" name="PARENT_ID" value="${record.PARENT_ID }" />
					<form:input3c required="true" labelName="人员姓名" name="EMP_NAME" defaultValue="${record.EMP_NAME}"/>
					<form:input3c required="true" labelName="人员代码" name="EMP_CODE" defaultValue="${record.EMP_CODE}" />
					<form:dictSelect3c labelName="性别" name="GENDER" typeCode="GENDER" cls="form-control" defaultValue="${record.GENDER }" />
				</div>
				<div class="row">
						<form:input3c required="true" labelName="登录用户名" name="USER_ID" defaultValue="${record.USER_ID}"/>
						<form:input3c labelName="密码</br>(不改留空)" name="PASSWORD" defaultValue="${record.PASSWORD}" />
						<form:dictSelect3c labelName="操作员状态" required="true" name="STATUS" typeCode="STATUS" cls="form-control" defaultValue="${record.STATUS }" />
				</div>
				<div class="row">
					<form:dictSelect3c labelName="人员状态" required="true" name="EMP_STATUS" typeCode="EMP_STATUS" cls="form-control" defaultValue="${record.EMP_STATUS }" />
					<form:input3c required="true" labelName="证件类型" name="CARD_TYPE" defaultValue="${record.CARD_TYPE}"/>
					<form:input3c required="true" labelName="证件号码" name="CARD_NO" defaultValue="${record.CARD_NO}"/>
				</div>
				<div class="row">
					<form:input3c labelName="出生日期" required="false" cls="form-control date date-picker" name="BIRTH_DATE" dateFmt="yyyy-mm-dd" defaultValue="${record.BIRTH_DATE}"/>
					<form:input3c labelName="入职日期" required="false" cls="form-control date date-picker" name="IN_DATE" dateFmt="yyyy-mm-dd" defaultValue="${record.IN_DATE}"/>
					<form:input3c labelName="离职日期" required="false" cls="form-control date date-picker" name="OUT_DATE" dateFmt="yyyy-mm-dd" defaultValue="${record.OUT_DATE}"/>
				</div>
				<div class="row">
					<form:input3c labelName="办公电话" name="O_TEL" defaultValue="${record.O_TEL}"/>
					<form:input3c labelName="办公邮编" name="O_ZIP_CODE" defaultValue="${record.O_ZIP_CODE}"/>
					<form:input3c labelName="办公邮箱" name="O_EMAIL" defaultValue="${record.O_EMAIL}"/>
				</div>
				<div class="row">
					<form:input3c labelName="办公地址" name="O_ADDRESS" defaultValue="${record.O_ADDRESS}"/>
					<form:input3c labelName="传真号码" name="FAX_NO" defaultValue="${record.FAX_NO}"/>
					<form:input3c labelName="手机号码" name="MOBILE_NO" defaultValue="${record.MOBILE_NO}"/>
				</div>
				<div class="row">
					<form:input3c labelName="MSN号码" required="false" name="MSN" defaultValue="${record.MSN}"/>
					<form:input3c labelName="家庭电话" required="false" name="H_TEL" defaultValue="${record.H_TEL}"/>
					<form:input3c labelName="家庭地址" required="false" name="H_ADDRESS" defaultValue="${record.H_ADDRESS}"/>
				</div>
				<div class="row">
					<form:input3c labelName="家庭邮编" required="false" name="H_ZIP_CODE" defaultValue="${record.H_ZIP_CODE}"/>
					<form:input3c labelName="私人邮箱" required="false" name="P_EMAIL" defaultValue="${record.P_EMAIL}"/>
					<form:dictSelect3c labelName="政治面貌" name="PARTY" typeCode="PARTY" cls="form-control" defaultValue="${record.PARTY }" />
				</div> 
				<div class="row">
					<form:dictSelect3c labelName="职级" name="DEGREE" typeCode="DEGREE" cls="form-control" defaultValue="${record.DEGREE }" />
					<form:dictSelect3c labelName="直接主管" name="D_SUPERVISOR" typeCode="D_SUPERVISOR" cls="form-control" defaultValue="${record.D_SUPERVISOR }" />
					<input type="hidden" name="ORG_ID_LIST" value="${record.ORG_ID_LIST }" />
					<form:input3c labelName="可管理机构" required="false" name="ORG_ID_LIST_TEXT" defaultValue="${record.ORG_ID_LIST_TEXT}" onclick="forOrgOrganCheckTree('${record.ORG_ID}')" readonly="readonly"/>
				</div>
				<div class="row">
					<form:text labelName="工作描述" name="WORK_EXP" defaultValue="${record.WORK_EXP }" />
					<%-- <div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2">工作描述
							</label>
							<div class="col-md-10">
								<input type="text" name="WORK_EXP" id="WORK_EXP" class="form-control" value="${record.WORK_EXP}"> 
							</div>
						</div>
					</div> --%>
				</div>
				<div class="row">
					<form:text labelName="备注" name="REMARK" defaultValue="${record.REMARK }" />
					<%-- <div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2">备注
							</label>
							<div class="col-md-10">
								<input type="text" name="REMARK" id="REMARK" class="form-control" value="${record.REMARK}"> 
							</div>
						</div>
					</div> --%>
				</div>
			</div>
		</form>
	</div>
</div>