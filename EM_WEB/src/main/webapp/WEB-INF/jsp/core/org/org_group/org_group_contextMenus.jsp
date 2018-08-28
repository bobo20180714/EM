<%@ page pageEncoding="UTF-8"%>
<div id="root">
	<ul class="dropdown-menu pull-left" role="menu">
		<li>
			<a href="javascript:void(0);" onclick="forCreateMenu('group')"> 
				<i class="icon-flag"></i> 
				增加工作组
			<!-- <span class="badge badge-danger">4</span> -->
			</a>
		</li>
		<li class="divider"></li>
		<li>
			<a href="javascript:void(0);" onclick="reloadNode();"> 
				<i class="icon-flag"></i> 
				刷新
			<!-- <span class="badge badge-danger">4</span> -->
			</a>
		</li>

	</ul>
</div>
<div id="group">
	<ul class="dropdown-menu pull-left" role="menu">
		<li>
			<a href="javascript:void(0);" onclick="forCreateMenu('leafGroup')"> 
				<i class="icon-flag"></i> 
				增加工作组
			<!-- <span class="badge badge-danger">4</span> -->
			</a>
		</li>
		<li>
			<a href="javascript:void(0);" onclick="forAddEmp()"> 
				<i class="icon-flag"></i> 
				增加人员
			</a>
		</li>
		<li>
			<a href="javascript:void(0);" onclick="forDelete('leafGroup')"> 
				<i class="icon-flag"></i> 
				删除
			<!-- <span class="badge badge-danger">4</span> -->
			</a>
		</li>
		<li class="divider"></li>
		<li>
			<a href="javascript:void(0);" onclick="reloadNode();"> 
				<i class="icon-flag"></i> 
				刷新
			<!-- <span class="badge badge-danger">4</span> -->
			</a>
		</li>

	</ul>
</div>
<div id="leaf">
	<ul class="dropdown-menu pull-left" role="menu">
		<li>
			<a href="javascript:void(0);" onclick="forDelete('LEAF')"> 
				<i class="icon-flag"></i> 
				删除
			<!-- <span class="badge badge-danger">4</span> -->
			</a>
		</li>
		<li class="divider"></li>
		<li>
			<a href="javascript:void(0);" onclick="reloadNode();"> 
				<i class="icon-flag"></i> 
				刷新
			<!-- <span class="badge badge-danger">4</span> -->
			</a>
		</li>

	</ul>
</div>

<!-- <div id="root" class="easyui-menu" style="width:120px;display:none;">
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateMenu('ROOT');">增加工作组</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div> 

<div id="group" class="easyui-menu" style="width:120px;display:none;">
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateMenu('group');">增加工作组</div>
	<div class="menu-sep"></div>
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forAddEmp();">增加人员</div>
	<div class="menu-sep"></div>
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('leafGroup');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="leaf" class="easyui-menu" style="width:120px;display:none;">
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('LEAF');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>-->

