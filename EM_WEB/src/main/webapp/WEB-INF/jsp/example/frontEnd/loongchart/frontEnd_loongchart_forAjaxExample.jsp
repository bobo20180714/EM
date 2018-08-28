<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %>
<%@ include file="../../../common/loongchart_header.jsp"%>
<script type="text/javascript">
	var loongAjaxDemoOptions = {
		valueType : 'n',
		animationSteps : 60,
		bar : {
			useSameColor : true
		},
		histogram : {
			useSameColor : true
		},
		//title: { content: 'XX信息系统有限公司在2013年1至10月份的销售额' },
		valueAxis : {
			linewidth : 1
		},
		//subTitle: { content: '结论为：八月份销售额最高' },
		scale : {
			linewidth : 1,
			backcolors : [ 'rgba(175,199,238,0.2)', 'rgba(245,222,179,0.2)' ]
		},
		cross : {
			linewidth : 3,
			linecolor : '#ffffff'
		},
		caption : {
			content : '(十万元)'
		},
		footer : {
			content : '销售部统计结果',
			fontcolor : '#f8d8d8',
			fontsize : 14
		},
		shadow : {
			show : true,
			color : 'rgba(10,10,10,1)',
			blur : 3,
			offsetX : -3,
			offsetY : 3
		}
	};
	function initLoongchartDemoData(options) {
		//获得图表的options对象  
		//var options = myAjaxChart.getOption();  
		//通过Ajax获取数据  
		$.ajax({
			type : "post",
			async : false, //同步执行  
			url : "${ctx}/loongchartDemo/ajaxExampleData.do",
			data : {},
			dataType : "json", //返回数据形式为json  
			success : function(result) {
				window.lchart = new LChart.Histogram3D('divLoongchartAjaxCanvas', 'CN');
				lchart.SetSkin('BlackAndWhite');
				lchart.SetOptions(options);
				lchart.Draw(result);
			},
			error : function(errorMsg) {
				alert("请求数据失败!");
			}
		});
	}
	$(function() {
		initLoongchartDemoData(loongAjaxDemoOptions);
	});
</script>
<div  class="portlet box yellow">
	<form:formTitle title="10月份的销售额" />
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12" >
				<div id="divLoongchartAjaxCanvas" style="width: 100%; height: 100%;">
				</div>
			</div>
		</div>
	</div>
</div>


