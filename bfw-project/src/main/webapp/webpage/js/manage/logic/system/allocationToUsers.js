$(function(){
	var orgId=$("#orgId").val();
	var roleId=$("#roleId").val();	
	var userType=$("#userType").val();		
	
	//树结构加载上：待完善
	//学校的角色：加载学校（系统管理员所在的组织机构）及其各个部门
	//家长：只加载根节点（学校）
	//学生：学校和各个班级
	//系统管理员：组织机构树结构
	$('#orgDepartTree').tree({
		url:basePath+"webpage/manage/system/orgManage/json/tree2.json",
		onLoadSuccess:function(node, data){
			//被选列表：默认加载该组织下的所有可选用户（排除已经分配该角色的用户）			
			getSelectingUsers(orgId,null,userType,roleId);
			//已选列表：默认加载该组织下的已经分配该角色的用户列表
			getSelectedUsers(orgId,null,userType,roleId);
		},
		onSelect:function(node){	
			//node.target.setAttribute("style","font-weight:bold");
			//alert(node.id);
			var selectId=node.id;
			if(selectId==orgId){//根节点
				//可选用户：组织机构下可选用户（排除已经分配该角色的用户）
				getSelectingUsers(selectId,null,userType,roleId);
				//已选用户：该组织机构下的已经分配该角色的用户列表
				getSelectedUsers(selectId,null,userType,roleId);
			}else{//非根节点 
				//可选用户：部门下可选用户（排除已经分配该角色的用户）
				getSelectingUsers(orgId,selectId,userType,roleId);
				//已选用户：部门下的已经分配该角色的用户列表
				getSelectedUsers(orgId,selectId,userType,roleId);				
			}
		}
	});

	
	$("#queryUser").click(function(){
		var realName=$("#real_name").val();	
		getSelectingUsers(orgId,null,userType,roleId,realName);
		getSelectedUsers(orgId,null,userType,roleId,realName);
		var node = $("#orgDepartTree").tree("find", orgId); 
		$('#orgDepartTree').tree("select",node.target);
	});
});

//待选用户
function getSelectingUsers(orgId,deptId,userType,roleId,realName){
	$.ajax({
        url:basePath+'manage/system/userManagerController.do?getSelectingUsers',
        type:"POST",
        dataType:"json",
        data:{'orgId':orgId,'departId':deptId,'userType':userType,'roleId':roleId,'realName':realName},
        success: function(data){	
        	$("#selectingUser").html("");
			for(var i in data){  
				$("#selectingUser").append("<option value='"+data[i].id+"'>"+data[i].realName+"</option>");
			}                
        }
    });	
} 

//已选用户
function getSelectedUsers(orgId,deptId,userType,roleId,realName){
	$.ajax({
        url:basePath+'manage/system/userManagerController.do?getSelectedUsers',
        type:"POST",
        dataType:"json",
        data:{'orgId':orgId,'departId':deptId,'userType':userType,'roleId':roleId,'realName':realName},
        success: function(data){	
        	$("#selectedUser").html("");
        	var arr=new Array();
			for(var i in data){  
				$("#selectedUser").append("<option value='"+data[i].id+"'>"+data[i].realName+"</option>");
				arr[i]=data[i].id;
			}  
			$("#oldSelectedUsers").val(arr);			
        }
    });	
} 

//左右移动
function shift(op) {
	var selectingObj = document.getElementById('selectingUser');
	var selectedObj = document.getElementById('selectedUser');
	switch (op) {
		case 1://右移单个
			upSelect(selectingObj, selectedObj);
			break;
		case 2://右移全部
			var len = selectingObj.options.length;
			for (var i = len - 1; i >= 0; i--) {
				upSelectAll(selectingObj, selectedObj, i);
			}
			break;
		case 3://左移单个
			upSelect(selectedObj, selectingObj);
			break;
		case 4://左移全部
			var len = selectedObj.options.length;
			for (var i = len - 1; i >= 0; i--) {
				upSelectAll(selectedObj, selectingObj, i);
			}
			break;
	}
}
//单个选中移动
function upSelect(selecteds, addselect){
	if (selecteds.selectedIndex == -1) {
		return;
	}
	var selecting = selecteds;
	var addselect = addselect;
	var oOption = document.createElement("option");
	oOption.text = selecting.options[selecting.selectedIndex].text;
	oOption.value = selecting.options[selecting.selectedIndex].value;
	addselect.add(oOption);
	selecting.remove(selecting.selectedIndex);
}
//所有选中移动
function upSelectAll(selecteds, addselect, i){
	var selecting = selecteds;
	var addselect = addselect;
	var oOption = document.createElement("option");
	oOption.text = selecting.options[i].text;
	oOption.value = selecting.options[i].value;
	addselect.add(oOption);
	selecting.remove(i);

}

function changeUserRoles(){
	//选中的用户
	var userIds='';
	$("#selectedUser option").each(function(){
		userIds += ','+$(this).val() ;
	});
	if(userIds!=''){
		userIds=userIds.substring(1);
	}
	$.ajax({
        url:basePath+'manage/system/userManagerController.do?changeUserRoles',
        type:"POST",
        dataType:"json",
        data:{'roleId':$("#roleId").val(),'oldSelectedUser':$("#oldSelectedUsers").val(),'selectedUser':userIds},
        success: function(d){
        	if(d.resultCode==0){
        		$.messager.alert("提示",d.msg);
                //操作完成后关闭窗口
        		closeWin();		
				//需要刷新DataGrid
        		$('#getRoleList').datagrid("reload");
        	}                
        }
    });	
}

//关闭窗口
function closeWin(){
	$('#allocationToUserWindow').window('close');		
}