<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="label.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/mystyle.css">
    <title>论坛</title>
    <style type="text/css">
        #JavaArea {
            position: absolute;
            margin-left: 400px;
            margin-top: 60px;
        }
    </style>
</head>
<body>

<a id="JavaArea" href="<%=basePath%>postController/queryPostByType?currentPage=1&post_type=java">
    <img alt="Java区..." src="<%=basePath%>images/JavaAreaLogo.png" width="300" height="200">
</a>

</body>
</html>