<%@page import="org.ltq.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/label.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath%>styles/mystyle.css">
<title>发帖</title>
</head>
<body>
	<div class="box">
		<div class="content">
				
			<form action="<%=basePath %>postController/addPostControl/?post_type=${sessionScope.post_type}" enctype="multipart/form-data" method="post">
				<h1>标题</h1>
				<input name="post_title" size="100" required="required">
				<br/>
				<br/>
				<hr>
				<h1>内容</h1>
				<textarea name="post_content" rows="30" cols="100"></textarea>
				<br/>
				<br/>
				<hr>
				<br>
				<input type="submit" value="发布">
				&nbsp;&nbsp;&nbsp;选择图片（可选多张，小于4MB）：&nbsp;<input type="file" name="post_image" multiple="multiple">
			</form>
			<br/><br/><br/>
		</div>
	</div>

</body>
</html>