<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理-角色管理</title>
<jsp:include page="../manage/header.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/alarm/alarmNoticeManage.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
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
				<td width="60" style="padding-left:20px;"><label>告警类型：</label></td>
				<td width="160">

					<select id="noticeName" class="easyui-combobox" data-options="editable:false" panelHeight="auto" style="width:150px">
						<option value="">---请选择告警类型---</option>
						<option value="JYWJHSJ">交易文件和数据</option>
						<option value="JYZXT">清算子系统</option>
						<option value="TXYWXTXXSF">通信业务系统消息收发</option>
						<option value="TXYWXTFTPWJ">通信业务系统FTP文件</option>
					</select>
				</td>
				<td width="60" style="padding-left:20px;"><label>告警时间：</label></td>
				<td width="320">
					<input id="beginDate" type="text" class="easyui-datebox" currentText="Today" style="width: 110px" />
					-
					<input id="endDate" type="text" class="easyui-datebox" currentText="Today" style="width: 110px" />

				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton"
						onclick="alarmSearch();" style="padding: 0 10px">查询</a>
						
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
	<div class="easyui-layout" style="height: 600px; padding: 10px">
		<table id="getAlarmList" >
        </table>
	</div>
</div>

</body>
<script type="text/javascript">

	var noticeStatus = "${noticeStatus}";
</script>
</html>