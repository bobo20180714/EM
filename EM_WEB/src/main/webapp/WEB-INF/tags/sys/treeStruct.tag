<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="treeCaption" type="java.lang.String" description="默认选中"%>
<%@ attribute name="treeId" type="java.lang.String" description="默认选中"%>

	<div class="col-md-3"> 
			<div id="treeDiv" class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<span class="caption-subject bold uppercase"><i class="fa fa-cogs"></i>${treeCaption}</span>
						<span class="caption-helper"></span>
					</div>
				</div>
				<div class="portlet-body">
					<ul class="ztree" style="overflow:auto;">
						<li id="${treeId}"></li>
					</ul>
				</div>
			</div>
		</div>