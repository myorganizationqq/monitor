//默认执行查询方法
$(document).ready(function() {
    doSearch();
});

//查询
function search(){

    //获取查询框的值
    var qParams={
        'state':$('#state').combobox('getValue')
    };
    //表单信息
    $("#getList").datagrid({
        pagination:true,	    //翻页工具栏
        pageNumber:1,           //默认显示第一页
        rownumbers:true,	    //行号
        //singleSelect:true,      //单选
        url:basePath+'project/network/snmpInfoController.do?getPageList',  //数据源
        queryParams:qParams,//查询框内容
        fit:true,
        fitColumns:true,
        columns:[[//列
            {field:'id',checkbox:true,width:30,align:'center'},
            {title:'服务器ID',field:'serverInfoId',width:80,align:'center',hidder:true},
            {title:'服务器IP',field:'serverIp',width:80,align:'center'},
            {title:'snmp端口',field:'port',width:50,align:'center'},
            {title:'snmp版本',field:'version',width:100,align:'center'},
            {title:'认证用户',field:'user',width:100,align:'center'},
            {title:'密码',field:'passwd',width:80,align:'center',hidden:true},
            {title:'认证协议',field:'authVersion',width:100,align:'center'},
            {title:'状态',field:'state',width:50,align:'center',formatter:stateFmt},
            {title:'创建时间',field:'createDate',width:100,align:'center',formatter:dateFmt},
            {
                title: '操作', field: 'operation', width: 50, align: 'center',
                formatter: function (value, row) {
                    var id = row.id;
                    var d = ' <a href="#"  onclick="toEditPage(\'' + id + '\')">修改</a> ';
                    return d;
                }
            }
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
    $('#snmpInfo_search_form').form('clear');
    //$("#orgId").combotree("setValue",$("#h_orgId").val());
}


function dateFmt(value, row){
    var dateStr="";
    if (value != null && value != undefined){
        dateStr= new Date(value.time).pattern("yyyy-MM-dd HH:mm:ss");
    }
    return dateStr;
}

function toEditPage(id) {

    $(this).amsWindow({
        container: 'editWindow',
        type: 'add',
        url: basePath + 'project/network/snmpInfoController.do?toEdit&id=' + id,
        title: "snmp详情",
        width: 700,
        height: 550,
        top: '10%'
    });
}

function delData() {
    var rows = $("#getList").datagrid("getSelections");
    if (rows.length < 1) {
        $.messager.alert("提示", "至少选择一条数据操作！");
    } else {
        var snmpInfoId = new Array();
        for (var i = 0;i < rows.length;i++){
            var row = rows[i];
            snmpInfoId.push(row.id);
        }

        $.messager.confirm('系统提示', '您确定删除选中的所有数据吗？', function(flag) {
            if(flag){
                $.post(basePath + "project/network/snmpInfoController.do?del", {idArr : snmpInfoId.toString()},function(data){
                    $.messager.alert('提示',unescape(data.msg));
                    if (data.resultCode == 0) {
                        $('#getList').datagrid("reload");
                    }
                },'json');
            }
        });
    }
}

function stateFmt(value,row){
    if(value=='1'){
       return '启用';
    }
    if (value == '0'){
        return '停用';
    }
}
