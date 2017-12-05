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
        <input id="id" name="id" value="${obj.id }" type="hidden"/>

        <!-- 是否有效 -->
        <table cellpadding="5" class="newAddOrg" width="680">
            <tr>

                <td width="30" align="right" bgcolor="#f1f2f3" class="p_right10">告警原因：</td>
                <td width="300">
                    <c:out value="${obj.noticeReason}" escapeXml="false" />

                </td>

            </tr>
            <tr>


            <tr>
                <td colspan="2">
                    <div align="right" style="padding-right: 50px;padding-top: 5px;">
                        <a id="closeOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;"
                           onclick="closeWin();">&nbsp;&nbsp;关闭&nbsp;&nbsp;</a>
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
        $('#detailWindow').window('close');

    }

</script>
</html>
