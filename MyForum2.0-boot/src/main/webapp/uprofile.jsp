<%@page import="org.ltq.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="label.jsp" %>
<%
User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath%>styles/mystyle.css">
<link rel="stylesheet" href="<%=basePath%>styles/labelStyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<title>${requestScope.usr.user_name }的个人空间</title>
</head>
<body>
<div class="content_1">
		<div class="content_2">
			<font style="font-size: 40px;">${requestScope.usr.user_name }</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<c:if test="${sessionScope.user.user_account != requestScope.usr.user_account }">
				<a href="<%=basePath%>userController/addOrRmSub/${sessionScope.user.user_account }/${requestScope.usr.user_account}" onclick="return loginCheck()">关注</a>
			</c:if>
			<br/>
			<c:if test="${requestScope.usr.user_photo==0}">
				<img src="<%=basePath%>images/default_photo.jpg" width="300" height="300">
			</c:if>
			
			<c:if test="${requestScope.usr.user_photo!=0}">
				<img src="<%=basePath%>u${requestScope.usr.user_account}.jpg" width="300" height="300">
			</c:if>
			
			<hr>
			
			<h1>Ta的关注</h1>
			<a href="<%=basePath%>sub/${requestScope.usr.user_account }/1" target="_blank">关注的人</a>
			
			<hr>
			
			<h1>Ta的收藏</h1>
			<a href="<%=basePath%>userController/showSubsPost/${requestScope.usr.user_account }/1" target="_blank">收藏的帖子</a>
			
			<hr>
			
			<h1>Ta的发布</h1>
			<a href="<%=basePath%>userController/queryUserPosts/${requestScope.usr.user_account }/1" target="_blank">发布的帖子</a>
			
			<br/><br/><br/><br/><br/><br/>
		</div>
	</div>
	
	<script type="text/javascript">
		function loginCheck(){
			if(<%=user%>==null){
				alert("请登录！");
				return false;	
			}
		}
	</script>
</body>
</html>