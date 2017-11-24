<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../../manage/header.jsp"></jsp:include>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/project/applicationSystemView/tradeFileRptChart.js"></script>
</head>
<head>
    <title></title>
</head>
<body>
<div style="width: 99%; padding-top: 10px; padding-left: 10px"
     id="centerMain">
    <div class="" style="width:100%;height:50%;">
        <div class="easyui-draggable" style="width:50%;height:100%;background:#fafafa;border:1px solid #ccc">

        </div>
        <div class="" style="width:50%;height:100%;">
            <div class="easyui-draggable" style="width:100%;height:50%;background:#fafafa;border:1px solid #ccc">

            </div>
            <div class="easyui-draggable" style="width:100%;height:50%;background:#fafafa;border:1px solid #ccc">

            </div>
        </div>
    </div>
    <div class="" style="width:100%;height:50%;">
        <div class="easyui-draggable" style="width:33%;height:100%;background:#fafafa;border:1px solid #ccc">

        </div>
        <div class="easyui-draggable" style="width:30%;height:100%;background:#fafafa;border:1px solid #ccc">

        </div>
    </div>

</div>
</body>
</html>
