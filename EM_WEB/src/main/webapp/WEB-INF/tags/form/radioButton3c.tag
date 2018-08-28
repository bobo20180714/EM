<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>

<%@ attribute name="labelName" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="required" type="java.lang.String" description="是否必填"%>

<%@ attribute name="typeCode" type="java.lang.String" required="true" description="字典code"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认选中"%>
<%@ attribute name="cls" type="java.lang.String" description="宽度：格式类似col-md-6"%>
<%@ attribute name="readonly" type="java.lang.String" description="帮助信息"%>
<%@ attribute name="onclick" type="java.lang.String" description="点击事件"%>
<%@ attribute name="name" type="java.lang.String" description="默认选中"%>
<%@ attribute name="disabled" type="java.lang.String" description="默认是否禁用"%>
<%@ attribute name="labelClass" type="java.lang.String" description="文字样式"%>
<%@ attribute name="divClass" type="java.lang.String" description="总div样式"%>
<%@ attribute name="widgetClass" type="java.lang.String" description="输入框样式"%>


<div class="${( !empty divClass) ? divClass : 'col-md-4'} ">
	<div class="form-group">

		<label class="${( !empty labelClass) ? labelClass : 'control-label col-md-4'} ">
				${labelName }
			<c:if test="${required == 'true' }">
				<span class="required" aria-required="true">*</span>
			</c:if>
		</label>
		<div class="${( !empty widgetClass) ? widgetClass : 'col-md-8'}">
			<c:if test="${not empty typeCode}">
				<div class="radio-list" data-error-container="#${name }-error-container">
				<c:forEach items="${fns:getDictList(typeCode)}" var='dict'>
					<label class="radio-inline">
						<div>
							<input type="radio" 
								name="${name }" 
								class="${(cls==''||cls==null)?'':cls }"
								${(readonly==''||readonly==null)?'':'readonly' }
								<c:if test="${onclick != '' and onclick!=null }">
									onclick="${onclick }"
								</c:if>
								<c:if test="${not empty disabled}">disabled="disabled"</c:if> 
								value="${dict.VALUE}" 
								${defaultValue == dict.VALUE || (defaultValue == true && dict.VALUE == '1') || (defaultValue == false && dict.VALUE == '0') ? 'checked="checked"' : ''}
							/>
							${dict.TEXT}
						</div>
					</label>
				</c:forEach>
				</div>
				<div id="${name }-error-container"></div>
			</c:if>
			
		</div>


	</div>
</div>