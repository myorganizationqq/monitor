<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="cache-control" >
    <jsp:include page="../../manage/header.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${requestScope.basePath }webpage/js/bootstrap3.3.5/css/bootstrap.min.css" />
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/echarts/echarts.min.js"></script>
    <script type="text/javascript"
            src="${requestScope.basePath }webpage/js/project/applicationSystemView/tradeFileRptChart.js"></script>
</head>

<body>
    <input type="hidden" id="flag" value="2">
<div class="row" style="width: 100%;height: 100%; padding-top: 10px; padding-left: 10px">
    <div class="col-sm-12">
        <div class="col-sm-5">
            <div  style="background:#fafafa;border:1px solid #ccc;width:100%">
                <label style="padding-left: 50px;padding-top: 30px">▍整体统计</label>
                <div id="totalCountPie" style="min-height:300px">

                </div>
            </div>
        </div>
        <div class="col-sm-7" style="padding-left: 8px">
            <div  style="background:#fafafa;border:1px solid #ccc;width:100%">
                <div id="faultTimePillar" style="min-height:200px">

                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
