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
		'linkIp' : $('#linkIp').val(),
		'serverCode' : $('#serverCode').val(),
		'dateTime1' : $('#dateTime1').datebox('getValue'),
		'dateTime2' : $('#dateTime2').datebox('getValue')
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
							field : 'LINK_IP',
							width : 100,
							align : 'center'
						},
						{
							title : '消息总数',
							field : 'totalCount',
							width : 70,
							align : 'center'
						},
						{
							title : '成功数量',
							field : 'SUCCESS',
							width : 100,
							align : 'center'
						},
						{
							title : '失败数量',
							field : 'FAILURE',
							width : 100,
							align : 'center'
						},{
							title : '流量',
							field : 'MSG_LENGTH',
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
			alert("开始时间不能大于结束时间！");
			return;
		}
	}
	init();
}

// 清空
function clean() {
	$('#role_search_form').form('clear');
}
