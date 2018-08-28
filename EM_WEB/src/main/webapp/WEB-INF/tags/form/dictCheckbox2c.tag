<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="required" type="java.lang.String" description="是否必填"%>
<%@ attribute name="helpBlock" type="java.lang.String" description="帮助信息"%>

<%@ attribute name="typeCode" type="java.lang.String" required="true" description="字典code"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认选中"%>
<%@ attribute name="style" type="java.lang.String" description="默认选中"%>
<%@ attribute name="readonly" type="java.lang.String" description="帮助信息"%>
<%@ attribute name="cls" type="java.lang.String" description="默认选中"%>
<%@ attribute name="name" type="java.lang.String" description="默认选中"%>
<%@ attribute name="onclick" type="java.lang.String" description="点击事件"%>

<div class="col-md-6">
	<div class="form-group">
		<label class="control-label col-md-4">${labelName }
		<c:if test="${required == 'true' }">
				<span class="required" aria-required="true">* </span>
			</c:if>
		</label>
		<div class="col-md-8">
			<c:if test="${not empty typeCode}">
				<div class="checkbox-list" data-error-container="#${name }-error-container">
				<c:forEach items='${fns:getDictList(typeCode)}' var='dict'>
					<label class="radiolabel">
						<input 
							type="checkbox" 
							class="${(cls==''||cls==null)?'form-control':cls }"
							${(readonly==''||readonly==null)?'':'readonly' }
							<c:if test="${onclick != '' and onclick!=null }">
								onclick="${onclick }"
							</c:if>
							name="${name}" 
							value="${dict.VALUE}" 
							${defaultValue==dict.VALUE?'checked':''}
						/>&nbsp;${dict.TEXT}&nbsp;&nbsp;
					</label>
				</c:forEach>
				</div>
			</c:if>
			<div id="${name }-error-container"></div>
		</div>
	</div>
</div>


