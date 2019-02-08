<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="label.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=basePath%>styles/mystyle.css">
    <style>
        <%--图片播放模块的CSS代码--%>
        * {
            margin: 0;
            padding: 0;
        }

        /*播放模块ul*/
        .player {
            z-index: 1;
            list-style: none;
            position: relative;
            margin: 20px auto;
            width: 500px;
            height: 425px;
            background-color: gray;
        }

        .player > li {
            z-index: 1;
            position: absolute;
            width: 500px;
            height: 400px;
        }

        /*内容*/
        .con {
            z-index: 1;
            position: absolute;
            display: none;
            margin-top: 0px;
        }

        .player > li > div > img {
            z-index: 1;
            position: relative;
            width: 500px;
            height: 400px;
        }

        /*按钮*/
        .btn {
            z-index: 1;
            position: absolute;
            margin-left: 150px;
            margin-top: 400px;
        }

        .btn > li {
            z-index: 1;
            position: relative;
            list-style: none;
            width: 50px;
            height: 25px;
            background-color: aquamarine;
            float: left;
            text-align: center;
        }

        .btn > li:hover {
            background-color: #9ACD32;
        }

        .current .con {
            display: block;
        }
    </style>
    <script src="webjars/jquery/3.1.1-1/jquery.js"></script>
    <script>
        $(function () {
            //编写jQuery相关代码
            $(".btn").children("li").mouseenter(function () {
                var index = $(this).index();
                var $cur = $(".player").children("li").eq(index);
                $cur.addClass("current");
                $cur.siblings().removeClass("current");
            });
        });
    </script>
    <title>MyForum</title>
</head>
<body>
<br/>
<br/>
<div class="box">
    <%--图片播放模块--%>
    <ul class="player">
        <li class="current">
            <div class="con" id="a"><img src="<%=basePath%>images/play1.jpg"></div>
        </li>
        <li>
            <div class="con"><img src="<%=basePath%>images/play2.jpg"></div>
        </li>
        <li>
            <div class="con"><img src="<%=basePath%>images/play3.jpg"></div>
        </li>
        <li>
            <div class="con"><img src="<%=basePath%>images/play4.jpg"></div>
        </li>
        <ul class="btn">
            <li>1</li>
            <li>2</li>
            <li>3</li>
            <li>4</li>
        </ul>
    </ul>
    <div class="content">

        <h1>最新发布</h1>
        <hr>
        <%--循环打印Redis数据库中的最新帖子--%>
        <c:forEach items="${requestScope.posts }" var="post">
            <div id="list">
                    <%--帖子标题--%>
                <a href="<%=basePath%>postController/queryPostByPostId?currentPage=1&post_id=${post.post_id}"
                   style="font-size: 23px;color:black;text-decoration:none;" target="_blank">
                        ${post.post_title }
                </a>
                <br/>
                <p>
                        <%--用户头像--%>
                    <a href="<%=basePath %>uprofile/${post.user_account}" target="_blank">
                        <img alt=" " src="<%=basePath%>u${post.user_account}.jpg" width="30" height="30">
                    </a>
                        <%--用户名以及帖子信息--%>
                    <a href="<%=basePath %>uprofile/${post.user_account}" target="_blank">${post.user_name }</a>
                    --${post.post_time }--阅读量：${post.post_viewNum }--点赞：${post.post_likeNum }--收藏：${post.post_subscribeNum }--分区：${post.post_type }
                </p>
                <br>
                <hr>
            </div>
        </c:forEach>

        <br/><br/>

    </div>
</div>


</body>
</html>