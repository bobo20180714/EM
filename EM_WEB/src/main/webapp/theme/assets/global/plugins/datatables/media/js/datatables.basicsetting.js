/**
 * datatables 自定义基础设置
 * @author fuht
 * @date 2016年7月8日下午1:48:00
 */
var DatatablesCustom = {
	basicSetting : {
		//"dom":"l<'buttonDiv'Bf>rt<'fenyebox'<'yeshubox'i><'search_fenye overflow'p>>",
		"dom":"l<'table-toolbar'<'row'<'col-md-6'<'clearfix'Bf>>>>rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
		"serverSide": true,//开启服务器模式
		"lengthChange": false,//是否允许用户自定义显示数量
		"bPaginate": true, //翻页功能
		"bFilter": false, //列筛序功能
		"searching": false,//本地搜索
		"ordering": true, //排序功能
		"autoWidth": true,//自动宽度
		"processing" : true,//显示正在加载
		"colReorder": {
			"realtime": true
		}/*,//列拖动顺序
		"language": {
			"zeroRecords": "没有匹配的记录",
			"info": "<p>总记录数：_TOTAL_条</p><p>|</p><p>每页显示：10  条</p><p>|</p><p>总页数：_PAGES_页</p>",
			"infoEmpty": "无数据！"
		},//国际化*/
		/*"buttons": [
			{extend: '',text: '导出全部',action:function (){expExecl('1');}},
			{extend: 'colvis',columns: ':not(:first-child)',text:"显示/隐藏列"},
			{extend: '',text: '查询',action:function (){searchSubmit();}}
		]//按钮*/
	}
};

//列宽拖动
var ColResizable = {
	//列宽拖动初始化
	applyColresizable : function(tableId){
		if(null != tableId && "" != tableId){
			$("#"+tableId).colResizable({
				liveDrag:true,
				gripInnerHtml:"<div class='grip'></div>",
				draggingClass:"dragging",
				resizeMode:'fit',
				disabledColumns:[0]
			});
		}
	},
	//禁用列宽拖动
	//当进行类似于列显示隐藏或者列顺序拖动等对表格结构有变更的操作时,需要先禁用列宽拖动的插件,等待前者操作完成后再重新初始化列宽拖动
	refrechColResizable : function(tableId){
		//console.log("关:"+tableId);
		if(null != tableId && "" != tableId){
			$("#"+tableId).colResizable({disable:true});
		}
	}
};
