var fileTypeArr=[];
var statArr=[];
var beginTime=0;
var endTime=0;
//默认执行查询方法
$(document).ready(function() {
    getFileType();
    initDate();
    //doSearch();
});

function initDate(){
    //日期控制扩展选择日期小于等于当前日期，开始日期小于等于结束日期
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
}

//查询
function search(){

    //获取查询框的值
    var qParams={
        'fileType':$('#fileType').combobox('getValue'),
        'beginDate':$("#beginDate").datebox('getValue'),
        'endDate':$("#endDate").datebox('getValue')
    };
    //表单信息
    $("#getList").datagrid({
        pagination:true,	    //翻页工具栏
        pageNumber:1,           //默认显示第一页
        rownumbers:true,	    //行号
        //singleSelect:true,      //单选
        url:basePath+'project/tradeFileRpt/tradeFileRptController.do?getPageList',  //数据源
        queryParams:qParams,//查询框内容
        fit:true,
        fitColumns:true,
        columns:[[//列
            {field:'id',checkbox:true,width:30,align:'center',hidden:true},
            {title:'流水号',field:'balanceWaterNo',width:80,align:'center'},
            {title:'状态',field:'stat',width:100,align:'center',formatter:statFmt},
            {title:'文件类型',field:'fileType',width:100,align:'center',formatter:fileTypeFmt},
            {title:'文件数量',field:'fileCount',width:100,align:'center'},
            {title:'处理数量',field:'handleCount',width:100,align:'center'},
            {title:'不合法数据',field:'wrongfulCount',width:100,align:'center'},
            {title:'重复数据',field:'duplicateCount',width:100,align:'center'},
            {title:'无法预处理数据',field:'noPretreatmentCount',width:100,align:'center'},
            {title:'告警次数',field:'alarmCount',width:100,align:'center'}
        ]],
        onLoadSuccess: function(data){

        },
        onLoadError:function(data){
            if(data){

            }
        }
    });
}


//点击查询按钮
function doSearch(){
    search();
}

//清空
function clean(){
    $('#tradeFileRpt_search_form').form('clear');
    //$("#orgId").combotree("setValue",$("#h_orgId").val());
}

function statFmt(value, row) {
    var str="";
    if (str == '0'){
        str="未开始";
    }else if(str == '1'){
        str="进行中";
    }else{
        str="已完成";
    }
    return str;
}
function fileTypeFmt(value, row) {
    return getFmt(fileTypeArr,value);
}

function getFileType() {
    $.ajax({
        type: "get",
        url: basePath+'project/tradeFileRpt/tradeFileRptController.do?getFileType',
        data: "json",
        success: function(msg){
            fileTypeArr = eval(msg);
        }
    });
}
/**
 * 返回value对应的值
 */
function getFmt(fileTypeArr,value) {
    var str ="";
    for(var i=0;i<fileTypeArr.length;i++){
        if(fileTypeArr[i].id == value){
            str=fileTypeArr[i].text;
            break;
        }
    }
    return str;
}







