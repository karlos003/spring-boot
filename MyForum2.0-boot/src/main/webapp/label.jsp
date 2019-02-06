<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        * {
            margin: 0px;
            padding: 0px;
        }

        .nav {
            list-style: none;
            width: 800px;
            height: 50px;
            background: antiquewhite;
            margin: 0px auto;
        }

        .nav > li {
            width: 200px;
            height: 50px;
            line-height: 50px;
            text-align: center;
            float: left;
        }

        .nav > li > a {
            padding: 8px 50px; /* 设置内边距 */
            color: #333333;
            /* 设置文字颜色 */
            text-decoration: none; /* 去掉下划线 */
        }

        .nav > li:hover {
            background: aquamarine;
        }

        .sub {
            list-style: none;
            background: #9ACD32;
            display: none;
        }

        #info {
            font-size: large;
            float: right;
            color: white;
        }
        .nav>li>img{
            width: 200px;
            height: 50px;
        }
    </style>

    <script src="webjars/jquery/3.1.1-1/jquery.js"></script>
    <script>
        $(function () {
            //编写jQuery相关代码
            $(".nav>li").mouseenter(function () {
                var $sub = $(this).children(".sub");
                $sub.stop();
                $sub.slideDown(500);
            });
            $(".nav>li").mouseleave(function () {
                var $sub = $(this).children(".sub");
                $sub.stop();
                $sub.slideUp(500);
            });
        });
    </script>

    <link rel="stylesheet" href="<%=basePath%>styles/labelStyle.css">
    <link rel="shortcut icon" href="<%=basePath%>images/icon.png">
</head>
<body>
<!-- 菜单栏 -->
    <div id="info">
        <c:if test="${sessionScope.user != null}">
            <c:if test="${sessionScope.user.user_photo==0}">
                <a href="<%=basePath%>userInfo.jsp" target="_blank"><img src="<%=basePath%>images/default_photo.jpg"
                                                                         width="35" height="35"></a>
            </c:if>
            <c:if test="${sessionScope.user.user_photo!=0}">
                <a href="<%=basePath%>userInfo.jsp" target="_blank"><img
                        src="<%=basePath%>u${sessionScope.user.user_account}.jpg" width="35" height="35"></a>
            </c:if>
            <a href="<%=basePath%>userInfo.jsp" target="_blank"
               style="color: white;font-style: italic;">${sessionScope.user.user_name}</a>，
            <a href="<%=basePath%>userController/logOffControl" style="color: white;font-style: italic;">注销</a>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <font>
                <a href="<%=basePath%>login.jsp"><img src="<%=basePath%>images/default_photo.jpg"
                                                                      width="35"
                                                                      height="35"></a>
                <a href="<%=basePath%>login.jsp" style="color: white;font-style: italic;">点击登录</a>
            </font>
        </c:if>
    </div>

<%--
<div style="margin-left: 500px">
    <ul id="menu">
        <li><img alt="LogoHere..." src="<%=basePath%>images/logo.png"></li>
        <li><a href="<%=basePath%>index" class="last" target="_blank">首页</a></li>
        <li><a href="<%=basePath%>msgBoard?currentPage=1" class="last" target="_blank">留言板</a></li>
        <li>
            <a href="<%=basePath%>forum.jsp" class="last" target="_blank">论坛</a>

        </li>
    </ul>
</div>
--%>
<ul class="nav">
    <li><img alt="LogoHere..." src="<%=basePath%>images/logo.png"></li>
    <li><a href="<%=basePath%>index">首页</a></li>
    <li><a href="<%=basePath%>msgBoard?currentPage=1">留言板</a></li>
    <li>
        <a href="<%=basePath%>forum.jsp">论坛</a>
        <ul class="sub">
            <li>
                <a href="<%=basePath%>postController/queryPostByType?currentPage=1&post_type=java">JAVA</a>
            </li>
            <li>二级菜单</li>
            <li>二级菜单</li>
        </ul>
    </li>
</ul>


<div id="footer">CopyRight@copy2012</div>
<!-- 菜单栏结束 -->
</body>
</html>