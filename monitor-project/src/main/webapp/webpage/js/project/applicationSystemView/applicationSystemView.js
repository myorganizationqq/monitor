$(document).ready(function () {
    $("#businessType").combobox({
        onChange : function(n, o) {
            var url='';
            if (n==1){
                url=basePath+'webpage/project/applicationSystemView/tradeFileRptChart.jsp';
            }else if(n==2){
                url=basePath+'webpage/project/applicationSystemView/stSysFlowCurrentChart.jsp';
            }else if(n==3){

            }
            $("#businessFrame").attr("src",url);
        }
    });
})
function businessViewChange() {
    var businessType=$("#businessType").val();
    var url='';
    if (businessType == 1){
        url=basePath+'webpage/project/applicationSystemView/tradeFileRptChart.jsp';
    }else if(businessType == 2){
        url=basePath+'webpage/project/applicationSystemView/stSysFlowCurrentChart.jsp';
    }else if(businessType == 3){

    }
    $("#businessFrame").attr("src",url);
}
