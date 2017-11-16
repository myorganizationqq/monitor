<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="/webpage/mz/model/commonn/easy-ui-config.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>show-demo</title>
</head>
<body>

	<h2>基本数据表格</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>数据表格使用标签创建，不需要JavaScript代码.</div>
	</div>
	<div style="margin: 10px 0;"></div>
	
    <table id="dg" title="用户" class="easyui-datagrid" style="width:500px;height:250px"  
        url="test.jsp"  
        toolbar="#toolbar"  rownumbers="true"  
        fitColumns="true" singleSelect="true">  
    <thead>  
        <tr>  
           <th field="firstname" width="50">姓</th>  
           <th field="lastname" width="50">名</th>  
           <th field="phone" width="50">电话</th>  
           <th field="email" width="50">邮件</th>  
       </tr>  
   </thead>  
    </table>  
</body>
</html>