$(function(){
	init();
})
//查询
function init(){
	//获取查询框的值
	var qParams={
			'alarmName':$('#alarmName').val(),
			'alarmType':$('#alarmType').combobox("getValue")
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
		          {title:'告警配置名称',field:'alarmName',width:100,align:'center'},
		          {title:'告警配置类别',field:'alarmType',width:70,align:'center'},
		          {title:'添加时间',field:'createDateStr',width:100,align:'center'},
			      {title:'修改时间',field:'updateDateStr',width:100,align:'center'},
		          {title:'操作',field:'operation',width:100,align:'center',
		        	  formatter:function(value, row) {
		        		  var s = ' <a href="#"  onclick="alarmEdit(\'' + row.id + '\')">修改</a> ';
		        		  var d = ' <a href="#"  onclick="alarmDel(\'' + row.id + '\')">删除</a> ';
		        		  return s + d;
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

//新增告警配置
function alarmAdd(alarmType){
	$(this).amsWindow({
		container : 'addWindow',
		type : 'add',
		url : basePath+'/alarm/alarmManagerController.do?toAdd&alarmType='+alarmType,
		title : '新增告警配置',
		width : 760,
		height : 350,
		top : '15%'
	});
}

//修改告警配置
function alarmEdit(id){
	$(this).amsWindow({
		container : 'editWindow',
		type : 'add',
		url : basePath+'alarm/alarmManagerController.do?toEdit&id='+id,
		title : '修改告警配置',
		width : 760,
		height : 350,
		top : '15%'
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