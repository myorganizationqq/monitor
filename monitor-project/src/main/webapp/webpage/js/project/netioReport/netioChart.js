$(document).ready(function() {
	getData();
});

function getData() {
	$.ajax({
		type : 'POST',
		url : basePath + 'netio/getChartData.do?serverInfoId=' + $('#serverInfoId').text(),
		dataType : 'json',
	    scriptCharset : 'utf-8',
		data : {
		},
		success : function(result) {
			console.log(result);
			setDataFlow(result);
			setDataPacket(result);
			setDataCpu(result);
			setDataMem(result);
		}
	});
}

function setDataFlow(result) {
	var dataFlowOption = {
		title : {
			text : '数据流量',
			subtext : '（M）'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '流出数据', '流入数据' ],
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
			name : '流出数据',
			type : 'line',
			data : result.ifinoctetsArr,
		}, {
			name : '流入数据',
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

function setDataCpu(result) {
	var dataCpuOption = {
		title : {
			text : 'cpu使用率',
			subtext : '（个/s）'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '使用率' ],
			y : 'bottom'
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : result.cpuData.dateArr
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '使用率',
			type : 'line',
			data : result.cpuData.systemArr
		}]
	};
	var dataCpu = echarts.init(document.getElementById('dataCpu'));
	dataCpu.setOption(dataCpuOption);
}

function setDataMem(result) {
	var dataMemOption = {
		title : {
			text : '内存使用率',
			subtext : '（个/s）'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '使用率' ],
			y : 'bottom'
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : result.memData.dateArr
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '使用率',
			type : 'line',
			data : result.memData.userageRateArr
		}]
	};
	var dataMem = echarts.init(document.getElementById('dataMem'));
	dataMem.setOption(dataMemOption);
}
