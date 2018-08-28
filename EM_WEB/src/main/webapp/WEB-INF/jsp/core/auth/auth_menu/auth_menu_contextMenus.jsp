<%@ page pageEncoding="UTF-8"%>
<span style="display:none;" id="appContextMenu">1</span>
<span style="display:none;" id="parentMenuContextMenu">2</span>
<span style="display:none;" id="leafMenuContextMenu">3</span>
<script type="text/javascript">
$(function(){
	$.contextMenu({
	    selector: '#appContextMenu', 
	    trigger: 'none',
	    items: {
	        "add": {
	        	name: "增加菜单", 
	        	icon: "edit", 
	        	callback: function(key, opt){
	        		forCreateMenu('APP');
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh",
	        	callback: function(key, opt){
	        		reloadNode();
	        	}
	        }
	    }
	});
	$.contextMenu({
	    selector: '#parentMenuContextMenu', 
	    trigger: 'none',
	    items: {
	        "createMenu": {
	        	name: "增加菜单", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateMenu('PARENT_MENU');
	        	}
	        },
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		forDeleteAcMenu('PARENT_MENU');
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh", 
	        	callback: function(key, opt){
	        		reloadNode();
	        	}
	        }
	    }
	});


	$.contextMenu({
	    selector: '#leafMenuContextMenu', 
	    trigger: 'none',
	    items: {
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		forDeleteAcMenu('LEAF_MENU');
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh", 
	        	callback: function(key, opt){
	        		reloadNode();
	        	}
	        }
	    }
	});
});

</script>


