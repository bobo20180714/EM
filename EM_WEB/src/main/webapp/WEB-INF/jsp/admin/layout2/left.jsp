<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%-- <link rel="stylesheet" href="${ctx}/theme/assets/global/plugins/bootstrap-tabdrop/css/bootstrap-addtabs.css" type="text/css" media="screen" />
	<script src="${ctx}/theme/assets/global/plugins/bootstrap-tabdrop/js/bootstrap-addtabs.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
		    $('#tabs').addtabs({monitor:'.topbar'});
		    /* $('#save').click(function(){
		        Addtabs.add({
		           id: $(this).attr('addtabs'),
		           title: $(this).attr('title') ? $(this).attr('title') : $(this).html(),
		           content: Addtabs.options.content ? Addtabs.options.content : $(this).attr('content'),
		           url: $(this).attr('url'),
		           ajax: $(this).attr('ajax') ? true : false
		        })
		    }); */
		})
	</script> --%>
<!-- BEGIN SIDEBAR -->
	<div id="main_left" class="page-sidebar-wrapper" style="margin-top:45px; ">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse " >
			<!-- BEGIN SIDEBAR MENU -->
			<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
			<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
			<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
			<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			
		<header class="topbar admin-header">
           <!--  <div class="topbar-collapse"> -->
			<ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200"">
				<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
				
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<!-- <li class="sidebar-search-wrapper">
					BEGIN RESPONSIVE QUICK SEARCH FORM
					DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box
					DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box
					<form class="sidebar-search " action="extra_search.html" method="POST">
						<a href="javascript:;" class="remove">
						<i class="icon-close"></i>
						</a>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
							<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
							</span>
						</div>
					</form>
					END RESPONSIVE QUICK SEARCH FORM
				</li> -->
				<li>
					<div class="sidebar-shortcuts-large"  style='margin-top:5px; margin-bottom:5px; margin-left:15px; height:34px; overflow:hidden;'>
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>

						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>
					</div>
				</li>
				<li class="start" style=' border-top:solid 1px #e5e5e5;'>
					<a href="javascript:BaseUtils.loadMainContent('home.do','');">
					<i class="fa fa-home"></i>
					<span class="title">首页</span>
					<span class="selected"></span>
					<!-- <span class="arrow open"></span> -->
					</a>
				</li>
				
				<c:forEach items="${permMenuListLevel1}" var="menuRec1" varStatus="status">
					<li>
						<a href="#" >
						<i class="${menuRec1.MENU_CSS}" ></i>
						<span class="title">${menuRec1.MENU_LABEL}</span>
							<c:if test="${menuRec1.IS_LEAF eq '0'}"><span class="arrow active"></span></c:if>
						</a>
						<c:if test="${menuRec1.IS_LEAF eq '0'}">
						<ul class="sub-menu">
						<c:forEach items="${permMenuListLevel2}" var="menuRec2" varStatus="status">
							<c:if test="${menuRec2.PARENT_ID eq menuRec1.MENU_ID}">
							
								<li>
									<a data-addtab="${menuRec2.MENU_LABEL}" url="${menuRec2.MENU_ACTION}${menuRec2.PARAMETER}">
									<i class="${menuRec2.MENU_CSS}"></i> ${menuRec2.MENU_LABEL} 
										<c:if test="${menuRec2.IS_LEAF eq '0'}"><span class="arrow"></span></c:if>
									</a>
									<c:if test="${menuRec2.IS_LEAF eq '0'}">
									<ul class="sub-menu">
									<c:forEach items="${permMenuListLevel3}" var="menuRec3" varStatus="status">
										<c:if test="${menuRec3.PARENT_ID eq menuRec2.MENU_ID}">
											<li>
											<!-- 根据不同的OPEN_MODE来决定不同的打开方式 -->
											<!-- 如果OPEN_MODE为空或者为0，表示默认打开方式-->
											<!-- 如果OPEN_MODE为1，则处理单点登录，参数为MENU_ID,USER_ID,PASSWORD-->
											<c:choose>
												<c:when test="${(menuRec3.OPEN_MODE eq '0') }">
													<a data-addtab="${menuRec3.MENU_LABEL}" url="${menuRec3.MENU_ACTION}${menuRec3.PARAMETER}" >
													<i class="${menuRec3.MENU_CSS}"></i>
													 	${menuRec3.MENU_LABEL} 
													 	<c:if test="${menuRec3.IS_LEAF eq '0'}"><span class="arrow"></span></c:if>
													</a>
												</c:when>
												<c:when test="${menuRec3.OPEN_MODE eq '1' }">
													<a  data-addtab="${menuRec3.MENU_LABEL}" url="${menuRec3.MENU_ACTION}${'?MENU_ID='}${menuRec3.MENU_ID}${menuRec3.PARAMETER}" >
														<i class="${menuRec3.MENU_CSS}"></i>
										 				${menuRec3.MENU_LABEL} 
										 			</a>
												</c:when>
												<c:when test="${menuRec3.OPEN_MODE eq '2' }">
													<a  href="${menuRec3.MENU_ACTION}${menuRec3.PARAMETER}" >
														<i class="${menuRec3.MENU_CSS}"></i>
										 				${menuRec3.MENU_LABEL} 
										 			</a>
												</c:when>
												<c:when test="${menuRec3.OPEN_MODE eq '3' }">
												</c:when>
												<c:when test="${menuRec3.OPEN_MODE eq '4' }">
												</c:when>
												<c:otherwise>									
													<a data-addtab="${menuRec3.MENU_LABEL}" url="${menuRec3.MENU_ACTION}${menuRec3.PARAMETER}" >
													<i class="${menuRec3.MENU_CSS}"></i>
													 	${menuRec3.MENU_LABEL} 
													 	<c:if test="${menuRec3.IS_LEAF eq '0'}"><span class="arrow"></span></c:if>
													</a>
												</c:otherwise>
											</c:choose>
												<c:if test="${menuRec3.IS_LEAF eq '0'}">
												<ul class="sub-menu">
												<c:forEach items="${permMenuListLevel4}" var="menuRec4" varStatus="status">
													<c:if test="${menuRec4.PARENT_ID eq menuRec3.MENU_ID}">
														<li>
															<a data-addtab="${menuRec4.MENU_LABEL}" url="${menuRec4.MENU_ACTION}">
															<i class="${menuRec4.MENU_CSS}"></i> 
															${menuRec4.MENU_LABEL}</a>
														</li>
													<c:remove var="menuRec4"/>
													</c:if>
												</c:forEach>
												</ul>
												</c:if>
											</li>
										<c:remove var="menuRec3"/>
										</c:if>
									</c:forEach>
									</ul>
									</c:if>
								</li>
							<c:remove var="menuRec2"/>
							</c:if>
						</c:forEach>
						</ul>
						</c:if>
					</li>
				</c:forEach>
				
				<!-- 自定义菜单 -->
				<c:forEach items="${permCustomMenuListLevel1}" var="menuCustomRec1">
					<li>
					<a href="#">
					<i class="fa fa-list-ul "></i>
					<span class="title">${menuCustomRec1.MENU_OPERATOR_CUSTOM_CODE }</span>
					</a>
					<ul class="sub-menu">
						<c:forEach items="${permCustomMenuListLevel2}" var="menuCustomRec2">
						<c:if test="${menuCustomRec2.MENU_OPERATOR_CUSTOM_CODE eq menuCustomRec1.MENU_OPERATOR_CUSTOM_CODE}">
							<li>
								<a data-addtab="${menuCustomRec2.MENU_LABEL}" url="${menuCustomRec2.MENU_ACTION }">
								<i class="${menuCustomRec2.MENU_CSS}"></i> 
								${menuCustomRec2.MENU_LABEL}</a>	
							</li>
						</c:if>
							
						</c:forEach>
					</ul>
				</c:forEach>
				<!-- 自定义菜单end -->
				
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler"style="">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
			</ul>
			<!-- </div> -->
			</header>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->