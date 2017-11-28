var finishFlagArr=[];
//默认执行查询方法
$(document).ready(function() {
    getFinishFlag();
    doSearch();
});

//查询
function search(){

    //获取查询框的值
    var qParams={
        'finishFlag':$('#finishFlag').combobox('getValue')
    };
    //表单信息
    $("#getList").datagrid({
        pagination:true,	    //翻页工具栏
        pageNumber:1,           //默认显示第一页
        rownumbers:true,	    //行号
        //singleSelect:true,      //单选
        url:basePath+'project/stSysFlowCurrentDt/stSysFlowCurrentDtController.do?getPageList',  //数据源
        queryParams:qParams,//查询框内容
        fit:true,
        fitColumns:true,
        columns:[[//列
            {field:'id',checkbox:true,width:30,align:'center',hidden:true},
            {title:'清算流程',field:'step',width:80,align:'center',hidden:true},
            {title:'清算流程',field:'remark',width:100,align:'center'},
            {title:'状态',field:'stat',width:50,align:'center'},
            {title:'上个步骤',field:'stepPre',width:100,align:'center'},
            {title:'完成标志',field:'finishFlag',width:100,align:'center',formatter:finishFlagFmt},
            {title:'开始时间',field:'beginTime',width:100,align:'center'},
            {title:'结束时间',field:'endTime',width:100,align:'center'},
            {title:'告警',field:'alarm',width:100,align:'center'}
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
    $('#stSysFlowCurrentDt_search_form').form('clear');
    //$("#orgId").combotree("setValue",$("#h_orgId").val());
}

function finishFlagFmt(value, row) {
    var str ="";
    for(var i=0;i<finishFlagArr.length;i++){
        if(finishFlagArr[i].id == value){
            str=finishFlagArr[i].text;
            break;
        }
    }
    return str;
}

function getFinishFlag() {
    $.ajax({
        type: "get",
        url: basePath+'project/stSysFlowCurrentDt/stSysFlowCurrentDtController.do?getFinishFlag',
        data: "json",
        success: function(msg){
            finishFlagArr = eval(msg);
        }
    });
}





