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
</head>
<body>
<div class="tahoma_font  tdstyle"
     style="padding-top: 20px; padding-bottom: 20px; padding-left: 20px; overflow: hidden">
    <form id="form_user" method="post">
        <input name="id" value="${obj.id }" type="hidden"/>

        <!-- 是否有效 -->
        <table cellpadding="5" class="newAddOrg" width="630">
            <tr>

                <td width="50" align="right" bgcolor="#f1f2f3" class="p_right10">告警原因：</td>
                <td width="300">
                    <c:out value="${obj.noticeReason}" escapeXml="false" />

                </td>

            </tr>

            <tr>

                <td width="50" align="right" bgcolor="#f1f2f3" class="p_right10">处理备注：</td>
                <td width="300">
                    <textarea style="height:200px;width: 400px" name="noticeType"></textarea>
                </td>

            </tr>
            <tr>


            <tr>
                <td colspan="2">
                    <div align="right" style="padding-right: 50px;padding-top: 5px;">
                        <a  href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;"
                           onclick="closeWin();">&nbsp;&nbsp;关闭&nbsp;&nbsp;</a>
                        &nbsp;&nbsp;
                        <a  href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;"
                           onclick="saveOrUpdate();">&nbsp;&nbsp;处理&nbsp;&nbsp;</a>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>

<script type="text/javascript">
    //关闭
    function closeWin(){
        $('#editWindow').window('close');
    }

    //保存
    function saveOrUpdate() {
        var isValid = $("#form_user").form("validate");//表单验证方法
        //进行录入操作的后台交互
        $.ajax({
            url: basePath + 'alarm/alarmNoticeManagerController.do?saveOrUpdate',
            type: "POST",
            dataType: "json",
            data: $("#form_user").serialize(),
            success: function (d) {
                if (d.resultCode == 0) {
                    $.messager.alert("提示", d.msg);
                    closeWin();

                    $("#getAlarmList").datagrid("reload");
                } else if (d.resultCode == 1) {
                    $.messager.alert("提示", d.msg);
                }
            }
        });
    }
</script>
</html>
