<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>旅鼠论坛</title>
    <link rel="icon" href="images/logo.png">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="css/index.css">
    <link rel="icon" href="images/logo.png">
</head>
<body>
<div class="header">
    <header class="container">
        <div class="row">
            <div class="logo col-md-2 hidden-sm hidden-xs">旅鼠论坛</div>
            <nav class="col-md-7 col-xs-10">
                <div class="row">
                    <a class="nav_item col-xs-2 " href="#">目的地</a>
                    <a class="nav_item col-xs-2 " href="#">旅游攻略</a>
                    <a class="nav_item col-xs-2 dropdown dropdown-toggle" href="#"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">去旅行</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">当地游</a>
                        <a class="dropdown-item" href="#">自由行</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">跟团游</a>
                    </div>
                    <a class="nav_item col-xs-2" href="#">机票</a>
                    <a class="nav_item col-xs-2" href="#">订酒店</a>
                    <a class="nav_item col-xs-2" href="#">社区</a>
                    <a class="nav_item col-xs-2" href="#">APP</a>
                </div>
            </nav>


            <div class="user col-md-2 col-xs-2">
                <img src="images/logo.png" id="head-image">
                <div class="userBox">
                    <a id="userName" class="hidden-sm hidden-xs" href="login.jsp">注册 / 登录</a>
                    <span id="groupName" class="hidden-sm hidden-xs">游客</span>
                </div>
            </div>
        </div>
        <div class="row">
            <a href="newpost.jsp">发布新文章</a>
        </div>
    </header>
</div>
</body>
</html>