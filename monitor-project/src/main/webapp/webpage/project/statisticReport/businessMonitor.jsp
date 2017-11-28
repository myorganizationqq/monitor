<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../../manage/header.jsp"></jsp:include>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/manage/amsWindow.js"></script>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/public.js"></script>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/project/statisticReport/appSystemReport.js"></script>
	<style type="text/css">
		.warn {
			width: 95%;
			padding: 8px;
			position: absolute;
			margin: -90px 10px;
			background: #444C63;
			color: #FFF;
			font-size: 16px;
		}
	</style>
</head>
<body>

<div style="width: 99%; padding-top: 10px; padding-left: 10px" id="centerMain">
    <!--input 表格模块-->
    <div class="tahoma_font tableStyle_5_0">

        <form id="businessMonitor_search_form">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="160">
                        <select id="businessType" style="width:150px;">
                            <option value="1">交易文件和数据</option>
                            <option value="2">清算子系统运行状态</option>
                            <option value="3">通信业务</option>
                        </select>
                        
                        <div style="display:none; margin:-22px 0 0 180px;" id="cmnc">
	                        <select id="communication" style="width:150px;">
	                            <option value="1">消息收发</option>
	                            <option value="2">FTP文件</option>
	                        </select>
                        </div>
                    </td>
                    
                </tr>

            </table>
        </form>
    </div>

    <!--上下距间-->
    <div class="easyui-layout" style="height: 10px"></div>
    <div class="line"></div>

    <div class="easyui-layout" style="height: 616px;">
        <iframe id="businessFrame" width="99%" height="100%" frameBorder="no" scrolling="no" onLoad="" src="${requestScope.basePath}project/tradeFileRpt/tradeFileRptController.do?toPageList"></iframe>
    </div>
    
	<div class="warn">告警统计</div>
    
</div>
</body>
</html>