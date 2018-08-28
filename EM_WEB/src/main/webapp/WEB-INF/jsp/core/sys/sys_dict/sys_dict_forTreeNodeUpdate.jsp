<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function(){
	//设置提交规则
	 BaseUtils.getCurrentTabElementById("${idPrefix}dictNodeForm").validate({
		rules: {
			text: {
				required: true
			},
			value: {
				required: true
			},
			isUse: {
				required: true
			},
			seq: {
				required: true,
				digits: true
			}
		},
		//改变默认的提交方法
		submitHandler:function(form){
			defaultAjaxSubmit('${idPrefix}dictNodeForm',function(data){
				//BaseUtils.alert(data.msg); 
				//阻滞写法
				BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
					if (data.flag) {
						//var sysDictTree = $.fn.zTree.getZTreeObj("dictItemTree");
						var pid ="${record.pId}";
						if(pid==''){
							pid = 'root';
						}
						var tabIdPrefix = BaseUtils.getCurrentTabIdPrefix();
		            	var pnode=null;
		            	
		            	eval("pnode="+tabIdPrefix+"sysDictTree.getNodeByParam('id',pid)");
		            	pnode.isParent = true;
		            	eval(tabIdPrefix+'sysDictTree.reAsyncChildNodes(pnode, "refresh")');
						BaseUtils.load("${idPrefix}dictItemNodeDetail","sysDict/forTreeNodeUpdate.do?id="+data.obj);
					}
				});
			});
		}

	});
});


</script>
<div class="portlet box yellow">
	<form:formTitle title="字典项编辑" />
	<div class="portlet-body form">
		<form id="${idPrefix}dictNodeForm" name="dictNodeForm" action="sysDict/rightUpdate.do" method="post" class="form-horizontal">
			<input type="hidden" id="TYPE_ID" name="typeId" value="${record.typeId}" />
			<input type="hidden" id="TYPE_CODE" name="typeCode" value="${record.typeCode}" />
			<input type="hidden" name="dicId" value="${record.dicId }" />
			<input type="hidden" name="pId" value="${record.pId}" />
			<input type="hidden" name="csrftoken" value="${csrftoken }" />
			<div class="form-body">
				<div class="row">
				<form:input2c labelName="字典项" required="true" name="text" defaultValue="${record.text}" />
				<form:input2c labelName="字典值" required="true" name="value" defaultValue="${record.value}" />
			</div>
			<div class="row">
				<form:dictSelect2c labelName="是否使用" required="true" name="isUse" cls="form-control" typeCode="YN" defaultValue="${record.isUse==null?'1':record.isUse }" />
				<form:input2c labelName="字典顺序" required="true" name="seq" defaultValue="${record.seq}" placeholder="填入整数" />
			</div>
			</div>
			<form:formSaveButton />
		</form>
	</div>
</div>
