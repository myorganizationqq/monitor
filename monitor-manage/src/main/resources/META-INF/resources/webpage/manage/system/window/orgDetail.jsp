<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<jsp:include page="../../include.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/orgDetail.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/common-validate.js"></script>
</head>
<body>
<div class="tahoma_font  tdstyle"  style="padding-top:20px; padding-bottom:20px; padding-left:20px;">
  <form id="form_org" method="post">
  	<input id="id" name="id" value="${commonOrg.id }" type="hidden"/>
  	<input id="load" name="load" value="${load }" type="hidden"/>
    <table cellpadding="5" class="newAddOrg" width="680">
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">机构编码：</td>
        <td width="300" >
        	<input id="orgCode" name="orgCode" type="text" style="height:25px;width:60%;"
        	 class="easyui-numberbox" data-options="validType:'maxLength[10]'"
        	 value="${commonOrg.orgCode }" />
        </td>
      </tr>
      <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>机构名称：</td>
        <td width="300" >
        	<input id="orgName" name="orgName" type="text" style="height:25px;width:60%;"
        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入机构名称！',validType:'length[1,20]'"
        	 value="${commonOrg.orgName }" />
        </td>
      </tr>
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>机构全名：</td>
        <td width="300" >
        	<input id="fullName" name="fullName" type="text" style="height:25px;width:60%;"
        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入机构全名！',validType:'length[1,50]'"
        	 value="${commonOrg.fullName }" />
        </td>
      </tr>
      <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">上级组织机构：</td>
        <td width="300" >
	        <select id="parentId" name="parentId" class="easyui-combotree" style="height:30px;width:200px;"   
	        	data-options="url:'${requestScope.basePath }manage/system/orgManagerController.do?getOrgComboTree',
	        	onLoadSuccess:function(data){
	        			if(${!empty commonOrg.parentId }){
	        				$('#parentId').combotree('setValue','${commonOrg.parentId }');
	        			}else if(${!empty orgParentId }){
	        				$('#parentId').combotree('setValue','${orgParentId }');
	        			}
	        	}"></select> 
        </td>
      </tr>
      <tr>
        <td align="right" bgcolor="#f1f2f3" class="p_right10">机构描述 ：</td>
        <td>
        	<input id="description" name="description" class="easyui-textbox" data-options="multiline:true,validType:'maxLength[200]'" maxlength="200"
        		type="text" style="height:80px;width:530px;" value="${commonOrg.description }" />
        </td>
      </tr> 
      <input name="disabled" type="hidden" value="<c:choose><c:when test='${empty commonOrg}'>1</c:when><c:otherwise>${commonOrg.disabled}</c:otherwise></c:choose>" />     
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
          	<a id="saveOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" onclick="validOrgCode();">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
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
