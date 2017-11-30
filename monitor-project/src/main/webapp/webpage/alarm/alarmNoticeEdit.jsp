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
	<style type="text/css"></style>
</head>
<body>
	<div class="tahoma_font  tdstyle"
		style="padding-top: 20px; padding-bottom: 20px; padding-left: 20px; overflow: hidden">
		<form id="form_user" method="post">
			<input id="id" name="id" value="${obj.id}" type="hidden"/>
			<input  name="alarmType" value="${obj.alarmType}" type="hidden"/>
			<table cellpadding="5" class="newAddOrg" width="500">

				<tr>
					<td width="100" align="right" bgcolor="#f1f2f3" class="p_right10">告警时间</td>
					<td width="250" colspan="3">
						从<input name="alarmCronTriggerStart" type="text" value="${obj.alarmCronTriggerStart}" style="height:25px;width:50px;"
							   class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[1,2]'"
							     />点钟开始,每<input name="alarmCronTriggerHour" type="text" value="${obj.alarmCronTriggerHour}" style="height:25px;width:50px;"
											class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[1,2]'"
					/>小时执行一次
					</td>
				</tr>

				<tr>
					<td width="100" align="right" bgcolor="#f1f2f3" class="p_right10">告警方式</td>
					<td width="250" colspan="3">
						<table cellpadding="0" class="newAddOrg" width="290" border="0" >
							<tr>
								<td width="100" >
									短信告警：
									<input id="message_1" type="checkbox" value="1" name="alarmMessage">故障  &nbsp;&nbsp;
									<input id="message_2" type="checkbox" value="2" name="alarmMessage">危险
								</td>

							</tr>

							<tr>
								<td width="100" >
									邮件告警：
									<input id="mail_1" type="checkbox" value="1" name="alarmEmail">故障  &nbsp;&nbsp;
									<input id="mail_2" type="checkbox" value="2" name="alarmEmail">危险
								</td>
							</tr>
						</table>
					</td>
				</tr>


				<tr style="border-style:none">
					<td width="100" align="right" bgcolor="#f1f2f3" class="p_right10">告警通知人</td>
					<td valign="middle" style="border-style:none" width="130px">
						<select id="selectingUser" style="width:120px;" size="10" >
						</select>
					</td>

					<td style="border-style:none" valign="middle" width="20">
						<img style="cursor:pointer;margin-bottom:5px;" src="${requestScope.basePath }webpage/image/sysimages/right1.png" onclick="shift(1)"/>
						<img style="cursor:pointer;margin-bottom:5px;" src="${requestScope.basePath }webpage/image/sysimages/right2.png" onclick="shift(2)"/>
						<img style="cursor:pointer;margin-bottom:5px;" src="${requestScope.basePath }webpage/image/sysimages/left1.png" onclick="shift(3)"/>
						<img style="cursor:pointer" src="${requestScope.basePath }webpage/image/sysimages/left2.png" onclick="shift(4)"/>
					</td>

					<td  valign="middle" id="td_id_1">
						<select id="selectedUser" style="width:120px;" size="10" ondblclick="shift(3)">
						</select>
					</td>
				</tr>



			      <tr>
			        <td colspan="4">
			          <div align="right" style="padding-right: 50px;padding-top: 5px;" > 
			          	<a id="saveOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" onclick="saveOrUpdate();">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
			          </div>
			      	</td>
			      </tr>			      
			</table>			
		</form>
	</div>
</body>

