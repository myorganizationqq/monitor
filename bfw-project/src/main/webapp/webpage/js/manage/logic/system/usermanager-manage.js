//默认执行查询方法
$(document).ready(function() {
//	alert($("#h_orgId").val() == "")
	if($("#h_orgId").val() == ""){
		search();
	}
});

//查询
function search(){

	//获取查询框的值
	var qParams={
		'realName':$('#realName').val(),
		'loginName':$('#loginName').val(),
		'orgId':$('#orgId').combobox('getValue'),
		'departId':$('#departId').combobox('getValue'),
		'userType':$('#userType').combobox('getValue'),
		'isValid':$('#isValid').combobox('getValue')
	};
	//表单信息
	$("#getUserList").datagrid({
        pagination:true,	    //翻页工具栏
        pageNumber:1,           //默认显示第一页
        rownumbers:true,	    //行号
        //singleSelect:true,      //单选
        url:basePath+'manage/system/userManagerController.do?getUserList',  //数据源
        queryParams:qParams,//查询框内容
        fit:true,
        fitColumns:true,
        columns:[[//列
            {field:'id',checkbox:true,width:30,align:'center'},	
            {title:'真实姓名',field:'realName',width:80,align:'center'},	
            {title:'登录名',field:'loginName',width:100,align:'center'},                        	
			{title:'所属单位ID',field:'orgId',width:100,align:'center',hidden:true},
			{title:'所属单位',field:'orgName',width:100,align:'center'},
			{title:'所在部门ID',field:'departId',width:100,align:'center',hidden:true},
			{title:'所在部门',field:'departName',width:100,align:'center'},
			{title:'用户角色',field:'roleName',width:100,align:'center'},
			{title:'用户类别ID',field:'userType',width:60,align:'center',hidden:true},	
            {title:'用户类别',field:'userTypeStr',width:60,align:'center'},
			{title:'用户状态ID',field:'isValid',width:60,align:'center',hidden:true},
			{title:'用户状态',field:'isValidStr',width:60,align:'center'},
			{title:'操作',field:'operation',width:120,align:'center',
				formatter:function(value, row) {
					var s = ' <a href="#"  onclick="editUser(\'' + row.id + '\')">修改</a> ';
					var e = ' <a href="#"  onclick="viewUser(\'' + row.id + '\')">查看</a> ';
					var d = ' <a href="#"  onclick="del(\'' + row.id + '\')">删除</a> ';
					var p = ' <a href="#"  onclick="editPassword(\'' + row.id + '\')">重置密码</a> ';
					return s + e + d + p;
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
function userSearch(){
	search();
}

//清空
function clean(){
	$('#user_search_form').form('clear');
	$("#orgId").combotree("setValue",$("#h_orgId").val());
}

//查看用户
function viewUser(userId) {
	$(this).amsWindow({
		container : 'detailWindow',
		type : 'add',
		para : '',
		url : basePath+'manage/system/userManagerController.do?toEdit&load=view&id='+userId,
		title : '查看用户信息',
		width : 760,
		height : 400,
		top : '15%'

	});

}

//修改用户
function editUser(userId) {
	$(this).amsWindow({
		container : 'editWindow',
		type : 'add',
		url : basePath+'manage/system/userManagerController.do?toEdit&id='+userId,
		title : '修改用户信息',
		width : 760,
		height : 400,
		top : '15%'
	});
}

//新增用户
function addUser() {
	$(this).amsWindow({
		container : 'addWindow',
		type : 'add',
		url : basePath+'manage/system/userManagerController.do?toAdd',
		title : '新增用户信息',
		width : 760,
		height : 400,
		top : '15%'
	});
}

//删除用户
function del(userId) {
	$.messager.confirm('系统提示', '您确定要执行删除操作吗？', function(data) {
		if(data){
			$.post(basePath+"manage/system/userManagerController.do?delete", {id : userId,ran : Math.random()},function(data){
				$.messager.alert('提示',unescape(data.msg));
				$('#getUserList').datagrid("reload");
			},'json');
		}
	});
}

//重置密码
function editPassword(userId) {
	$.messager.confirm('系统提示', '您确定将该用户的密码重置吗？', function(data) {
		if(data){
			$.post(basePath + "manage/system/userManagerController.do?setDefaultPwd", {userId : userId},function(data){
				$.messager.alert('提示',unescape(data.msg));
			},'json');
		}
	});
}

//账号启用与停用
function isValidEdit(status){
	var rows = $("#getUserList").datagrid("getSelections");
	if (rows.length < 1) {
		$.messager.alert("提示", "至少选择一条数据操作！");
	} else {
		var userIdArr = new Array();
		for (var i = 0;i < rows.length;i++){
			var row = rows[i];
			userIdArr.push(row.id);
		}
		var str = "停用";
		if (status == 1) {
			str = "启用";
		}
		$.messager.confirm('系统提示', '您确定' + str + '选中的所有用户吗？', function(data) {
			if(data){
				$.post(basePath + "manage/system/userManagerController.do?userValid", {userIdArr : userIdArr.toString(), isValid : status},function(data){
					$.messager.alert('提示',unescape(data.msg));
					if (data.resultCode == 0) {
						$('#getUserList').datagrid("reload");
					}
				},'json');
			}
		});
	}
}
