<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
//设置提交规则
$("#empForm").validate({
	rules: {
		'empName': "required",//写法1
		empCode: {//写法2
			required: true
		},
		gender: {
			required: true
		},
		userId: {
			required: true
		},
		status: {
			required: true
		},
		empStatus: {
			required: true
		},
		cardType: {
			required: true
		},
		cardNo: {
			number: true,
			required: true
		},
		oEmail: {
			email: true
		},
		pEmail: {
			email: true
		},
		mobileNo: {
			minlength: 11,
			digits: true
		}
		
	},
	messages: {
		empName: "人员姓名不能为空！",
		empCode: {
			required: "人员代码不能为空！"
		},
		gender: {
			required: "性别不能为空！"
		},
		userId: {
			required: "登陆用户名不能为空！"
		},
		status: {
			required: "操作员状态不能为空！"
		},
		empStatus: {
			required: "人员状态不能为空！"
		},
		cardType: {
			required: "证件类型不能为空！"
		},
		cardNo: {
			required: "证件号码不能为空！"
		},
		oEmail: {
			email: "电子邮箱格式不正确！"
		},
		pEmail: {
			required: "电子邮箱格式不正确！"
		},
		mobileNo: {
			digits: "手机号码格式不正确！"
		}
	},
	//改变默认的提交方法
	submitHandler:function(form){
		defaultAjaxSubmit('empForm',function(data){
			//BaseUtils.alert(data.msg); 
			//阻滞写法
			BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
				if (data.flag) {
					var orgtree = $.fn.zTree.getZTreeObj("orgTree_Org");
					var pid ="${record.position }";
					if(pid==""){
						pid ="${record.orgId }";
					}
	            	var pnode=orgtree.getNodeByParam("id",pid);
	            	pnode.isParent = true;
	            	orgtree.reAsyncChildNodes(pnode, "refresh");
					BaseUtils.load("orgRightFrame","orgEmployee/forUpdate.do?id="+data.obj);
				}
			}); 
		});
	}
 });

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
	<form:formTitle title="机构人员详情" />
	<div class="portlet-body form">
		<form id="empForm" name="empForm" action="orgEmployee/update.do" method="post" class="form-horizontal">
			<input type="hidden" name="empId" value="${record.empId }" />
			<input type="hidden" name="operatorId" value="${record.operatorId }" />
			<input type="hidden" name="orgId" value="${record.orgId }" />
			<input type="hidden" name="position" value="${record.position }" />
			<input type="hidden" name="csrftoken" value="${csrftoken }" />
						<div class="form-body">
				<div class="row">
					<form:input3c required="true" labelName="人员姓名" name="empName" defaultValue="${record.empName}"/>
					<form:input3c required="true" labelName="人员代码" name="empCode" defaultValue="${record.empCode}" />
					<form:dictSelect3c labelName="性别" name="gender" typeCode="GENDER" cls="form-control" defaultValue="${record.gender }" />
				</div>
				<div class="row">
						<form:input3c required="true" labelName="登录用户名" name="userId" defaultValue="${record.userId}"/>
						<form:input3c labelName="密码</br>(不改留空)" name="password" defaultValue="" />
						<form:dictSelect3c labelName="操作员状态" required="true" name="status" typeCode="STATUS" cls="form-control" defaultValue="${oper.status }" />
				</div>
				<div class="row">
					<form:dictSelect3c labelName="人员状态" required="true" name="empStatus" typeCode="EMP_STATUS" cls="form-control" defaultValue="${record.empStatus }" />
					<form:input3c required="true" labelName="证件类型" name="cardType" defaultValue="${record.cardType}"/>
					<form:input3c required="true" labelName="证件号码" name="cardNo" defaultValue="${record.cardNo}"/>
				</div>
				<div class="row">
					<form:input3c labelName="出生日期" required="false" cls="form-control date date-picker" name="birthDate" dateFmt="yyyy-mm-dd" defaultValue="${record.birthDate}"/>
					<form:input3c labelName="入职日期" required="false" cls="form-control date date-picker" name="inDate" dateFmt="yyyy-mm-dd" defaultValue="${record.inDate}"/>
					<form:input3c labelName="离职日期" required="false" cls="form-control date date-picker" name="outDate" dateFmt="yyyy-mm-dd" defaultValue="${record.outDate}"/>
				</div>
				<div class="row">
					<form:input3c labelName="办公电话" name="oTel" defaultValue="${record.oTel}"/>
					<form:input3c labelName="办公邮编" name="oZipCode" defaultValue="${record.oZipCode}"/>
					<form:input3c labelName="办公邮箱" name="oEmail" defaultValue="${record.oEmail}"/>
				</div>
				<div class="row">
					<form:input3c labelName="办公地址" name="oAddress" defaultValue="${record.oAddress}"/>
					<form:input3c labelName="传真号码" name="faxNo" defaultValue="${record.faxNo}"/>
					<form:input3c labelName="手机号码" name="mobileNo" defaultValue="${record.mobileNo}"/>
				</div>
				<div class="row">
					<form:input3c labelName="MSN号码" required="false" name="msn" defaultValue="${record.msn}"/>
					<form:input3c labelName="家庭电话" required="false" name="hTel" defaultValue="${record.hTel}"/>
					<form:input3c labelName="家庭地址" required="false" name="hAddress" defaultValue="${record.hAddress}"/>
				</div>
				<div class="row">
					<form:input3c labelName="家庭邮编" required="false" name="hZipCode" defaultValue="${record.hZipCode}"/>
					<form:input3c labelName="私人邮箱" required="false" name="pEmail" defaultValue="${record.pEmail}"/>
					<form:dictSelect3c labelName="政治面貌" name="party" typeCode="PARTY" cls="form-control" defaultValue="${record.party }" />
				</div> 
				<div class="row">
					<form:dictSelect3c labelName="职级" name="degree" typeCode="DEGREE" cls="form-control" defaultValue="${record.degree }" />
					<form:dictSelect3c labelName="直接主管" name="dSupervisor" typeCode="D_SUPERVISOR" cls="form-control" defaultValue="${record.dSupervisor}" />
					<input type="hidden" name="orgIdList" id="orgIdList" value="${record.orgIdList }" />
					<form:input3c labelName="可管理机构" required="false" name="orgIdListText" defaultValue="${record.orgIdListText}" onclick="forOrgOrganCheckTree('${record.orgId}')" readonly="readonly"/>
				</div>
				<div class="row">
					<form:text labelName="工作描述" name="workExp" defaultValue="${record.workExp }" />
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
					<form:text labelName="备注" name="remark" defaultValue="${record.remark }" />
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
			<form:formSaveButton />
		</form>
	</div>
</div>
