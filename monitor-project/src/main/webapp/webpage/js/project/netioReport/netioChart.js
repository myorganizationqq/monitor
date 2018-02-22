$(document).ready(function() {
	getData();
});

function getData() {
	$.ajax({
		type : "POST",
		url : basePath + 'netio/getChartData.do',
		dataType : 'json',
	    scriptCharset : 'utf-8',
		data : {
		},
		success : function(result) {
			setDataFlow(result);
			setDataPacket(result);
		}
	});
}

function setDataFlow(result) {
	var dataFlowOption = {
		title : {
			text : '数据流量',
			subtext : '（kbps）'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '网络发送速率', '网络接收速率' ],
			y : 'bottom'
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : result.dateArr
		} ],
		yAxis : [ {
			type : 'value',
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [ {
			name : '网络发送速率',
			type : 'line',
			data : result.ifinoctetsArr,
		}, {
			name : '网络接收速率',
			type : 'line',
			data : result.ifoutoctetsArr,
		} ]
	};
	var dataFlow = echarts.init(document.getElementById('dataFlow'));
	dataFlow.setOption(dataFlowOption);
}

function setDataPacket(result) {
	var dataPacketOption = {
		title : {
			text : '数据包',
			subtext : '（个/s）'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '发送数', '接收数' ],
			y : 'bottom'
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : result.dateArr
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '发送数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : result.ifoutucastpktsArr
		}, {
			name : '接收数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : result.ifinucastpktsArr
		} ]
	};
	var dataPacket = echarts.init(document.getElementById('dataPacket'));
	dataPacket.setOption(dataPacketOption);
}
