<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<body>
	<br />
	<div style="width: 99%; padding-top: 10px; padding-left: 10px"
		id="centerMain">
		<div class="tahoma_font tableStyle_5_0">
			<form id="role_search_form">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="padding-left: 20px; padding-top: 10px;" colspan="6">
							<a href="javascript:void(0);" class="easyui-linkbutton"
							onclick="addOperation();" style="padding: 0 10px">新增按钮 </a>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<!--上下距间-->
		<div class="easyui-layout" style="height: 10px"></div>
		<div class="line"></div>
		<!--gap-->
		<div class="easyui-layout" style="height: 10px"></div>
		<div class="easyui-layout" style="height: 443px; padding: 10px">
			<table id="getOperationList">
			</table>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			init();
		})
		function init() {
			$("#getOperationList")
					.datagrid(
							{
								pagination : true, //翻页工具栏
								pageNumber : 1, //默认显示第一页
								rownumbers : true, //行号
								singleSelect : true, //单选
								url : basePath
										+ 'manage/system/functionManagerController.do?getOperationList&functionId=${functionId}', //数据源
								fit : true,
								fitColumns : true,
								idField : "id",
								columns : [ [//列
										{
											field : 'id',
											hidden : true
										},
										{
											title : '操作名称',
											field : 'operateName',
											width : 70,
											align : 'center'
										},
										{
											title : '操作代码',
											field : 'operateCode',
											width : 100,
											align : 'center'
										},
										{
											title : '操作',
											field : 'operation',
											width : 100,
											align : 'center',
											formatter : function(value, row) {
												var s = ' <a href="#"  onclick="editOperation(\''
														+ row.id
														+ '\')">修改</a> ';
												var d = ' <a href="#"  onclick="delOperation(\''
														+ row.id
														+ '\')">删除</a> ';
												return s + d;
											}
										} ] ],
								onLoadSuccess : function(data) {

								},
								onLoadError : function(data) {
									if (data) {

									}
								}
							});
		}

		function addOperation() {
			$(this)
					.amsWindow(
							{
								container : 'addOperation',
								type : 'add',
								url : basePath
										+ 'manage/system/functionManagerController.do?toEditFunction&functionId=${functionId}',
								title : '新增按钮',
								width : 740,
								height : 350,
								top : '15%'
							});
		}

		function editOperation(id) {
			$(this)
					.amsWindow(
							{
								container : 'addOperation',
								type : 'add',
								url : basePath
										+ 'manage/system/functionManagerController.do?toEditFunction&functionId=${functionId}&operationId='
										+ id,
								title : '新增按钮',
								width : 740,
								height : 350,
								top : '15%'
							});
		}

		function delOperation(id) {
			$.messager
					.confirm(
							'系统提示',
							'您确定要执行删除操作吗？',
							function(data) {
								if (data) {
									$
											.ajax({
												url : basePath
														+ 'manage/system/functionManagerController.do?deleteOperation&id='
														+ id,
												type : "POST",
												dataType : "json",
												success : function(d) {
													if (d.resultCode == 0) {
														$.messager.alert("提示",
																d.msg);
														init();
													}
												}
											});
								}
							});
		}
	</script>