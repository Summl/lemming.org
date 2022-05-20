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
<%--                    <a class="nav_item col-xs-2 dropdown dropdown-toggle" href="#"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">去旅行</a>--%>
<%--                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--                        <a class="dropdown-item" href="#">当地游</a>--%>
<%--                        <a class="dropdown-item" href="#">自由行</a>--%>
<%--                        <div class="dropdown-divider"></div>--%>
<%--                        <a class="dropdown-item" href="#">跟团游</a>--%>
<%--                    </div>--%>
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
<%--            <%--%>
<%--                List<Post> posts = PostDao.queryPost(1,10, PostDao.POST_ORDER.ORDER_BY_TIME);--%>
<%--                if (posts!=null){--%>
<%--                    for(Post postFilename : posts){--%>
<%--            %>--%>
<%--            <div class="itemBox">--%>
<%--                <h3 class="itemTitle"><%=postFilename.getTitle()%></h3>--%>
<%--                <p class="itemBrief"><%=postFilename.getBrief()%></p>--%>
<%--            </div>--%>
<%--            <%--%>
<%--                    }--%>
<%--                }--%>
<%--            %>--%>
        </div>
    </div>
    <div class="col-xs-2 leftBox hidden-xs hidden-sm">
        <div class="end"></div>
    </div>
</div>
</body>
</html>