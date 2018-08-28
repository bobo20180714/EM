<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="href" type="java.lang.String" required="true" description="弹出页地址"%>
<%@ attribute name="target" type="java.lang.String" description="目标模态框"%>
<%@ attribute name="delclick" type="java.lang.String" description="删除按钮点击事件"%>

<div class="table-toolbar">
	<div class="row">
		<div class="col-md-6">
			<div class="clearfix">
				<a class="btn green" href="${href}" data-target="${target}" data-toggle="modal">
					新增 <i class="fa fa-plus"></i></a>								
				<button onclick="${delclick}" id="delete" class="btn btn-danger">
					删除 <i class="fa fa-minus"></i>
				</button>
			</div>
		</div>									
	</div>
</div>