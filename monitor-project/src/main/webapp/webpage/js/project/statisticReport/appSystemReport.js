$(document).ready(function() {
	$("#businessType").combobox({
		onChange : function(n, o) {
			if(n == 1){
				$("#cmnc").hide();
				$("#businessFrame").attr("src", basePath + 'project/tradeFileRpt/tradeFileRptController.do?toPageList');
			}else if(n == 2){
				$("#cmnc").hide();
				$("#businessFrame").attr("src", basePath + 'project/stSysFlowCurrentDt/stSysFlowCurrentDtController.do?toPageList');
			}else if (n == 3) {
				$("#cmnc").show();
				$("#businessFrame").attr("src", basePath + 'communication/logRecvsend.do?toPageList');
			}
		}
	});

	$("#communication").combobox({
		onChange : function(n, o) {
			if (n == 1) {
				$("#businessFrame").attr("src", basePath + 'communication/logRecvsend.do?toPageList');
			}else if (n == 2) {
				$("#businessFrame").attr("src", basePath + 'communication/logFtp.do?toPageList');
			}
		}
	});
})



