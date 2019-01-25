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
<title>${post.post_title }</title>
<script type="text/javascript">
	function loginCheck(){
		if(<%=user%>==null){
			alert("请登录！");
			return false;	
		}
	}
	
	function valid(){
		var content = document.getElementById("content").value;
		if(<%=user%>==null){
			alert("登录后可以留言！");
			return false;	
		}else if(content==null||content==""||(content.length>0 && content.trim().length == 0)){
			alert("请输入评论");
			return false;
		}else if(content.length>400){
			alert("请输入少于400个字！");
			return false;	
		}
		return true;
	}
</script>
<style type="text/css">
#commentInput{
	margin-top: 20px;margin-left: 250px;
}
</style>
</head>
<body>
	<div class="content_1">
		<div class="content_2">
		
			<br/><br/>
			
			<h1>${requestScope.post.post_title }</h1>
			
			
			<div>
				<a href="<%=basePath %>uprofile/${requestScope.post.user_account }" target="_blank"><img alt=" " src="/upload/${requestScope.post.user_account}.jpg" width="30" height="30"></a>
				<a href="<%=basePath %>uprofile/${requestScope.post.user_account }" target="_blank">${requestScope.post.user_name }</a>
				--${requestScope.post.post_time }
				--阅读量：${requestScope.post.post_viewNum }--点赞：${requestScope.post.post_likeNum }--收藏：${requestScope.post.post_subscribeNum }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
			<c:if test="${requestScope.post != null }">
				<a onclick="return loginCheck()" href="<%=basePath%>postController/addLikeNum?post_id=${requestScope.post.post_id}">点赞</a>&nbsp;&nbsp;
				<a onclick="return loginCheck()" href="<%=basePath%>userController/addSubsPost?post_id=${requestScope.post.post_id}">收藏</a>&nbsp;&nbsp;
				<c:if test="${sessionScope.user.user_account == requestScope.post.user_account }">
					<a href="<%=basePath%>postController/deletePost?post_id=${requestScope.post.post_id}&post_image=${requestScope.post.post_image}">删除帖子</a>
				</c:if>
			</c:if>
				

				
			</div>
				
			<hr>
			<c:if test="${requestScope.post == null }">
				<h1>帖子不存在或已被删除</h1>
			</c:if>
			
			<p style="font-size: 20px;font-weight: bold;">${requestScope.post.post_content }</p>
			
			<c:forEach begin="1" end="${requestScope.post.post_image }" var="i">
				<a href="/postPic/${requestScope.post.post_id }_${i }.jpg" target="_blank">
					<img alt="图片..." src="/postPic/${requestScope.post.post_id }_${i }.jpg" onload="AutoResizeImage(900,700,this);" >
				</a>
				<br/>
			</c:forEach>
			
			<br/>
			<br/>
			
			<hr>
			
			<br/>
			<c:if test="${requestScope.post != null }">
				<c:if test="${requestScope.comments == null }">
					<h1>还没有留言，快来发表吧~</h1>
				</c:if>
			</c:if>
			
			<!-- 当有评论时 -->
			<c:if test="${requestScope.comments != null}">
			
				<h1>用户评论：</h1>
				
				<!-- 循环打印所有评论 -->
				<c:forEach items="${requestScope.comments}" var="comment">
					<p style="color: blue;">
						<img alt=" " src="/upload/${comment.account}.jpg" width="30" height="30">
						${comment.uname}，${comment.time}
						
						<c:if test="${sessionScope.user.user_account == comment.account}">
							，<a href="<%=basePath %>commentController/deleteCommentInPost?commentid=${comment.commentid}&post_id=${requestScope.post.post_id }">删除</a>
						</c:if>
						
					</p>
					
					<!-- 评论正文内容 -->
					<p style="font-size: 20px;font-weight: bold;">${comment.content}</p>
					
					<c:if test="${comment.image!=0}">
						<!-- 评论图片 -->
						<a href="/commentPic/${comment.commentid}.jpg" target="_blank"><img src="/commentPic/${comment.commentid}.jpg" onload="AutoResizeImage(300,400,this);" alt="评论图片"></a>
						<br/><br/>
					</c:if>
					
					<hr>
					
				</c:forEach>
				<div style="text-align: center;">
				
					<c:if test="${sessionScope.currentPage > 1 }">
						<a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${requestScope.post.post_id }">首页</a>--
						<a href="<%=basePath%>postController/queryPostByPostId?currentPage=${sessionScope.currentPage-1 }&post_id=${requestScope.post.post_id }">上一页</a>--
					</c:if>
					
					<c:if test="${requestScope.comments != null}">
						<a href="<%=basePath%>postController/queryPostByPostId?currentPage=${sessionScope.currentPage+1 }&post_id=${requestScope.post.post_id }">下一页</a>
					</c:if>
					
				</div>
				
				<br/>
				
			</c:if>
			<c:if test="${requestScope.post != null }">
				<!-- 评论输入栏 -->
				<div id="commentInput">
				<form  action="<%=basePath %>commentController/addPostComment?post_id=${requestScope.post.post_id}" onsubmit="return valid()" enctype="multipart/form-data" method="post">
					<input type="submit" value="发表留言">
					&nbsp;&nbsp;&nbsp;选择图片（可选一张）：&nbsp;<input type="file" name="comPic"><br/>
					<textarea name="content" id="content" rows="8" cols="80"></textarea><br/>
				</form>
				</div>
			</c:if>
			
			
			<br/><br/><br/><br/>
			
		</div>
	</div>
	
	<!-- 图片大小控制 -->
	<script type="text/javascript">
	
	function AutoResizeImage(maxWidth,maxHeight,objImg){
		var img= new Image();
		img.src=objImg.src;//定义一个图片对象
		var hRatio;
		var wRatio;
		var Ratio=1;
		var w=img.width;//图片的宽
		var h=img.height;//图片的长
		wRatio=maxWidth/w;//宽压缩的比例
		hRatio=maxHeight/h;//长压缩的比例
		if(wRatio==0&&hRatio==0){
			Ratio=1;//如果压缩最大值都为零，按原图比例压缩
		}else if(wRatio==0){
			if(hRatio<1)Ratio=hRatio;//如果宽为零，长的比例小于一的情况按长比例压缩
		}else if(hRatio==0){
			if(wRatio<1)Ratio=wRatio;//如果长的压缩最大值为零，宽的比例小于一的情况按宽压缩
		}else if(wRatio<1&&hRatio<1){
			Ratio=(wRatio<hRatio?wRatio:hRatio);//如果长宽最大压缩值都不为零，取比例的最小值
		}
		if(Ratio<1){
			w=w*Ratio;
			h=h*Ratio;
		}
		//赋值
		objImg.height=h;
		objImg.width=w;
	}
	
	</script>
</body>
</html>