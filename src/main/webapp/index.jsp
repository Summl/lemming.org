<%@ page import="java.util.List" %>
<%@ page import="com.lemming.lemming.dao.UserDao" %>
<%@ page import="com.lemming.lemming.bean.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>旅鼠论坛</title>
<%--引入顶栏--%>
    <link rel="stylesheet" href="topbar/topbar.css">
    <script src="topbar/topbar.js"></script>

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
<div id="topBar"></div>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-2 contentList">
            <div class="bg_image_tip">
                <h4 id="bg_title"></h4>
                <p id="bg_copyright"></p>
            </div>
        <%-- 这里由JS添加内容 --%>
        </div>
    </div>
    <div class="col-xs-2 leftBox hidden-xs hidden-sm">
        <i class="bigIcon bi bi-star-fill"></i>
        <h2>旅游达人榜</h2>
        <ul>
            <%List<User> list = UserDao.getLeaderboard(6);
            if (list == null){%>
            <li>加载失败</li>
            <%}else for (User user : list){%>
            <li><%=user.getUserName()%></li>
            <%}%>
        </ul>
        <div class="end"></div>
    </div>
</div>
</body>
</html>