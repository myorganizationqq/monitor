$(function(){
	init();
})

//查询
function init(){
	$("#getFunctionList").treegrid({
		idField:'id',    
		treeField:'functionName', 
		animate:true,
		pagination:true,	    //翻页工具栏
		pageNumber:1,			//默认显示第一页
		pageSize:20,
		rownumbers:true,	    //行号
		singleSelect:true,      //单选
		url:basePath+'manage/system/functionManagerController.do?getFunctionList',  //数据源
		fit:true,
		fitColumns:true,
		columns:[[//列
		          {field:'id',width:100,hidden:true},
		          {title:'菜单名称',field:'functionName',width:100,align:'left',halign:'center'},	
		          {title:'菜单地址',field:'functionUrl',width:200,align:'left',halign:'center'},            
		          {title:'排序',field:'functionOrder',width:50,align:'center'},			
		          {title:'操作',field:'operation',width:100,align:'center',
		        	  formatter:function(value, row) {
		        		  var s = ' <a href="#"  onclick="editFunc(\'' + row.id + '\')">修改</a> ';
		        		  var e = ' <a href="#"  onclick="detail(\'' + row.id + '\')">查看</a> ';
		        		  var d = ' <a href="#"  onclick="del(\'' + row.id + '\')">删除</a> ';
		        		  var a = ' <a href="#"  onclick="buttonList(\'' + row.id + '\')">按钮设置</a> ';
		        		  return s + e + d + a;
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

//查看菜单
function detail(id){
	$(this).amsWindow({
		container : 'detailWindow',
		type : 'add',
		url : basePath+'manage/system/functionManagerController.do?toEdit&load=view&id='+id,
		title : '查看菜单',
		width : 740,
		height : 350,
		top : '15%'
	});
}

//添加菜单
function addFunc(){
	var row=$("#getFunctionList").treegrid("getSelected");
	var id="";
	if(row!=null){
		id=row.id;
	}
	$(this).amsWindow({
		container : 'addWindow',
		type : 'add',
		url : basePath+'manage/system/functionManagerController.do?toAdd&parentId='+id,
		title : '添加菜单',
		width : 740,
		height : 350,
		top : '15%'
	});
}

//修改菜单
function editFunc(id){
	$(this).amsWindow({
		container : 'editWindow',
		type : 'add',
		url : basePath+'manage/system/functionManagerController.do?toEdit&id='+id,
		title : '修改菜单',
		width : 740,
		height : 350,
		top : '15%'
	});
}

//删除菜单
function del(id){
	$.messager.confirm('系统提示', '您确定要执行删除操作吗？', function(data) {
		if(data){
			$.post(basePath+"manage/system/functionManagerController.do?delete", {id:id},function(data){
				$.messager.alert('提示',unescape(data.msg));
				init();
			},'json');
		}
	});
}

//删除关系
function forceDelete(id){
	$.messager.confirm('系统提示', '该菜单关联了角色和下级菜单，强制删除？', function(data) {
		if(data){
			$.post(basePath+"manage/system/functionManagerController.do?delete", {id:id,forceDelete:1},function(data){
				$.messager.alert('提示',unescape(data.msg));
				init();
			},'json');
		}
	});
}

//弹出按钮列表
function buttonList(id){
	$(this).amsWindow({
		container : 'operationList',
		type : 'add',
		url : basePath+'manage/system/functionManagerController.do?toOperationList&functionId='+id,
		title : '菜单管理-分配按钮资源',
		width : 700,
		height : 500,
		top : '3%'
	});
}
