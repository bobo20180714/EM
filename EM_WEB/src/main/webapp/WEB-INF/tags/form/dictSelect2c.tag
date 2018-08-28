<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="required" type="java.lang.String" description="是否必填"%>
<%@ attribute name="helpBlock" type="java.lang.String" description="帮助信息"%>

<%@ attribute name="typeCode" type="java.lang.String" required="true" description="字典code"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认选中"%>
<%@ attribute name="style" type="java.lang.String" description="默认选中"%>
<%@ attribute name="cls" type="java.lang.String" description="默认选中"%>
<%@ attribute name="name" type="java.lang.String" description="默认选中"%>
<%@ attribute name="onchange" type="java.lang.String" description="默认选中"%>

<div class="col-md-6">
	<div class="form-group">
		<label class="control-label col-md-3">${labelName }
		<c:if test="${required == 'true' }">
				<span class="required" aria-required="true">* </span>
			</c:if>
		</label>
		<div class="col-md-9">
				<select style="${style}" class="${cls}" name="${name}" id="${name}" onchange="${onchange}">
					<option value="">请选择...</option>
					<c:if test="${not empty typeCode}">
						<c:forEach items="${fns:getDictList(typeCode)}" var='dict'>
							<option value='${dict.VALUE}' ${defaultValue==dict.VALUE?'selected':''}>${dict.TEXT}</option>
						</c:forEach>
					</c:if>
				</select> 
			
			<span class="help-block">${helpBlock } </span>
		</div>
	</div>
</div>


