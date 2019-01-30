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
    <title>Title</title>
    <script src="webjars/jquery/3.1.1-1/jquery.js"></script>
    <script>
        $(function () {
            var obj = {1:2,2:25,3:40,4:88,0:5}
            $.each(obj,function (index,value) {
                console.log(index,value);
            })
        });
    </script>
</head>
<body>
    <div>div1</div>
    <div>div2</div>
    <div>div3</div>

</body>
</html>
