<%@page import="org.ltq.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/label.jsp" %>
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
<title>我的帖子</title>
</head>
<body>
<br/><br/>
	<div class="content_1">
		<div class="content_2">
			<hr>
			<c:forEach items="${requestScope.posts }" var="post">
				<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${post.post_id}" style="font-size: 23px;color:black;text-decoration:none;" target="_blank">${post.post_title }</a><br/>
				<p>
				<img alt=" " src="/upload/${post.user_account}.jpg" width="30" height="30">${post.user_name }--${post.post_time }
				--阅读量：${post.post_viewNum }--点赞：${post.post_likeNum }--收藏：${post.post_subscribeNum }--分区：${post.post_type }
				</p>
				<hr>
			</c:forEach>
			
			<div style="text-align: center;">
			
				<c:if test="${sessionScope.currentPage > 1 }">
					<a href="<%=basePath%>userController/queryUserPosts/${requestScope.user_account }/1">首页</a>--
					<a href="<%=basePath%>userController/queryUserPosts/${requestScope.user_account }/${sessionScope.currentPage-1 }">上一页</a>--
				</c:if>
				<a href="<%=basePath%>userController/queryUserPosts/${requestScope.user_account }/${sessionScope.currentPage+1 }">下一页</a>
				
			</div>
			
			<br/><br/><br/><br/>
			
		</div>
	</div>
</body>
</html>