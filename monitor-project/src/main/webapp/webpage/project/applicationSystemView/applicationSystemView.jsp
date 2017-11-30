<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>应用系统视图</title>
    <jsp:include page="../../manage/header.jsp"></jsp:include>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
</head>
<body>

<%--<div style="margin:20px 0 10px 0;"></div>--%>
<div class="easyui-tabs" style="width:100%;height:700px; ">
    <div title="清算应用程序性能监控" style="padding:10px">

    </div>
    <div title="清算应用程序业务监控" style="padding:10px;height: 700px;overflow:scroll;overflow-y:hidden">
        <iframe id="businessMonitorFrame"  width="100%" height="100%" frameBorder="no"   scrolling="auto"   src="${requestScope.basePath }webpage/project/applicationSystemView/businessMonitor.jsp"></iframe>
    </div>
</div>
</body>
</html>
