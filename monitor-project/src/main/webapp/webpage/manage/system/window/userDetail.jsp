<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<jsp:include page="../../include.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/userDetail.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/common-validate.js"></script>
</head>
<body>
	<div class="tahoma_font  tdstyle"
		style="padding-top: 20px; padding-bottom: 20px; padding-left: 20px; overflow: hidden">
		<form id="form_user" method="post">
			<input id="id" name="id" value="${commonUser.id }" type="hidden"/>
  			<input id="load" name="load" value="${load }" type="hidden"/>			
			<!-- 是否有效 -->
  			<input name="isValid" value="${!empty commonUser?commonUser.isValid:1 }" type="hidden"/>
			<table cellpadding="5" class="newAddOrg" width="680">
				<tr>
					<c:choose>
		        		<c:when test="${!empty commonUser }">
			        		<td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">登录名：</td>
			        		<td width="300" >
				        		<input name="loginName" value="${commonUser.loginName }" type="hidden"/>
				        		${commonUser.loginName }  
				        	</td>
		        		</c:when>
		        		<c:otherwise>
			        		<td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>登录名：</td>
			        		<td width="300" >
			        			<input name="loginName" type="text" style="height:25px;width:200px;"
			        			 class="easyui-textbox" data-options="required:true,missingMessage:'请输入用户登录名！',validType:'length[2,50]'" />			        		
		        			</td>
		        		</c:otherwise>
		        	</c:choose>
			      </tr>
			      <tr>
			       
			        <c:choose>
		        		<c:when test="${!empty commonUser }">
		        			<td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">用户类别：</td>
			       			<td width="300" >
			       				<input name="userType" type="hidden" value="${commonUser.userType }"/>
			        			<select id="d_userType" class="easyui-combobox" style="height:30px;width:200px;"  disabled
					        	data-options="valueField:'id',textField:'text',
					        	url:'${requestScope.basePath }manage/common/constantController.do?getCombobox&mapName=user_type',
					        	onLoadSuccess:function(data){
					        			$('#d_userType').combobox('setValue','${commonUser.userType }');
					        	}"></select>
					         </td>
		        		</c:when>
		        		<c:otherwise>
		        		 	<td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>用户类别：</td>
			       			<td width="300" >			       				
			        			<select id="d_userType" name="userType" class="easyui-combobox" style="height:30px;width:200px;"
					        	data-options="required:true,valueField:'id',textField:'text',editable:false,
					        	url:'${requestScope.basePath }manage/common/constantController.do?getCombobox&mapName=user_type'"></select>
					         </td>
				        </c:otherwise>
		        	</c:choose>     				        	   	
			       
			      </tr>
				 <tr>
			        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>真实姓名：</td>
			        <td width="300" >
			        	<input id="d_realName" name="realName" type="text" style="height:25px;width:200px;"
			        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入真实姓名！',validType:'length[2,50]'"
			        	 value="${commonUser.realName }" />        	
			        </td>
			      </tr>
			      <tr>
			        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>组织机构：</td>
			        <td width="300" >
			        	<select id="d_orgId" name="orgId" class="easyui-combotree" style="height:30px;width:200px;"   
				        	data-options="required:true,missingMessage:'请选择组织机构！',
				        	url:'${requestScope.basePath }manage/system/orgManagerController.do?getOrgComboTree',
				        	onLoadSuccess:function(data){$('#d_orgId').combotree('setValue','${commonUser.orgId }');}"></select>    	
			        </td>
			      </tr>
			      <tr>
			        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">部门：</td>
			        <td width="300" >
			        	<select id="d_departId" name="departId" class="easyui-combotree" style="height:30px;width:200px;"   
				        	data-options="url:'${requestScope.basePath }manage/system/departManagerController.do?getDepartComboTree',
				        	onLoadSuccess:function(data){$('#d_departId').combotree('setValue','${commonUser.departId }');}"></select>            	
			        </td>
			      </tr>   
			      <tr>
			        <td colspan="2">
			          <div align="right" style="padding-right: 50px;padding-top: 5px;" > 
			          	<a id="saveOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" onclick="saveOrUpdate();">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
			          	&nbsp;&nbsp;
			          	<a id="closeOperation"  href="javascript:void(0)" class="easyui-linkbutton"  style="width:50px;" onclick="closeWin();">&nbsp;&nbsp;关闭&nbsp;&nbsp;</a> 
			          </div>
			      	</td>
			      </tr>			      
			</table>			
		</form>
	</div>
</body>
</html>
