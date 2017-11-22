$(function() {
	init();
})
// 查询
function init() {
	// 获取查询框的值
	var qParams = {
		'linkIp' : $('#linkIp').val(),
		'serverCode' : $('#serverCode').val(),
		'dateTime1' : $('#dateTime1').val(),
		'dateTime2' : $('#dateTime2').val()
	};

	// 表单信息
	$("#getLogRecvsendList").datagrid(
			{
				pagination : true, // 翻页工具栏
				pageNumber : 1, // 默认显示第一页
				rownumbers : true, // 行号
				singleSelect : true, // 单选
				url : basePath + 'communication/logRecvsend.do?getLogRecvsendList', // 数据源
				queryParams : qParams,// 查询框内容
				fit : true,
				fitColumns : true,
				idField : "id",
				columns : [ [// 列
						{
							field : 'id',
							hidden : true
						},
						{
							title : 'IP地址',
							field : 'linkIp',
							width : 100,
							align : 'center'
						},
						{
							title : '消息总数',
							field : 'fileName',
							width : 70,
							align : 'center'
						},
						{
							title : '成功数量',
							field : 'successCount',
							width : 100,
							align : 'center'
						},
						{
							title : '失败数量',
							field : 'failureCount',
							width : 100,
							align : 'center'
						},{
							title : '流量',
							field : 'msgLength',
							width : 100,
							align : 'center'
						}
						] ],
				onLoadSuccess : function(data) {

				},
				onLoadError : function(data) {
					if (data) {

					}
				}
			});
}

// 点击查询按钮
function listSearch() {
	init();
}

// 清空
function clean() {
	$('#role_search_form').form('clear');
}
