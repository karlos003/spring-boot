<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="label.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=basePath%>styles/mystyle.css">
    <title>关注</title>
</head>
<body>
<div class="box">
    <div class="content">
        <c:forEach items="${requestScope.subs }" var="sub">
            <div id="list">
                <a href="<%=basePath %>uprofile/${sub.user_account}" target="_blank"><img alt=" "
                                                                                          src="<%=basePath%>u${sub.user_account}.jpg"
                                                                                          width="80" height="80"></a>
                <a href="<%=basePath %>uprofile/${sub.user_account}" target="_blank">${sub.user_name }</a>
                <c:if test="${sessionScope.user.user_account == requestScope.user_account}">
                    <a href="<%=basePath %>userController/addOrRmSub/${sessionScope.user.user_account }/${sub.user_account}"
                       style="float: right;">取消关注</a>
                </c:if>

            </div>
            <hr>
        </c:forEach>

        <div style="text-align: center;">

            <c:if test="${sessionScope.currentPage > 1 }">
                <a href="<%=basePath%>sub/${requestScope.user_account }/1">首页</a>--
                <a href="<%=basePath%>sub/${requestScope.user_account }/${sessionScope.currentPage-1 }">上一页</a>--
            </c:if>
            <a href="<%=basePath%>sub/${requestScope.user_account }/${sessionScope.currentPage+1 }">下一页</a>

        </div>
    </div>
</div>

</body>
</html>