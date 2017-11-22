$(function() {
	init();
})
// 查询
function init() {
	// 获取查询框的值
	var qParams = {
		'ftpId' : $('#ftpId').val(),
		'fileName' : $('#fileName').val(),
		'dateTime1' : $('#dateTime1').val(),
		'dateTime2' : $('#dateTime2').val(),
	};

	// 表单信息
	$("#getLogFtpList").datagrid(
			{
				pagination : true, // 翻页工具栏
				pageNumber : 1, // 默认显示第一页
				rownumbers : true, // 行号
				singleSelect : true, // 单选
				url : basePath + 'communication/logFtp.do?getLogFtpList', // 数据源
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
							title : 'FTP IP地址',
							field : 'FTP_IP',
							width : 100,
							align : 'center'
						},
						{
							title : '文件名称',
							field : 'FILENAME',
							width : 70,
							align : 'center'
						},
						{
							title : '下载成功数量',
							field : 'SUCCESS',
							width : 100,
							align : 'center'
						},
						{
							title : '下载失败数量',
							field : 'FAILURE',
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
