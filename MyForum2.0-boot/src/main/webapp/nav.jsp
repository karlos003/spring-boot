<%@page import="org.ltq.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/label.jsp" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=basePath%>styles/mystyle.css">
    <title>${requestScope.title }</title>
    <style type="text/css">
        #postLogo {
            float: right;
            margin-top: 20px;
            color: white;
            font-style: italic;
            font-size: 30px;
        }
    </style>
</head>
<body>

<a id="postLogo" onclick="return loginCheck()" href="<%=basePath%>post.jsp">写帖子</a>

<br/><br/>

<div class="box">
    <div class="content">
        <c:forEach items="${requestScope.posts }" var="post">
            <div id="list">
                <a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${post.post_id}"
                   style="font-size: 23px;color:black;text-decoration:none;"
                   target="_blank">${post.post_title }</a><br/>

                <p>
                    <a href="<%=basePath %>uprofile/${post.user_account}" target="_blank"><img alt=" "
                                                                                               src="<%=basePath%>u${post.user_account}.jpg"
                                                                                               width="30"
                                                                                               height="30"></a>
                    <a href="<%=basePath %>uprofile/${post.user_account}" target="_blank">${post.user_name }</a>
                    --${post.post_time }--阅读量：${post.post_viewNum }--点赞：${post.post_likeNum }--收藏：${post.post_subscribeNum }
                </p>
                <br>
                <hr>
            </div>
        </c:forEach>

        <br/><br/>

        <div style="text-align: center;">

            <c:if test="${sessionScope.currentPage > 1 }">
                <a href="<%=basePath%>postController/queryPostByType?currentPage=1&post_type=java">首页</a>--
                <a href="<%=basePath%>postController/queryPostByType?currentPage=${sessionScope.currentPage-1 }&post_type=java">上一页</a>--
            </c:if>
            <a href="<%=basePath%>postController/queryPostByType?currentPage=${sessionScope.currentPage+1 }&post_type=java">下一页</a>

        </div>

        <br/><br/><br/><br/>

    </div>
</div>

<script type="text/javascript">
    function loginCheck() {
        if (<%=user%>==null
    )
        {
            alert("登录后可以发帖！");
            return false;
        }
    }
</script>

</body>
</html>