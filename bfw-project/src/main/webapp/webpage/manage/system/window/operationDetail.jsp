<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript">
function saveOrUpdateOperation(){
	var isValid = $("#form_operation").form("validate");
	if (isValid){
		$.ajax({
	        url:basePath+'manage/system/functionManagerController.do?saveOperation',
	        type:"POST",
	        dataType:"json",
	        data:$("#form_operation").serialize(),
	        success: function(d){
	        	if(d.resultCode==0){
	        		$.messager.alert("提示",d.msg);
	        		closeWin();				
	        		init();
	        	}          
	        }
	    });	
	}
}

function closeWin(){
	$('#addOperation').window('close');	
}
</script>

<div class="tahoma_font  tdstyle"  style="padding-top:20px; padding-bottom:20px; padding-left:20px;">
  <form id="form_operation" method="post">
  	<input id="id" name="id" value="${commonOper.id }" type="hidden"/>
  	<input id="functionId" name="functionId" value="${functionId }" type="hidden"/>
    <table cellpadding="5" class="newAddOrg" width="680">
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>操作名称：</td>
        <td width="300" >
        	<input id="operateName" name="operateName" type="text" style="height:25px;width:200px;"
        	 class="easyui-textbox" data-options="required:true,missingMessage:'请输入菜单名称！',validType:'maxLength[15]'"
        	 value="${commonOper.operateName }" />        	
        </td>
      </tr>
       <tr>
        <td width="110" align="right" bgcolor="#f1f2f3" class="p_right10"><span class="text_required">*</span>操作代码：</td>
        <td width="300" >
        	<input id="operateCode" name="operateCode" type="text" style="height:25px;width:200px;"
        	 class="easyui-numberbox" data-options="required:true,missingMessage:'请输入菜单顺序！',min:0,max:999,precision:0"
        	 value="${commonOper.operateCode }" />
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <div align="right" style="padding-right: 50px;padding-top: 5px;" > 
          	<a id="saveOperation" href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" onclick="saveOrUpdateOperation();">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
          	&nbsp;&nbsp;
          	<a id="closeOperation"  href="javascript:void(0)" class="easyui-linkbutton"  style="width:50px;" onclick="closeWin();">&nbsp;&nbsp;关闭&nbsp;&nbsp;</a> 
          </div>
      	</td>
      </tr>
    </table>    
  </form>
</div>