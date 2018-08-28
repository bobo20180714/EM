<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %><%-- ${fns:xxxx}方法标签 --%>
<!-- 自定义首页内容 BEGIN -->
<%@ include file="../../common/gridster_header.jsp"%>
<style type="text/css">
			<!-- 
			.gridster * {
				margin: 0;
				padding: 0;
			}
			
			/*/
			/* demo
			/*/
			body {
				list-style:none;
				font-size: 16px;
				font-family: 'Helvetica Neue', Arial, sans-serif;
				color: #444;
				/* margin: 30px 40px; */
			}
			
			.controls {
				margin-bottom: 20px;
			}
			/*/
			/* gridster
			/*/
			.gridster ul {
				background-color: #eee
			}
			
			.gridster li {
				font-size: 1em;
				font-weight: bold;
				text-align: center;
				line-height: 100%;
			}
			
			.gridster {
				margin: 0 auto;
				opacity: .8;
				-webkit-transition: opacity .6s;
				-moz-transition: opacity .6s;
				-o-transition: opacity .6s;
				-ms-transition: opacity .6s;
				transition: opacity .6s;
			}
			
			.gridster .gs-w {
				background: #DDD;
				cursor: pointer;
			}
			
			.gridster .player {
				background: #BBB;
			}
			
			.gridster .preview-holder {
				border: none !important;
				background: red !important;
			}
			-->
		</style>
