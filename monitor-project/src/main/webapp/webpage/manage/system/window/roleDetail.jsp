<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<jsp:include page="../../include.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/roleDetail.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/common-validate.js"></script>
</head>
<body>
<div class="tahoma_font  tdstyle"  style="padding-top:20px; padding-bottom:20px; padding-left:20px;">
  <form id="form_role" method="post">
  	<input id="id" name="id" value="${bkCommonRole.id }" type="hidden"/>
  	<input id="load" name="load" value="${load }" type="hidden"/>
    <table cellpadding="5" class="newAddOrg" width="680">
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>角色编码：</td>
        <td width="300" >
        	<input id="d_rolecode" name="rolecode" type="text" style="height:25px;width:60%;"
        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入角色编码！',validType:'length[2,20]'"
        	 value="${bkCommonRole.rolecode }" />        	
        </td>
      </tr>
      <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>角色名称：</td>
        <td width="300" >
        	<input id="d_rolename" name="rolename" type="text" style="height:25px;width:60%;"
        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入角色名称！',validType:'length[2,50]'"
        	 value="${bkCommonRole.rolename }" />
        </td>
      </tr>
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>角色类别：</td>
        <td width="300" >
        	 <select id="d_roleType" name="roleType" class="easyui-combobox" style="height:30px;width:200px;"
	        	data-options="required:true,valueField:'id',textField:'text',editable:false,
	        	url:'${requestScope.basePath }manage/common/constantController.do?getCombobox&mapName=role_type',
	        	onLoadSuccess:function(data){
	        			$('#d_roleType').combobox('setValue','${bkCommonRole.roleType }');
	        	}"></select>
        </td>
      </tr>
      <tr>
        <td align="right" bgcolor="#f1f2f3" class="p_right10">角色描述 ：</td>
        <td>
        	<input id="d_description" name="description" class="easyui-textbox" data-options="multiline:true,validType:'maxLength[200]'" maxlength="200"
        		type="text" style="height:80px;width:530px;" value="${bkCommonRole.description }" />
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
