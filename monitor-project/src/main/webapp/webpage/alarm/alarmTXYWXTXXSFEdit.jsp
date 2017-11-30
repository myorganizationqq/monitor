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
			<input name="alarmCronTriggerStart" value="${obj.alarmCronTriggerStart }" type="hidden"/>
			<input name="alarmCronTriggerHour" value="${obj.alarmCronTriggerHour }" type="hidden"/>
			<table cellpadding="5" class="newAddOrg" width="680" aria-label="fewafaewf">
				<tr>
					<td colspan="3">
						<div align="left" style="padding-right: 50px;padding-top: 5px;" >
							消息收发
						</div>
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">监控指标</td>
					<td width="110" align="center" bgcolor="#f1f2f3" class="p_right10">故障</td>
					<td width="110" align="center" bgcolor="#f1f2f3" class="p_right10">危险</td>
				</tr>

				 <tr>
			        <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">接收最大流量</td>
			        <td width="250" >
						小于：<input id="txywxt_jszdll_gz"  name="txywxt_jszdll_gz" type="text" style="height:25px;width:150px;"
			        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
			        	 value="${obj.txywxt_jszdll_gz }"  />个
			        </td>

					 <td width="250" >
						 小于：<input id="txywxt_jszdll_wx" name="txywxt_jszdll_wx" type="text" style="height:25px;width:150px;"
								class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								value="${obj.txywxt_jszdll_wx }" />个
					 </td>
			      </tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">接收最小流量</td>
					<td width="250" >
						小于：<input id="txywxt_jszxll_gz"  name="txywxt_jszxll_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.txywxt_jszxll_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="txywxt_jszxll_wx" name="txywxt_jszxll_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.txywxt_jszxll_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">成功个数</td>
					<td width="250" >
						小于：<input id="txywxt_cggs_gz"  name="txywxt_cggs_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.txywxt_cggs_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="txywxt_cggs_wx" name="txywxt_cggs_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.txywxt_cggs_wx }" />个
					</td>
				</tr>

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">失败个数</td>
					<td width="250" >
						小于：<input id="txywxt_sbgs_gz"  name="txywxt_sbgs_gz" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.txywxt_sbgs_gz }" />个
					</td>

					<td width="250" >
						小于：<input id="txywxt_sbgs_wx" name="txywxt_sbgs_wx" type="text" style="height:25px;width:150px;"
								  class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								  value="${obj.txywxt_sbgs_wx }" />个
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