<!-- BEGIN CONTENT -->
	<!-- <div id="main_content" class="page-content-wrapper"> -->
		<div class="page-content">
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="index.html">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#">主页面</a>
					</li>
				</ul>
			</div>


		
		
		<script type="text/javascript">
	      var gridster;
	      // same object than generated with gridster.serialize() method
	      //var serialization = [{"col":1,"row":1,"size_x":2,"size_y":2},{"col":4,"row":2,"size_x":1,"size_y":2},{"col":4,"row":1,"size_x":1,"size_y":1},{"col":1,"row":3,"size_x":1,"size_y":1},{"col":2,"row":4,"size_x":3,"size_y":1},{"col":1,"row":4,"size_x":1,"size_y":1},{"col":2,"row":5,"size_x":1,"size_y":1},{"col":2,"row":6,"size_x":1,"size_y":1}];
	
	
	      // sort serialization
	      //serialization = Gridster.sort_by_row_and_col_asc(serialization);
	
	      $(function(){
			var cWidth = document.documentElement.clientWidth*0.96;
			var cHeight = document.documentElement.clientHeight*0.94;
			//alert(cWidth+" " +cHeight);
	        gridster = $(".gridster ul").gridster({
	          max_cols:8,
	          widget_base_dimensions: [cWidth/8, cHeight/6],
	          widget_margins: [5, 5],
	          resize: {
	              enabled: true
	          },
	          serialize_params: function(w, wgd) {
	        	  return {OPERATOR_ID:'${loginOperator.OPERATOR_ID}',HOME_MODULE_ID:w.attr('id'), DATA_COL: wgd.col, DATA_ROW: wgd.row, DATA_SIZEX: wgd.size_x, DATA_SIZEY: wgd.size_y } 
	        	}
	        }).data('gridster');
			//初始化后禁用
	        gridster.disable();
			
		    $(document).contextmenu({
				  target: '#contextMenu'
			});
	        
	      });
	      
	      function changeHomePageModule(){
	    	  
	    	  var css = $('#collapseExample').attr('class');
	    	  if(css=='collapse'){
	    		  gridster.enable();
	    	  }else{
	    		  gridster.disable();
	    	  }
	    	  
	  		  $('.gridster ul').css("background-color","#EFEFEF"); 
	  		  $('#collapseExample').collapse('toggle');
	      }
	      
	      function nothing(){
	    	  
	      }
	      
	      function toggelModule(id,name,col,row,sizex,sizey){
	    	  if($("#cb_"+id).prop("checked")==false){
	    		  gridster.remove_widget( $('#'+id), nothing );
	    		  //$('#'+id).remove();
	    	  }else{
	    		   var gridste = $(".gridster ul")
	    		   //gridste.append('<li id="'+id+'" data-col="'+col+'" data-row="'+row+'" data-sizex="'+sizex+'" data-sizey="'+sizey+'" class="gs-w" style="display: list-item;">'+name+'</li>');
	    		   gridster.add_widget( '<li id="'+id+'">'+name +'</li>', sizex, sizey, col, row )
	    		  
	    		  
	/*     		  var widgets = [
	    		                 ['<li id="'+id+'">'+name +'</li>', sizex, sizey]
	    		             ];
	
	    		             $.each(widgets, function(i, widget){
	    		                 gridster.add_widget.apply(gridster, widget)
	    		             }); */
	    		  
	    	  }
	
	      }
	      
	      function saveDefModify(){
	    	  var jsonDataPer = gridster.serialize();
	    	  jsonDataPer= JSON.stringify(jsonDataPer);
	    	  //jsonDataPer="["+jsonDataPer+"]";
	    		var jsonData = {"dataJsonStr":jsonDataPer,"operatorId":"${loginOperator.OPERATOR_ID}"};
	    	  defaultAjax("acHomeModule/batchInsertOperHomeModule.do",jsonData,homeModuleCallBack);
	    	  
	      }
	      
	      function homeModuleCallBack(data){
	      	//var ret = jQuery.parseJSON(data);
	      	BaseUtils.hideWaitMsg();
	      	alert(data.msg);
	      	if (data.flag) {
	      		BaseUtils.loadMainContent('home.do','');
	      	}
	      }
	    </script>
		<div id="contextMenu">
			<ul class="dropdown-menu pull-left" role="menu">
				<li>
					<a  role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="javascript:changeHomePageModule();"> 
						<i class="icon-flag"></i> 首页定制
					</a>
				</li>
			</ul>
		
	
		<!-- <div class="menu-sep"></div> -->
	</div>

	<div class="collapse" id="collapseExample">
	  <div class="well well-sm" style="margin-bottom:1px">
	  	<button onclick="saveDefModify()" type="button" class="btn btn-primary btn-sm">保存定制</button>
	  	<c:forEach var="r" items="${allPermHomeModuleViewList}" varStatus="status">
	  		<label class="checkbox-inline">
			  <input onclick="toggelModule('${r.HOME_MODULE_ID }','${r.MODULE_NAME }','${r.DATA_COL }','${r.DATA_ROW }','${r.DATA_SIZEX }','${r.DATA_SIZEY }')" type="checkbox" <c:if test="${r.CHECKED =='true'}">checked</c:if> id="cb_${r.HOME_MODULE_ID }" value="${r.HOME_MODULE_ID }"> ${r.MODULE_NAME }
			</label>
	  	</c:forEach>
	  </div>
	</div>
   <div class="gridster ready"  style="width: 100%; height: 100%;margin:0">
   		<ul>
   			<c:forEach var="r" items="${operHomeModuleViewList}" varStatus="status">
				<li id="${r.HOME_MODULE_ID }" data-col="${r.DATA_COL }" data-row="${r.DATA_ROW }" data-sizex="${r.DATA_SIZEX }" data-sizey="${r.DATA_SIZEY }" class="gs-w player-revert" style="list-style-type:none;display: list-item; position: absolute;">
				
				<div class="panel panel-primary" style="width: 100%; height: 100%;">
				  <div class="panel-heading" style="text-align:left">
			        <h3 class="panel-title">${r.MODULE_NAME }</h3>
			      </div>
			      <div class="panel-body" style="width: 100%; height: 100%;">
					${r.HTML_CONTENT }
				  </div>
				</div>
				</li>
			</c:forEach>
   		</ul>

	</div>
		
		

		<!-- 自定义首页内容END -->
			
			
			
			
			
		</div>
	<!-- </div> -->
	<!-- END CONTENT -->