//默认执行查询方法
$(document).ready(function() {
	if($("#h_orgId").val() == ""){
		search();
	}
});

//查询
function search(){

	//获取查询框的值
	var qParams={
		'departName':$('#departName').val(),
		'orgId':$('#orgId').combobox("getValue")
	};
	
	//表单信息
	$("#getDepartList").treegrid({
		idField:'id',    
	    treeField:'departName', 
	    animate:true,
        pagination:true,	    //翻页工具栏
        pageNumber:1,           //默认显示第一页
        rownumbers:true,	    //行号
        singleSelect:true,      //单选
        url:basePath+'manage/system/departManagerController.do?getDepartList',  //数据源
        queryParams:qParams,//查询框内容
        fit:true,
        fitColumns:true,
        columns:[[//列
            {title:'ID',field:'id',width:100,hidden:true},
            {title:'部门名称',field:'departName',width:100,align:'center'},	
            {title:'部门编号',field:'departCode',width:90,align:'center'},            
			{title:'描述',field:'description',width:130,align:'center'},
			{title:'联系电话',field:'phone',width:50,align:'center'},
			{title:'父ID',field:'parentId',width:100,hidden:true},
			{title:'操作',field:'operation',width:100,align:'center',
				formatter:function(value, row) {
					var s = ' <a href="#"  onclick="editDepart(\'' + row.id + '\')">修改</a> ';
					var e = ' <a href="#"  onclick="detail(\'' + row.id + '\')">查看</a> ';
					var d = '';
					if((row.parentId==0 && (row.children==null || row.children==''))//根节点，同时也为叶子节点
							|| (row.parentId!=0 && (row.children==null || row.children==''))//非根节点的叶子节点
								){
						d = ' <a href="#"  onclick="del(\'' + row.id + '\')">删除</a> ';
					}
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
function departSearch(){
	search();
}
//清空
function clean(){
	$('#depart_search_form').form('clear');
	$("#orgId").combotree("setValue",$("#h_orgId").val());
}
//查看部门
function detail(departId){
	$(this).amsWindow({
		container : 'detailWindow',
		type : 'add',
		url : basePath+'manage/system/departManagerController.do?toEdit&load=view&id=' + departId,
		title : '查看部门',
		width : 760,
		height : 450,
		top : '15%'
	});
}

//添加部门
function addDepart(){
	var rows = $("#getDepartList").datagrid("getSelections");
	var id = "";
	if (rows.length >= 1) {
		id = rows[0].id;
	}
	$(this).amsWindow({
		container : 'addWindow',
		type : 'add',
		url : basePath + 'manage/system/departManagerController.do?toAdd&parentId=' + id,
		title : '添加部门',
		width : 760,
		height : 450,
		top : '15%'
	});
}

//修改组织机构
function editDepart(departId){
	$(this).amsWindow({
		container : 'editWindow',
		type : 'add',
		url : basePath+'manage/system/departManagerController.do?toEdit&id='+departId,
		title : '修改部门',
		width : 760,
		height : 450,
		top : '15%'
	});
}

//删除组织机构
function del(id){
	//判断机构下是否有部门 有部门不能删除
	//待完成.....
	
	
	
	$.messager.confirm('系统提示', '您确定要执行删除操作吗？', function(data) {
		if(data){
			$.post(basePath+"manage/system/departManagerController.do?delete", {id:id,ran:Math.random()},function(data){
				$.messager.alert('提示',unescape(data.msg));
				$('#getDepartList').treegrid("reload");
			},'json');
		}
	});
}

