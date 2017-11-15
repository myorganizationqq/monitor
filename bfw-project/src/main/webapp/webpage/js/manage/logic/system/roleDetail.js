$(document).ready(function() {
	//加载的是查看页面时，所有控件均不可编辑
	var load=$("#load").val();
	if(load=="view"){
		$("#detailWindow :input").attr("disabled","true");
		$("#saveOperation").remove();
	}
 
});

//保存
function saveOrUpdate(){
	var isValid = $("#form_role").form("validate");//表单验证方法
	if (isValid){
		//进行录入操作的后台交互
		$.ajax({
	        url:basePath+'manage/system/roleManagerController.do?saveOrUpdate',
	        type:"POST",
	        dataType:"json",
	        data:$("#form_role").serialize(),
	        success: function(d){
	        	if(d.resultCode==0){
	        		$.messager.alert("提示",d.msg);
	                //操作完成后关闭窗口
	        		closeWin();				
					//需要刷新DataGrid
					$("#getRoleList").datagrid("reload");
	        	}                
	        }
	    });	
	}	
}

//关闭
function closeWin(){
	var id=$("#id").val();
	var load=$("#load").val();
	if(load!=null && load!=""){
		$('#detailWindow').window('close');
	}else if(id!=null && id!=""){
		$('#editWindow').window('close');
	}else{
		$('#addWindow').window('close');
	}
	
}