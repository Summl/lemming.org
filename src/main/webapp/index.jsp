<%@ page import="java.util.List" %>
<%@ page import="com.lemming.lemming.bean.Post" %>
<%@ page import="com.lemming.lemming.dao.PostDao" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>旅鼠论坛</title>
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
<div id="bg_image"></div>
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
            <div class="user col-md-3 col-xs-2">
                <img src="images/logo.png" id="head-image">
                <div class="userBox">
                    <a id="userName" class="hidden-sm hidden-xs" href="login.jsp">注册 / 登录</a>
                    <span id="groupName" class="hidden-sm hidden-xs">游客</span>
                </div>
            </div>
        </div>
    </header>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-2 contentList">
        <%-- 这里由JS添加内容 --%>
        </div>
    </div>
    <div class="col-xs-2 leftBox hidden-xs hidden-sm">
        <i class="bigIcon bi bi-star-fill"></i>
        <h2>旅游达人榜</h2>
        <ul>
            <li>Maicss</li>
            <li>啦啦啦</li>
            <li>哦哦哦</li>
        </ul>

        <div class="end"></div>
    </div>
</div>
</body>
</html>