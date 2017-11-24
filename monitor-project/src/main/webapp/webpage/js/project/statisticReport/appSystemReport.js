$(document).ready(function() {
	$("#businessType").combobox({
		onChange : function(n, o) {
			if (n == 3) {
				$("#cmnc").show();
			}
		}
	});
	
	$("#communication").combobox({
		onChange : function(n, o) {
			if (n == 1) {
				$("#businessFrame").attr("src", basePath + 'communication/logFtp.do?toPageList');
			}
		}
	});
})



