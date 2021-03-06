var totalCountPie;
var fileCountPillar;
var handleCountPillar;
var wrongfulCountPillar;
var duplicateCountPillar;
var noPretreatmentCountPillar;
var faultTimePillar;
var fileTypeArr;
var wrongTypeMap={'fileCount':'TOP排名 文件数量','handleCount':'TOP排名 处理记录数','wrongfulCount':'TOP排名 不合法数量','duplicateCount':'TOP排名 重复数据数量','noPretreatmentCount':'TOP排名 无法预处理数据数量'};
$(window).ready(function () {
    init();
});

function init(){
    var flag=$("#flag").val();
    var beginDate=getBeginDate();
    var endDate=getEndDate();
    initTotalCountPie();
    if (1==flag){
        //饼图设值
        setTotalCountData(basePath+'project/tradeFileRpt/tradeFileRptController.do?getTotalCountData&beginDate='+beginDate+'&endDate='+endDate+'&flag=JYWJHSJ');

        var url=basePath+'project/tradeFileRpt/tradeFileRptController.do?getChartData&beginDate='+beginDate+'&endDate='+endDate+'&wrongType=';
        for (var key in wrongTypeMap){
            var chartPillar=initPillar(key+"Pillar",wrongTypeMap[key]);
            setChartData(url+key,chartPillar);
        }
    }else if (2==flag){
        //饼图设值
        setTotalCountData(basePath+'project/tradeFileRpt/tradeFileRptController.do?getTotalCountData&beginDate='+beginDate+'&endDate='+endDate+'&flag=JYZXT');

        var chartPillar=initPillar("faultTimePillar","TOP排名 故障时间百分比排名");
        //设数据
        var url=basePath+'project/stSysFlowCurrentDt/stSysFlowCurrentDtController.do?getFaultTimeData&beginDate='+beginDate+'&endDate='+endDate;
        setChartData(url,chartPillar);
    }

}

