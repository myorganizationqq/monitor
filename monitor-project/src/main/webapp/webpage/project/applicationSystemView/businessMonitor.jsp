<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../../manage/header.jsp"></jsp:include>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/manage/amsWindow.js"></script>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/public.js"></script>
    <script type="text/javascript" src="${requestScope.basePath}webpage/js/project/applicationSystemView/applicationSystemView.js"></script>
</head>
<body>

<div style="width: 99%; padding-top: 10px; padding-left: 10px">
    <!--input 表格模块-->
    <div class="tahoma_font tableStyle_5_0">

        <form id="businessMonitor_search_form">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <td width="160px">
                        <select id="businessType" data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false" style="width:150px;">
                            <option value="1" selected="selected">交易文件和数据</option>
                            <option value="2">清算子系统运行状态</option>
                            <option value="3">通信业务</option>
                        </select>
                     </td>
                    <td width="160px" style="display:none" id="cmnc">
	                        <select id="communication" data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false" style="width:150px;">
	                            <option value="1">消息收发</option>
	                            <option value="2">FTP文件</option>
	                        </select>
                    </td>
                    <td width="320px" id="dateSelectDiv">
                            <input id="beginDate" type="text" class="easyui-datebox" currentText="Today" required="required" style="width: 110px" />
                            -
                            <input id="endDate" type="text" class="easyui-datebox" currentText="Today" required="required" style="width: 110px" />
                   </td>
                   <td align="right">
                            <a id="searchBtn" href="javascript:void(0);" onclick="doSearch();" class="easyui-linkbutton" style="padding: 0 10px;margin-right: 20px">刷新</a>
                   </td>

                </tr>

            </table>
        </form>
    </div>

    <!--上下距间-->
    <div class="easyui-layout" style="height: 10px"></div>
    <div class="line"></div>
    <!--gap-->
    <div class="easyui-layout" style="padding: 5px;min-height: 700px">
        <iframe id="businessFrame" name="businessFrame" width='100%' height="600px" frameBorder='no' scrolling="auto"  src='${requestScope.basePath}webpage/project/applicationSystemView/tradeFileRptChart.jsp'></iframe>
    </div>
</div>
</body>
</html>