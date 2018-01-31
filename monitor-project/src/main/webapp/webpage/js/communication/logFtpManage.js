$(function() {
	init();
	//日期控制扩展选择日期小于等于当前日期，开始日期小于等于结束日期
    $("#dateTime1").datetimebox('calendar').calendar({
        validator: function(beginDate) {
            var date = new Date();
            return beginDate < date;
        }
    });
    $("#dateTime2").datetimebox('calendar').calendar({
        validator: function(beginDate) {
            var date = new Date();
            return beginDate < date;
        }
    });
})
// 查询
function init() {
	// 获取查询框的值
	var qParams = {
		'ftpIp' : $('#ftpIp').val(),
		'fileName' : $('#fileName').val(),
		'dateTime1' : $('#dateTime1').datebox('getValue'),
		'dateTime2' : $('#dateTime2').datebox('getValue')
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
							title : 'FTP类型',
							field : 'OPER_CODE',
							width : 100,
							align : 'center'
						},
						{
							title : '操作结果',
							field : 'RESULT',
							width : 100,
							align : 'center'
						},
						{
							title : '数量',
							field : 'NUM',
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
	var date1 = $('#dateTime1').datebox('getValue');
	var date2 = $('#dateTime2').datebox('getValue');
	if(date1 !="" && date2 !="") {
		if(date1 > date2) {
			$.messager.alert('提示','开始时间不能大于结束时间！');
			return;
		}
	}
	init();
}

// 清空
function clean() {
	$('#role_search_form').form('clear');
}
