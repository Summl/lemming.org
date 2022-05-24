<%--
  Created by IntelliJ IDEA.
  User: 2uli
  Date: 2022/5/21
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="topbar/topbar.css">
    <script src="topbar/topbar.js"></script>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <link rel="icon" href="images/logo.png">
    <link rel="stylesheet" href="bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="css/examination.css">
    <script src="js/examination.js"></script>
    <title>社区行为规范测试</title>
</head>
<body>
<div id="topBar"></div>
<div class="container">
    <h1 class="top row">社区行为规范测试</h1>
    <p class="brief row">
        为了维护良好的社区环境，需要您先回答该问卷的内容，我们将依此确认你是否明白社区礼仪以及需要遵守的社区规范，另外还会涉及到本论坛的常识性问题，请您认真作答非常感谢您的配合。
    </p>
    <form class="row" action="" method="post">
        <div id="out">
        </div>
        <input type="submit" value="提交" onclick="submitdata()">
    </form>
</div>

</body>
</html>
