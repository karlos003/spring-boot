<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <script src="webjars/jquery/3.1.1-1/jquery.js"></script>
    <script>
        $(function () {
            //编写jQuery相关代码
            $("#btn").click(function () {
                var user_account = $("#i1").val();
                var user_pwd = $("#i2").val();
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>userController/login",
                    dataType: "json",
                    data: {
                        user_account: user_account,
                        user_pwd: user_pwd
                    },
                    success: function (data) {
                        if (data.status == 1) {
                            $(window).attr("location", "index");
                        } else if (data.status == -3) {
                            alert("密码错误");
                        } else if (data.status == -2) {
                            alert("用户不存在");
                        } else {
                            alert("不能为空！");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                        //                alert(XMLHttpRequest.readyState);
                        //                alert(textStatus);
                    }

                });
            });

            $("#btn2").click(function () {
                var user_account = $("#i3").val();
                var user_pwd = $("#i4").val();
                var user_name = $("#i5").val();
                $.post("<%=basePath%>userController/signUp", {
                        user_account: user_account,
                        user_pwd: user_pwd,
                        user_name: user_name
                    }, function (data) {
                        if (data.status == 4) {
                            alert("账号、密码、用户名不能为空");
                        }else if(data.status == 23){
                            alert("账号由6--16位数字、字母组成")
                        }else if(data.status == 22){
                            alert("密码由6--20位数字、字母、英文符号组成")
                        }else if(data.status == 24){
                            alert("昵称由1--13位字符组成")
                        }else if(data.status == -1){
                            alert("账号已存在，请更换")
                        }else if(data.status == -2){
                            alert("昵称已存在，请更换")
                        }else {
                            alert("注册成功")
                            $(window).attr("location","index");
                        }
                    },
                    "json");
            });

        });
    </script>
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
        <input id="i1" type="text" name="user_account" placeholder="用户名" required="required"
               value="${cookie.account.value}">
        <input id="i2" type="password" name="user_pwd" placeholder="密码" required="required" value="${cookie.pwd.value}">
        <div class="send-button w3layouts agileits">
            <input id="btn" type="submit" value="登 录">
        </div>

        <div class="clear"></div>
    </div>
    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/">企业网站模板</a></div>
    <div class="register w3layouts agileits">
        <h2>注 册</h2>

        <input id="i3" type="text" Name="user_account" placeholder="用户名" required="required" maxlength="16">
        <input id="i4" type="password" Name="user_pwd" placeholder="密码" required="required" maxlength="20">
        <input id="i5" type="text" Name="user_name" placeholder="昵称" required="required" maxlength="13">
        <div class="send-button w3layouts agileits">
            <input id="btn2" type="submit" value="注册">
        </div>


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