<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理-分配到用户</title>
<jsp:include page="../../include.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/allocationToUsers.js"></script>
</head>
<body>
<div class="tahoma_font  tdstyle"  style="padding-top:10px; padding-bottom:10px; padding-left:10px;">
  <form id="form_allocation_users" method="post">
 	<input id="orgId" name="orgId" value="152" type="hidden"/>
 	<input id="roleId" name="roleId" value="${roleInfo.id }" type="hidden"/>
 	<input id="userType" name="userType" value="1" type="hidden"/>
 	<!-- 保存原来的选中的学生id集合 -->
 	<input id="oldSelectedUsers" type="hidden"/>
    <table cellpadding="5" class="newAddOrg" width="680">
    <!-- 角色信息 -->
       <tr>
        <td width="100" align="right" bgcolor="#f1f2f3" class="p_right10">角色编码：</td>
        <td width="200" >${roleInfo.rolecode }</td>
        <td width="100" align="right" bgcolor="#f1f2f3" class="p_right10">角色名称：</td>
        <td width="200" >${roleInfo.rolename }</td>
      </tr>    
       <tr>
        <td colspan="4">
        	<table width="100%" height="150" cellpadding="0" cellspacing="1">
				<tr>
					<!-- 机构-部门 -->
					<td width="230" height="150" valign="top">
						<div id="treeDivId" align="left" style="background-color:#FFF; width: 205px; height: 322px; overflow: auto;">
							<ul id="orgDepartTree" style="padding: 5px;">
							</ul>
						</div>
					</td>
					<td width="2"></td>
					<td align="center" valign="top">
						<table width="400" cellpadding="0" cellspacing="0">
						<!-- 用户查询 -->
							<tr height="25">
								<td align="right">真实姓名：</td>
								<td colspan="2">
									<input id="real_name" name="real_name" type="text"  style="width:120px;"/>
									<!-- 查询按钮 -->
									<img id="queryUser" style="cursor: pointer;" 
									src="${requestScope.basePath }webpage/image/sysimages/easyui/searchbox_button.png" /></td>
							</tr>
							
							<tr height="25">
								<td align="center">可选用户列表</td>
								<td>&nbsp;</td>
								<td align="center">已选用户列表</td>
							</tr>
						
							<tr>
								<td valign="middle">
									<select id="selectingUser" style="width:160px;margin:-2px;" size="17" ondblclick="oper(1)">
									</select>
								</td>
								
								<td valign="middle" width="25">
									<img style="cursor:pointer;margin-bottom:5px;" src="${requestScope.basePath }webpage/image/sysimages/right1.png" onclick="shift(1)"/>
									<img style="cursor:pointer;margin-bottom:5px;" src="${requestScope.basePath }webpage/image/sysimages/right2.png" onclick="shift(2)"/>
									<img style="cursor:pointer;margin-bottom:5px;" src="${requestScope.basePath }webpage/image/sysimages/left1.png" onclick="shift(3)"/>
									<img style="cursor:pointer" src="${requestScope.basePath }webpage/image/sysimages/left2.png" onclick="shift(4)"/>
								</td>
								
								<td valign="middle" id="td_id_1">
									<select id="selectedUser" style="width:160px;margin:-2px;" size="17" ondblclick="shift(3)">
									</select>
								</td>
							</tr>
						</table>       
        			</td>
        		</tr>
        	</table>
        </td>       
      </tr>
      <tr>
        <td colspan="4">
          <div align="right" style="padding-right: 50px;padding-top: 5px;" > 
          	<a id="saveOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" onclick="changeUserRoles();">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
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
