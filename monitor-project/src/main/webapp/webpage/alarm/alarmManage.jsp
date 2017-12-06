<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理-角色管理</title>
<jsp:include page="../manage/header.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/alarm/alarmManage.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
</head>
<body>
<br />
<div style="width: 99%; padding-top: 10px; padding-left: 10px"
	id="centerMain">
	<!--	查询模块开始		-->
	<%--<div class="tahoma_font tableStyle_5_0">
		<form id="role_search_form">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="60" style="padding-left:20px;"><label>告警名称：</label></td>
				<td width="160">
					<input type="t" class="easyui-textbox" id="alarmName"
						style="width: 150px" data-options="" fit="true" />
				</td>
				<td width="60" style="padding-left:20px;"><label>告警类别：</label></td>
				<td width="160">
					<select id="alarmType" class="easyui-combobox" style="width:150px;"
				        	data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false,
				        	url:'${requestScope.basePath }manage/common/constantController.do?getCombobox&mapName=role_type'"></select>  
				</td>
				<td></td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton"
						onclick="alarmSearch();" style="padding: 0 10px">查询</a>
						
					<a href="javascript:void(0);" class="easyui-linkbutton"
						onclick="clean();" style="padding: 0 10px">清空</a> 						
				</td>	
			</tr>
			<tr>
				<td style="padding-left:20px;padding-top:10px;" colspan="6">		
					<a href="javascript:void(0);" class="easyui-linkbutton"
					    onclick="alarmAdd('JYZXT');" style="padding: 0 10px">新增清算子系统告警配置</a>
					&nbsp;
					<a href="javascript:void(0);" class="easyui-linkbutton"
					   onclick="alarmAdd('JYWJHSJ');" style="padding: 0 10px">新增交易文件和数据告警配置</a>
					&nbsp;
					<a href="javascript:void(0);" class="easyui-linkbutton"
					   onclick="alarmAdd('TXYWXT');" style="padding: 0 10px">新增通信业务系统告警配置</a>
					&nbsp;
				</td>
			</tr>

		</table>
		</form>
	</div>--%>
	<!--	查询模块结束		-->
	
	<!--上下距间-->
	<div class="easyui-layout" style="height: 10px"></div>
	<div class="line"></div>
	<!--gap-->
	<div class="easyui-layout" style="height: 10px"></div>
	<div class="easyui-layout" style="height: 443px; padding: 10px">
		<table id="getAlarmList" >
        </table>
	</div>
</div>

</body>
</html>