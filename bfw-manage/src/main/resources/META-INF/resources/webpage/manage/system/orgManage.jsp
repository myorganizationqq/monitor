<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理-组织机构管理</title>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/orgmanager-manage.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
</head>
<body>
<br />
<div style="width: 99%; padding-top: 10px; padding-left: 10px"
	id="centerMain">
	<!--	查询模块开始		-->
	<div class="tahoma_font tableStyle_5_0">
		<form id="org_search_form">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="60" style="padding-left:20px;"><label>机构名称：</label></td>
				<td width="160">
					<input type="t" class="easyui-textbox" id="orgName"
						style="width: 150px" data-options="" fit="true" />
				</td>
				<td width="60" style="padding-left:20px;"><label>机构全名：</label></td>
				<td width="350">
					<input type="t" class="easyui-textbox" id="fullName"
						style="width: 150px" data-options="" fit="true" />
				</td>
				<td></td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" id="orgSearch"
						onclick="orgSearch();" style="padding: 0 10px">查询</a> 
					
					<a id="cleanBtn" href="javascript:void(0);" onclick="clean()"
					class="easyui-linkbutton" style="padding: 0 10px">清空</a>						
				</td>	
			</tr>
			<tr>
				<td style="padding-left:20px;padding-top:10px;" colspan="6">		
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="addOrg"
					    onclick="addOrg();" style="padding: 0 20px">新增</a>
					    
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="addOrg"
					    onclick="isEnabledOrg(1);" style="padding: 0 20px">机构启用</a>
					    
					 <a href="javascript:void(0);" class="easyui-linkbutton"  id="addOrg"
					    onclick="isEnabledOrg(0);" style="padding: 0 20px">机构禁用</a>
					    
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="addOrg"
					    onclick="situation('depart');" style="padding: 0 20px">部门情况</a>
					 	
					    
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="addOrg"
					    onclick="situation('user');" style="padding: 0 20px">人员情况</a>
				</td>
			</tr>

		</table>
		</form>
	</div>
	<!--	查询模块结束		-->
	
	<!--上下距间-->
	<div class="easyui-layout" style="height: 10px"></div>
	<div class="line"></div>
	<!--gap-->
	<div class="easyui-layout" style="height: 10px"></div>
	<div class="easyui-layout" style="height: 443px; padding: 10px">
		<table id="getOrgList" >
        </table>
	</div>
</div>

</body>
</html>