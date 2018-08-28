<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="labelName" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="required" type="java.lang.String" description="是否必填"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认值"%>
<%@ attribute name="name" type="java.lang.String" description="控件的id和name相同"%>
<%@ attribute name="placeholder" type="java.lang.String" description="placeholder"%>
<%@ attribute name="helpBlock" type="java.lang.String" description="帮助信息"%>
<%@ attribute name="cls" type="java.lang.String" description="input的样式类"%>
<%@ attribute name="style" type="java.lang.String" description="input的自定义style"%>
<%@ attribute name="readonly" type="java.lang.String" description="是否显示上传按钮"%>

<%@ attribute name="defaultFileList" type="java.util.ArrayList" description="已上传文件列表"%>
<%@ attribute name="width" type="java.lang.Integer" description="上传按钮宽度"%>
<%@ attribute name="height" type="java.lang.Integer" description="上传按钮高度"%>
<%@ attribute name="buttonText" type="java.lang.String" description="上传按钮显示文本"%>
<%@ attribute name="uploadLimit" type="java.lang.Integer" description="最大上传文件数量"%>
<%@ attribute name="fileType" type="java.lang.String" description="文件类型"%>
<%@ attribute name="callBack" type="java.lang.String" description="文件上传成功后JS回调函数"%>
<%@ attribute name="multi" type="java.lang.Boolean" description="是否开启多文件上传"%>
<%@ attribute name="fileSizeLimit" type="java.lang.Integer" description="文件大小限制(单位:kb,默认10000kb)"%>
<%@ attribute name="queueSizeLimit" type="java.lang.Integer" description="队列文件数量限制(默认5个)"%>
<%@ attribute name="overwriteFile" type="java.lang.Boolean" description="是否开启文件覆盖（默认关闭）"%>

<%@ attribute name="div1Class" type="java.lang.String" description="第一行的列div样式"%>
<%@ attribute name="labelClass" type="java.lang.String" description="文字样式"%>
<%@ attribute name="widgetClass" type="java.lang.String" description="输入框样式"%>

<%@ attribute name="div2Class" type="java.lang.String" description="第二行的列div样式"%>
<%@ attribute name="widget2Class" type="java.lang.String" description="第二行的输入框样式"%>

<div class="row">
	<div class="${( !empty divClass) ? divClass : 'col-md-12'} ">
		<div class="form-group">
			<label class="${( !empty labelClass) ? labelClass : 'control-label col-md-2'} ">
				${labelName }
				<c:if test="${required == 'true' }">
					<span class="required" aria-required="true">*</span>
				</c:if>
			</label>
			<c:if test="${empty readonly}">
				<div class="${( !empty widgetClass) ? widgetClass : 'col-md-10'}">
					<div class="col-md-12 ">
						<input type="file" name="file" id="${name}_buttonId" disabled="disabled"/>
					</div>
					<span class="help-block">${helpBlock }</span>
					<div id="${name }-error-container"></div>
				</div>
			</c:if>
		</div>
	</div>
