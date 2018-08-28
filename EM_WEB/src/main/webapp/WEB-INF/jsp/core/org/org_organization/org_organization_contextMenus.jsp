<%@ page pageEncoding="UTF-8"%>
<span style="display:none;" id="orgOrgRootContextMenus">1</span>
<span style="display:none;" id="orgOrgOrgContextMenus">2</span>
<span style="display:none;" id="orgOrgPosiContextMenus">3</span>
<span style="display:none;" id="orgOrgEmpContextMenus">4</span>
<script type="text/javascript">
$(function(){
	$.contextMenu({
	    selector: '#orgOrgRootContextMenus', 
	    trigger: 'none',
	    items: {
	        "add": {
	        	name: "增加下级机构", 
	        	icon: "edit", 
	        	callback: function(key, opt){
	        		forCreateChildOrg();
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
	    selector: '#orgOrgOrgContextMenus', 
	    trigger: 'none',
	    items: {
	        "createOrg": {
	        	name: "增加下级机构", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateChildOrg();
	        	}
	        },
	        "createPosi": {
	        	name: "增加下级岗位", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateChildPosi('ORG');
	        	}
	        },
	        "createEmp": {
	        	name: "增加人员", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateChildEmp('ORG');
	        	}
	        },
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		forDelete('ORG');
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
	    selector: '#orgOrgPosiContextMenus', 
	    trigger: 'none',
	    items: {
	        "createPosi": {
	        	name: "增加下级岗位", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateChildPosi('POSI');
	        	}
	        },
	        "createEmp": {
	        	name: "增加人员", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forCreateChildEmp('POSI');
	        	}
	        },
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		forDelete('POSI');
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
	    selector: '#orgOrgEmpContextMenus', 
	    trigger: 'none',
	    items: {
	        "modifyRoles": {
	        	name: "维护人员角色", 
	        	icon: "edit", 
	        	callback: function(key, opt){
	        		forOperatorRole();
	        	}
	        },
	        "modifyFunc": {
	        	name: "维护特殊权限", 
	        	icon: "add", 
	        	callback: function(key, opt){
	        		forOperFunc();
	        	}
	        },
	        "delete": {
	        	name: "删除", 
	        	icon: "delete", 
	        	callback: function(key, opt){
	        		forDelete('EMP');
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

