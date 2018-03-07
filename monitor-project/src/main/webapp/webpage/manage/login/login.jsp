<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Monitor</title>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<link rel="stylesheet" type="text/css" href="${requestScope.basePath }webpage/css/syscss/easyui.css">
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript">
$(function(){
    $("#loginForm").submit();
});
function check(){
	 var isValid = valid();//表单验证方法
	 if (isValid){
		//进行录入操作的后台交互
		$.ajax({
	        url:basePath+'manage/system/loginController.do?checkuser',
	        type:"POST",
	        dataType:"json",
	        data:$("#loginForm").serialize(),
	        success: function(d){
	        	if(d.resultCode==0){
	        		var data=d.data;	        		
	        		chooseOrgOrRole(data);
	        	}else{
	        		$.messager.alert("提示",d.msg);
	        	}             
	        }
	    });	
	}	
}
//校验方法
function valid(){	
	var loginName=$("#loginName").val();
	if(loginName==null || loginName==""){
		$.messager.alert("提示","请输入登录名！");
		return false;
	}
	
	var password=$("#password").val();
	if(password==null || password==""){
		$.messager.alert("提示","请输入密码！");
		return false;
	}
	
	return true;
}
//重置
function reset(){
	$("input[type='radio']").attr("checked",false);//单选置为空
	$("#loginForm").form('clear');
}

function chooseOrgOrRole(data){
	var orgs=data.orgs;
	var roles=data.roles;	
	if(orgs.length==1){
		if(roles.length==1){//所属单位和角色都只有一个
			//提交表单
			$("#loginForm").submit();
		}else{//用户已经设置一个单位，多个角色或者未设置角色
			chooseWin();
		}
	}else if(orgs.length>1){//单位多个 角色一个或多个或0个
		chooseWin();
	}else{
		$.messager.alert("提示",'该用户没有设置单位，无法登录');
	}
}
function chooseWin(){
	$(this).amsWindow({
		container : 'chooseWindow',
		type : 'add',
		url : basePath+'manage/system/loginController.do?toChoose',
		title : '选择单位和角色',
		width : 600,
		height : 300,
		top : '15%'
	});
}
</script>
<style type="text/css">
.l_n { display:inline-block; padding:0px 10px 0 0px; width:70px; text-align:right; font-size:14px; }
.l_r { display:inline-block; padding:0px 0px 10 0px;  font-size:14px; }
</style>
</head>
<body>
<div style="height:100px; ">
	&nbsp;&nbsp;<b>加载中......</b>
</div>
<div style="height:500px; width:500px; margin:0 auto; display: none">
  <form id="loginForm" method="post" action="${requestScope.basePath }manage/system/loginController.do?login">
  	<input id="orgId" name="orgId" type="hidden" />
  	<input id="roleId" name="roleId" type="hidden" />
  	<input id="loginType" name="loginType" type="hidden" value="0"/><!-- 0:直接登录 1：选择单位角色登录 -->
     <table  cellpadding="10" border="0">      
      	<tr style="height:35px;">       
	      	<td>
	      	 	<label class="l_n ">用户名:</label>
	       	</td> 
	       	<td>
	       		<input id="loginName" name="loginName" type="text"
	      	 	 style="height:25px;width:200px;" class="easyui-textbox" value="admin"/>
	       	</td>     
       	</tr>      
       	<tr style="height:35px;">        
       		<td>
       			<label class="l_n ">密码:</label>
       		</td> 
       		<td>
       			<input id="password" name="password" type="password"
	      	 	 style="height:25px;width:200px;" class="easyui-textbox"  value="123456"/>

				<input  name="roleParam" type="password"
					   style="height:25px;width:200px;" class="easyui-textbox"  value="${roleParam}"/>
       		</td>
        </tr>

        <tr>       
            <td colspan="2">
	            <div align="center" style="padding-top: 5px;" > 
		          	<a id="loginOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" onclick="check();">&nbsp;&nbsp;登录&nbsp;&nbsp;</a>
		          	&nbsp;&nbsp;&nbsp;&nbsp;
		          	<a id="resetOperation"  href="javascript:void(0)" class="easyui-linkbutton"  style="width:50px;" onclick="reset();">&nbsp;&nbsp;重置&nbsp;&nbsp;</a> 
	          	</div>
            </td>      
         </tr>
      </table>  
   </form>
</div>  
</body>
</html>