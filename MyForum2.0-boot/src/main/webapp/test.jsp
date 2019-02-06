<%--
  Created by IntelliJ IDEA.
  User: Hasee
  Date: 2019/1/30
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        *{
            margin: 0px;
            padding: 0px;
        }
        .nav{
            list-style: none;
            width: 600px;
            height: 50px;
            background: antiquewhite;
            margin: 100px auto;
        }
        .nav>li{
            width: 200px;
            height: 50px;
            line-height: 50px;
            text-align: center;
            float: left;
        }
        .nav>li:hover{
            background: aquamarine;
        }
        .sub{
            list-style: none;
            background: #9ACD32;
            display: none;
        }
    </style>
    <script src="webjars/jquery/3.1.1-1/jquery.js"></script>
    <script>
        $(function () {
            //编写jQuery相关代码
            $(".nav>li").mouseenter(function () {
                var $sub = $(this).children(".sub");
                $sub.stop();
                $sub.slideDown(1000);
            });
            $(".nav>li").mouseleave(function () {
                var $sub = $(this).children(".sub");
                $sub.stop();
                $sub.slideUp(1000);
            });
        });
    </script>
</head>
<body>
<ul class="nav">
    <li>
        一级菜单
        <ul class="sub">
            <li><a href="https://www.baidu.com">百度</a></li>
            <li>二级菜单</li>
            <li>二级菜单</li>
        </ul>
    </li>
    <li>一级菜单</li>
    <li>一级菜单</li>
</ul>

</body>
</html>
