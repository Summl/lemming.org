<%--
  Created by IntelliJ IDEA.
  User: Summ
  Date: 2022/5/18
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/message.css">
<html>
<%
    String title = (String) request.getAttribute("title");
    String content = (String) request.getAttribute("content");
%>
<head>
    <title><%=title%></title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="topbar/topbar.css">
    <link rel="stylesheet" href="bootstrap-icons/bootstrap-icons.css">
    <script src="topbar/topbar.js"></script>
</head>
<body>
<div id="topBar"></div>
<div class="page" id="messagePage">
    <i id="icon" class="bi bi-chat-left-heart-fill"></i>
    <h1><%=title%></h1>
    <p><%=content%></p>
</div>
</body>
</html>
