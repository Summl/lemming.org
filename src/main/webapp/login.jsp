<%--
  Created by IntelliJ IDEA.
  User: maicss
  Date: 2022/5/18
  Time: 上午10:53
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.bundle.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册 - 旅鼠论坛</title>
</head>
<body>
<% session.removeAttribute("user");%>
<div class="mainbox">
<iframe src="loginPage.jsp" class="loginbox"></iframe>
</div>
</body>
<script>
 let $ifme=$(".loginbox")[0];
 let $mainbox=$(".loginbox")[0];
</script>
</html>
