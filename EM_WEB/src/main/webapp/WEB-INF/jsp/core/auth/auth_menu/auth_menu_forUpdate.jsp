<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
function forAcFuncCheckTree(menuId,appId){
	layer.open({
		title:'功能分配',
	    type: 2,
	    area: ['300px', '400px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/authFunction/forCheckTree.do?APP_ID='+appId+'&MENU_ID='+menuId+'&TYPE=AUTH_MENU&IS_MENU=1'
	});
}
function leafInfo(){
	debugger
	var IS_LEAF=$("#isLeaf").val();
	if("1" == IS_LEAF){//1:是        0:否
		$("#leafinfo").show();
		$('#iconSkin').val('RFicon03');
	} else {
		$('#iconSkin').val('');
		$("#leafinfo").hide();
		$("#funcId").val("");
		$("#funcName").val("");
		/* $("#appid").val(""); */
		$("#menuAction").val("");
		$("#parameter").val("");
		/* $("#expandpath").val("");
		$("#imagepath").val(""); */
	}
	
	if("${record.menuLevel=='4'}"=='true'){
		$("#leafinfo").show();
		$('#ICON_SKIN').val('RFicon03');
		$('#isLeaf').val('1');
		$('#isLeaf').prop('disabled',true);
	}
	if("${record.menuLevel=='1'}"=='true'){
		$("#leafinfo").hide();
		
		$('#isLeaf').val('0');
		$('#isLeaf').prop('disabled',true);
	}
}

$(function(){
	leafInfo();

	//设置提交规则
	$("#menuForm").validate({
		rules: {
			menuName: {//写法2
				required: true
			},
			menuLabel: {
				required: true
			},
			menuCode: {
				required: true
			},
			displayOrder: {
				required: true,
				digits: true//必须输入数字
			},
			isLeaf: {
				required: true
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			$('#isLeaf').prop('disabled',false);
			defaultAjaxSubmit('menuForm',function(data){
				
				//不阻滞写法
				BaseUtils.alert(data.msg); 
				if (data.flag) {
					var omtree = $.fn.zTree.getZTreeObj("menuTree");
	            	var pnode=omtree.getNodeByParam("id","${record.parentId}");
	            	pnode.isParent = true;
	            	//alert("fresh");
	            	omtree.reAsyncChildNodes(pnode, "refresh");
	            	//alert("refresh");
					BaseUtils.load("menuRightFrame","authMenu/forUpdate.do?id="+data.obj);
				} 
			});
		}
     });
});
</script>
<div id="appDetailDiv" class="portlet box yellow">
	<form:formTitle title="菜单详情" />
	<div class="portlet-body form">
		<form id="menuForm" name="menuForm" action="authMenu/update.do" method="post" class="form-horizontal">
			<input type="hidden" id="menuId" name="menuId" value="${record.menuId }" />
			<input type="hidden" id="funcId" name="funcId" value="${record.funcId }" />
			<input type="hidden" id="appId" name="appId" value="${record.appId }" />
			<input type="hidden" id="iconSkin" name="iconSkin" value="${record.iconSkin}"/>
			<input type="hidden" name="csrftoken" value="${csrftoken }" />
			<div class="form-body">
				<div class="row">
					<input type="hidden" name="parentId" value="${record.parentId }" />
					<form:input2c labelName="父菜单" required="true" readonly="readonly" name="parentMenuName" defaultValue="${record.parentMenuName}"/>
					<form:input2c labelName="菜单名称" required="true" name="menuName" defaultValue="${record.menuName}"/>
				</div>
				<div class="row">
					<form:input2c labelName="菜单显示名称" required="true" name="menuLabel" defaultValue="${record.menuLabel}"/>
					<form:input2c labelName="菜单代码" required="true" name="menuCode" defaultValue="${record.menuCode}"/>
				</div>
				<div class="row">
					<form:input2c labelName="显示顺序" name="displayOrder" defaultValue="${record.displayOrder}"/>
					<form:input2c labelName="菜单层次" name="menuLevel" readonly="readonly" defaultValue="${record.menuLevel}"/>
				</div>
				<div class="row">
					<form:dictSelect2c onchange="leafInfo()" labelName="是否叶子菜单" required="true" name="isLeaf" cls="form-control" typeCode="YN" defaultValue="${record.isLeaf }" />
					<form:input2c labelName="菜单直接样式" name="menuCss" defaultValue="${record.menuCss}"/>
				</div>
				
				<div class="row" id="leafinfo">
					<form:input2c labelName="对应功能" name="funcName" readonly="readonly" defaultValue="${record.funcName}"  onclick="forAcFuncCheckTree('${record.menuId }','${record.appId }')"/>
					
					
					<div id="ajax-modal" class="modal fade" tabindex="-1">
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">
									资源url
							</label>
							<div class="col-md-9">
								<span id="MENU_URL">${record.menuAction}${record.parameter}</span>
								<input type="hidden" id="menuAction" name="menuAction" value="${record.menuAction}"/>
								<input type="hidden" id="parameter" name="parameter" value="${record.parameter}"/>
		                	</div>	
                		</div>
                	</div>			
                </div>
                <div class="row">
	                <c:if test="${record.isLeaf eq '1'}">
	                	<form:dictSelect2c labelName="页面打开方式" name="openMode" typeCode="OPEN_MODE" cls="form-control" defaultValue="${record.openMode == null || record.openMode == '' ? '0' : record.openMode}" /> <!-- 因为OPEN_MODE中有很多的值为空，所以当NULL或者0或者''时显示默认-->
	                </c:if>	
                </div>
			</div>
			<form:formSaveButton />
		</form>
	</div>
</div>