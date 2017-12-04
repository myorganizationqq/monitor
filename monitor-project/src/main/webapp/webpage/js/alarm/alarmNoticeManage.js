$(function(){
	init();
})
//查询
function init(){
	//获取查询框的值
	var qParams={
			'createDateStr':$("#beginDate").datebox('getValue'),
			'updateDateStr':$('#endDate').datebox('getValue'),
			'noticeName':$('#noticeName').combobox("getValue")
	};

	//表单信息
	$("#getAlarmList").datagrid({
		pagination:true,	    //翻页工具栏
		pageNumber:1,           //默认显示第一页
		rownumbers:true,	    //行号
		singleSelect:true,      //单选
		url:basePath+'alarm/alarmNoticeManagerController.do?getAlarmList',  //数据源
		queryParams:qParams,//查询框内容
		fit:true,
		fitColumns:true,
		idField:"id",
		columns:[[//列
		          {field:'id',hidden:true},
            		{title:'告警类型',field:'noticeName',width:150,align:'center'},
                    {title:'告警状态',field:'noticeStatus',width:80,align:'center'},
			        {title:'异常指标',field:'noticeIndex',width:200,align:'center'},
                    {title:'开始时间',field:'createDateStr',width:150,align:'center'},
                    {title:'持续时间',field:'noticeDuration',width:100,align:'center'},
			        {title:'修改时间',field:'updateDateStr',width:150,align:'center'},
		          {title:'操作',field:'operation',width:150,align:'center',
		        	  formatter:function(value, row) {
						  var id = row.id;
		        		  var s = ' <a href="#"  onclick="toDetail(\'' + id  + '\')">详情</a> ';
                          var d = ' <a href="#"  onclick="toEdit(\'' + id + '\')">处理</a> ';


		        		  return s +"&nbsp;"+ d;
		        	  }
		          }
		          ]],
		          onLoadSuccess: function(data){

		          },
		          onLoadError:function(data){
		        	  if(data){

		        	  }
		          }
	});
}

//点击查询按钮
function alarmSearch(){
	init();
}

//清空
function clean(){
	$('#role_search_form').form('clear');
}


//修改告警配置
function toDetail(id){

    $(this).amsWindow({
        container : 'editWindow',
        type : 'add',
        url : basePath+'alarm/alarmNoticeManagerController.do?toDetail&id='+id,
        title : title,
        width : 730,
        height : 550,
        top : '10%'
    });
}

//修改告警配置
function toEdit(id){

    $(this).amsWindow({
        container : 'editWindow',
        type : 'add',
        url : basePath+'alarm/alarmNoticeManagerController.do?toEdit&id='+id,
        title : title,
        width : 730,
        height : 550,
        top : '10%'
    });
}
