<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理-用户管理</title>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/usermanager-manage.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
</head>
<body>
<br />
<div style="width: 99%; padding-top: 10px; padding-left: 10px"
	id="centerMain">
	<!--input 表格模块-->
	<div class="tahoma_font tableStyle_5_0">
		<input id="h_orgId" type="hidden" value="${p_orgId}">
		<form id="user_search_form">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="60" style="padding-left:20px;"><label>真实姓名：</label></td>
				<td width="160">
					<input type="t" class="easyui-textbox" id="realName" name="realName"
						style="width: 150px" data-options="" fit="true" />
				</td>
				
				<td width="60" style="padding-left:20px;"><label>登录名：</label></td>
				<td width="160">
					<input type="t" class="easyui-textbox" id="loginName" name="loginName"
						style="width: 150px" data-options="" fit="true" />
				</td>
				
				<td width="60" style="padding-left:20px;"><label>用户类别：</label></td>
				<td width="160">
					<select id="userType" name="userType" class="easyui-combobox" style="width:150px;" 
				        	data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false,
				        	url:'${requestScope.basePath }manage/common/constantController.do?getCombobox&mapName=user_type'"></select>  
				</td>

				<td>
				</td>
			</tr>
			
			<tr>
				<td width="60" style="padding-left:20px;"><label>所在单位：</label></td>
				<td width="160">
					<select id="orgId" name="orgId" class="easyui-combotree" style="width:150px;"   
				        	data-options="panelHeight:'auto',
				        	url:'${requestScope.basePath }manage/system/orgManagerController.do?getOrgComboTree',
				        	onLoadSuccess:function(data){
				        		if(${!empty p_orgId }){
				        			$('#orgId').combotree('setValue','${p_orgId}');
				        			$('#orgId').combotree('disable');
				        			search();
				        		}
				        	}"></select> 
				</td>
				
				<td width="60" style="padding-left:20px;"><label>所在部门：</label></td>
				<td width="160">
<!-- 					<input type="t" class="easyui-textbox" id="departId" name="departId" -->
<!-- 						style="width: 150px" data-options="" fit="true" /> -->
					<select id="departId" name="departId" class="easyui-combotree" style="width:150px;"   
				        	data-options="panelHeight:'auto',
				        	url:'${requestScope.basePath }manage/system/departManagerController.do?getDepartComboTree'"></select>
				</td>
				
				<td width="60" style="padding-left:20px;"><label>用户状态：</label></td>
				<td width="160">
					<select id="isValid" name="isValid" class="easyui-combobox" style="width:150px;" 
				        	data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false,
				        	url:'${requestScope.basePath }manage/common/constantController.do?getCombobox&mapName=is_vaild'"></select>  
				</td>
				
				<td>
					<a id="searchBtn" href="javascript:void(0);" onclick="userSearch();"
					class="easyui-linkbutton" style="padding: 0 10px">查询</a>
					
					<a id="cleanBtn" href="javascript:void(0);" onclick="clean();"
					class="easyui-linkbutton" style="padding: 0 10px">清空</a>
				</td>
			</tr>
			<tr>
				<td style="padding-left:20px;padding-top:10px;" colspan="7">
					<a href="javascript:void(0);" class="easyui-linkbutton" style="padding: 0 10px" onclick="addUser();">新增</a>
				
			 		<a href="javascript:void(0);" class="easyui-linkbutton" style="padding: 0 10px" onclick="isValidEdit(1);">账号启用</a>
			 		
			 		<a href="javascript:void(0);" class="easyui-linkbutton" style="padding: 0 10px" onclick="isValidEdit(0);">账号停用</a>
			 	</td>			 	
			</tr>
		</table>
		</form>
	</div>

	<!--上下距间-->
	<div class="easyui-layout" style="height: 10px"></div>
	<div class="line"></div>
	<!--gap-->
	<div class="easyui-layout" style="height: 10px"></div>
	<div class="easyui-layout" style="height: 443px; padding: 10px">
		<table id="getUserList">
		</table>
	</div>
</div>

</body>
</html>