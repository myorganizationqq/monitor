var fileTypeArr=[];
var statArr=[];
//默认执行查询方法
$(document).ready(function() {
    /*if($("#h_orgId").val() == ""){
     search();
     }*/
    getFileType();
});

//查询
function search(){

    //获取查询框的值
    var qParams={
        'fileType':$('#fileType').combobox('getValue')
    };
    //表单信息
    $("#getList").datagrid({
        pagination:true,	    //翻页工具栏
        pageNumber:1,           //默认显示第一页
        rownumbers:true,	    //行号
        //singleSelect:true,      //单选
        url:basePath+'project/tradeFileRpt/tradeFileRptCpntroller.do?getPageList',  //数据源
        queryParams:qParams,//查询框内容
        fit:true,
        fitColumns:true,
        columns:[[//列
            {field:'id',checkbox:true,width:30,align:'center'},
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
        url: basePath+'project/tradeFileRpt/tradeFileRptCpntroller.do?getFileType',
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







