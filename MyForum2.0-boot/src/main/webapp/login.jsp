<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <link rel="stylesheet" href="styles/loginSignUp.css">
    <title>登录/注册到MyForum</title>
</head>
<body>

<a href="<%=basePath%>index"
   style="color: white;font-size: 20px;font-style: italic;float:right;margin-right: 80px;margin-top: 50px;">返回首页</a><br/>


<div class="adcenter">
    <script src="http://www.cssmoban.com/include/new/ggad2_728x90.js"></script>
</div>
<h1>登录表单</h1>

<div class="container w3layouts agileits">

    <div class="login w3layouts agileits">
        <h2>登 录</h2>
        <form action="userController/loginControl" onsubmit="return valid()">
            <input type="text" name="user_account" placeholder="用户名" required="required"
                   value="${cookie.account.value}">
            <input type="password" name="user_pwd" placeholder="密码" required="required" value="${cookie.pwd.value}">
            <div class="send-button w3layouts agileits">
                <input type="submit" value="登 录">
            </div>
        </form>


        <div class="social-icons w3layouts agileits">
            <p>- 其他方式登录 -</p>
            <ul>
                <li class="qq"><a href="#">
                    <span class="icons w3layouts agileits"></span>
                    <span class="text w3layouts agileits">QQ</span></a></li>
                <li class="weixin w3ls"><a href="#">
                    <span class="icons w3layouts"></span>
                    <span class="text w3layouts agileits">微信</span></a></li>
                <li class="weibo aits"><a href="#">
                    <span class="icons agileits"></span>
                    <span class="text w3layouts agileits">微博</span></a></li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/">企业网站模板</a></div>
    <div class="register w3layouts agileits">
        <h2>注 册</h2>
        <form action="userController/signUpControl">
            <input type="text" Name="user_account" placeholder="用户名" required="required" maxlength="16">
            <input type="password" Name="user_pwd" placeholder="密码" required="required" maxlength="20">
            <input type="text" Name="user_name" placeholder="昵称" required="required" maxlength="13">
            <div class="send-button w3layouts agileits">
                <input type="submit" value="注册">
            </div>
        </form>


        <div class="clear"></div>
    </div>

    <div class="clear"></div>

</div>

<div class="footer w3layouts agileits">
    <p>Copyright &copy; More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> -
        Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
</div>


</body>
</html>