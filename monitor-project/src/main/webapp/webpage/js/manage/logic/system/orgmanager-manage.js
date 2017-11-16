//默认执行查询方法
$(document).ready(function() {
		search();
});
//查询
function search(){

	//获取查询框的值
	var qParams={
		'orgName':$('#orgName').val(),
		'fullName':$('#fullName').val()
	};
	
	//表单信息
	$("#getOrgList").treegrid({
		idField:'id',    
	    treeField:'orgName', 
	    animate:true,
        pagination:true,	    //翻页工具栏
        pageNumber:1,           //默认显示第一页
        rownumbers:true,	    //行号
        singleSelect:false,      //多选
        url:basePath+'manage/system/orgManagerController.do?getOrgList',  //数据源
        queryParams:qParams,//查询框内容
        fit:true,
        fitColumns:true,
        columns:[[//列
            {field:'id',checkbox:true,width:30,align:'center'},	
//            {title:'ID',field:'id',width:100,hidden:true},
            {title:'机构名称',field:'orgName',width:100,align:'center'},
            {title:'机构全名',field:'fullName',width:130,align:'left',halign:'center'},
            {title:'机构编码',field:'orgCode',width:90,align:'center'},            
			{title:'状态',field:'disabled',width:50,align:'center',
				formatter:function(value, row) {
					if(value==1){
						return "正常";
					}else{
						return "禁用";
					}
				}
			},
			{title:'父ID',field:'parentId',width:100,hidden:true},
			{title:'操作',field:'operation',width:100,align:'center',
				formatter:function(value, row) {
					var s = ' <a href="#"  onclick="editOrg(\'' + row.id + '\')">修改</a> ';
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
function orgSearch(){
	search();
}

//清空
function clean(){
	$('#org_search_form').form('clear');
}

//查看组织机构
function detail(orgId){
	$(this).amsWindow({
		container : 'detailWindow',
		type : 'add',
		url : basePath+'manage/system/orgManagerController.do?toEdit&load=view&id='+orgId,
		title : '查看组织机构',
		width : 760,
		height : 450,
		top : '15%'
	});
}

//添加组织机构
function addOrg(){
	var row=$("#getOrgList").treegrid("getSelected");
	var id="";
	if(row!=null){
		id=row.id;
	}
	$(this).amsWindow({
		container : 'addWindow',
		type : 'add',
		url : basePath+'manage/system/orgManagerController.do?toAdd&parentId='+id,
		title : '添加组织机构',
		width : 760,
		height : 450,
		top : '15%'
	});
}

//修改组织机构
function editOrg(orgId){
	$(this).amsWindow({
		container : 'editWindow',
		type : 'add',
		url : basePath+'manage/system/orgManagerController.do?toEdit&id='+orgId,
		title : '修改组织机构',
		width : 760,
		height : 450,
		top : '15%'
	});
}

//删除组织机构
function del(id){
	$.messager.confirm('系统提示', '您确定要执行删除操作吗？', function(data) {
		if(data){
			$.post(basePath+"manage/system/orgManagerController.do?delete", {id:id,ran:Math.random()},function(data){
				$.messager.alert('提示',unescape(data.msg));
				if (data.resultCode == 1) {
					$('#getOrgList').treegrid("reload");
				}
			},'json');
		}
	});
}


// 启用/禁用
function isEnabledOrg(status){
	//子节点 直接禁用
	//非子节点 提示是否禁用其所有子节点
	var rows = $("#getOrgList").datagrid("getSelections");
	if (rows.length < 1) {
		$.messager.alert("提示", "请先选择一条数据！");
	} else {
		var orgIdArr = new Array();
		for (var i = 0;i < rows.length;i++) {
			orgIdArr.push(rows[i].id);
		}
		var str = "禁用";
		if (status == 1) {
			str = "启用";
		}
		$.post(basePath+"manage/system/orgManagerController.do?getOrgListByParentId", {idArr : orgIdArr.toString()},function(data){
			if (data.resultCode > 0) {
				$.messager.confirm('系统提示', '您确定要' + str + '当前机构及其所有子机构吗？', function(data) {
					if(data){
						setOrgIsableStatus(orgIdArr.toString(), status, true);
					}
				});
			} else {
				setOrgIsableStatus(orgIdArr.toString(), status, false);
			}
		},'json');
	}
}
// 更改启用/禁用状态
function setOrgIsableStatus(orgIdArr, status, haveChildren) {
	$.post(basePath+"manage/system/orgManagerController.do?isEnable", {orgIdArr:orgIdArr, status:status, haveChildren : haveChildren},function(data){
		$.messager.alert('提示',unescape(data.msg));
		if (data.resultCode == 0) {
			$('#getOrgList').treegrid("reload");
		}
	},'json');
}

// 部门情况/人员情况页面跳转
function situation(type) {
	var rows = $("#getOrgList").datagrid("getSelections");
	if (rows.length < 1) {
		$.messager.alert("提示", "请先选择一条数据！");
	} else if (rows.length > 1) {
		$.messager.alert("提示", "只能选择一条数据进行操作！");
	} else {
		if (type && type == 'depart') {
			openPath(basePath + "manage/system/departManagerController.do?toPageList&orgId=" + rows[0].id);
		} else if (type && type == 'user') {
			openPath(basePath + "manage/system/userManagerController.do?toPageList&orgId=" + rows[0].id);
		}
	}
}

function openPath(path){
	$(window.parent.document.activeElement).attr("src", path);
}
