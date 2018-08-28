<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/echarts_header.jsp"%>
<script type="text/javascript">
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('mainStatic')); 
                
                var option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['销量']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"销量",
                            "type":"bar",
                            "data":[5, 20, 40, 10, 10, 20]
                        }
                    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
    
  <div class=" tabPage">
	<!-- <div class="page-bar">
		<ul class="page-breadcrumb">
			<li><i class="fa fa-home"></i> <a href="${ctx }/main.do">首页</a>
				<i class="fa fa-angle-right"></i></li>
			<li><a href="#">开发示例</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#">【统计图表】官方示例</a></li>
		</ul>
	</div>张慧敏修改-->
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="mainStatic" style="height:400px"></div>
  </div>
