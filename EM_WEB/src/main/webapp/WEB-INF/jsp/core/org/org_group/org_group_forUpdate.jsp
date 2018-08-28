<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<%-- <script type="text/javascript" src="${ctx}/script/common/formValidation.js"></script> --%>
<script type="text/javascript">
function leafInfo(){
	var isLeaf=$("#isLeaf").val();
	if("1" == isLeaf){//1:是        0:否
		/* $(".leafinfo").show(); */
	} else if ("0" == isLeaf) {
		/* $(".leafinfo").hide();
		$("#FUNC_ID").val("");
		$("#FUNC_NAME").val(""); */
	}
}
$(function(){
	leafInfo()
	var a=$(window).height();
	//a-=96;(a=a-96)
	$(".center_right").css("height",a);
	
	//叶子菜单选择相应资源
	$("#isLeaf").change(function(){
		leafInfo()
	});
	})
	
$(function(){
	//设置提交规则
	$("#groupForm").validate({
		rules: {
			'groupName': "required",//写法1
			orgId: {//写法2
				required: true
			}
		},
		messages: {
			groupName: "工作组名称不能为空！",
			orgId: {
				required: "机构名称不能为空！"
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('groupForm',function(data){
				//BaseUtils.alert(data.msg); 
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						var omtree = $.fn.zTree.getZTreeObj("groupTree");
		            	var pnode=omtree.getNodeByParam("id","root");
		            	pnode.isParent = true;
		            	omtree.reAsyncChildNodes(pnode, "refresh");
						BaseUtils.load("groupRightFrame","omGroup/forUpdate.do?id="+data.obj);
					}
				}); 
				
			});
		}
     });
});

</script>
<div id="appDetailDiv" class="portlet box yellow">
	<form:formTitle title="工作组详情" />
	<div class="portlet-body form">
		<form id="groupForm" name="groupForm" action="omGroup/update.do" method="post" class="form-horizontal">
			<input type="hidden" name="orgId" value="${record.orgId }" /> 
			<input type="hidden" name="groupLevel" value="${record.groupLevel}" />
			<input type="hidden" name="csrftoken" value="${csrftoken }" />
			<div class="form-body">
				<div class="row">
					<input type="hidden" name="parentGroupId" value="${record.parentGroupId}" /> 
					<form:input2c labelName="父工作组" required="true" readonly="readonly" name="parentGroupName" defaultValue="${record.parentGroupName}"/>
					<form:input2c labelName="工作组名称" required="true" name="groupName" defaultValue="${record.groupName}"/>
				</div>
				<div class="row">
					<form:input2c labelName="工作组描述" required="false" name="groupDesc" defaultValue="${record.groupDesc}"/>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">所属机构<span class="required" aria-required="true">* </span>
							</label>
							<div class="col-md-9">
								<select class="form-control" id="ORG_ID" name="ORG_ID">
									<option value="" >请选择...      </option>
									<c:forEach var="key" items="${orgList}">
										<option value="${key.ORG_ID}" ${record.orgId==key.ORG_ID?'selected':''}>${key.ORG_NAME}</option>
				        			</c:forEach>
								</select> <span class="help-block">  </span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<form:dictSelect2c labelName="工作组类型" name="IS_MENU" cls="form-control" typeCode="GROUP_TYPE" defaultValue="${record.groupType }" />
				</div>
			<c:if test="${record.GROUP_LEVEL!=1}">
            	<div class="row">
            		<form:input2c labelName="上次更新日期" required="false" cls="form-control date date-picker" name="lastupDate" dateFmt="yyyy-mm-dd" defaultValue="${record.lastupDate}"/>
            		
					<form:dictSelect2c labelName="是否叶子工作组" required="true" name="isLeaf" cls="form-control" typeCode="YN" defaultValue="${record.isLeaf}" />
				</div>
            </c:if> 
            </div>
			<form:formSaveButton />
		</form>
	</div>
</div>
