<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<jsp:include page="../manage/include.jsp"></jsp:include>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/common-validate.js"></script>
</head>
<body>
	<div class="tahoma_font  tdstyle"
		style="padding-top: 20px; padding-bottom: 20px; padding-left: 20px; overflow: hidden">
		<form id="form_user" method="post">
			<input id="id" name="id" value="${obj.id }" type="hidden"/>
			<input name="alarmName" value="${obj.alarmName }" type="hidden"/>
			<input name="alarmType" value="${obj.alarmType }" type="hidden"/>
			<input name="alarmStatus" value="${obj.alarmStatus }" type="hidden"/>
			<input name="alarmMessage" value="${obj.alarmMessage }" type="hidden"/>
			<input name="alarmEmail" value="${obj.alarmEmail }" type="hidden"/>
			<input name="alarmUser" value="${obj.alarmUser }" type="hidden"/>
			<input name="alarmCronTrigger" value="${obj.alarmCronTrigger }" type="hidden"/>
			<input name="alarmCronTriggerHour" value="${obj.alarmCronTriggerHour }" type="hidden"/>
			<input name="alarmCronTriggerStart" value="${obj.alarmCronTriggerStart }" type="hidden"/>
			<input name="createDateStr" value="${obj.createDate }" type="hidden"/>
			<table cellpadding="5" class="newAddOrg" width="680">

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">监控指标</td>
					<td width="110" align="center" bgcolor="#f1f2f3" class="p_right10">故障</td>
					<td width="110" align="center" bgcolor="#f1f2f3" class="p_right10">危险</td>
				</tr>

				 <tr>
			        <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">金融IC卡黑名单</td>
			        <td width="250" >
						小于：<input id="jyzxt_jrickhmd_gz"  name="jyzxt_jrickhmd_gz" type="text" style="height:25px;width:150px;"
			        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
			        	 value="${obj.jyzxt_jrickhmd_gz }"  />个
			        </td>

					 <td width="250" >
						 小于：<input id="jyzxt_jrickhmd_wx" name="jyzxt_jrickhmd_wx" type="text" style="height:25px;width:150px;"
								class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								value="${obj.jyzxt_jrickhmd_wx }" />个
					 </td>
			      </tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">初始化羊城通导出</td>
					<td width="250" >
						小于：<input id="jyzxt_cshyctdc_gz"  name="jyzxt_cshyctdc_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_cshyctdc_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_cshyctdc_wx" name="jyzxt_cshyctdc_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_cshyctdc_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">表清理</td>
					<td width="250" >
						小于：<input id="jyzxt_bql_gz"  name="jyzxt_bql_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_bql_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_bql_wx" name="jyzxt_bql_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_bql_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">初始化待入库FTP文件</td>
					<td width="250" >
						小于：<input id="jyzxt_cshdrkftpwj_gz"  name="jyzxt_cshdrkftpwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_cshdrkftpwj_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_cshdrkftpwj_wx" name="jyzxt_cshdrkftpwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_cshdrkftpwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出羊城通其他线路CPU卡文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcyctqtxlcpukwj_gz"  name="jyzxt_dcyctqtxlcpukwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctqtxlcpukwj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcyctqtxlcpukwj_wx" name="jyzxt_dcyctqtxlcpukwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctqtxlcpukwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">审计文件下发</td>
					<td width="250" >
						小于：<input id="jyzxt_sjwjxf_gz"  name="jyzxt_sjwjxf_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_sjwjxf_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_sjwjxf_wx" name="jyzxt_sjwjxf_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_sjwjxf_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出羊城通广佛普通卡文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcyctgfptkwj_gz"  name="jyzxt_dcyctgfptkwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctgfptkwj_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcyctgfptkwj_wx" name="jyzxt_dcyctgfptkwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctgfptkwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">重跑文件入库</td>
					<td width="250" >
						小于：<input id="jyzxt_cpwjrk_gz"  name="jyzxt_cpwjrk_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_cpwjrk_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_cpwjrk_wx" name="jyzxt_cpwjrk_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_cpwjrk_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">下发黑名单</td>
					<td width="250" >
						小于：<input id="jyzxt_xfhmd_gz"  name="jyzxt_xfhmd_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_xfhmd_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_xfhmd_wx" name="jyzxt_xfhmd_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_xfhmd_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">计算线网、有轨金融IC卡票价</td>
					<td width="250" >
						小于：<input id="jyzxt_jsxwygjrickpj_gz"  name="jyzxt_jsxwygjrickpj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_jsxwygjrickpj_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jywjhsj_bhfsjgs_wx" name="jywjhsj_bhfsjgs_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jywjhsj_bhfsjgs_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">备份本地文件</td>
					<td width="250" >
						小于：<input id="jyzxt_bfbdwj_gz"  name="jyzxt_bfbdwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_bfbdwj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_bfbdwj_wx" name="jyzxt_bfbdwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_bfbdwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">报表统计</td>
					<td width="250" >
						小于：<input id="jyzxt_bbtj_gz"  name="jyzxt_bbtj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_bbtj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_bbtj_wx" name="jyzxt_bbtj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_bbtj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出羊城通广佛CPU卡文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcyctgfcpukwj_gz"  name="jyzxt_dcyctgfcpukwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctgfcpukwj_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcyctgfcpukwj_wx" name="jyzxt_dcyctgfcpukwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctgfcpukwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出羊城通有轨普通卡文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcyctygptkwj_gz"  name="jyzxt_dcyctygptkwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctygptkwj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcyctygptkwj_wx" name="jyzxt_dcyctygptkwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctygptkwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出羊城通有轨CPU卡文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcyctygcpukwj_gz"  name="jyzxt_dcyctygcpukwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctygcpukwj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcyctygcpukwj_wx" name="jyzxt_dcyctygcpukwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctygcpukwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出有轨金融IC卡交易文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcygjrickjywj_gz"  name="jyzxt_dcygjrickjywj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcygjrickjywj_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcygjrickjywj_wx" name="jyzxt_dcygjrickjywj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcygjrickjywj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出金融IC卡退款文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcjricktkwj_gz"  name="jyzxt_dcjricktkwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcjricktkwj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcjricktkwj_wx" name="jyzxt_dcjricktkwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcjricktkwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出金融IC卡交易文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcjrickjywj_gz"  name="jyzxt_dcjrickjywj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcjrickjywj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcjrickjywj_wx" name="jyzxt_dcjrickjywj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcjrickjywj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导出羊城通其他线路普通卡文件</td>
					<td width="250" >
						小于：<input id="jyzxt_dcyctqtxlptkwj_gz"  name="jyzxt_dcyctqtxlptkwj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctqtxlptkwj_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_dcyctqtxlptkwj_wx" name="jyzxt_dcyctqtxlptkwj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_dcyctqtxlptkwj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导进出闸错误表非重复数据</td>
					<td width="250" >
						小于：<input id="jyzxt_djczcwbfcfsj_gz"  name="jyzxt_djczcwbfcfsj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_djczcwbfcfsj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_djczcwbfcfsj_wx" name="jyzxt_djczcwbfcfsj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_djczcwbfcfsj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">审计、异常统计</td>
					<td width="250" >
						小于：<input id="jyzxt_sjyctj_gz"  name="jyzxt_sjyctj_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_sjyctj_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_sjyctj_wx" name="jyzxt_sjyctj_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_sjyctj_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">收益统计模块</td>
					<td width="250" >
						小于：<input id="jyzxt_sytjmk_gz"  name="jyzxt_sytjmk_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_sytjmk_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_sytjmk_wx" name="jyzxt_sytjmk_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_sytjmk_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">结算模块</td>
					<td width="250" >
						小于：<input id="jyzxt_jsmk_gz"  name="jyzxt_jsmk_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_jsmk_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_jsmk_wx" name="jyzxt_jsmk_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_jsmk_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">客流、乘距分析</td>
					<td width="250" >
						小于：<input id="jyzxt_klcjfx_gz"  name="jyzxt_klcjfx_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_klcjfx_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_klcjfx_wx" name="jyzxt_klcjfx_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_klcjfx_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">生成寄存器数据统计报表</td>
					<td width="250" >
						小于：<input id="jyzxt_ccjcqsjtjbb_gz"  name="jyzxt_ccjcqsjtjbb_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_ccjcqsjtjbb_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_ccjcqsjtjbb_wx" name="jyzxt_ccjcqsjtjbb_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_ccjcqsjtjbb_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">生成运营商收益报表</td>
					<td width="250" >
						小于：<input id="jyzxt_ccyyssybb_gz"  name="jyzxt_ccyyssybb_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_ccyyssybb_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_ccyyssybb_wx" name="jyzxt_ccyyssybb_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_ccyyssybb_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">分表日志记录</td>
					<td width="250" >
						小于：<input id="jyzxt_fbrzjl_gz"  name="jyzxt_fbrzjl_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_fbrzjl_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_fbrzjl_wx" name="jyzxt_fbrzjl_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_fbrzjl_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">金融IC卡对账报表</td>
					<td width="250" >
						小于：<input id="jyzxt_jrickdzbb_gz"  name="jyzxt_jrickdzbb_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_jrickdzbb_gz }"  />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_jrickdzbb_wx" name="jyzxt_jrickdzbb_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_jrickdzbb_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">羊城通对账报表</td>
					<td width="250" >
						小于：<input id="jyzxt_yctdzbb_gz"  name="jyzxt_yctdzbb_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_yctdzbb_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_yctdzbb_wx" name="jyzxt_yctdzbb_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_yctdzbb_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">导入历史表</td>
					<td width="250" >
						小于：<input id="jyzxt_drllb_gz"  name="jyzxt_drllb_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_drllb_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="jyzxt_drllb_wx" name="jyzxt_drllb_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.jyzxt_drllb_wx }" />个
					</td>
				</tr>

			      <tr>
			        <td colspan="3">
			          <div align="right" style="padding-right: 50px;padding-top: 5px;" > 
			          	<a id="saveOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" onclick="saveOrUpdate(1);">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
			          </div>
			      	</td>
			      </tr>			      
			</table>			
		</form>
	</div>
</body>

<script type="text/javascript">

    //保存
    function saveOrUpdate(step){
        var isValid = $("#form_user").form("validate");//表单验证方法
        if (true){
            //进行录入操作的后台交互
            $.ajax({
                url:basePath+'alarm/alarmManagerController.do?saveOrUpdate',
                type:"POST",
                dataType:"json",
                data:$("#form_user").serialize(),
                success: function(d){
                    if(d.resultCode==0){
                        $.messager.alert("提示",d.msg);
                        closeWin();

                        $("#getAlarmList").datagrid("reload");
                    } else if(d.resultCode == 1){
                        $.messager.alert("提示",d.msg);
                    }
                }
            });
        }
    }

    //关闭
    function closeWin(){

        $('#editWindow').window('close');

    }
</script>
</html>
