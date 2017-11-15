$(function(){
	init();
})
//查询
function init(){
	//获取查询框的值
	var qParams={
			'rolename':$('#rolename').val(),
			'roleType':$('#roleType').combobox("getValue")
	};

	//表单信息
	$("#getRoleList").datagrid({
		pagination:true,	    //翻页工具栏
		pageNumber:1,           //默认显示第一页
		rownumbers:true,	    //行号
		singleSelect:true,      //单选
		url:basePath+'manage/system/roleManagerController.do?getRoleList',  //数据源
		queryParams:qParams,//查询框内容
		fit:true,
		fitColumns:true,
		idField:"id",
		columns:[[//列
		          {field:'id',hidden:true},
		          {title:'角色编码',field:'rolecode',width:100,align:'center'},
		          {title:'角色名称',field:'rolename',width:100,align:'center'},
		          {title:'角色类别',field:'roleTypeStr',width:70,align:'center'},
		          {title:'添加时间',field:'createTimeStr',width:100,align:'center'},
		          {title:'操作',field:'operation',width:100,align:'center',
		        	  formatter:function(value, row) {
		        		  var s = ' <a href="#"  onclick="editRole(\'' + row.id + '\')">修改</a> ';
		        		  var e = ' <a href="#"  onclick="detail(\'' + row.id + '\')">查看</a> ';
		        		  var d = ' <a href="#"  onclick="del(\'' + row.id + '\')">删除</a> ';
		        		  return s + e + d;
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
function roleSearch(){
	init();
}

//清空
function clean(){
	$('#role_search_form').form('clear');
}

//查看角色
function detail(roleId){
	$(this).amsWindow({
		container : 'detailWindow',
		type : 'add',
		url : basePath+'manage/system/roleManagerController.do?toEdit&load=view&id='+roleId,
		title : '查看角色',
		width : 760,
		height : 350,
		top : '15%'
	});
}

//添加角色
function addRole(){
	$(this).amsWindow({
		container : 'addWindow',
		type : 'add',
		url : basePath+'manage/system/roleManagerController.do?toAdd',
		title : '添加角色',
		width : 760,
		height : 350,
		top : '15%'
	});
}

//修改角色
function editRole(roleId){
	$(this).amsWindow({
		container : 'editWindow',
		type : 'add',
		url : basePath+'manage/system/roleManagerController.do?toEdit&id='+roleId,
		title : '修改角色',
		width : 760,
		height : 350,
		top : '15%'
	});
}

//删除角色
function del(id){
	$.messager.confirm('系统提示', '您确定要执行删除操作吗？', function(data) {
		if(data){
			$.post(basePath+"manage/system/roleManagerController.do?delete", {id:id},function(data){
				$.messager.alert('提示',unescape(data.msg));
				init();
			},'json');
		}
	});
}

//删除关系
function forceDelete(id){
	$.messager.confirm('系统提示', '该角色关联了用户和菜单，强制删除？', function(data) {
		if(data){ 
			$.post(basePath+"manage/system/roleManagerController.do?delete", {id:id,forceDelete:1},function(data){
				$.messager.alert('提示',unescape(data.msg));
				init();
			},'json');
		}
	});
}

//分配到用户
function allocationToUser(){
	var row=$('#getRoleList').datagrid("getSelected");
	if(row){
		var roleId=row.id;	
		$(this).amsWindow({
			container : 'allocationToUserWindow',
			type : 'add',
			url : basePath+'manage/system/roleManagerController.do?toAllocationToUser&roleId='+roleId,
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
	var row=$('#getRoleList').datagrid("getSelected");
	if(row){
		var roleId=row.id;	
		$(this).amsWindow({
			container : 'allocationToUserWindow',
			type : 'add',
			url : basePath+'manage/system/roleManagerController.do?toAllocationToMenu&roleId='+roleId,
			title : '角色管理-分配菜单资源',
			width : 630,
			height : 570,
			top : '3%'
		});
	}else{
		$.messager.alert('提示','请选择一个角色分配菜单');
	}	
}