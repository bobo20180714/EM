<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/echarts_header.jsp"%>

	<div class=" tabPage">
		<!--<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <a href="${ctx }/main.do">首页</a>
					<i class="fa fa-angle-right"></i></li>
				<li><a href="#">开发示例</a> <i class="fa fa-angle-right"></i></li>
				<li><a href="#">【统计图表】动态数据示例</a></li>
			</ul>
		</div>  张慧敏修改-->
    	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->  
        <div id="mainAjax" style="height:400px"></div>  
  	</div>
  
    <script type="text/javascript" language="javascript">  
        var myAjaxChart;  
        var eChartsAjax;  
  
        require(  
            [ 'echarts',   
              'echarts/chart/line'  
            ], DrawEChart //异步加载的回调函数绘制图表  
        );  
  
        //创建ECharts图表方法  
        function DrawEChart(ec) {  
            eChartsAjax = ec;  
            myAjaxChart = eChartsAjax.init(document.getElementById('mainAjax'));  
            myAjaxChart.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
            var options = {  
                title : {  
                    text : "未来一周气温变化",  
                    subtext : "纯属虚构",  
                    sublink : "http://www.baidu.com"  
                },  
                tooltip : {  
                    trigger : 'axis'  
                },  
                legend : {  
                    //data : [ "最高气温" ]  
                },  
                toolbox : {  
                    show : true,  
                    feature : {  
                        mark : {  
                            show : true  
                        },  
                        dataView : {  
                            show : true,  
                            readOnly : false  
                        },  
                        magicType : {  
                            show : true,  
                            type : [ 'line', 'bar' ]  
                        },  
                        restore : {  
                            show : true  
                        },  
                        saveAsImage : {  
                            show : true  
                        }  
                    }  
                },  
                calculable : true,  
                xAxis : [ {  //x轴标题
                    type : 'category',  
                    boundaryGap : false,  
                    //data : [ '1', '2', '3', '4', '5', '6', '7' ]  
                } ],  
                yAxis : [ {  //y轴标题
                    type : 'value',  
                    axisLabel : {  
                        formatter : '{value} °C'  
                    },  
                    splitArea : {  
                        show : true  
                    }
                } ],  
                grid : {  
                    width : '90%'  
                },  
                series : [ {  
                    name : '最高气温',  
                    type : 'line',  
                    //【数据】data : [ 11, 22, 33, 44, 55, 33, 44 ],//必须是Integer类型的,String计算平均值会出错  
                    markPoint : {  
                        data : [ {  
                            type : 'max',  
                            name : '最大值'  
                        }, {  
                            type : 'min',  
                            name : '最小值'  
                        } ]  
                    },  
                    markLine : {  
                        data : [ {  
                            type : 'average',  
                            name : '平均值'  
                        } ]  
                    }  
                } ]  
            };  
            getChartData(options);//aja后台交互   
            //myAjaxChart.setOption(options); //先把可选项注入myAjaxChart中  
            //myAjaxChart.hideLoading();  
           
        }  

        function getChartData(options) {  
            //获得图表的options对象  
            //var options = myAjaxChart.getOption();  
            //通过Ajax获取数据  
            $.ajax({  
                type : "post",  
                async : false, //同步执行  
                url : "${ctx}/echartsDemo/ajaxExampleData.do",  
                data : {},  
                dataType : "json", //返回数据形式为json  
                success : function(result) {  
                    if (result) {  
                        options.legend.data = result.legend;  
                        options.xAxis[0].data = result.category;  
                        options.series[0].data = result.series[0].data;  
  
                        myAjaxChart.hideLoading();  
                        myAjaxChart.setOption(options);  
                    }  
                },  
                error : function(errorMsg) {  
                    alert("请求数据失败!");  
                    myAjaxChart.hideLoading();  
                }  
            });  
        }  
    </script>  
