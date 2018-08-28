<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %><%-- ${fns:xxxx}方法标签 --%>
<!-- 自定义首页内容 BEGIN -->
<style>
.stat-boxes, .quick-actions, .quick-actions-horizontal, .stats-plain {
    display: inline-block;
    list-style: none outside none;
    margin: 20px 0 10px;
    text-align: center;
}
.stat-boxes li, .quick-actions li, .quick-actions-horizontal li {
    background-color: #F6F6F6;
    background-image: -webkit-gradient(linear, 0 0%, 0 100%, from(#F9F9F9), to(#EDEDED));
    background-image: -webkit-linear-gradient(top, #F9F9F9 0%, #EDEDED 100%);
    background-image: -moz-linear-gradient(top, #F9F9F9 0%, #EDEDED 100%);
    background-image: -ms-linear-gradient(top, #F9F9F9 0%, #EDEDED 100%);
    background-image: -o-linear-gradient(top, #F9F9F9 0%, #EDEDED 100%);
    background-image: linear-gradient(top, #F9F9F9 0%, #EDEDED 100%);
    border: 1px solid #d5d5d5;
    border-radius: 4px 4px 4px 4px;
    box-shadow: 0 1px 0 0 #FFFFFF inset, 0 1px 0 rgba(255,255,255,0.4);
    display: inline-block;
    line-height: 18px;
    margin: 0 10px 10px;
    padding: 0 10px;
}
.stat-boxes .left span, .stat-boxes .right strong {
    display: block;
}
.stat-boxes .peity_bar_good, .stat-boxes .peity_line_good {
    color: #459D1C;
}
.stat-boxes .peity_bar_good span, .stat-boxes .peity_line_good span,.stat-boxes .peity_bar_neutral span,.stat-boxes .peity_bar_bad span{
	display:none;
}
.stat-boxes .left, .stat-boxes .right {
    text-shadow: 0 1px 0 #ffffff;
    float: left;
}
.stat-boxes .left {
    border-right: 1px solid #DCDCDC;
    box-shadow: 1px 0 0 0 #FFFFFF;
    margin-right: 12px;
    padding: 10px 14px 6px 4px;
    font-size: 10px;
    font-weight: bold;
}
.stat-boxes .right {
    font-size: 12px;
    padding: 9px 10px 7px 0;
    text-align: center;
    width: 70px;
    color: #666666;
}
.stat-boxes .right strong {
    font-size: 26px;
    margin-bottom: 3px;
    margin-top: 6px;
}


.widget-box {
    background: none repeat scroll 0 0 #F9F9F9;
    border-top: 1px solid #CDCDCD;
    border-left: 1px solid #CDCDCD;
    border-right: 1px solid #CDCDCD;
    clear: both;
    margin-top: 16px;
    margin-bottom: 16px;
    position: relative;
}
.widget-content {
    padding: 12px 15px;
    border-bottom: 1px solid #cdcdcd;
}
/* .widget-title, .modal-header, .table th, div.dataTables_wrapper .ui-widget-header {
    background-color: #efefef;
    background-image: -webkit-gradient(linear, 0 0%, 0 100%, from(#fdfdfd), to(#eaeaea));
    background-image: -webkit-linear-gradient(top, #fdfdfd 0%, #eaeaea 100%);
    background-image: -moz-linear-gradient(top, #fdfdfd 0%, #eaeaea 100%);
    background-image: -ms-linear-gradient(top, #fdfdfd 0%, #eaeaea 100%);
    background-image: -o-linear-gradient(top, #fdfdfd 0%, #eaeaea 100%);
    background-image: -linear-gradient(top, #fdfdfd 0%, #eaeaea 100%);
    filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fdfdfd', endColorstr='#eaeaea',GradientType=0 );
    border-bottom: 1px solid #CDCDCD;
    height: 36px;
} */
.widget-title span.icon {
    border-right: 1px solid #cdcdcd;
    padding: 9px 10px 7px 11px;
    float: left;
    opacity: .7;
}
.widget-title h5 {
    color: #666666;
    text-shadow: 0 1px 0 #ffffff;
    float: left;
    font-size: 12px;
    font-weight: bold;
    padding: 12px;
    line-height: 12px;
    margin: 0;
}
.widget-title .buttons {
    float: right;
    margin: 8px 10px 0 0;
}
.widget-title .buttons a{
	color:#333;
    border-color: rgba(0,0,0,0.15) rgba(0,0,0,0.15) rgba(0,0,0,0.25);
    padding: 1px 6px;
    font-size: 10.5px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    display: inline-block;
    margin-bottom: 0;
    line-height: 20px;
    text-align: center;
    text-shadow: 0 1px 1px rgba(255,255,255,0.75);
    vertical-align: middle;
    cursor: pointer;
    background-color: #f5f5f5;
    background-image: -moz-linear-gradient(top,#fff,#e6e6e6);
    background-image: -webkit-gradient(linear,0 0,0 100%,from(#fff),to(#e6e6e6));
    background-image: -webkit-linear-gradient(top,#fff,#e6e6e6);
    background-image: -o-linear-gradient(top,#fff,#e6e6e6);
    background-image: linear-gradient(to bottom,#fff,#e6e6e6);
    background-repeat: repeat-x;
    border: 1px solid #bbb;
    border-color: #e6e6e6 #e6e6e6 #bfbfbf;
    border-color: rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);
    border-bottom-color: #a2a2a2;
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    border-radius: 4px;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff',endColorstr='#ffe6e6e6',GradientType=0);
    filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
    -webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);
    -moz-box-shadow: inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);
    box-shadow: inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);
}
</style>
<div class="page-content">
			<div id="tabs">
				<ul id="myTab" class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active">
						<a href="#home" aria-controls="home" role="tab" data-toggle="tab">
						<i class="fa fa-home"></i>
						主页</a>
					</li>
				</ul>
				<div id="tab-content" class="tab-content">
					<div class="tab-pane active" id="home" role="tabpanel" >
					
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12 center" style="text-align: center;">					
									<ul class="stat-boxes">
										<li>
											<div class="left peity_bar_good"><span>2,4,9,7,12,10,12</span>+20%</div>
											<div class="right">
												<strong>36094</strong>
												Visits
											</div>
										</li>
										<li>
											<div class="left peity_bar_neutral"><span>20,15,18,14,10,9,9,9</span>0%</div>
											<div class="right">
												<strong>1433</strong>
												Users
											</div>
										</li>
										<li>
											<div class="left peity_bar_bad"><span>3,5,9,7,12,20,10</span>-50%</div>
											<div class="right">
												<strong>8650</strong>
												Orders
											</div>
										</li>
										<li>
											<div class="left peity_line_good"><span>12,6,9,23,14,10,17</span>+70%</div>
											<div class="right">
												<strong>8650</strong>
												Orders
											</div>
										</li>
									</ul>
								</div>	
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="alert alert-info">
										欢迎进入<strong>易捷平台</strong>！/  Welcome to <strong>easyFast platform</strong>！
										<a href="#" data-dismiss="alert" class="close">×</a>
									</div>
									<div class="widget-box">
										<div class="widget-title">
											<span class="icon">
												<i class="fa fa-signal">
												</i>
											</span>
											<h5>Site Statistics</h5>
											<div class="buttons">
												<a href="#" class="btn btn-mini">
													<i class="icon-refresh"></i>
												 	Update stats
											 	</a>
											</div>
										</div>
										<div class="widget-content">
											<div class="row-fluid">
											<div class="span4">
												<ul class="site-stats">
													<li><i class="icon-user"></i> <strong>1433</strong> <small>Total Users</small></li>
													<li><i class="icon-arrow-right"></i> <strong>16</strong> <small>New Users (last week)</small></li>
													<li class="divider"></li>
													<li><i class="icon-shopping-cart"></i> <strong>259</strong> <small>Total Shop Items</small></li>
													<li><i class="icon-tag"></i> <strong>8650</strong> <small>Total Orders</small></li>
													<li><i class="icon-repeat"></i> <strong>29</strong> <small>Pending Orders</small></li>
												</ul>
											</div>
											<div class="span8">
												<div class="chart"></div>
											</div>	
											</div>							
										</div>
									</div>					
								</div>
							</div>
							<div class="row-fluid">
								<div class="col-md-6">
									<div class="widget-box">
										<div class="widget-title">
											<span class="icon">
												<i class="icon-file">
												</i>
											</span>
											<h5>Recent Posts</h5>
											<span title="54 total posts" class="label label-info tip-left">54</span>
										</div>
										<div class="widget-content nopadding">
											<ul class="recent-posts">
												<li>
													<div class="user-thumb">
														<img width="40" height="40" alt="User" src="img/demo/av2.jpg" />
													</div>
													<div class="article-post">
														<span class="user-info"> By: neytiri on 2 Aug 2012, 09:27 AM, IP: 186.56.45.7 </span>
														<p>
															<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
														</p>
														<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
													</div>
												</li>
												<li>
													<div class="user-thumb">
														<img width="40" height="40" alt="User" src="img/demo/av3.jpg" />
													</div>
													<div class="article-post">
														<span class="user-info"> By: john on on 24 Jun 2012, 04:12 PM, IP: 192.168.24.3 </span>
														<p>
															<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
														</p>
														<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
													</div>
												</li>
												<li>
													<div class="user-thumb">
														<img width="40" height="40" alt="User" src="img/demo/av1.jpg" />
													</div>
													<div class="article-post">
														<span class="user-info"> By: michelle on 22 Jun 2012, 02:44 PM, IP: 172.10.56.3 </span>
														<p>
															<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
														</p>
														<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
													</div>
												</li>
												<li class="viewall">
													<a title="View all posts" class="tip-top" href="#"> + View all + </a>
												</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="widget-box">
										<div class="widget-title"><span class="icon"><i class="icon-comment"></i></span><h5>Recent Comments</h5><span title="88 total comments" class="label label-info tip-left">88</span></div>
										<div class="widget-content nopadding">
											<ul class="recent-comments">
												<li>
													<div class="user-thumb">
														<img width="40" height="40" alt="User" src="img/demo/av1.jpg" />
													</div>
													<div class="comments">
														<span class="user-info"> User: michelle on IP: 172.10.56.3 </span>
														<p>
															<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
														</p>
														<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Approve</a> <a href="#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
													</div>
												</li>
												<li>
													<div class="user-thumb">
														<img width="40" height="40" alt="User" src="img/demo/av3.jpg" />
													</div>
													<div class="comments">
														<span class="user-info"> User: john on IP: 192.168.24.3 </span>
														<p>
															<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
														</p>
														<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Approve</a> <a href="#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
													</div>
												</li>
												<li>
													<div class="user-thumb">
														<img width="40" height="40" alt="User" src="img/demo/av2.jpg" />
													</div>
													<div class="comments">
														<span class="user-info"> User: neytiri on IP: 186.56.45.7 </span>
														<p>
															<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
														</p>
														<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Approve</a> <a href="#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
													</div>
												</li>
												<li class="viewall">
													<a title="View all comments" class="tip-top" href="#"> + View all + </a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="widget-box widget-calendar">
										<div class="widget-title"><span class="icon"><i class="icon-calendar"></i></span><h5>Calendar</h5></div>
										<div class="widget-content nopadding">
											<div class="calendar"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div id="footer" class="span12">
									2012 &copy; Unicorn Admin. Brought to you by <a href="https://wrapbootstrap.com/user/diablo9983">diablo9983</a>
								</div>
							</div>
						</div>
			
			
			
			
					</div>
				</div>	
			</div>
</div>