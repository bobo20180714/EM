<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/loongchart_header.jsp"%>

<div class=" tabPage">
	<div id="mainLoongchartStatic" style="width: 80%; height: 400px;">
    </div>
</div>

<script type="text/javascript">
        $(function() {
            var data = [
                { text: 'C', value: 17.855 },
                { text: 'Java', value: 17.417 },
                { text: 'Objective-C', value: 10.283 },
                { text: 'C++', value: 9.140 },
                { text: 'C#', value: 6.196 },
                { text: 'PHP', value: 5.546 },
                { text: 'Visual Basic', value: 4.749 },
                { text: 'Python', value: 4.173 },
                { text: 'Perl', value: 2.264 },
                { text: 'Javascript', value: 1.976 },
                { text: 'Others', value: 20.401 }
            ];
            var options = {
                animation: false,
                title: { content: 'Programming Language Ranking List in January 2013.' },
                subTitle: { content: 'Here\'s Top Ten Languages.' },
                legend: { sidelength: 10, fontsize: 13, enablecontrol: true },
                tip: { content: function(data) { return '<div>' + data.text + '<br/>value：' + data.value.toString() + '<br/>percent：' + data.percent.toFixed(3) + '%</div>'; } }
            };

            window.lchart = new LChart.Pie3D('mainLoongchartStatic', 'CN');
            lchart.SetSkin('BlackAndWhite');
            lchart.SetOptions(options);
            lchart.Draw(data);
        });
    </script>
