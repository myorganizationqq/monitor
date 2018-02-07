<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>snmp详情编辑</title>
    <jsp:include page="../../manage/include.jsp"></jsp:include>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
    <script type="text/javascript"
            src="${requestScope.basePath}webpage/js/project/network/snmpInfoEdit.js"></script>
</head>
<body>
<div class="tahoma_font  tdstyle"
     style="padding-top: 20px; padding-bottom: 20px; padding-left: 20px; overflow: hidden">
    <form id="form_snmpInfoEdit" >
        <input id="id" name="id" value="${obj.id }" type="hidden"/>
        <input id="serverIpVal" type="hidden" value="${obj.serverIp}"/>
        <input id="stateVal" type="hidden" value="${obj.state}"/>
        <table cellpadding="5" class="newAddOrg" width="680">

            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">服务器</td>
                <td width="250">
                    <select id="serverInfoId" name="serverInfoId" type="text" style="height:25px;width:150px;"
                           class="easyui-combobox" data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false,
				        	url:'${requestScope.basePath }project/network/snmpInfoController.do?getServerInfoIds',
				        	onLoadSuccess:function(data){
	        			        $('#serverInfoId').combobox('setValue','${obj.serverInfoId}');
	        	            }">
                    </select>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">服务器IP</td>
                <td width="250">
                    <select id="serverIp" name="serverIp" type="text" style="height:25px;width:150px;"
                           class="easyui-combobox" data-options="valueField:'id', textField:'text', required:true,validType:'maxLength[255]',panelHeight:'auto',editable:false">

                    </select>
                </td>
            </tr>

            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">snmp端口</td>
                <td width="250">
                    <input id="port" name="port" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:true,validType:'maxLength[255]'"
                           value="${obj.port }"/>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">snmp版本</td>
                <td width="250">
                    <select id="version" name="version" type="text" style="height:25px;width:150px;"
                           class="easyui-combobox" data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false,
				        	url:'${requestScope.basePath }project/network/snmpInfoController.do?getVersions',
				        	onLoadSuccess:function(data){
	        			        $('#version').combobox('setValue','${obj.version}');
	        	            }">
                    </select>
                </td>
            </tr>

            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">认证用户</td>
                <td width="250">
                    <input id="user" name="user" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[15]'"
                           value="${obj.user }"/>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">密码</td>
                <td width="250">
                    <input id="passwd" name="passwd" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[15]'"
                           value="${obj.passwd }"/>
                </td>
            </tr>

            <tr>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">认证协议</td>
                <td width="250">
                    <input id="authVersion" name="authVersion" type="text" style="height:25px;width:150px;"
                           class="easyui-textbox" data-options="required:false,validType:'maxLength[15]'"
                           value="${obj.authVersion }"/>
                </td>
                <td width="150" align="center" bgcolor="#f1f2f3" class="p_right10">状态</td>
                <td width="250">
                    <select id="state_id" name="state" class="easyui-combobox" style="width:150px;"
                            data-options="valueField:'id',textField:'text',panelHeight:'auto',editable:false,mode:'local',
                            data: [
                            {text: '启用',id: '1'},
                            {text: '停用',id: '0'}
                            ],onLoadSuccess:function(data1){
                                $('#state_id').combobox('setValue','${obj.state}');
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
