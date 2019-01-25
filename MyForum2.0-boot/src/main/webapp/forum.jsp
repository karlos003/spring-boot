<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="label.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/mystyle.css">
<link rel="stylesheet" href="styles/labelStyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<title>论坛</title>
<style type="text/css">
#JavaArea{
	margin-left: 400px;margin-top: 70px;
}
</style>
</head>
<body>
	
	<a id="JavaArea" href="<%=basePath%>postController/queryPostByType?currentPage=1&post_type=java" target="_blank">
		<img alt="Java区..." src="<%=basePath%>images/JavaAreaLogo.png" width="300" height="200">
	</a>
	<div class="content_1">
		<div class="content_2">
			
			
		</div>
	</div>	
	
</body>
</html>