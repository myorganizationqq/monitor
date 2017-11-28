<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../../manage/header.jsp"></jsp:include>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/echarts/echarts.min.js"></script>
    <script type="text/javascript"
            src="${requestScope.basePath }webpage/js/project/applicationSystemView/tradeFileRptChart.js"></script>
</head>
<head>
    <title></title>
</head>
<body>
<input type="hidden" id="flag" value="1">
<div class="row" style="width: 100%;height: 100%; padding-top: 10px; padding-left: 10px">
    <div class="col-sm-12">
        <div class="col-sm-6">
            <div class="" style="background:#fafafa;border:1px solid #ccc">
                <label style="font-size: 16px;padding-left: 50px;padding-top: 30px">▍整体统计</label>
                <div id="totalCountPie" style="width:500px;height:300px">
                    
                </div>
            </div>
        </div>
        <div class="col-sm-6" style="padding-left: 8px">
            <div class="" style="background:#fafafa;border:1px solid #ccc">
                <div id="fileCountPillar" style="width:600px;height:145px">

                </div>
            </div>
            <div class="" style="background:#fafafa;border:1px solid #ccc;margin-top: 10px">
                <div id="handleCountPillar" style="width:600px;height:145px">

                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-12" style="margin-left: 5px;margin-top: 6px">
        <div class="col-sm-4">
            <div class="" style="background:#fafafa;border:1px solid #ccc">
                <div id="wrongfulCountPillar" style="width:450px;height:200px">

                </div>
            </div>
        </div>
        <div class="col-sm-4" style="padding-left: 5px">
            <div class="" style="background:#fafafa;border:1px solid #ccc">
                <div id="duplicateCountPillar" style="width:450px;height:200px">

                </div>
            </div>
        </div>
        <div class="col-sm-4" style="padding-left: 5px">
            <div class="" style="background:#fafafa;border:1px solid #ccc">
                <div id="noPretreatmentCountPillar" style="width:450px;height:200px">

                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
