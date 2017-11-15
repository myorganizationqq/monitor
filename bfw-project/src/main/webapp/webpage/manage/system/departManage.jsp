<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理-部门管理</title>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/departmanager-manage.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
</head>
<body>
<br />
<div style="width: 99%; padding-top: 10px; padding-left: 10px"
	id="centerMain">
	<!--	查询模块开始		-->
	<div class="tahoma_font tableStyle_5_0">
		<input id="h_orgId" type="hidden" value="${p_orgId}">
		<form id="depart_search_form">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="60" style="padding-left:20px;"><label>部门名称：</label></td>
				<td width="160">
					<input type="t" class="easyui-textbox" id="departName"
						style="width: 150px" data-options="" fit="true" />
				</td>
				<td width="60" style="padding-left:20px;"><label>组织机构：</label></td>
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
				<td></td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" id="departSearch"
						onclick="departSearch();" style="padding: 0 10px">查询</a> 
					
					<a id="cleanBtn" href="javascript:void(0);" onclick="clean()"
					class="easyui-linkbutton" style="padding: 0 10px">清空</a>						
				</td>	
			</tr>
			<tr>
				<td style="padding-left:20px;padding-top:10px;" colspan="6">		
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="addDepart"
					    onclick="addDepart();" style="padding: 0 20px">新增</a>		
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
		<table id="getDepartList" >
        </table>
	</div>
</div>

</body>
</html>