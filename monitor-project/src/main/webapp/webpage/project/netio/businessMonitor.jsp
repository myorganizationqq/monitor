<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<style type="text/css">
		.tbborder { border:1px solid #ccc; border-radius:3px; }
		.deviceDiv { margin:0 0 8px 8px; width:400px; height: 300px; overflow:auto; }
		.interfaceDiv { margin:0 0 8px -156px; width:720px; height: 300px; }
		.deviceTb { margin:auto; }
		.interfaceTb { margin:auto; width:695px; margin-bottom:3px; }
		.dataDiv { width:715px; height: 262px; overflow:auto; }
		.dataDiv table td { border:1px solid #ccc; }
		.dataDiv tr:nth-child(odd){ background:#F4F4F4; }
		.txtInfo { font-size:18px; padding:5px; }
		td { text-align: center; }
		.bold { font-weight: bold; }
		.container-div { height:100%; padding-top:10px; overflow:auto; }
		.chart-div { background:#fff; border:1px solid #ccc; padding-left:40px; border-radius:3px; }
	</style>
</head>
<body>
<div class="container-div">
    <div class="col-sm-12">
        <div class="col-sm-6">
            <div class="deviceDiv tbborder">
                <div class="txtInfo bold" align="center">设备信息</div>
                <table class="deviceTb">
                	<tr>
                		<td>服务器ID</td>
                		<td id="serverInfoId">${device.server_info_id}</td>
                	</tr>
                	<tr>
                		<td>IP地址</td>
                		<td>${device.server_ip}</td>
                	</tr>
                	<tr>
                		<td>SNMP端口</td>
                		<td>${device.port}</td>
                	</tr>
                	<tr>
                		<td>SNMP版本</td>
                		<td>${device.version}</td>
                	</tr>
                	<tr>
                		<td>SNMP状态</td>
                		<td>
                			<c:if test="${device.state == true}">开启</c:if>
                			<c:if test="${device.state == false}">停用</c:if>
                		</td>
                	</tr>
                </table>
            </div>
        </div>
        
        <div class="col-sm-6">
            <div class="interfaceDiv tbborder">
                <div class="txtInfo bold" align="center">接口信息</div>
                <div class="dataDiv">
                	<table class="interfaceTb">
	                	<tr>
	                		<td class="bold">接口信息</td>
	                		<td class="bold">接收流量（M/s）</td>
	                		<td class="bold">发送流量（M/s）</td>
	                	</tr>
	                	<c:forEach items="${list}" var="entry">
	                		<tr>
		                		<td>${entry.netioname}</td>
		                		<td>
		                			<fmt:formatNumber value="${entry.ifinoctets/1024}" pattern="##.##" minFractionDigits="2"/>
		                		</td>
		                		<td>
		                			<fmt:formatNumber value="${entry.ifoutoctets/1024}" pattern="##.##" minFractionDigits="2"/>
		                		</td>
		                	</tr>
	                	</c:forEach>
	                </table>
                </div>
            </div>
        </div>
        
        <div class="col-sm-6" style="padding-left: 8px">
            <div class="chart-div">
                <div id="dataFlow" style="min-height:172px"></div>
            </div>
            
            <div class="chart-div" style="margin-top:10px;">
                <div id="dataPacket" style="min-height:172px"></div>
            </div>
        </div>
        
        <div class="col-sm-6" style="padding-left:8px; width:564px;">
            <div class="chart-div">
                <div id="dataCpu" style="min-height:172px;"></div>
            </div>
            
            <div class="chart-div" style="margin-top:10px;">
                <div id="dataMem" style="min-height:172px"></div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
