
//默认执行查询方法
$(document).ready(function() {

});

function dateFmt(value, row){
    var dateStr="";
    if (value != null && value != undefined){
        dateStr= new Date(value.time).pattern("yyyy-MM-dd HH:mm:ss");
    }
    return dateStr;
}

/*function toEdit(id) {

    $(this).amsWindow({
        container: 'editWindow',
        type: 'add',
        url: basePath + 'project/network/networkEquipmentController.do?toEdit&id=' + id,
        title: "网络设备",
        width: 700,
        height: 550,
        top: '10%'
    });
}*/

//保存
function saveOrUpdate(step){
    //表单验证方法
    var isValid = $("#form_networkEquipmentEdit").form("validate");
    if (isValid){
        //进行录入操作的后台交互
        $.ajax({
            url:basePath+'project/network/networkEquipmentController.do?saveOrUpdate',
            type:"POST",
            dataType:"json",
            data:$("#form_networkEquipmentEdit").serialize(),
            success: function(d){
                if(d.resultCode==0){
                    $.messager.alert("提示",d.msg);
                    closeWin();
                   $("#getList").datagrid("reload");
                } else if(d.resultCode == 1){
                    $.messager.alert("提示",d.msg);
                }
            }
        });
    }
}

//关闭
function closeWin(){

    $('#editWindow').window('close');

}


/*$("#form_networkEquipmentEdit").form({
 success: function(d){
 if(d.resultCode==0){
 $.messager.alert("提示",d.msg);
 closeWin();

 // $("#getList").datagrid("reload");
 } else if(d.resultCode == 1){
 $.messager.alert("提示",d.msg);
 }
 }
 });*/




