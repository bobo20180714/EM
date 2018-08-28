<%@ page pageEncoding="UTF-8"%>
<span style="display:none;" id="${idPrefix}sysDictRootContextMenus">1</span>
<span style="display:none;" id="${idPrefix}sysDictChildrenContextMenus">2</span>
<script type="text/javascript">
$(function(){
	$.contextMenu({
	    selector: '#${idPrefix}sysDictRootContextMenus', 
	    trigger: 'none',
	    items: {
	        "add": {
	        	name: "新增下级字典", 
	        	icon: "edit", 
	        	callback: function(key, opt){
	        		${idPrefix}forCreateDictItem();
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh",
	        	callback: function(key, opt){
	        		${idPrefix}reloadDictNodes();
	        	}
	        }
	    }
	});
	$.contextMenu({
	    selector: '#${idPrefix}sysDictChildrenContextMenus', 
	    trigger: 'none',
	    items: {
	        "createOrg": {
	        	name: "新增下级字典", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		${idPrefix}forCreateDictItem();
	        	}
	        },
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		${idPrefix}forDeleteDictItem();
	        	}
	        },
	        "sep1": "---------",
	        "refresh": {
	        	name: "刷新", 
	        	icon: "fa-refresh", 
	        	callback: function(key, opt){
	        		${idPrefix}reloadDictNodes();
	        	}
	        }
	    }
	});
});
</script>



