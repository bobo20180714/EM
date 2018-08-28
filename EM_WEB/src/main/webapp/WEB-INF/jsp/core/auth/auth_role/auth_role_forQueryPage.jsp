<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/form_editor_header.jsp"%>

<script type="text/javascript">
	function forAuthHomeModuleCheck(roleId, appId) {
		layer.open({
			title : '首页模块分配',
			type : 2,
			area : [ '300px', '400px' ],
			fix : false, //不固定
			maxmin : true,
			content : '${ctx}/authHomeModule/forCheckTree.do?ROLE_ID=' + roleId
					+ '&APP_ID=' + appId + ''
		});
	}

	function forAuthFuncCheckTreeToAuthRole(roleId, appId) {
		layer.open({
			title : '功能分配',
			type : 2,
			area : [ '300px', '400px' ],
			fix : false, //不固定
			maxmin : true,
			content : '${ctx}/authFunction/forCheckTree.do?ROLE_ID=' + roleId
					+ '&APP_ID=' + appId + '&TYPE=AUTH_ROLE'
		});
	}

	function forOrgEmployeeCheckTree(roleId) {
		layer.open({
			title : '关联人员',
			type : 2,
			area : [ '300px', '500px' ],
			fix : false, //不固定
			maxmin : true,
			content : '${ctx}/orgEmployee/forCheckTree.do?ROLE_ID=' + roleId
					+ '&TYPE=AUTH_ROLE'
		});
	}

	function deleteRole() {
		var checkedList = $("input[name='check']:checked")
		if (checkedList.length == 0) {
			BaseUtils.alert('请选择要删除的记录');
			return;
		}

		var ids = [];
		for (var i = 0; i < checkedList.length; i++) {
			ids.push($(checkedList[i]).val());
		}

		BaseUtils.defaultConfirm("你确定要删除吗？删除后不可恢复！", (function() {
			BaseUtils.showWaitMsg();
			$.ajax({
				type : "post",
				url : '${ctx}/authRole/batchDelete.do',
				data : {
					ids : ids.join(',')
				},
				dataType : "json",
				success : function(data) {
					//var ret = jQuery.parseJSON(data);
					BaseUtils.hideWaitMsg();
					BaseUtils.alert(data.msg);
					if (data.flag) {
						role_dataTable.ajax.reload();
					}
				}
			});
		}));
	}

	var role_dataTable;
	var ajaxParams = {};
	$(function() {

		//往后台传其他参数 ajaxParams["gogogogtest"] = "yesimtest";

		role_dataTable = $('#roleListTable')
				.DataTable(
						{
							/*  "bStateSave": true, */
							"serverSide" : true,
							"ajax" : {
								"url" : "authRole/getPageData.do",
								"data" : function(data) { // add request parameters before submit
									$.each(ajaxParams, function(key, value) {
										data[key] = value;
									});
								}
							},
							//需要排序或者需要筛选的列请加入names属性，并于数据库列名一致
							"columns" : [ null, null, {
								"width" : "15%",
								"data" : "roleName",
								"visible" : true,
								"bSortable" : false,
								"defaultContent" : ""
							}, {
								"width" : "15%",
								"data" : "roleType",
								"defaultContent" : "",
								"name" : "ROLE_TYPE"
							}, {
								"width" : "15%",
								"data" : "roleDesc",
								"defaultContent" : "",
								"name" : "ROLE_DESC"
							}, {
								"width" : "15%",
								"data" : "createTime",
								"defaultContent" : "",
								"name" : "CREATE_TIME"
							} ],
							"aaSorting" : [ [ 3, "desc" ] ],//此句会影响汉化，是个插件的bug，所以还得跟上下面一段代码
							"oLanguage" : {
								"sLengthMenu" : "显示  _MENU_ 条 "
							},

							"columnDefs" : [
									//不过滤的列请如下设定
									{
										"bSearchable" : false,
										"bVisible" : false,
										"aTargets" : [ 2 ]
									},
									{
										"width" : "200px",
										"searchable" : false,
										"orderable" : false,
										"targets" : [ 6 ],
										"data" : "roleId",
										"render" : function(data, type, row,
												meta) {
											var htmlContent = "<a href='authRole/forUpdate.do?id="
													+ data
													+ "' data-target='#ajax_lg' data-toggle='modal'>修改</a>";
											htmlContent = htmlContent
													+ " <a href='javascript:forAuthFuncCheckTreeToAuthRole(\""
													+ data + "\",\""
													+ row.appId
													+ "\");'>功能分配</a>";
											htmlContent = htmlContent
													+ " <a href='javascript:forOrgEmployeeCheckTree(\""
													+ data + "\");'>关联人员</a>";
											htmlContent = htmlContent
													+ " <a href='javascript:forAuthHomeModuleCheck(\""
													+ data + "\",\""
													+ row.appId
													+ "\");'>首页模块分配</a>";

											return htmlContent;
										},
									},
									{
										"width" : "5%",
										"searchable" : false,
										"orderable" : false,
										"data" : null,
										"targets" : [ 1 ],
										"render" : function(data, type, row,
												meta) {
											//每行的索引
											return meta.row
													+ role_dataTable.page
															.info().start + 1;
										}
									},
									{
										"width" : "5%",
										"searchable" : false,
										"orderable" : false,
										"data" : "roleId",
										"targets" : [ 0 ],
										"render" : function(data, type, row,
												meta) {
											return '<div class="acRolechecker"><span class="checkSpanAcRole"><input class="checker checkboxes" type="checkbox" name="check" value="'+data+'" /></span></div>';
										}
									}

							]
						});

		$('#roleListTable').on('change', 'tbody tr .checkboxes', function() {
			var beforeState = this.checked;
			$(this).prop("checked", this.checked);
			$(this).attr("checked", this.checked);
			if (!this.checked) {
				$("#checkAllAcRole").prop("checked", false);
				$("#checkAllAcRole").attr("checked", false);
				$("#checkAllAcRole").parents('span').removeClass("checked");
			}
			$(this).parents('span').toggleClass("checked");
			$(this).parents('tr').toggleClass("active");

		});
		//全选择 全解除
		$("#checkAllAcRole").click(function() {
			$(".acRolechecker :checkbox").prop("checked", this.checked);
			$(".acRolechecker :checkbox").attr("checked", this.checked);
			if (this.checked) {
				$(".checkSpanAcRole").addClass("checked");
				$(".checkSpanAcRole").parents('tr').addClass("active");
			} else {
				$(".checkSpanAcRole").removeClass("checked");
				$(".checkSpanAcRole").parents('tr').removeClass("active");
			}

			//var list = $("input[name='check']:checkbox:checked")
			/* var list = $("input[name='check']:checked")
			alert($(list[0]).val()); */
		});

		$("#upload").click(function() {
			debugger
			var fileObj = document.getElementById("file").files[0]; // 获取文件对象  
			var form = new FormData();
			form.append("file", fileObj);// 文件对象    
			$.ajax({
				url : "testupload.do",
				type : 'POST',
				data : form,
				// 告诉jQuery不要去处理发送的数据
				processData : false,
				// 告诉jQuery不要去设置Content-Type请求头
				contentType : false,
				beforeSend : function() {
					console.log("正在进行，请稍候");
				},
				success : function(responseStr) {
					if (responseStr.status === 0) {
						console.log("成功" + responseStr);
					} else {
						console.log("失败");
					}
				},
				error : function(responseStr) {
					console.log("error");
				}
			});
		});

	});
</script>
<div class=" tabPage">
	<sys:pageNavigation firstLevel="首页" secLevel="权限管理" thdLevel="角色管理" />
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<table:tableTitle title="角色列表" />
				<div class="portlet-body">
					<table:toolbar href="authRole/forUpdate.do?id=create"
						target="#ajax_lg" delclick="deleteRole()" />
					<table
						class="table table-striped table-bordered table-hover datatable"
						id="roleListTable">
						<thead>
							<tr role="row" class="heading">
								<th><input type="checkbox" id="checkAllAcRole"
									class="group-checkable" data-set="#roleListTable .checkboxes" /></th>
								<th>编号</th>
								<th>角色名称</th>
								<th>角色类型</th>
								<th>角色描述</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>

						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>