<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
<title>人员角色</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<%-- <meta charset="utf-8"> --%>
<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<link href="${ctx}/css/core/auth/auth_operator_role/auth_operator_role.css" rel="stylesheet" type="text/css" />

<!-- Bootstrap -->
<link href="${ctx}/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script><!-- 1.11.2版 -->
<!-- 页面dialog\Tip工具 -->
<script type="text/javascript" src="${ctx}/component/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/component/layer/extend/layer.ext.js"></script>
<!-- 为保证选择角色后点击保存正常加上的 -->
<script src="${ctx}/theme/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>   <!-- http://bootboxjs.com/documentation.html -->

<script type="text/javascript" src="${ctx}/script/common/commonUtils.js" ></script>

<!-- Bootstrap -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="${ctx}/component/bootstrap/js/html5shiv.min.js"></script>
      <script src="${ctx}/component/bootstrap/js/respond.min.js"></script>
<![endif]-->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/theme/assets/global/plugins/bootstrap/js/bootstrap.js" type="text/javascript"></script>

 <script type="text/javascript">
$(function(){	
	var a=$(window).height();
	//a-=96;(a=a-96)
	a-=96;
	$(".center_right").css("height",a);
	//移到右边
	$('#add').click(function(){
		//先判断是否有选中
		if(!$("#select1 option").is(":selected")){			
			BaseUtils.alert("请选择需要移动的选项");
		}
		//获取选中的选项，删除并追加给对方
		else{
			$('#select1 option:selected').appendTo('#select2');
		}	
	});
	
	//移到左边
	$('#remove').click(function(){
		//先判断是否有选中
		if(!$("#select2 option").is(":selected")){			
			BaseUtils.alert("请选择需要移动的选项");
		}
		else{
			$('#select2 option:selected').appendTo('#select1');
		}
	});
	
	//全部移到右边
	$('#add_all').click(function(){
		//获取全部的选项,删除并追加给对方
		$('#select1 option').appendTo('#select2');
	});
	
	//全部移到左边
	$('#remove_all').click(function(){
		$('#select2 option').appendTo('#select1');
	});
	
	//双击选项
	$('#select1').dblclick(function(){ //绑定双击事件
		//获取全部的选项,删除并追加给对方
		$("option:selected",this).appendTo('#select2'); //追加给对方
	});
	
	//双击选项
	$('#select2').dblclick(function(){
		$("option:selected",this).appendTo('#select1');
	});
	//点击保存
	$('#save').click(function(){
		$("#select2 option").attr("selected","selected");
		var operatorId = $('#operatorId').val();
		var selectrightParam = "";
		$("#select2 option").each(function(){
			selectrightParam = selectrightParam+"&selectright="+$(this).val();
			//selectright.push($(this).val()); //这里得到的就是
        });  
		
	    $.ajax({
	    	async:false,
	        url: "${ctx }/authOperatorRole/batchInsertByArray.do?operatorId="+operatorId+selectrightParam,
	        type:"post",
	        //data:$("#frm").serialize(),
	        //data:  {operatorId:operatorId,"selectright":selectright},
	        success: function(data) {
	        	BaseUtils.alertWithFunc("添加完成!",function(){
	        		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	        		parent.layer.close(index);
	        	});
	        }
	    });
	});
});
</script>
</head>
<body>

<div class="right_con">
<form id="frm" name="frm" action="" method="post">
<table class="table_juese">
 		<tr >
 			<th width="260" align="center">未授权角色</th>
 			<th width="64" align="center"></th>
 			<th width="260" align="center">已授权角色</th>
 		</tr>
 		<tr class="selectbox">
			<td class="select-bar">
			    <select multiple="multiple" style="height:100%" id="select1" name="selectleft">
			    <c:forEach var="s" items="${roleList}">
			        <option value="${s.roleId }">${s.roleName }</option>
			    </c:forEach>
			    </select>
			</td>
			<td class="btn-bar">
		    <p><span id="add"><input type="button" class="btn_juese" value=">" title="移动选择项到右侧"/></span></p>
		    <p><span id="add_all"><input type="button" class="btn_juese" value=">>" title="全部移到右侧"/></span></p>
		    <p><span id="remove"><input type="button" class="btn_juese" value="<" title="移动选择项到左侧"/></span></p>
		    <p><span id="remove_all"><input type="button" class="btn_juese" value="<<" title="全部移到左侧"/></span></p>
			</td>
			<td class="select-bar">
		   	 <select multiple="multiple" style="height:100%" id="select2" name="selectright">
			    <c:forEach var="s" items="${operatorRoleList}">
			        <option value="${s.roleId }">${s.roleName }</option>
			    </c:forEach>
		   	 </select>
			</td>	
	</tr>
	<tr>
		<td colspan="3">
		<input type="button" value="确认保存" id="save" class="formbtn1"/>
		<input type="hidden" id="operatorId" name="operatorId" value="${operatorId }"/>
		</td>
 	</tr>
 	</table>
</form>
</div>
</body>
</html>