<script type="text/javascript">
	var alarmMail = '${obj.alarmEmail}';
    var alarmMessage = '${obj.alarmMessage}';

    if(alarmMessage.indexOf("1")!=-1){
        $("#message_1").attr("checked", true);
    }
    if(alarmMessage.indexOf("2")!=-1){
        $("#message_2").attr("checked", true);
    }

    if(alarmMail.indexOf("1")!=-1){
        $("#mail_1").attr("checked", true);
    }
    if(alarmMail.indexOf("2")!=-1){
        $("#mail_2").attr("checked", true);
    }
    //保存
    function saveOrUpdate(){
        //var isValid = $("#form_user").form("validate");//表单验证方法

        //选中的用户
        var userIds='';
        $("#selectedUser option").each(function(){
            userIds += ','+$(this).val() ;
        });
        if(userIds!=''){
            userIds=userIds.substring(1);
        }

        if (true){
            //进行录入操作的后台交互
            $.ajax({
                url:basePath+'alarm/alarmManagerController.do?updateAlarmNotice&alarmUser='+userIds,
                type:"POST",
                dataType:"json",
                data:$("#form_user").serialize(),
                success: function(d){
                    if(d.resultCode==0){
                        $.messager.alert("提示",d.msg);
                        closeWin();
                        $("#role_search_form").datagrid("reload");
                    } else if(d.resultCode == 1){
                        $.messager.alert("提示",d.msg);
                    }
                }
            });
        }
    }

    //关闭
    function closeWin(){
        var id=$("#id").val();
        if(id){
            $('#editWindow').window('close');
        }else{
            $('#addWindow').window('close');
        }
    }
</script>

<script type="text/javascript">
	var id = "${obj.id}";
    $(function(){
        var orgId=1;
        var roleId=1;
        var userType=1;


        getSelectingUsers(null,null,null,null,null);

        getSelectedUsers(null,null,null,null,null);


    });

    //待选用户
    function getSelectingUsers(orgId,deptId,userType,roleId,realName){
        $.ajax({
            url:basePath+'alarm/alarmManagerController.do?getSelectingUsers',
            type:"POST",
            dataType:"json",
            data:{'orgId':orgId,'departId':deptId,'userType':userType,'roleId':roleId,'realName':realName,'id':id},
            success: function(data){
                $("#selectingUser").html("");
                for(var i in data){
                    $("#selectingUser").append("<option value='"+data[i].id+"'>"+data[i].realName+"</option>");
                }
            }
        });
    }

    //已选用户
    function getSelectedUsers(orgId,deptId,userType,roleId,realName){
        $.ajax({
            url:basePath+'alarm/alarmManagerController.do?getSelectedUsers',
            type:"POST",
            dataType:"json",
            data:{'orgId':orgId,'departId':deptId,'userType':userType,'roleId':roleId,'realName':realName,'id':id},
            success: function(data){
                $("#selectedUser").html("");
                var arr=new Array();
                for(var i in data){
                    $("#selectedUser").append("<option value='"+data[i].id+"'>"+data[i].realName+"</option>");
                    arr[i]=data[i].id;
                }
                $("#oldSelectedUsers").val(arr);
            }
        });
    }

    //左右移动
    function shift(op) {
        var selectingObj = document.getElementById('selectingUser');
        var selectedObj = document.getElementById('selectedUser');
        switch (op) {
            case 1://右移单个
                upSelect(selectingObj, selectedObj);
                break;
            case 2://右移全部
                var len = selectingObj.options.length;
                for (var i = len - 1; i >= 0; i--) {
                    upSelectAll(selectingObj, selectedObj, i);
                }
                break;
            case 3://左移单个
                upSelect(selectedObj, selectingObj);
                break;
            case 4://左移全部
                var len = selectedObj.options.length;
                for (var i = len - 1; i >= 0; i--) {
                    upSelectAll(selectedObj, selectingObj, i);
                }
                break;
        }
    }
    //单个选中移动
    function upSelect(selecteds, addselect){
        if (selecteds.selectedIndex == -1) {
            return;
        }
        var selecting = selecteds;
        var addselect = addselect;
        var oOption = document.createElement("option");
        oOption.text = selecting.options[selecting.selectedIndex].text;
        oOption.value = selecting.options[selecting.selectedIndex].value;
        addselect.add(oOption);
        selecting.remove(selecting.selectedIndex);
    }
    //所有选中移动
    function upSelectAll(selecteds, addselect, i){
        var selecting = selecteds;
        var addselect = addselect;
        var oOption = document.createElement("option");
        oOption.text = selecting.options[i].text;
        oOption.value = selecting.options[i].value;
        addselect.add(oOption);
        selecting.remove(i);

    }

    //关闭窗口
    function closeWin(){
        $('#editNoticeWindow').window('close');
    }
</script>
</html>
