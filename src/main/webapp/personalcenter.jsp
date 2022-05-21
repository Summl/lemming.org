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
    <link rel="stylesheet" href="topbar/topbar.css">
    <script src="topbar/topbar.js"></script>

    <link rel="icon" href="images/logo.png">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <link rel="icon" href="images/logo.png">
    <link rel="stylesheet" href="bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="css/personalcenter.css">
    <script src="personalcenter.js"></script>
</head>
<body>
<%--    页面通用顶栏--%>
    <div id="topBar"></div>
<%--左侧--%>
    <div class="leftbox">
        <div class="user_information">
<%--            头像、用户名称--%>
            <image src="images/128x128.png"></image>
            <h3>admin</h3>
        </div>
        <%--            左侧列表内容--%>
        <div class="basic_information">
            <h2>个人资料</h2>
            <ul>
                <li><a>用户名管理</a></li>
                <li><a>密码管理</a></li>
                <li><a>头像管理</a></li>
                <li><a>邮箱修改/核验</a></li>
            </ul>
        </div>
    </div>
    <%--右侧内容--%>
    <div class="rightbox">
        <ul>
            <li>用户名</li>
            <li>性别</li>
            <li>电话号码</li>
            <li>电子邮箱</li>
            <li>用户权限</li>
            <li>发帖数</li>
            <li>注册时间</li>
        </ul>
        <div>
            <a>用户名修改</a>

        </div>
    </div>
</body>
</html>
