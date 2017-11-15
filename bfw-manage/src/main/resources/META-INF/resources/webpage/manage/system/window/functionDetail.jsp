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
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/functionDetail.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/common-validate.js"></script>
</head>
<body>
<div class="tahoma_font  tdstyle"  style="padding-top:20px; padding-bottom:20px; padding-left:20px;">
  <form id="form_function" method="post">
  	<input id="id" name="id" value="${commonFunc.id }" type="hidden"/>
  	<input id="load" name="load" value="${load }" type="hidden"/>
    <table cellpadding="5" class="newAddOrg" width="680">
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>菜单名称：</td>
        <td width="300" >
        	<input id="functionName" name="functionName" type="text" style="height:25px;width:200px;"
        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入菜单名称！',validType:'maxLength[15]'"
        	 value="${commonFunc.functionName }" />        	
        </td>
      </tr>
      <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">菜单地址：</td>
        <td width="300" >
        	<input id="functionUrl" name="functionUrl" type="text" style="height:25px;width:300px;"
        	 class="easyui-textbox" data-options="validType:['maxLength[100]','urlCheck[100]']"
        	 value="${commonFunc.functionUrl }" />
        </td>
      </tr>
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>菜单顺序：</td>
        <td width="300" >
        	<input id="functionOrder" name="functionOrder" type="text" style="height:25px;width:200px;"
        	 class="easyui-numberbox" data-options="required:true,missingMessage:'请输入菜单顺序！',min:0,max:999,precision:0"
        	 value="${commonFunc.functionOrder }" />
        </td>
      </tr>
      <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10">上级菜单：</td>
        <td width="300" >
	        <select id="parentId" name="parentFunctionId" class="easyui-combotree" style="height:30px;width:200px;"   
	        	data-options="url:'${requestScope.basePath }manage/system/functionManagerController.do?getFuncComboTree',
	        	onLoadSuccess:function(data){
	        			if(${!empty commonFunc.parentFunctionId }){
	        				$('#parentId').combotree('setValue','${commonFunc.parentFunctionId }');
	        			}else if(${!empty funcParentId }){
	        				$('#parentId').combotree('setValue','${funcParentId }');
	        			}
	        		}"></select> 
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
