<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../../manage/header.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${requestScope.basePath}webpage/js/bootstrap3.3.5/css/bootstrap.min.css" />
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/manage/amsWindow.js"></script>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/public.js"></script>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/project/netioReport/netioChart.js"></script>
</head>
<body>
<div style="width: 99%; padding-top: 10px; padding-left: 10px" id="centerMain">
	<div style="margin:0 0 10px 500px;">
		<button>最近2小时</button><button>最近24小时</button><button>最近7天</button><button>最近30天</button>
	</div>
    <div class="line"></div>
</div>
<div class="row" style="width: 100%;height: 100%; padding-top: 10px; padding-left: 10px">
    <div class="col-sm-12">
        <div class="col-sm-6">
            <div style="background:#fafafa;border:1px solid #ccc;width: 100%">
                <label style="font-size: 16px;padding-left: 50px;padding-top: 30px">▍整体统计</label>
                <div id="totalCountPie" style="min-height:300px"></div>
            </div>
        </div>
        <div class="col-sm-6" style="padding-left: 8px">
            <div style="background:#fafafa;border:1px solid #ccc;width: 100%">
                <div id="dataFlow" style="min-height:172px"></div>
            </div>
            <div style="background:#fafafa;border:1px solid #ccc;margin-top: 10px;width: 100%">
                <div id="dataPacket" style="min-height:172px"></div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
