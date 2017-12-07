<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!-- 项目基本请求路径 -->
<input id="basePath" type="hidden" value="${requestScope.basePath }" />
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/common-validate.js"></script>