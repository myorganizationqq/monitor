$(document).ready(function () {
    businessViewChange();
    $("#businessType").change(businessViewChange);
})
function businessViewChange() {
    var businessType=$("#businessType").val();
    var url='';
    if (1==businessType){
        url=basePath+'webpage/project/applicationSystemView/tradeFileRptChart.jsp';
    }else if(2==businessType){
        url=basePath+'webpage/project/applicationSystemView/stSysFlowCurrentChart.jsp';
    }else if(3==businessType){

    }
    alert(11111);
    $("#businessFrame").attr("src",url);
}
