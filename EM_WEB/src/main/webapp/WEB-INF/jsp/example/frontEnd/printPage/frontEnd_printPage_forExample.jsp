<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/form_editor_header.jsp"%>
<%@ include file="../../../common/printPage_header.jsp"%>
<script type="text/javascript">
	function testPrint() {
		$("#testFormPrint").print({
		    globalStyles: true,
		    mediaPrint: false,
		    stylesheet: null,
		    noPrintSelector: ".no-print",
		    iframe: false,
		    append: null,
		    prepend: null,
		    manuallyCopyFormValues: true,
		    deferred: $.Deferred()
		});
	}
	
	/* 
	参数					默认值				接收值									描述
	globalStyles		true				Boolean									是否包含父文档的样式
	mediaPrint			false				Boolean									是否包含media='print'的链接标签。会被globalStyles选项覆盖
	stylesheet			null				URL-string								外部样式表的URL地址
	noPrintSelector		".no-print"			任何可用的jQuery选择器						不想打印的元素的jQuery选择器
	iframe				默认true，			任何可用的jQuery选择器或Boolean			是否使用一个iframe来替代打印表单的弹出窗口
						如果没有iframe选择
						器被传入会创建一个
						隐藏的iframe	
										
	append/prepend		null				Boolean									是否将用户更新的表单输入框内容作为打印内容（通过迭代每一个表单元素来实现）
	deferred			$.Deferred()		任何可用的jQuery.Deferred对象				当打印函数被调用时的jQuery.Deferred对象 
	*/
</script>

<div class=" tabPage">
		<div id="testFormPrint" class="portlet box yellow">
		<form:formTitle title="机构人员详情" />
	<div class="portlet-body form">
		<form id="empFormTest" name="empFormTest" method="post" class="form-horizontal">
				<div class="row">
					
					<div class="col-md-4 col-sm-4 col-xs-4">
						<div class="form-group">
							<label class="control-label col-md-4">
									控件
								<c:if test="${required == 'true' }">
									<span class="required" aria-required="true">* </span>
								</c:if>
							</label>
							<div class="col-md-8">
								<input type="text"  class="form-control"> 
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4"">
						<div class="form-group">
							<label class="control-label col-md-4">
									控件
								<c:if test="${required == 'true' }">
									<span class="required" aria-required="true">* </span>
								</c:if>
							</label>
							<div class="col-md-8">
								<input type="text"  class="form-control"> 
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4"">
						<div class="form-group">
							<label class="control-label col-md-4">
									控件
								<c:if test="${required == 'true' }">
									<span class="required" aria-required="true">* </span>
								</c:if>
							</label>
							<div class="col-md-8">
								<input type="text"  class="form-control"> 
							</div>
						</div>
					</div>
				</div>
				<div class="row">
						<form:input3c required="true" labelName="登录用户名" name="USER_ID" />
						<form:input3c labelName="密码</br>(不改留空)" name="PASSWORD"  />
						<form:dictSelect3c labelName="操作员状态" required="true" name="STATUS" typeCode="STATUS" cls="form-control"  />
				</div>

			</div>
			<div class="form-actions">
				<div class="row">
					<div class="col-md-offset-1 col-md-11">
						<input type="button" onclick="testPrint()" class="btn green no-print" value="确认打印" />
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
</div>
<%-- <body>
   <a class="btnPrint" href="${ctx }/acRole/forQueryPage.do">打印一</a>
</body> --%>