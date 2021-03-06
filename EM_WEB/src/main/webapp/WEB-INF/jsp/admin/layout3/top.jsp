<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(function(){
	$('#logout').click(function(){
		window.parent.location='${ctx}/login.do'
	});
	
	/* $('#changePassword').click(function(){
		BaseUtils.loadMainContent('acOperator/forUpdatePassword.do','');
	}); */
});


</script>
<style>

</style>
<!-- BEGIN HEADER -->
<div id="main_top" class="page-header navbar navbar-fixed-top" style=' background: url(${ctx}/theme/assets/admin/${layout}/img/topBac.png); height:45px;'>
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner">
    	<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
        </a>
        <!-- BEGIN LOGO -->
        <div class="page-logo pull-left">
            <a href="index.html" class="navbar-brand" style='padding:0; width:100%; color:white;'>
				<img src="${ctx}/theme/assets/admin/${layout}/img/logo_3.png" alt="logo3" style='height:32px; margin-left:30px; margin-top:10px;' class="logo-default"/>
			</a>
            <div class="menu-toggler sidebar-toggler hide">
                <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
            </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        
		    <!-- END HEADER INNER -->
		    <div class="user_bac pull-right">
	         	<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon">
							<i class="ace-icon fa fa-search nav-search-icon"></i>
							<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						</span>
					</form>
				</div><!-- /.nav-search -->
	            <ul class="nav navbar-nav pull-right">
	            	<li class="btn btn-inverse">
	            		<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
	                    	<i class="icon icon-user"></i>
	                    	<span class="username">
	                    	${loginEmp.EMP_NAME } </span>
	            		</a>
	            	</li>
	            	<li class="dropdown dropdown-user" >
	                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
	                    	<i class="icon icon-envelope"></i>
	            			<span class="text">消息</span>
	            			<span class="label label-important">5</span>
	            			<b class="caret"></b> 
	                    </a>
	                    <ul class="dropdown-menu">
	                        <li>
	                            <a class="btn" href="#" data-target="#ajax_lg" data-toggle="modal">
	                            <i class="fa fa-user"></i> 个人中心 </a>
	                        </li>
	                        <li>
	                            <a class="btn" href="acOperator/forUpdatePassword.do" data-target="#ajax_lg" data-toggle="modal">
	                            <i class="fa fa-cog"></i> 设置 </a>
	                        </li>
	                        <li class="divider"></li>
	                        <li>
	                            <a class="btn" href="javascript:;" id="logout"  >
	                            <i class="fa fa-power-off"></i> 退出 </a>
	                        </li>
	                    </ul>
	                </li>
	                <li class="btn btn-inverse">
	                	<a title="" class="dropdown-toggle" href="#" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
	                		<i class="fa fa-cog"></i>
							<span class="">设置</span>
						</a>
					</li>
	                <li class="btn btn-inverse">
	                	<a title="" class="dropdown-toggle" href="#" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
	                		<i class="icon icon-share-alt"></i>
	                		<span class="">退出</span>
	                	</a>
	                </li>
	                <!-- END USER LOGIN DROPDOWN -->
	            </ul>
	            </div>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        	<!--<div class="input-group pull-left">
			<input type="text" class="form-control "  placeholder="搜索" >
			<span class="input-group-addon">
				<img src="${ctx}/theme/assets/admin/${layout}/img/search_icon.png"/>
			</span>
			</div>-->
        </div>
        <!--<div class="input-group pull-right" style='margin-right:20px; margin-top:32px; font-size:14px;'>
        		<img class='pull-left' src="${ctx}/theme/assets/admin/${layout}/img/input_left.png">
				<input type="text" class="pull-left"  placeholder="搜索" style=' background-image: url(${ctx}/theme/assets/admin/${layout}/img/input_mild.png);  border:none; height:28px; line-height:28px; corlor:#dfdfdf'>
				<img class='pull-right' src="${ctx}/theme/assets/admin/${layout}/img/input_right.png" style='cursor: pointer ;'/>
		</div> -->
        <%-- 保留拓展内容
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                <!-- BEGIN NOTIFICATION DROPDOWN -->
                <li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    <i class="icon-bell"></i>
                    <span class="badge badge-default">
                    7 </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <p>
                                 You have 14 new notifications
                            </p>
                        </li>
                        <li>
                            <ul class="dropdown-menu-list scroller" style="height: 250px;">
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-success">
                                    <i class="fa fa-plus"></i>
                                    </span>
                                    New user registered. <span class="time">
                                    Just now </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-danger">
                                    <i class="fa fa-bolt"></i>
                                    </span>
                                    Server #12 overloaded. <span class="time">
                                    15 mins </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-warning">
                                    <i class="fa fa-bell-o"></i>
                                    </span>
                                    Server #2 not responding. <span class="time">
                                    22 mins </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-info">
                                    <i class="fa fa-bullhorn"></i>
                                    </span>
                                    Application error. <span class="time">
                                    40 mins </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-danger">
                                    <i class="fa fa-bolt"></i>
                                    </span>
                                    Database overloaded 68%. <span class="time">
                                    2 hrs </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-danger">
                                    <i class="fa fa-bolt"></i>
                                    </span>
                                    2 user IP blocked. <span class="time">
                                    5 hrs </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-warning">
                                    <i class="fa fa-bell-o"></i>
                                    </span>
                                    Storage Server #4 not responding. <span class="time">
                                    45 mins </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-info">
                                    <i class="fa fa-bullhorn"></i>
                                    </span>
                                    System Error. <span class="time">
                                    55 mins </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="label label-sm label-icon label-danger">
                                    <i class="fa fa-bolt"></i>
                                    </span>
                                    Database overloaded 68%. <span class="time">
                                    2 hrs </span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="external">
                            <a href="#">
                            See all notifications <i class="m-icon-swapright"></i>
                            </a>
                        </li>
                    </ul>
                </li>
                <!-- END NOTIFICATION DROPDOWN -->
                <!-- BEGIN INBOX DROPDOWN -->
                <li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    <i class="icon-envelope-open"></i>
                    <span class="badge badge-default">
                    4 </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <p>
                                 You have 12 new messages
                            </p>
                        </li>
                        <li>
                            <ul class="dropdown-menu-list scroller" style="height: 250px;">
                                <li>
                                    <a href="inbox.html?a=view">
                                    <span class="photo">
                                    <img src="${ctx}/theme/assets/admin/${layout}/img/avatar2.jpg" alt=""/>
                                    </span>
                                    <span class="subject">
                                    <span class="from">
                                    Lisa Wong </span>
                                    <span class="time">
                                    Just Now </span>
                                    </span>
                                    <span class="message">
                                    Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="inbox.html?a=view">
                                    <span class="photo">
                                    <img src="${ctx}/theme/assets/admin/${layout}/img/avatar3.jpg" alt=""/>
                                    </span>
                                    <span class="subject">
                                    <span class="from">
                                    Richard Doe </span>
                                    <span class="time">
                                    16 mins </span>
                                    </span>
                                    <span class="message">
                                    Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="inbox.html?a=view">
                                    <span class="photo">
                                    <img src="${ctx}/theme/assets/admin/${layout}/img/avatar1.jpg" alt=""/>
                                    </span>
                                    <span class="subject">
                                    <span class="from">
                                    Bob Nilson </span>
                                    <span class="time">
                                    2 hrs </span>
                                    </span>
                                    <span class="message">
                                    Vivamus sed nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="inbox.html?a=view">
                                    <span class="photo">
                                    <img src="${ctx}/theme/assets/admin/${layout}/img/avatar2.jpg" alt=""/>
                                    </span>
                                    <span class="subject">
                                    <span class="from">
                                    Lisa Wong </span>
                                    <span class="time">
                                    40 mins </span>
                                    </span>
                                    <span class="message">
                                    Vivamus sed auctor 40% nibh congue nibh... </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="inbox.html?a=view">
                                    <span class="photo">
                                    <img src="${ctx}/theme/assets/admin/${layout}/img/avatar3.jpg" alt=""/>
                                    </span>
                                    <span class="subject">
                                    <span class="from">
                                    Richard Doe </span>
                                    <span class="time">
                                    46 mins </span>
                                    </span>
                                    <span class="message">
                                    Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="external">
                            <a href="inbox.html">
                            See all messages <i class="m-icon-swapright"></i>
                            </a>
                        </li>
                    </ul>
                </li>
                <!-- END INBOX DROPDOWN -->
                <!-- BEGIN TODO DROPDOWN -->
                <li class="dropdown dropdown-extended dropdown-tasks" id="header_task_bar">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    <i class="icon-calendar"></i>
                    <span class="badge badge-default">
                    3 </span>
                    </a>
                    <ul class="dropdown-menu extended tasks">
                        <li>
                            <p>
                                 You have 12 pending tasks
                            </p>
                        </li>
                        <li>
                            <ul class="dropdown-menu-list scroller" style="height: 250px;">
                                <li>
                                    <a href="#">
                                    <span class="task">
                                    <span class="desc">
                                    New release v1.2 </span>
                                    <span class="percent">
                                    30% </span>
                                    </span>
                                    <span class="progress">
                                    <span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">
                                    40% Complete </span>
                                    </span>
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="task">
                                    <span class="desc">
                                    Application deployment </span>
                                    <span class="percent">
                                    65% </span>
                                    </span>
                                    <span class="progress progress-striped">
                                    <span style="width: 65%;" class="progress-bar progress-bar-danger" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">
                                    65% Complete </span>
                                    </span>
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="task">
                                    <span class="desc">
                                    Mobile app release </span>
                                    <span class="percent">
                                    98% </span>
                                    </span>
                                    <span class="progress">
                                    <span style="width: 98%;" class="progress-bar progress-bar-success" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">
                                    98% Complete </span>
                                    </span>
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="task">
                                    <span class="desc">
                                    Database migration </span>
                                    <span class="percent">
                                    10% </span>
                                    </span>
                                    <span class="progress progress-striped">
                                    <span style="width: 10%;" class="progress-bar progress-bar-warning" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">
                                    10% Complete </span>
                                    </span>
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="task">
                                    <span class="desc">
                                    Web server upgrade </span>
                                    <span class="percent">
                                    58% </span>
                                    </span>
                                    <span class="progress progress-striped">
                                    <span style="width: 58%;" class="progress-bar progress-bar-info" aria-valuenow="58" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">
                                    58% Complete </span>
                                    </span>
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="task">
                                    <span class="desc">
                                    Mobile development </span>
                                    <span class="percent">
                                    85% </span>
                                    </span>
                                    <span class="progress progress-striped">
                                    <span style="width: 85%;" class="progress-bar progress-bar-success" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">
                                    85% Complete </span>
                                    </span>
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                    <span class="task">
                                    <span class="desc">
                                    New UI release </span>
                                    <span class="percent">
                                    18% </span>
                                    </span>
                                    <span class="progress progress-striped">
                                    <span style="width: 18%;" class="progress-bar progress-bar-important" aria-valuenow="18" aria-valuemin="0" aria-valuemax="100">
                                    <span class="sr-only">
                                    18% Complete </span>
                                    </span>
                                    </span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="external">
                            <a href="#">
                            See all tasks <i class="m-icon-swapright"></i>
                            </a>
                        </li>
                    </ul>
                </li>
                <!-- END TODO DROPDOWN -->
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <li class="dropdown dropdown-user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    <img alt="" class="img-circle" src="${ctx}/theme/assets/admin/${layout}/img/avatar3_small.jpg"/>
                    <span class="username">
                    Bob </span>
                    <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="extra_profile.html">
                            <i class="icon-user"></i> My Profile </a>
                        </li>
                        <li>
                            <a href="page_calendar.html">
                            <i class="icon-calendar"></i> My Calendar </a>
                        </li>
                        <li>
                            <a href="inbox.html">
                            <i class="icon-envelope-open"></i> My Inbox <span class="badge badge-danger">
                            3 </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                            <i class="icon-rocket"></i> My Tasks <span class="badge badge-success">
                            7 </span>
                            </a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="extra_lock.html">
                            <i class="icon-lock"></i> Lock Screen </a>
                        </li>
                        <li>
                            <a href="login.html">
                            <i class="icon-key"></i> Log Out </a>
                        </li>
                    </ul>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
                <!-- BEGIN QUICK SIDEBAR TOGGLER -->
                <li class="dropdown dropdown-quick-sidebar-toggler">
                    <a href="javascript:;" class="dropdown-toggle">
                    <i class="icon-logout"></i>
                    </a>
                </li>
                <!-- END QUICK SIDEBAR TOGGLER -->
            </ul>
        </div>
         --%><!-- END TOP NAVIGATION MENU -->
    </div>
</div>
<!-- END HEADER -->