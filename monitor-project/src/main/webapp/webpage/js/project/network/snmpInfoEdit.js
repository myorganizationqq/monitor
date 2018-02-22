var serverIpVal=$("#serverIpVal").val();
//默认执行查询方法
$(document).ready(function() {
    $("#serverInfoId").combobox({
        onChange : function(n, o) {
            $.ajax({
                url:basePath+'project/network/snmpInfoController.do?getIpByServerInfoIds',
                type:"POST",
                data:{"serverInfoId":n},
                success: function(list){
                    var length=list.length;
                    if(length >0){
                        var data=[];
                        var flag=false;
                        for (var i in list){
                            var ip=list[i];
                            //如果后台有值匹配上就选择后台值,如果没有匹配上就默认最后一个值
                            if(ip == serverIpVal ||(i == length-1 && !flag)){
                                data.push({"id":ip,"text":ip,"selected":true});
                                flag=true;
                            }else {
                                data.push({"id":ip,"text":ip});
                            }
                        }

                        $("#serverIp").combobox("loadData",data);
                    }
                },
                error:function(err){
                      alert("此功能暂不可用,请联系管理员");
                }
            });
        }
    });
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
    var isValid = $("#form_snmpInfoEdit").form("validate");
    if (isValid){
        //进行录入操作的后台交互
        $.ajax({
            url:basePath+'project/network/snmpInfoController.do?saveOrUpdate',
            type:"POST",
            dataType:"json",
            data:$("#form_snmpInfoEdit").serialize(),
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





