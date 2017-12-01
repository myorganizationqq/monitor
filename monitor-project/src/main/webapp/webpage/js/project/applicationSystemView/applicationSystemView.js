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
})
