<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="${ctx}/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/theme/assets/global/plugins/bootstrap-tabdrop/css/bootstrap-addtabs.css" type="text/css" media="screen" />

	<script src="${ctx}/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${ctx}/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
	<script src="${ctx}/theme/assets/global/plugins/bootstrap-tabdrop/js/bootstrap-addtabs.js" type="text/javascript"></script>
<script type="text/javascript">
	var activeTab = "tab1"; //当前的tab页。默认为第一个tab页。
	var previousTab;  //上一个打开的tab页。默认为空。
	$(function(){
	    //实现事件响应函数，当tab页被显示时会触发
	    $("a[data-toggle='tab']").on("shown.bs.tab", function (e) {
	        //获取当前被显示的tab页标签的aria-controls属性值
	        activeTab = $(e.target).attr("aria-controls");
	        //获取前一个被显示的tab页标签的aria-controls属性值
	        previousTab = $(e.relatedTarget).attr("aria-controls");
	    });
	    
	    //点击带关闭按钮tab页标签上的x后的响应事件
	    $("#closetabbtn").click(function(e){
	    	alert("");
	        $(this).parent().parent().css("display","none"); //隐藏tab头，调用remove方法就是删除了
	        $("#closetab").css("display","none"); //隐藏tab正文信息，调用remove方法就是删除了
	        if(activeTab=="closetab"){ //判断当前tab页是否是带关闭按钮的tab页，如果是，则显示上次打开的tab页
	            $("#contentnavid a[href='#'"+previousTab+"]").tab("show"); //显示tab页
	        }        
	        return false; //一定要return false，阻止事件往上冒泡
	    });
	});
</script>
</head>
<body>
	<!-- Nav tabs -->
	<div class=" tabPage">

		<ul class="nav nav-tabs" role="tablist" id="contentnavid">
			<li role="presentation" class="active"><a href="#tab1"
				aria-controls="tab1" role="tab" data-toggle="tab">tab1</a></li>
			<li role="presentation"><a href="#tab2" aria-controls="tab2"
				role="tab" data-toggle="tab">tab2</a></li>
			<li role="presentation"><a href="#tab3" aria-controls="tab3"
				role="tab" data-toggle="tab">tab3</a></li>
			<li role="presentation" id="closetabli"><a href="#closetab"
				aria-controls="closetab" role="tab" data-toggle="tab"><span>closetab</span>
					&nbsp;
					<button type="button" class="close" aria-label="Close"
						id="closetabbtn">
						<span aria-hidden="true" style="color: red">&times;</span>
					</button> </a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="tab1">this is
				tab1</div>
			<div role="tabpanel" class="tab-pane active" id="tab2">this is
				tab2</div>
			<div role="tabpanel" class="tab-pane active" id="tab3">this is
				tab3</div>
			<div role="tabpanel" class="tab-pane active" id="closetab">this
				is closetab</div>
		</div>
	</div>

</body>
</html>