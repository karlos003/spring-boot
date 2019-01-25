<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath %>styles/mystyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<style type="text/css">
a {
	color: white;
}
#requestResult{
	color: white;text-align: center;margin-top: 100px;
}
</style>
<title>结果</title>
</head>
<body>
	<div id="requestResult">
		<c:if test="${requestScope.resultType==1}">
			<h1>登录成功！欢迎</h1>
			<a href="<%=basePath %>index">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==2}">
			<h1>注册成功！欢迎</h1><br/>
			<a href="<%=basePath%>index">首页</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==3}">
			<h1>注册失败！</h1><br/>
			<p style="font-size: x-large;">账号已存在！<br/>
			<br/>
			<a href="<%=basePath%>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==4}">
			<h1>注册失败！</h1>
			<p>属性不能为空！请重试<br/>
			<br/>
			<a href="<%=basePath%>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==5}">
			<h1>留言发表成功！</h1>
			<a href="<%=basePath%>msgBoard?currentPage=1">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==6}">
			<h1>发表失败！请输入内容</h1>
			<a href="<%=basePath%>msgBoard?currentPage=1">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==7}">
			<h1>留言删除成功！</h1>
			<a href="<%=basePath%>msgBoard?currentPage=1">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==8}">
			<h1>图片类型错误！请上传jpg、jpeg、png格式的图片</h1>
			<a href="<%=basePath%>msgBoard?currentPage=1">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==9}">
			<h1>帖子发表成功！</h1>
			<a href="<%=basePath %>postController/queryPostByType?post_type=${sessionScope.post_type}&currentPage=1">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==10}">
			<h1>登录失败</h1>
			<p>用户名或密码错误！<br/>
			<br/>
			<a href="<%=basePath %>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==11}">
			<h1>上传成功！</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==12}">
			<h1>请上传jpg、png、jpeg格式的图片</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==13}">
			<h1>修改失败！原密码错误！</h1>
			<a href="<%=basePath%>pwd.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==14}">
			<h1>修改成功！</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==15}">
			<h1>请输入标题！</h1>
			<a href="<%=basePath%>post.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==16}">
			<h1>请选择图片</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==17}">
			<h1>不能为空！</h1>
			<a href="<%=basePath%>pwd.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==18}">
			<h1>图片大小不大于4MB！</h1>
			<a href="<%=basePath%>post.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==19}">
			<h1>注销成功！</h1>
			<a href="<%=basePath%>index">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==20}">
			<h1>点赞成功！</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==21}">
			<h1>请输入内容！</h1>
			<a href="<%=basePath%>post.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==22}">
			<h1>密码由6——20位英文、数字、特殊符号组成！</h1>
			<br/>
			<a href="<%=basePath%>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==23}">
			<h1>账号由6——12位英文、数字、减号与下划线组成！</h1>
			<br/>
			<a href="<%=basePath%>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==24}">
			<h1>昵称小于13个字符！</h1>
			<br/>
			<a href="<%=basePath%>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==25}">
			<h1>图片过大！请上传小于2MB的图片</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==26}">
			<h1>不能为空！</h1>
			<a href="<%=basePath%>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==27}">
			<h1>注册失败！</h1><br/>
			<p style="font-size: x-large;">昵称已存在！<br/>
			<br/>
			<a href="<%=basePath%>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==28}">
			<h1>留言发表成功！</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==29}">
			<h1>发表失败！请输入内容</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==30}">
			<h1>发表失败！请上传.jpg.png.jpeg格式的图片</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==31}">
			<h1>发表失败！图片过大！请上传小于4MB的图片</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==32}">
			<h1>留言删除成功！</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==33}">
			<h1>收藏成功！</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==34}">
			<h1>从收藏夹中移除！</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==35}">
			<h1>帖子删除成功！</h1>
			
			<c:if test="${sessionScope.user_location == 'userPosts'}">
				<a href="<%=basePath%>userController/queryUserPosts/${sessionScope.user.user_account }/1">返回</a>
			</c:if>
			<c:if test="${sessionScope.user_location == 'nav'}">
				<a href="<%=basePath%>postController/queryPostByType?currentPage=1&post_type=${sessionScope.post_type}">返回</a>
			</c:if>
			<c:if test="${sessionScope.user_location == 'userSubPosts'}">
				<a href="<%=basePath%>userController/showSubsPost?currentPage=1">返回</a>
			</c:if>
			
		</c:if>
		
		<c:if test="${requestScope.resultType==36}">
			<h1>图片小于4MB！</h1>
			<a href="msgBoard?currentPage=1">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==37}">
			<h1>密码由6——20位英文、数字、特殊符号组成！</h1>
			<a href="<%=basePath%>pwd.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==38}">
			<h1>关注成功！</h1>
			<c:if test="${sessionScope.user_location == 'uprofile'}">
				<a href="<%=basePath%>uprofile/${requestScope.sub_account}">返回</a>
			</c:if>
			<c:if test="${sessionScope.user_location == 'uprofile/sub'}">
				<a href="<%=basePath%>sub/${requestScope.user_account}/1">返回</a>
			</c:if>
			<c:if test="${sessionScope.user_location == 'sub'}">
				<a href="<%=basePath%>sub/${requestScope.user_account}/1">返回</a>
			</c:if>
		</c:if>
		
		<c:if test="${requestScope.resultType==39}">
			<h1>从关注中移除</h1>
			<c:if test="${sessionScope.user_location == 'uprofile'}">
				<a href="<%=basePath%>uprofile/${requestScope.sub_account}">返回</a>
			</c:if>
			<c:if test="${sessionScope.user_location == 'uprofile/sub'}">
				<a href="<%=basePath%>sub/${requestScope.user_account}/1">返回</a>
			</c:if>
			<c:if test="${sessionScope.user_location == 'sub'}">
				<a href="<%=basePath%>sub/${requestScope.user_account}/1">返回</a>
			</c:if>
		</c:if>
		
		<c:if test="${requestScope.resultType == 40}">
			<h1>没有更多了！</h1>
			<a href="<%=basePath%>sub/${requestScope.user_account}/${sessionScope.currentPage}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType == 41}">
			<h1>没有更多了！</h1>
			<a href="<%=basePath%>userController/showSubsPost/${requestScope.user_account}/${sessionScope.currentPage}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType == 42}">
			<h1>没有更多了！</h1>
			<a href="<%=basePath%>postController/queryPostByType?currentPage=${sessionScope.currentPage}&post_type=${requestScope.type}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType == 43}">
			<h1>没有更多了！</h1>
			<a href="<%=basePath%>userController/queryUserPosts/${requestScope.user_account}/${sessionScope.currentPage}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType == 44}">
			<h1>没有更多了！</h1>
			<a href="<%=basePath%>msgBoard?currentPage=${sessionScope.currentPage}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType == 45}">
			<h1>没有更多了！</h1>
			<a href="<%=basePath%>postController/queryPostByPostId?currentPage=${sessionScope.currentPage}&post_id=${requestScope.post_id}">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType == 46}">
			<h1>请上传jpg、png、jpeg格式的图片</h1>
			<a href="<%=basePath%>post.jsp">返回</a>
		</c:if>

	</div>
	
</body>
</html>