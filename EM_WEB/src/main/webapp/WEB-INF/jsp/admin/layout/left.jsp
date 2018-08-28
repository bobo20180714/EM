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
	<div id="main_left" class="page-sidebar-wrapper" style="margin-top:60px; ">
		<div class="page-sidebar navbar-collapse collapse " >
			
		<header class="topbar admin-header">
			<ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200"">
				<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler"style="">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
				
				<li class="start" style='margin-top:15px;background: url(${ctx}/theme/assets/admin/${layout}/img/left_shouye_bac.png) left center no-repeat;'>
					<a href="javascript:BaseUtils.loadMainContent('home.do','');">
					<i class="icon-home" style=' color:#3a4755;font-weight:bold;'></i>
					<span class="title" style=' color:#3a4755;font-weight:bold;'>首页</span>
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
			</ul>
			<!-- </div> -->
			</header>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->