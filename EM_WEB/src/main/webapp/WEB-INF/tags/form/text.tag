<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="labelName" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认值"%>
<%@ attribute name="name" type="java.lang.String" description="控件的id和name相同"%>
<%@ attribute name="cls" type="java.lang.String" description="input的样式类"%>
<%@ attribute name="readonly" type="java.lang.String" description="是否只读"%>

<div class="col-md-12">
	<div class="form-group">
		<label class="control-label col-md-2">${labelName}
		</label>
		<div class="col-md-10">
			<textarea name="${name}" id="${name}" class="form-control" ${(readonly==''||readonly==null)?'':'readonly'}>${defaultValue}</textarea> 
		</div>
	</div>
</div>