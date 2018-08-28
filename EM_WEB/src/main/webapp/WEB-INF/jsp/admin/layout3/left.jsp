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
		<div class="page-sidebar navbar-collapse collapse leftbox" >
			<!-- BEGIN SIDEBAR MENU -->
			<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
			<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
			<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
			<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="dividerL"></div>	
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
				<li class="start">
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
												<a data-addtab="${menuRec3.MENU_LABEL}" url="${menuRec3.MENU_ACTION}${menuRec3.PARAMETER}" >
												<i class="${menuRec3.MENU_CSS}"></i>
												 	${menuRec3.MENU_LABEL} 
												 	<c:if test="${menuRec3.IS_LEAF eq '0'}"><span class="arrow"></span></c:if>
												</a>
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