<%--
  Created by IntelliJ IDEA.
  User: 97393
  Date: 2022/5/20
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>旅鼠论坛-个人中心</title>
    <link rel="icon" href="images/logo.png">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/index.css">
    <link rel="icon" href="images/logo.png">
    <script src="js/index.js"></script>
    <link rel="stylesheet" href="bootstrap-icons/bootstrap-icons.css">
</head>
<body>
<div class="header">
    <header class="container">
        <div class="row">
            <div class="logo col-md-2 hidden-sm hidden-xs">旅鼠</div>
            <nav class="col-md-6 col-xs-10">
                <div class="row">
                    <a class="nav_item col-xs-2 " href="#">首页</a>
                    <a class="nav_item col-xs-2 " href="newpost.jsp">发贴</a>
                    <a class="nav_item col-xs-2" href="#">准则</a>
                    <a class="nav_item col-xs-2" href="#">关于</a>
                </div>
            </nav>
                <img src="images/logo.png" id="head-image">
                <div class="userBox">
                    <a id="userName" class="hidden-sm hidden-xs" href="login.jsp">注册 / 登录</a>
                    <span id="groupName" class="hidden-sm hidden-xs">游客</span>
                </div>
            </div>
    </header>
</div>
<div>
    这里弄一个圆角框控件装内部控件
</div>
</body>
</html>
