<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="org.ltq.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    User user = (User) session.getAttribute("user");
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
        /*菜单栏*/
        .menu {
            z-index: 2;
            list-style: none;
            width: 800px;
            height: 50px;
            background: antiquewhite;
            margin: 0px auto;
        }

        .menu > li {
            z-index: 2;
            width: 200px;
            height: 50px;
            line-height: 50px;
            text-align: center;
            float: left;
        }

        .menu > li > a {
            padding: 8px 50px; /* 设置内边距 */
            color: #333333;
            /* 设置文字颜色 */
            text-decoration: none; /* 去掉下划线 */
        }

        .menu > li:hover {
            background: aquamarine;
        }
        /*菜单栏二级菜单*/
        .sub {
            z-index: 2;
            list-style: none;
            background: antiquewhite;
            display: none;
        }

        .sub > li:hover {
            background-color: aquamarine;
        }
        .sub>li>a{
            padding: 8px 50px;
        }

        .menu > li > img {
            width: 200px;
            height: 50px;
        }
        /*右侧的用户信息栏*/
        #info {
            width: 200px;
            height: 50px;
            font-size: large;
            float: right;
            color: white;
        }
        /*头像*/
        #img {
            margin-left: 0px;
            position: relative;
            float: left;
        }
        /*用户名*/
        #nam {
            width: 150px;
            height: 50px;
            overflow: hidden;
            float: left;
            text-align: center;
            background-color: antiquewhite;
        }

        #nam:hover {
            background-color: aquamarine;
            color: white;
        }

        #nam > a {
            margin: 0px auto;
            padding: 8px 50px;
            color: black;
            top: 10px;
            text-decoration: none;
        }

        /*信息栏的二级菜单*/
        #slide {
            display: none;
            position: absolute;
            list-style: none;
            margin-top: 50px;
            float: right;
        }

        #slide > li {
            width: 200px;
            height: 50px;
            background-color: antiquewhite;
            text-align: center;
            color: black;
        }

        #slide > li:hover {
            background-color: aquamarine;
        }

        #slide > li > a {
            padding: 8px 50px;
            color: black;
            text-decoration: none;
        }
    </style>

    <script src="<%=basePath%>webjars/jquery/3.1.1-1/jquery.js"></script>
    <script>
        $(function () {
            //编写jQuery相关代码
            $(".menu>li").mouseenter(function () {
                var $sub = $(this).children(".sub");
                $sub.stop();
                $sub.slideDown(200);
            });
            $(".menu>li").mouseleave(function () {
                var $sub = $(this).children(".sub");
                $sub.stop();
                $sub.slideUp(200);
            });
            $("#info").mouseenter(function () {
                var $slide = $(this).children("#slide");
                $slide.stop();
                $slide.fadeIn(200);
            });
            $("#info").mouseleave(function () {
                var $slide = $(this).children("#slide");
                $slide.stop();
                $slide.fadeOut(200);
            });


        });
    </script>
    <script>
        function loginCheck() {
            if (<%=user%>==null){
                alert("请登录！");
                return false;
            }
        }
    </script>
    <link rel="stylesheet" href="<%=basePath%>styles/labelStyle.css">
    <link rel="shortcut icon" href="<%=basePath%>images/icon.png">
</head>
<body>
<!-- 菜单栏 -->
<div id="info">
    <div id="img">
        <c:if test="${sessionScope.user == null}">
            <a href="<%=basePath%>login.jsp"><img src="<%=basePath%>images/default_photo.jpg"
                                                  width="50"
                                                  height="50"></a>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <c:if test="${sessionScope.user.user_photo==0}">
                <a href="<%=basePath%>userInfo.jsp" target="_blank"><img src="<%=basePath%>images/default_photo.jpg"
                                                                         width="50" height="50"></a>
            </c:if>
            <c:if test="${sessionScope.user.user_photo!=0}">
                <a href="<%=basePath%>userInfo.jsp" target="_blank"><img
                        src="<%=basePath%>u${sessionScope.user.user_account}.jpg" width="50" height="50"></a>
            </c:if>
        </c:if>
    </div>

    <div id="nam">
        <c:if test="${sessionScope.user == null}">
            <a href="<%=basePath%>login.jsp"><p>点击登录</p></a>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <a href="<%=basePath%>userInfo.jsp" target="_blank"><p>${sessionScope.user.user_name}</p></a>
        </c:if>
    </div>

    <ul id="slide">
        <li>
            <a href="<%=basePath%>sub/${sessionScope.user.user_account }/1" target="_blank" onclick="return loginCheck()"><p>我的关注</p></a>
        </li>
        <li>
            <a href="<%=basePath%>userController/showSubsPost/${sessionScope.user.user_account }/1"
               target="_blank" onclick="return loginCheck()"><p>我的收藏</p></a>
        </li>
        <li>
            <a href="<%=basePath%>pwd.jsp" onclick="return loginCheck()"><p>修改密码</p></a>
        </li>
        <li>
            <a href="<%=basePath%>userController/logOffControl" onclick="return loginCheck()"><p>注销</p></a>
        </li>
    </ul>
</div>

<ul class="menu">
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