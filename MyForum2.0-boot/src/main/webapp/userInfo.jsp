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
        #head {
            margin: 10px;
            padding: 10px;
        }

        #acc {
            margin: 10px;
            padding: 10px;
        }

        #uname {
            margin: 10px;
            padding: 10px;
        }

        #pwd {
            margin: 10px;
            padding: 10px;
        }

        #sub {
            margin: 10px;
            padding: 10px;
        }

        #like {
            margin: 10px;
            padding: 10px;
        }
        #post {
            margin: 10px;
            padding: 10px;
        }
    </style>
    <title>个人信息</title>
</head>
<body>
<div class="box">
    <div class="content">
        <div id="head">
            <h1>头像:</h1>
            <c:if test="${sessionScope.user.user_photo==0}">
                <img src="images/default_photo.jpg" width="300" height="300">
                <form action="userController/updateUserPhotoControl" method="post" enctype="multipart/form-data">
                    修改头像：<input type="file" name="user_photo" id="user_photo">
                    <input type="submit" value="提交"><br/>
                </form>
            </c:if>

            <c:if test="${sessionScope.user.user_photo!=0}">
                <img src="<%=basePath%>u${sessionScope.user.user_account}.jpg" width="300" height="300">
                <form action="userController/updateUserPhotoControl" method="post" enctype="multipart/form-data">
                    修改头像（图片小于2MB）：&nbsp;&nbsp;&nbsp;&nbsp;<input type="file" name="user_photo" id="user_photo">
                    <input type="submit" value="提交"><br/>
                </form>
            </c:if>
        </div>
        <hr>
        <div id="acc">
            <h1>账号：</h1>
            ${sessionScope.user.user_account }
        </div>
        <hr>
        <div id="uname">
            <h1>昵称：</h1>
            ${sessionScope.user.user_name }
        </div>
        <hr>
        <div id="pwd">
            <h1>密码</h1>
            <a href="<%=basePath%>pwd.jsp">
                <button>修改密码</button>
            </a>
        </div>
        <hr>
        <div id="sub">
            <h1>关注</h1>
            <a href="<%=basePath%>sub/${sessionScope.user.user_account }/1" target="_blank">我的关注</a>
        </div>
        <hr>
        <div id="like">
            <h1>收藏</h1>
            <a href="<%=basePath%>userController/showSubsPost/${sessionScope.user.user_account }/1"
               target="_blank">我的收藏</a>
        </div>
        <hr>
        <div id="post">
            <h1>发布</h1>
            <a href="<%=basePath%>userController/queryUserPosts/${sessionScope.user.user_account }/1"
               target="_blank">我的发布</a>
        </div>
        <br/>
        <br/>
    </div>
</div>
</body>
</html>