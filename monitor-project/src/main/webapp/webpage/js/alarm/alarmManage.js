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
		url:basePath+'alarm/alarmManagerController.do?getAlarmList',  //数据源
		queryParams:qParams,//查询框内容
		fit:true,
		fitColumns:true,
		idField:"id",
		columns:[[//列
		          {field:'id',hidden:true},
		          {title:'告警名称',field:'alarmName',width:120,align:'center'},
					{
						title: '告警状态', field: 'alarmStatus', width: 50, align: 'center',
						formatter: function (value, row) {
							var e = "";
							if (row.alarmStatus == 'Y') {
								e = '正常';
							} else {
								e = '关闭';
							}
							return e;
						}
					},
			      {title:'修改时间',field:'updateDateStr',width:100,align:'center'},
			      {title:'告警方式',field:'alarmType',width:60,align:'center',
                      formatter: function (value, row) {
                          var e = "";

                          if (row.alarmMessage!="") {
                              e += "&nbsp;短信";
                          }

                          if (row.alarmEmail!="") {
                              e += "&nbsp;邮件";
                          }

                          if(e==""){
                          	e += "无";
						  }

                          return e;
                      }
                  },
            	  {title:'告警时间',field:'alarmCronTrigger',width:60,align:'center'},
		          {title:'操作',field:'operation',width:150,align:'center',
		        	  formatter:function(value, row) {
						  var idStr = row.id+","+row.alarmType;
		        		  var s = ' <a href="#"  onclick="alarmEdit(\'' + idStr  + '\')">配置告警阀值</a> ';
                          var d = ' <a href="#"  onclick="alarmEditNotice(\'' + idStr + '\')">配置告警通知</a> ';
                          var e = "";
                          if(row.alarmStatus=='Y'){
                              e = ' <a href="#"  onclick="editAlarmStatus(\'' + row.id + '\')">关闭</a> ';
						  }else{
                              e = ' <a href="#"  onclick="editAlarmStatus(\'' + row.id + '\')">开启</a> ';
						  }

		        		  return s +"&nbsp;"+ d +"&nbsp;"+ e;
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
        url : basePath+'alarm/alarmManagerController.do?toEdit&id='+id,
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
            $.post(basePath + "alarm/alarmManagerController.do?editAlarmStatus", {id: id}, function (data) {
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
        url : basePath+'alarm/alarmManagerController.do?toEditNotice&id='+id,
        title : title,
        width : 600,
        height : 500,
        top : '10%'
    });
}

//删除告警配置
function alarmDel(id){
	$.messager.confirm('系统提示', '您确定要执行删除操作吗？', function(data) {
		if(data){
			$.post(basePath+"alarm/alarmManagerController.do?delete", {id:id},function(data){
				$.messager.alert('提示',unescape(data.msg));
				init();
			},'json');
		}
	});
}


//分配到用户
function allocationToUser(){
	var row=$('#getAlarmList').datagrid("getSelected");
	if(row){
		var roleId=row.id;
		$(this).amsWindow({
			container : 'allocationToUserWindow',
			type : 'add',
			url : basePath+'manage/system/alarmManagerController.do?toAllocationToUser&roleId='+roleId,
			title : '角色管理-分配到用户',
			width : 730,
			height : 520,
			top : '3%'
		});
	}else{
		$.messager.alert('提示','请选择一个角色分配用户');
	}
}

//分配到菜单
function allocationMenu(){
	var row=$('#getAlarmList').datagrid("getSelected");
	if(row){
		var roleId=row.id;
		$(this).amsWindow({
			container : 'allocationToUserWindow',
			type : 'add',
			url : basePath+'manage/system/alarmManagerController.do?toAllocationToMenu&roleId='+roleId,
			title : '角色管理-分配菜单资源',
			width : 630,
			height : 570,
			top : '3%'
		});
	}else{
		$.messager.alert('提示','请选择一个角色分配菜单');
	}
}