function initTotalCountPie(){
    totalCountPie = echarts.init(document.getElementById('totalCountPie'));
    option = {
        title:{
            text:"指标",
            left:"center",
            top:'45%',
            textStyle:{
                color:'#031f2d',
                fontSize:12,
                align:'center'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:[]
        },
        toolbox: {
            show : false,
            feature : {
                mark : {show: false},
                dataView : {show: false, readOnly: false},
                magicType : {
                    show: false,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'center',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: false}
            }
        },
        calculable : true,

        series : [
            {
                name:'',
                color:["#C0C0C0","#DC143C","#f9a643","#f6d368","#4caaf6","#69de9d","#c38eec","#945305","#006241","#f769a8","#fcacc8"],
                type:'pie',
                startAngle:330,
                minAngle:1,
                radius : ['40%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : true
                        },
                        labelLine : {
                            show : true
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            shadowColor: 'rgba(0, 0, 0, 0.5)',
                            textStyle : {
                                fontSize : '30',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:[
                    {value:0,name:"正常指标0个"},
                    {value:0,name:"异常指标0个"}
                     ]
            }
        ]
    };
    totalCountPie.setOption(option);
}


function setPieData(pieChart,title,seriesData){
    pieChart.setOption({ title:{text:title},series : [{data:seriesData}]   });
}

function setTotalCountData(url){
    $.ajax({
        type: "POST",
        url:url,
        dataType:'json',
        scriptCharset: 'utf-8',
        data: {},
        success: function (result) {
            var dataArr=new Array();
            var title="";
            for(var key in result){
                if(key  == '正常指标' || key == '异常指标'){
                    dataArr.push({value:result[key],name : key+":"+result[key]+"个"});
                }else if(key == '指标'){
                    title= "指标:"+result[key]+"个";
                }
            }
            if (dataArr.length < 2){
                return;
            }
            setPieData(totalCountPie,title,dataArr);
        }
    });
}

function setChartData(url,chartPillar){
    $.ajax({
        type: "POST",
        url: url,
        dataType:'json',
        scriptCharset: 'utf-8',
        data: {},
        success: function (result) {
           /* var map=JSON.parse(result);*/
            var map=result;
            var xArr=new Array();
            var yArr=new Array();
            for(var key in map)
            {
                xArr.push(key);
                yArr.push(map[key]);
            }
            setPillarData(chartPillar,xArr,yArr);
        }
    });
}


function getFileType() {
    $.ajax({
        type: "get",
        url: basePath+'project/tradeFileRpt/tradeFileRptCpntroller.do?getFileType',
        data: "json",
        success: function(msg){
            fileTypeArr = eval(msg);
        }
    });
}

function initPillar(chartsId,title){
// 基于准备好的dom，初始化echarts实例
    if('fileCountPillar'== chartsId){
        fileCountPillar = echarts.init(document.getElementById(chartsId));
    }else if('handleCountPillar' == chartsId){
        handleCountPillar = echarts.init(document.getElementById(chartsId));
    }else if('wrongfulCountPillar' == chartsId){
        wrongfulCountPillar = echarts.init(document.getElementById(chartsId));
    }else if('duplicateCountPillar' == chartsId){
        duplicateCountPillar = echarts.init(document.getElementById(chartsId));
    }else if('noPretreatmentCountPillar' == chartsId){
        noPretreatmentCountPillar = echarts.init(document.getElementById(chartsId));
    }else if('faultTimePillar'==chartsId){
        faultTimePillar= echarts.init(document.getElementById(chartsId));
    }
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: title,
            textStyle:{
                color:"#242424",
                textAlign:"left",
                fontSize:12,
                fontWeight:'bold'
            },
            left:'center',
        },
        tooltip: {},


        xAxis: {
            data: [],
            nameTextStyle:{
                color:'#779796'
            },
            axisLabel :{
                interval:0,
                rotate: 40,
               /* margin : 2,*/
                textStyle :{
                    fontSize:12,
                    align:'center',
                    baseline:'top',
                    fontWeight:'lighter'
                }
            },
            /*nameGap:'10',*/
            axisLine:{
                lineStyle:{
                    color:'#858585'
                }
            },
            axisTick:{
                show:false
            },

            splitLine:{
                show:true,
                lineStyle:{
                    color:"#ececec"
                }
            }
        },
        yAxis: {
            name:'',
            // data: ["0","200","400","600","800","1000"],
            nameTextStyle:{
                color:'#86909c',
            },
            axisLine:{
                lineStyle:{
                    color:'#858585'
                }
            },
            axisTick:{
                show:false
            },
            splitLine:{
                show:true,
                lineStyle:{
                    color:"#ececec"
                }
            }
        },
        series: [{
            type: 'bar',
            data: [0],
            label:{
                normal:{
                    show:true,
                    position:"top"
                }
            },
            itemStyle:{
                normal:{
                    color:"#00A06B",
                    borderColor:'#4ca9f6',
                    borderWidth:'1'
                }
            },
            barWidth:'50%'
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    if('fileCountPillar'== chartsId){
        fileCountPillar.setOption(option);
        return fileCountPillar;
    }else if('handleCountPillar' == chartsId){
        handleCountPillar.setOption(option);
        return handleCountPillar;
    }else if('wrongfulCountPillar' == chartsId){
        wrongfulCountPillar.setOption(option);
        return wrongfulCountPillar;
    }else if('duplicateCountPillar' == chartsId){
        duplicateCountPillar.setOption(option);
        return duplicateCountPillar;
    }else if('noPretreatmentCountPillar' == chartsId){
        noPretreatmentCountPillar.setOption(option);
        return noPretreatmentCountPillar;
    }else if('faultTimePillar'==chartsId){
        faultTimePillar.setOption(option);
        return faultTimePillar;
    }
}

function setPillarData(pillarChart,xArr,dataArr){
    pillarChart.setOption({xAxis: {data: xArr}, series : [{data:dataArr}] });
}

function getBeginDate(){
  return window.parent.getBeginDate();
}

function getEndDate(){
    return window.parent.getEndDate();
}
