var typeArr=[];
var osTypeArr=[];
//默认执行查询方法
$(document).ready(function() {
    getTypeArr();
    doSearch();
});

//查询
function search(){

    //获取查询框的值
    var qParams={
        'type':$('#type').combobox('getValue'),
        'equipmentName':$('#equipmentName').val(),
        'equipmentVersion':$('#equipmentVersion').val()
    };
    //表单信息
    $("#getList").datagrid({
        pagination:true,	    //翻页工具栏
        pageNumber:1,           //默认显示第一页
        rownumbers:true,	    //行号
        //singleSelect:true,      //单选
        url:basePath+'project/network/networkEquipmentController.do?getPageList',  //数据源
        queryParams:qParams,//查询框内容
        fit:true,
        fitColumns:true,
        columns:[[//列
            {field:'id',checkbox:true,width:30,align:'center'},
            {title:'区域',field:'scope',width:80,align:'center'},
            {title:'设备名称',field:'equipmentName',width:100,align:'center'},
            {title:'设备型号',field:'equipmentVersion',width:100,align:'center'},
            {title:'ip',field:'ip',width:100,align:'center'},
            {title:'子网掩码',field:'subnetMark',width:100,align:'center'},
            {title:'网关',field:'gateway',width:80,align:'center',hidden:true},
            {title:'主机名',field:'hostName',width:100,align:'center'},
            {title:'说明',field:'remark',width:50,align:'center'},
            {title:'类型',field:'type',width:50,align:'center',formatter:typeFmt},
            {title:'操作系统',field:'osType',width:50,align:'center',formatter:osTypeFmt},
            /*{title:'cpu',field:'cpu',width:100,align:'center'},
            {title:'内存',field:'memory',width:20,align:'center'},*/
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
    $('#networkEquipment_search_form').form('clear');
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
        url: basePath + 'project/network/networkEquipmentController.do?toEdit&id=' + id,
        title: "网络设备",
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
        var userIdArr = new Array();
        for (var i = 0;i < rows.length;i++){
            var row = rows[i];
            userIdArr.push(row.id);
        }

        $.messager.confirm('系统提示', '您确定删除选中的所有数据吗？', function(flag) {
            if(flag){
                $.post(basePath + "project/network/networkEquipmentController.do?del", {idArr : userIdArr.toString()},function(data){
                    $.messager.alert('提示',unescape(data.msg));
                    if (data.resultCode == 0) {
                        $('#getList').datagrid("reload");
                    }
                },'json');
            }
        });
    }
}

function typeFmt(value,row){
    if(value==''){
       return '';
    }
    if (typeArr.length == 0){
        getTypeArr();
    }
    return fmt(typeArr,value);
}

function osTypeFmt(value,row){
    if(value==''){
        return '';
    }
    if (osTypeArr.length == 0){
        getOsTypeArr();
    }
    return fmt(osTypeArr,value);
}

//
function fmt(typeArr,value){
    var str = value;
    for(var i in typeArr){
        if(typeArr[i].id == value){
            str= typeArr[i].text;
            break;
        }
    }
    return str;
}

function getTypeArr() {
    $.ajax({
        type:"get",
        data: "json",
        url:basePath+"project/network/networkEquipmentController.do?getType",
        success:function (data) {
            typeArr=eval(data);
        }
    });
}

function getOsTypeArr() {
    $.ajax({
        type:"get",
        data: "json",
        url:basePath+"project/network/networkEquipmentController.do?getOsType",
        success:function (data) {
            osTypeArr=eval(data);
        }
    });
}