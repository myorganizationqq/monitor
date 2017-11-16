<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript"> 
	window.parent.location.href="${requestScope.basePath }manage/system/loginController.do?login";
</script>
</head>