$(function(){
	init();
})
//查询
function init(){
	//获取查询框的值
	var qParams={
			//'alarmName':$('#alarmName').val(),
			//'alarmType':$('#alarmType').combobox("getValue")
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
                    {title:'告警级别',field:'noticeLevel',width:100,align:'center'},
                    {title:'告警状态',field:'noticeStatus',width:100,align:'center'},
                    {title:'设备名称',field:'noticeName',width:100,align:'center'},
                    {title:'监测指标',field:'noticeType',width:100,align:'center'},
                    {title:'告警原因',field:'noticeReason',width:100,align:'center'},
                    {title:'开始时间',field:'createDateStr',width:100,align:'center'},
                    {title:'持续时间',field:'noticeDuration',width:100,align:'center'},
			        {title:'修改时间',field:'updateDateStr',width:100,align:'center'},
		          {title:'操作',field:'operation',width:150,align:'center',
		        	  formatter:function(value, row) {
						  var idStr = 1;
		        		  var s = ' <a href="#"  onclick="alarmEdit(\'' + idStr  + '\')">配置告警阀值</a> ';
                          var d = ' <a href="#"  onclick="alarmEditNotice(\'' + idStr + '\')">配置告警通知</a> ';


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
function alarmEdit(idStr){
	var idArr = idStr.split(",");
	var id = idArr[0];
	var alarmType = idArr[1];
	var title = "";
	if(alarmType=='JYZXT'){
		title = '交易子系统告警配置';
	}else if(alarmType=='JYWJHSJ'){
        title = '交易文件和数据告警配置';
	}else{
        title = '通信业务系统告警配置';
	}
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

//修改告警状态
function editAlarmStatus(id){
    $.messager.confirm('系统提示', '修改告警状态？', function(data) {
        if (data) {
            $.post(basePath + "alarm/alarmNoticeManagerController.do?editAlarmStatus", {id: id}, function (data) {
                $.messager.alert('提示', unescape(data.msg));
                init();
            }, 'json');
        }
    });

}

//修改告警通知配置
function alarmEditNotice(idStr){

    var idArr = idStr.split(",");
    var id = idArr[0];
    var alarmType = idArr[1];
    var title = "";
    if(alarmType=='JYZXT'){
        title = '交易子系统告警通知';
    }else if(alarmType=='JYWJHSJ'){
        title = '交易文件和数据告警通知';
    }else{
        title = '通信业务系统告警通知';
    }

    $(this).amsWindow({
        container : 'editNoticeWindow',
        type : 'add',
        url : basePath+'alarm/alarmNoticeManagerController.do?toEditNotice&id='+id,
        title : title,
        width : 600,
        height : 500,
        top : '10%'
    });
}
