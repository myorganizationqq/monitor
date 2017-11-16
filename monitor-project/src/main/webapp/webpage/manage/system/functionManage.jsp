<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理-菜单管理</title>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/logic/system/functionmanager-manage.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
</head>
<body>
<br />

<div style="width: 99%; padding-top: 10px; padding-left: 10px"
	id="centerMain">
	<!--	查询模块开始		-->
	<div class="tahoma_font tableStyle_5_0">
		<form id="function_search_form">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="padding-left:20px;padding-top:10px;" colspan="6">		
					<a href="javascript:void(0);" class="easyui-linkbutton" 
					    onclick="addFunc();" style="padding: 0 20px">新增</a>			
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
		<table id="getFunctionList" >
        </table>
	</div>
</div>
</body>
</html>