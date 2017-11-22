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
			<input id="alarmType" name="alarmType" value="JYWJHSJ" type="hidden"/>
			<table cellpadding="5" class="newAddOrg" width="680">

				<tr>
					<td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">监控指标</td>
					<td width="110" align="center" bgcolor="#f1f2f3" class="p_right10">故障</td>
					<td width="110" align="center" bgcolor="#f1f2f3" class="p_right10">危险</td>
				</tr>

				 <tr>
			        <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">不合法数据个数</td>
			        <td width="250" >
						小于：<input id="jywjhsj_bhfsjgs_gz"  name="jywjhsj_bhfsjgs_gz" type="text" style="height:25px;width:150px;"
			        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
			        	 value="${obj.jywjhsj_bhfsjgs_gz }"  />个
			        </td>

					 <td width="250" >
						 小于：<input id="jywjhsj_bhfsjgs_wx" name="jywjhsj_bhfsjgs_wx" type="text" style="height:25px;width:150px;"
								class="easyui-textbox" data-options="required:true,missingMessage:'请输入整数！',validType:'length[2,50]'"
								value="${obj.jywjhsj_bhfsjgs_wx }" />个
					 </td>
			      </tr>



			      <tr>
			        <td colspan="3">
			          <div align="right" style="padding-right: 50px;padding-top: 5px;" > 
			          	<a id="saveOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" onclick="saveOrUpdate();">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
			          	&nbsp;&nbsp;
			          	<a id="closeOperation"  href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;" onclick="closeWin();">&nbsp;&nbsp;设置告警通知&nbsp;&nbsp;</a>
			          </div>
			      	</td>
			      </tr>			      
			</table>			
		</form>
	</div>
</body>

<script type="text/javascript">

    //保存
    function saveOrUpdate(){
        //var isValid = $("#form_user").form("validate");//表单验证方法
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
</html>
