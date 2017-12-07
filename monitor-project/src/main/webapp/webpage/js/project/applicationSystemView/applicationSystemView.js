var beginTime=0;
var endTime=0;
$(document).ready(function () {
    $("#businessType").combobox({
        onChange : function(n, o) {
            var url = '';
            if (n == 1){
                $("#cmnc").hide();
                url = 'webpage/project/applicationSystemView/tradeFileRptChart.jsp';
            }else if(n == 2){
                $("#cmnc").hide();
                url = 'webpage/project/applicationSystemView/stSysFlowCurrentChart.jsp';
            }else if(n == 3){
                $("#cmnc").show();
                url = 'webpage/project/statisticReport/logRecvsendChart.jsp';
            }
            $("#businessFrame").attr("src", basePath + url);
        }
    });

    $("#communication").combobox({
        onChange : function(n, o) {
            if (n == 1) {
                $("#businessFrame").attr("src", basePath + 'webpage/project/statisticReport/logRecvsendChart.jsp');
            }else if (n == 2) {
                $("#businessFrame").attr("src", basePath + 'webpage/project/statisticReport/logFtpChart.jsp');
            }
        }
    });
    //jueryui的日期选择事件
    $("#beginDate").datebox({
        onSelect: function(date){
            var temp=date.getTime();
            if((endTime > 0) && (temp > endTime)){
                $.messager.alert('提示',"不能大于结束时间");
                $('#beginDate').datebox('setValue', '');
                return false;
            }
            beginTime=temp;
        }
    });
    $("#endDate").datebox({
        onSelect: function(date){
            var temp=date.getTime();
            if(temp < beginTime){
                $.messager.alert('提示',"不能小于开始时间");
                $('#endDate').datebox('setValue', '');
                return false;
            }
            endTime=temp;
        }
    });

    setYesterDay();
});

function setYesterDay(){
    var yesterdayStr=addDate(new Date().getTime(),-1);
    $('#beginDate').datebox('setValue', yesterdayStr);
    $('#endDate').datebox('setValue', yesterdayStr);
}

function getBeginDate(){
    var beginDate=$("#beginDate").datebox('getValue');
    if(beginDate == undefined || beginDate.toString().trim()==''){
        beginDate='';
    }
    return beginDate;
}

function getEndDate(){
    var endDate=$("#endDate").datebox('getValue');
    if(endDate == undefined || endDate.toString().trim()==''){
        endDate='';
    }
    return endDate;
}

function doSearch() {
    businessFrame.window.location.reload(true);
    /*var businessType=$("#businessType").combobox('getValue');
     if(businessType==1){
     tradeFileRptChartIframe.window.init();
     }else if(businessType==2){
     stSysFlowCurrentChartIframe.window.init();
     }*/
}