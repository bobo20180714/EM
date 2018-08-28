<%@ page pageEncoding="UTF-8"%>
<span style="display:none;" id="acAppRootContextMenu">1</span>
<span style="display:none;" id="acAppAppContextMenu">2</span>
<span style="display:none;" id="acAppFuncGroupContextMenu">3</span>
<span style="display:none;" id="acAppFunctionContextMenu">4</span>
<script type="text/javascript">
$(function(){
	$.contextMenu({
	    selector: '#acAppRootContextMenu', 
	    trigger: 'none',
	    items: {
	        "add": {
	        	name: "新增应用", 
	        	icon: "edit", 
	        	callback: function(key, opt){
	        		forCreateApplication();
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh",
	        	callback: function(key, opt){
	        		reloadAppNode();
	        	}
	        }
	    }
	});
	$.contextMenu({
	    selector: '#acAppAppContextMenu', 
	    trigger: 'none',
	    items: {
	        "createFuncGroup": {
	        	name: "增加下级功能组", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateFuncGroup('APP');
	        	}
	        },
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		forDeleteApp('APP');
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh", 
	        	callback: function(key, opt){
	        		reloadAppNode();
	        	}
	        }
	    }
	});
	$.contextMenu({
	    selector: '#acAppFuncGroupContextMenu', 
	    trigger: 'none',
	    items: {
	        "createFuncGroup": {
	        	name: "增加下级功能组", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateFuncGroup('FUNC_GROUP');
	        	}
	        },
	        "createRes": {
	        	name: "增加资源", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateFunction();
	        	}
	        },
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		forDeleteApp('FUNC_GROUP');
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh", 
	        	callback: function(key, opt){
	        		reloadAppNode();
	        	}
	        }
	    }
	});
	
	$.contextMenu({
	    selector: '#acAppFunctionContextMenu', 
	    trigger: 'none',
	    items: {
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		forDeleteApp('FUNCTION');
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh", 
	        	callback: function(key, opt){
	        		reloadAppNode();
	        	}
	        }
	    }
	});
});

</script>
