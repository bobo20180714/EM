<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="labelName" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="required" type="java.lang.String" description="是否必填"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认值"%>
<%@ attribute name="name" type="java.lang.String" description="控件的id和name相同"%>
<%@ attribute name="placeholder" type="java.lang.String" description="placeholder"%>
<%@ attribute name="helpBlock" type="java.lang.String" description="帮助信息"%>
<%@ attribute name="cls" type="java.lang.String" description="input的样式类"%>
<%@ attribute name="readonly" type="java.lang.String" description="帮助信息"%>
<%@ attribute name="onclick" type="java.lang.String" description="点击事件"%>
<%@ attribute name="type" type="java.lang.String" description="默认text"%>
<%@ attribute name="dateFmt" type="java.lang.String" description="日期格式"%>
<%@ attribute name="onkeyup" type="java.lang.String" description="按键松开事件"%>

<div class="col-md-6">
	<div class="form-group">

		<label class="control-label col-md-3">
				${labelName }
			<c:if test="${required == 'true' }">
				<span class="required" aria-required="true">*</span>
			</c:if>
		</label>
		<div class="col-md-9">
			<c:if test="${dateFmt != '' and dateFmt!=null }">
				<div class="input-group"> 	
			</c:if>
			<input type="${(type==''||type==null)?'text':type }" 
				name="${name }" 
				id="${name }" 
				onkeyup="${onkeyup}"
				class="${(cls==''||cls==null)?'form-control':cls }"
				placeholder="${placeholder }"
				${(readonly==''||readonly==null)?'':'readonly' }
				
				<c:if test="${onclick != '' and onclick!=null }">
					onclick="${onclick }"
				</c:if>
				<c:if test="${dateFmt != '' and dateFmt!=null }">
					data-date-format="${dateFmt}"
				</c:if>
				value="${defaultValue }"> 
				<c:if test="${dateFmt != '' and dateFmt!=null }">
					<span class="input-group-btn">
						<button class="btn default" type="button">
							<i class="fa fa-calendar"></i>
						</button>
					</span>
					</div>
				</c:if>
				<span class="help-block">${helpBlock }</span>
		</div>


	</div>
</div>