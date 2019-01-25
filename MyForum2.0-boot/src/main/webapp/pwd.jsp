<%@page import="org.ltq.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="label.jsp" %>
<%
User user = (User)session.getAttribute("user");
String systemPwd = user.getUser_pwd();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/mystyle.css">
<link rel="stylesheet" href="styles/labelStyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<style type="text/css">
#form{
	text-align: center;
	color: white;
	margin-top: 150px;
}
</style>
<title>修改密码</title>
</head>
<body>
	<div id="form">
	<!-- 修改密码输入栏 -->
	<form action="userController/changePasswordControl" onsubmit="return valid()">
		原密码：<input type="password" name="currentPwd" id="currentPwd" required="required" maxlength="20"><br/>
		输入新密码：<input type="password" name="pwd" required="required" maxlength="20"><br/>
		<input type="submit" value="确定">
	</form>
	<a href="userInfo.jsp"><button>返回</button></a>
	</div>
	
</body>
</html>