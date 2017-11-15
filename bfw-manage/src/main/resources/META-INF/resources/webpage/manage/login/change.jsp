<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择单位和角色</title>
<jsp:include page="../include.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
var orgId=null;
$(function() {	
	orgId=winData.para.orgId;
	$('#roleid').tree({
		url:basePath+'manage/system/loginController.do?loadRoleList&orgId='+orgId			
	});	
	$("#roleListPanel").panel({title :"选择我要切换的角色",headerCls:"panelHeader"});
});

function changeRole(){	
	var roleNode=$('#roleid').tree("getSelected");
	if(roleNode!=null){//选择角色
		var roleId=roleNode.id;
		parent.change(orgId,roleId);
	}else{
		$.messager.alert("提示","请选择要切换的角色!");
	}	
}

function closeWin(){
	$("#changeWindow").window("close");
}
</script>
<style type="text/css">
.panelHeader{height:25px;line-height:25px;}

</style>
</head>
<body>
	<div class="tahoma_font  tdstyle"
		style="padding-top: 10px; padding-bottom: 0px; padding-left: 10px;">
		<table cellpadding="5"  width="350px">
			<tr>
				<td colspan="4">
					<table width="320" cellpadding="0" cellspacing="1">
						<tr>
							<!-- 角色 -->
							<td height="200" width="200px" valign="top">
								<div class="easyui-panel" style="padding: 1px; width: 150px;"
									fit="true" border="false" id="roleListPanel">
									<ul id="roleid"></ul>
								</div>
							</td>							
						</tr>
					</table>
			<tr>
				<td colspan="4">
					<div align="right" style="padding-right: 50px; padding-top: 5px;">
						<a id="saveOperation" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 50px;"
							onclick="changeRole();">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
						&nbsp;&nbsp; <a id="closeOperation" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 50px;"
							onclick="closeWin();">&nbsp;&nbsp;关闭&nbsp;&nbsp;</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
