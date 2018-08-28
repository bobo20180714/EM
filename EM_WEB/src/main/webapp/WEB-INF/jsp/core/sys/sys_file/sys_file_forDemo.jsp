<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ include file="../../../common/uploadify_header.jsp"%> --%>
<%@ include file="../../../common/uploadifive_header.jsp"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<script type="text/javascript">
$(function() {
	initDefaultUploadButton('uploadFile',120,30,'上传');
});
/* function initDefaultUploadButton(buttonId,width,height,buttonText){
    $("#"+buttonId).uploadify({
    	'fileObjName' : 'multipartFiles',
        width         : width,
        height        : height,
        swf           : '${ctx}/component//uploadify/uploadify.swf',
        uploader      : '${ctx}/sysFile/batchUpload.do',
        buttonClass   :'formbtn1',
        buttonText	  :buttonText,
        'onUploadSuccess':function(file,data,response){
        	var jsonResult = eval('('+data+')');
        	$('#fileRealId').text(jsonResult.obj[0].FILE_ID);
        	//BaseUtils.alert(jsonResult.msg);
        },
        'onUploadStart' : function(file) {
        	$("#"+buttonId).uploadify("settings", "formData", getFormData());
        }
    });
} */

function initDefaultUploadButton(buttonId,width,height,buttonText){
	$("#"+buttonId).uploadifive({
    	'fileObjName' : 'multipartFiles',
    	'auto'             : true,
        'width'         : width,
        'height'        : height,
		//'queueID'          : 'queue',
		 'buttonClass'   :'formbtn1',
	     'buttonText'	  :buttonText,
		'uploadScript'     : '${ctx}/sysFile/batchUpload.do',
		'onUploadComplete' :function(file,data){
        	var jsonResult = eval('('+data+')');
        	$('#fileRealId').text(jsonResult.obj[0].FILE_ID);
        }
    });
	/* $("#"+buttonId).uploadifive({
    	'fileObjName' : 'multipartFiles',
    	'auto'             : true,
        'width'         : width,
        'height'        : height,
		'queueID'          : 'queue',
		 'buttonClass'   :'formbtn1',
	     'buttonText'	  :buttonText,
		'uploadScript'     : '${ctx}/sysFile/batchUpload.do',
		'onUploadComplete' :function(file,data){
        	var jsonResult = eval('('+data+')');
        	$('#fileRealId').text(jsonResult.obj[0].FILE_ID);
        }
    }); */
}

//uploadify部分浏览器要求必须设置sessionid 2016年4月11日10:05:29 刘宇祥  uploadfive则不需要
function getFormData(){
	var timestamp=new Date().getTime();
	return {'ASPSESSID':'<%=request.getSession().getId()%>'};
}
<%-- $(function() {
	$("#uploadFile").uploadify({
	    'auto': true,
		'buttonText':'选择文件',
		'buttonImg':'${ctx}/component/uploadify/img/btnUploadImg.jpg',
		'fileSizeLimit':'10MB',
		'height':30,
		'width':60,
		'method':'post',
		'multi':true,
		'queueID':'fileQueue',
		'queueSizeLimit':5,//队列最多显示的任务数量
		'swf' : '${ctx}/component/uploadify/uploadify.swf',
		'uploader' : '${ctx}/xtdoc/uploadFile.do;jsessionid=<%=request.getSession().getId()%>',
    	'uploadLimit':100,//最大上传文件数量
    	'onUploadSuccess':function(file,data,response){
    		var jsonResult = eval('('+data+')');
    		var fileId = jsonResult.id;
    		var filePath = "${ctx}"+"/uploadFiles/"+jsonResult.filepath+"/"+jsonResult.filerename;
    		var fileName = file.name;
    		
    		var htmlCode = "<li id='"+fileId+"'>";
        	htmlCode += "<a style='margin-right:8px;' href='"+filePath+"'>"+fileName+"</a>";
        	htmlCode += "<a style='color:#E6162D' href='javascript:deleteFile(\""+fileId+"\");'>删除</a>";
        	htmlCode += "</li>";
        	$("#annexListDIV ul").append(htmlCode);
        	
			$("#proofMaterial").val($("#proofMaterial").val()+","+fileId+",");
			$("#proofMaterial").val(stringFormat($("#proofMaterial").val()));
    		
			/*$("#fileInfo").append("<tr id='"+jsonResult.id+"'><td>"+file.name+"</td> <td>"+jsonResult.uploadTime+
    				"</td> <td><a class='red' href=\"javascript:deleteTR('" +jsonResult.id+"','"+jsonResult.filepath+"','"+jsonResult.filerename
    						+"');\">删除</a></td><input type='hidden' name='docid' value='" +jsonResult.id+"'></tr>"); */
    	},
    	'onComplete': function(event, queueID, fileObj, response, data) {//当单个文件上传完成后触发
        },
    	'onUploadError':function(file, errorCode, errorMsg, errorString){
    		alert(file.name+ "  " + errorMsg+"  " +errorMsg+"  "+errorString);
    	},
    	'onCancel':function(file){
    	}
     });
}); --%>
</script>
<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="其他管理" thdLevel="文件上传下载示例"/>
	<form id="menuForm" name="menuForm" action="omGroup/update.do" method="post" class="form-horizontal">
	<input type="hidden" name="HOME_MODULE_ID" value="${record.HOME_MODULE_ID}" />
	<div class="modal-body form">
		<div class="form-body">
			
			<div class="row">
				<%-- <form:input2c labelName="模块标题" required="true" name="MODULE_NAME" defaultValue="${record.MODULE_NAME}" /> --%>
				<div class="col-md-12">
				<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>上传下载示例
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse" data-original-title="" title="">
						</a>
					</div>
				</div>
				</div>
					<div class="form-group">
						<label class="control-label col-md-3">上传
						</label>
						<div class="col-md-9">
							<div class="clearfix">
								<div style="width:300px;float:left;padding-top:4px;"><input type="file" name="file" id="uploadFile"/>
								<span id="fileRealId"></span>
								</div>
							</div>
							<div id="annexListDIV" class="annexListDIV">
								<ul class="clearfix">
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<%-- <form:input2c labelName="模块标题" required="true" name="MODULE_NAME" defaultValue="${record.MODULE_NAME}" /> --%>
				<div class="col-md-12">
					<label class="control-label col-md-9">下载链接示例：http://localhost:8080/platform/sysFile/downloadFile.do?FILE_ID=000fe8f4ab2f4f87bc421b8904b93785
					</label>
				</div>
			</div>
		</div>
	</div> 
	
</form>
</div>

		
<%-- <div class="right_con">
	<div class="form_box">
	<form id="menuForm" name="menuForm" action="${ctx }/omGroup/update.do" method="post">
		<table class="form_table">
            <tr>
									<td align="left">上传</td>
									<td>
										<div class="clearfix">
									    	<div style="width:300px;float:left;padding-top:4px;"><input type="file" name="file" id="uploadFile"/></div>
										</div>
								    	<div id="annexListDIV" class="annexListDIV">
								    		<ul class="clearfix">
								    		</ul>
								    	</div>
								</tr>
								<tr>
									<td colspan="2">下载链接示例：http://localhost:8080/platform/sysFile/downloadFile.do?FILE_ID=000fe8f4ab2f4f87bc421b8904b93785</td>
								</tr>
		</table>
	</form>
	</div>
</div> --%>
