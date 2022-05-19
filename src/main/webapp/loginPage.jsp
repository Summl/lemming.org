<%--
  Created by IntelliJ IDEA.
  User: Summ
  Date: 2022/5/18
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册 - 旅鼠论坛</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.js"></script>
</head>
<body>
<div class="page">
    <div class="pageLogin" id="page_login">

        <form action="user?type=login" method="post" onsubmit="return checkLoginForm()" >
            <p class="message"></p>
            <input id="login_name" type="text" name="userName" placeholder="用户名" ><br>
            <input id="login_pwd" type="password" name="password" placeholder="密码"><br>
            <input id="login_verifyCode" type="text" name="verifyCode" placeholder="验证码" >
            <img src="verifyCode?type=image" alt="验证码"  onclick="changCode(this)"><br><br>
            <input type="submit" value="立即登录">
        </form>
        <div class="tip">
            还没有账号？<a href="#" onclick="switchPage (1)">马上注册</a>
        </div>
    </div>
    <div class="pageRegister" id="page_register">
        <form action="user?type=register" method="post" onsubmit="return checkRegisterForm()">
            <p class="message"></p>
            <input id="register_name" type="text" name="userName" placeholder="用户名"><br>
            <input id="register_pwd" type="password" name="password" placeholder="密码"><br>
            <input id="register_email" name="email" placeholder="邮箱"><br>
            <input id="register_verifyCode" type="text" name="verifyCode" placeholder="邮箱验证码"><br>
            <button id="getMailVerifyCode">发送邮箱验证码</button>
            <input type="submit" value="立即注册">
        </form>
        <div class="tip">
            已有注册？<a href="#" onclick="switchPage (0)">马上登录</a>
        </div>
    </div>
</div>
</body>
<script>

        function checkRegisterForm() {
            if($("#register_name").val() == ""){
                $(".message").innerHTML = "用户名不能为空！";
                return false;
            }else if($("#register_pwd").val() == ""){
                $(".message").innerHTML = "密码不能为空！";
                return false;
            }else if($("#register_email").val() == ""){
                $(".message").innerHTML = "邮箱不能为空！";
                return false;
            }else if($("#register_verifyCode").val() == ""){
                $(".message").innerHTML = "验证码不能为空！";
                return false;
            }

            return  true;

        }
        function checkLoginForm() {
            if($("#login_name").val() == ""){
                $(".message").innerHTML = "用户名不能为空！";
                return false;
            }else if($("#login_pwd").val() == ""){
                $(".message").innerHTML = "邮箱不能为空！";
                return false;
            }else if($("#login_verifyCode").val() == ""){
                $(".message").innerHTML = "验证码不能为空！";
                return false;
            }

            return true;
        }

        function switchPage(index) {
            let $pages = $(".page");
            /*for (let page of $pages){

            }*/
            let page = window.location.hash;
            if (page != null){
                if (page == "#register"){
                    switchPage(0)
                }else {
                    switchPage(1);
                }
            }
        }
        function changCode(obj) {
            obj.src = "verifyCode?type=image&r="+Math.random();
        }
        let email = document.getElementById("register_email");

        let getMailCodeBtn = document.getElementById("getMailVerifyCode")
        getMailCodeBtn.addEventListener("click",function (){
            let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
            httpRequest.open('GET', 'verifyCode?type=email&to='+email.value, true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
            httpRequest.send();//第三步：发送请求  将请求参数写在URL中
            /**
             * 获取数据后的处理程序
             */
            httpRequest.onreadystatechange = function () {
                getMailCodeBtn.innerText = "已发送，请注意查收"
            };
        })

</script>
</html>
