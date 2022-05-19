<%--
  Created by IntelliJ IDEA.
  User: maicss
  Date: 2022/5/18
  Time: 上午10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="images/logo.png">
    <title>登录 - 旅鼠论坛</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.js"></script>
    <script src="js/login.js"></script>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="mainbox">
    <div>
        <ul id="switchPage">
            <li id="switchToLogin" class="CurrentPageLi">登录</li>
            <li id="switchToRegister">注册</li>
        </ul>
        <%
            String tip = (String) request.getAttribute("tip");
            if (tip==null){
                tip="";
            }
        %>
        <p class="tip" id="tip"><%=tip%></p>
        <div id="pageLogin">
            <form action="user?type=login" method="post" onsubmit="return checkLoginForm()" >
                <p class="message"></p>
                <input id="login_name" type="text" name="login_userName" placeholder="用户名/邮箱"
                       value="<%=request.getParameter("login_userName")==null?"":request.getParameter("login_userName")%>">
                <input id="login_pwd" type="password" name="login_password" placeholder="密码"
                       value="<%=request.getParameter("login_password")==null?"":request.getParameter("login_password")%>">
                 <button class="show_btn_login" type="button">显示密码</button>

                <input id="login_verifyCode" type="text" name="login_verifyCode" placeholder="验证码">
                <img src="verifyCode?type=image" alt="验证码"  onclick="changCode(this)">
                <input type="submit" value="立即登录">
            </form>
        </div>
        <div id="pageRegister">
            <form action="user?type=register" method="post" onsubmit="return checkRegisterForm()">
                <p class="message"></p>
                <input id="register_name" type="text" name="register_userName" placeholder="用户名"
                       value="<%=request.getParameter("register_userName")==null?"":request.getParameter("register_userName")%>">
                <input id="register_pwd" type="password" name="register_password" placeholder="密码"
                       value="<%=request.getParameter("register_password")==null?"":request.getParameter("register_password")%>">
                <button class="show_btn_register" type="button">显示密码</button>
                <input id="register_email" type="email" name="register_email" placeholder="邮箱"
                       value="<%=request.getParameter("register_email")==null?"":request.getParameter("register_email")%>">
                <input id="register_verifyCode" type="text" name="register_verifyCode" placeholder="邮箱验证码">
                <button id="btn_mailVerifyCode" type="button">发送邮箱验证码</button>
                <input type="submit" value="立即注册">
            </form>
        </div>
    </div>
</div>
</body>
</html>
