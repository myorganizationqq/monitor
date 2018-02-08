<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>网络设备编辑</title>
    <jsp:include page="../../manage/include.jsp"></jsp:include>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
    <script type="text/javascript"
            src="${requestScope.basePath}webpage/js/project/network/networkEquipmentEdit.js"></script>
</head>
<body>
<div class="tahoma_font  tdstyle"
     style="padding-top: 20px; padding-bottom: 20px; padding-left: 20px; overflow: hidden">
    <form id="form_networkEquipmentEdit" >
        <input id="id" name="id" value="${obj.id }" type="hidden"/>

        <table cellpadding="5" class="newAddOrg" width="680">

            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">区域</td>
                <td width="250">
                    <input id="scope" name="scope" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[255]'"
                           value="${obj.scope }"/>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">设备名称</td>
                <td width="250">
                    <input id="equipmentName" name="equipmentName" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[255]'"
                           value="${obj.equipmentName }"/>
                </td>
            </tr>

            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">设备型号</td>
                <td width="250">
                    <input id="equipmentVersion" name="equipmentVersion" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[255]'"
                           value="${obj.equipmentVersion }"/>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">ip地址</td>
                <td width="250">
                    <input id="ip" name="ip" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[255]'"
                           value="${obj.ip }"/>
                </td>
            </tr>

            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">子网掩码</td>
                <td width="250">
                    <input id="subnetMark" name="subnetMark" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[15]'"
                           value="${obj.subnetMark }"/>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">网关</td>
                <td width="250">
                    <input id="gateway" name="gateway" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[15]'"
                           value="${obj.gateway }"/>
                </td>
            </tr>

            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">主机名</td>
                <td width="250">
                    <input id="hostName" name="hostName" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[50]'"
                           value="${obj.hostName }"/>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">类型</td>
                <td width="250">
                    <select id="type_id" name="type" class="easyui-combobox" style="width:150px;"
                            data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false,
				        	url:'${requestScope.basePath }project/network/networkEquipmentController.do?getType',
				        	onLoadSuccess:function(data){
	        			        $('#type_id').combobox('setValue','${obj.type}');
	        	            }">

                    </select>
                </td>
            </tr>

            <%--<tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">cpu</td>
                <td width="250">
                    <input id="cpu" name="cpu" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[50]'"
                           value="${obj.cpu }"/>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">内存大小</td>
                <td width="250">
                    <input id="memory" name="memory" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox"
                           data-options="required:true,missingMessage:'请输入整数！',validType:'integer'"
                           value="${obj.memory }"/>
                </td>
            </tr>--%>
            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">备注说明</td>
                <td width="250">
                    <input id="remark" name="remark" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[255]'"
                           value="${obj.remark }" />
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">系统or厂商</td>
                <td width="250">
                    <select id="osType" name="osType" class="easyui-combobox" style="width:150px;"
                            data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false,
				        	url:'${requestScope.basePath }project/network/networkEquipmentController.do?getOsType',
				        	onLoadSuccess:function(data){
	        			        $('#osType').combobox('setValue','${obj.osType}');
	        	            }">

                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div align="right" style="padding-right: 50px;padding-top: 5px;">
                        <a href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;"
                           onclick="saveOrUpdate(0)">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
                    </div>
                </td>

            </tr>
        </table>
    </form>
</div>
</body>

</html>
