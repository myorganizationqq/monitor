<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<head>
<title>Basic Panel - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"	href="<%=path%>/webpage/mz/js/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"	href="<%=path%>/webpage/mz/js/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"	href="<%=path%>/webpage/mz/js/jquery-easyui/demo/demo.css">
<script type="text/javascript"	src="<%=path%>/webpage/mz/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript"	src="<%=path%>/webpage/mz/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="<%=path%>/webpage/mz/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body> 
</body>  
</html>  