</div>
<div class="row">
	<div class="${( !empty div2Class) ? div2Class : 'col-md-10 col-md-offset-2'}">
		<div class="form-group">
			<div class="${( !empty widget2Class) ? widget2Class : 'col-md-10'}">
				<div id="${name}_FileListDIV" class="annexListDIV uploadifiveWithErrorPlace" data-error-container="#${name }-error-container">
					<input type="hidden" name="${name}" id="${name}" value="${defaultValue}" />
					<ul class="clearfix">
			   			<c:forEach var="file" items="${defaultFileList}" varStatus="status">
			   				<li id='${file.FILE_ID }'>
			   					<a style="margin-right:8px;" href="${ctx }/sysFile/downloadFile.do?FILE_ID=${file.FILE_ID}" target="_blank">${file.FILE_NAME}</a>
			   					<a style="color:#E6162D" href="javascript:deleteFile('${file.FILE_ID}');">删除</a>
			   				</li>
			   			</c:forEach>
					</ul>
					<c:if test="${empty readonly}">
						<script type="text/javascript">
							$(function() {
								$('#${name}_buttonId').uploadifive({
									'auto'				: true,/*是否开启自动上传*/
							    	'fileObjName'		: 'multipartFiles',
								    'buttonText'		: '' != '${buttonText}' ? '${buttonText}' : '上传',/*上传按钮显示文本*/
							        'width'				: '' != '${width}' ? Number('${width}') : 60,/*上传按钮宽度*/
							        'height'			: '' != '${height}' ? Number('${height}') : 30,/*上传按钮高度*/
									'buttonClass'		: 'formbtn1',
									/*'queueID'			: '${name}_queueID',*/
									/*'simUploadLimit'	: 2,//允许同时上传文件数量*/
									'multi'				: '' == "${multi}" ? true : ("${multi}" == "true" ? true : false),/*是否开启多文件上传,默认开启*/
							    	'fileType'			: '' != '${fileType}' ? '${fileType}' : '',/*文件类型*/
									'fileSizeLimit'		: '' != '${fileSizeLimit}' ? Number('${fileSizeLimit}') : 10000,/*上传文件大小限制(kb)*/
									'queueSizeLimit'	: '' != '${queueSizeLimit}' ? Number('${queueSizeLimit}') : 5,/*队列最多显示的任务数量*/
							    	'uploadLimit'		: '' != '${uploadLimit}' ? Number('${uploadLimit}') : 100,/*最大上传文件数量*/
									'method'			: 'post',/*请求提交方式*/
									'uploadScript'		: '${ctx}/sysFile/batchUpload.do',/*上传请求路径*/
									'onUploadComplete'	:function(file,data){
							    		var jsonResult = eval('('+data+')');
							    		var fileObj = jsonResult.obj[0];
							    		var FILE_ID = fileObj.FILE_ID;
							    		var FILE_DOWNPATH = "${ctx}"+"/sysFile/downloadFile.do?FILE_ID="+FILE_ID;
							    		var FILE_NAME = fileObj.FILE_NAME;
							    		
							    		var htmlCode = "<li id='"+FILE_ID+"'>";
							        	htmlCode += "<a style='margin-right:8px;' href='"+FILE_DOWNPATH+"' target='_blank'>"+FILE_NAME+"</a>";
							        	htmlCode += "<a style='color:#E6162D' href='javascript:deleteFile(\""+FILE_ID+"\",\"${name}\");'>删除</a>";
							        	htmlCode += "</li>";
							        	if("${overwriteFile}" == "true"){
							        		$('#${name}_FileListDIV ul').html(htmlCode);
							        		$('#${name}').val(FILE_ID);
							        	}else{
							        		$('#${name}_FileListDIV ul').append(htmlCode);
							        		$('#${name}').val($('#${name}').val()+","+FILE_ID+",");
											$('#${name}').val(stringFormat($('#${name}').val()));
							        	}
										
										if(null != '${callBack}' && '' != '${callBack}'){
											//eval('${callBack}("'+fileObj.FILE_ID+'","'+fileObj.FILE_NAME+'","'+fileObj.FILE_TYPE+'","'+fileObj.FILE_DOWNPATH+'","'+fileObj.FILE_DOWNPATH+'")');
											eval('${callBack}('+JSON.stringify(jsonResult.obj)+')');
										}
										
										setTimeout(function(){
											$('#${name}_buttonId').uploadifive('clearQueue');
										},500);
							        },
									'onError':function(errorType,file){
										//alert(file.name+ "  " + errorMsg+"  " +errorMsg+"  "+errorString);
									}
							    });
								$("#uploadifive-${name}_buttonId input[type='file']").css("width","" != "${width}" ? "${width}px" : "60px");
								$("#uploadifive-${name}_buttonId input[type='file']").css("height","" != "${height}" ? "${height}px" : "30px");
							});
						</script>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>