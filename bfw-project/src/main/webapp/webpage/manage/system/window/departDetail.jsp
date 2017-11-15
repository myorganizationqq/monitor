<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门管理</title>
<jsp:include page="../../include.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/departDetail.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/common-validate.js"></script>
</head>
<body>
<div class="tahoma_font  tdstyle"  style="padding-top:20px; padding-bottom:20px; padding-left:20px;">
  <form id="form_depart" method="post">
  	<input id="id" name="id" value="${commonDepart.id }" type="hidden"/>
  	<input id="load" name="load" value="${load }" type="hidden"/>
    <table cellpadding="5" class="newAddOrg" width="680">
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">部门编号：</td>
        <td width="300" >
        	<input id="departCode" name="departCode" type="text" style="height:25px;width:60%;"
        	 class="easyui-textbox" data-options="validType:'maxLength[20]'"
        	 value="${commonDepart.departCode }" />        	
        </td>
      </tr>
      <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>部门名称：</td>
        <td width="300" >
        	<input id="departName" name="departName" type="text" style="height:25px;width:60%;"
        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入部门呢名称！',validType:'length[1,20]'"
        	 value="${commonDepart.departName }" />
        </td>
      </tr>
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">联系电话：</td>
        <td width="300" >
        	<input id="phone" name="phone" type="text" style="height:25px;width:60%;"
        	 class="easyui-textbox" data-options="validType:'length[1,50]'"
        	 value="${commonDepart.phone }" />
        </td>
      </tr>
      <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">上级部门：</td>
        <td width="300" >
	        <select id="d_parentId" name="parentId" class="easyui-combotree" style="height:30px;width:200px;"   
	        	data-options="url:'${requestScope.basePath }manage/system/departManagerController.do?getDepartComboTree',
	        	onLoadSuccess:function(data){
	        		if(${!empty commonDepart.parentId }){
	        			$('#d_parentId').combotree('setValue','${commonDepart.parentId}');
	        		} else if (${!empty departParentId }) {
	        			$('#d_parentId').combotree('setValue','${departParentId}');
	        		}
	        	}"></select> 
        </td>
      </tr>
      <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>所属机构：</td>
        <td width="300" >
	        <select id="d_orgId" name="orgId" class="easyui-combotree" style="height:30px;width:200px;"   
	        	data-options="required:true,missingMessage:'请选择所属机构！',url:'${requestScope.basePath }manage/system/orgManagerController.do?getOrgComboTree',
	        	onLoadSuccess:function(data){$('#d_orgId').combotree('setValue','${commonDepart.orgId}');}"></select> 
        </td>
      </tr>
      <tr>
        <td align="right" bgcolor="#f1f2f3" class="p_right10">部门描述 ：</td>
        <td>
        	<input id="description" name="description" class="easyui-textbox" data-options="multiline:true,validType:'maxLength[200]'" maxlength="200"
        		type="text" style="height:80px;width:530px;" value="${commonDepart.description }" />
        </td>
      </tr> 
      <%-- <tr>
        <td align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>启用状态 ：</td>
        <td>
        	<input name="disabled" type="radio" value="1" <c:if test="${empty commonOrg || commonOrg.disabled==1 }">checked</c:if> />启用 &nbsp;&nbsp;
        	<input name="disabled" type="radio" value="0" <c:if test="${commonOrg.disabled==0 }">checked</c:if> />禁用 
        </td>
      </tr> --%>
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
