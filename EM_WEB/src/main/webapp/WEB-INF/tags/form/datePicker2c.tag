<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
写法示例
<form:datePicker2c labelName="开通日期" name="OPEN_DATE"  rules="{dateFmt:'yyyy年MM月dd日 HH:mm:ss'}" defaultValue="${record.OPEN_DATE}" />
<form:datePicker2c labelName="开通日期" name="OPEN_DATE"  defaultValue="${record.OPEN_DATE}" />
rules 写法更多细节
http://www.my97.net/dp/demo/resource/2.4.asp#m241
2016年9月13日 刘宇祥
--%>

<%@ attribute name="labelName" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="required" type="java.lang.String" description="是否必填"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认值"%>
<%@ attribute name="name" type="java.lang.String" description="控件的id和name相同"%>
<%@ attribute name="placeholder" type="java.lang.String" description="placeholder"%>
<%@ attribute name="helpBlock" type="java.lang.String" description="帮助信息"%>
<%@ attribute name="cls" type="java.lang.String" description="input的样式类"%>
<%@ attribute name="onfocus" type="java.lang.String" description="点击事件"%>
<%@ attribute name="rules" type="java.lang.String" description="WdatePicker参数"%>


<div class="col-md-6">
	<div class="form-group">

		<label class="control-label col-md-3">
				${labelName }
			<c:if test="${required == 'true' }">
				<span class="required" aria-required="true">*</span>
			</c:if>
		</label>
		<div class="col-md-9">
			<div class="input-group"> 	
					<input type="text" 
						name="${name }" 
						id="${name }" 
						class="${(cls==''||cls==null)?'form-control':cls }"
						placeholder="${placeholder }"
						readonly="readonly"
						onfocus="WdatePicker(${rules });${onfocus }" 
						value="${defaultValue }"> 
					<span class="input-group-btn">
						<button class="btn default" type="button" >
							<i class="fa fa-calendar"></i>
						</button>
					</span>
			</div>
				<span class="help-block">${helpBlock }</span>
		</div>


	</div>
</div>