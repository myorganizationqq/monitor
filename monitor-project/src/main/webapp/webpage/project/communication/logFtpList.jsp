<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FTP文件管理</title>
<jsp:include page="../../manage/header.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath}webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript" src="${requestScope.basePath}webpage/js/communication/logFtpManage.js"></script>
<script type="text/javascript" src="${requestScope.basePath}webpage/js/public.js"></script>
</head>
<body>
<br />
<div style="width: 99%; padding-top: 10px; padding-left: 10px"
	id="centerMain">
	<!--	查询模块开始		-->
	<div class="tahoma_font tableStyle_5_0">
		<form id="role_search_form">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="60" style="padding-left:20px;"><label>IP地址：</label></td>
				<td width="160">
					<input type="t" class="easyui-textbox" id="ftpIp"
						style="width: 150px" data-options="" fit="true" />
				</td>
				<td width="60" style="padding-left:20px;"><label>文件名称：</label></td>
				<td width="160">
					<input type="t" class="easyui-textbox" id="fileName"
						style="width: 150px" data-options="" fit="true" />
				</td>
				<td width="60" style="padding-left:20px;"><label>开始时间：</label></td>
				<td width="360">
					<input id="dateTime1" type="text" size="16" class="easyui-datetimebox">
					至
					<input id="dateTime2" type="text" size="16" class="easyui-datetimebox">
				</td>
				<td></td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton"
						onclick="listSearch();" style="padding: 0 10px">查询</a>
						
					<a href="javascript:void(0);" class="easyui-linkbutton"
						onclick="clean();" style="padding: 0 10px">清空</a> 						
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
		<table id="getLogFtpList" >
        </table>
	</div>
</div>

</body>
</html>