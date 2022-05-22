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
    <script src="js/personalcenter.js"></script>
</head>
<body>
<%--    页面通用顶栏--%>
    <div id="topBar"></div>
<%--左侧--%>
<div class="container">
    <div class="row">
        <div class="leftbox col-sm-2">
            <div class="user_information">
                <%--            头像、用户名称--%>
                <image class="leftimage" src="images/128x128.png"></image>
            </div>
            <%--            左侧列表内容--%>
                <ul>
                    <li><a href="#">基本信息</a></li>
                    <li><a href="#">用户名管理</a></li>
                    <li><a href="#">密码管理</a></li>
                    <li><a href="#">头像管理</a></li>
                    <li><a href="#">邮箱修改/核验</a></li>
                    <li><a href="#">用户注销</a></li>
                </ul>
        </div>
        <%--右侧内容--%>
        <%--1.个人信息界面--%>
        <div class="rightbox col-sm-10">
            <div class="basic_information form-group">
                <div class="leftusercontent">
                    <form action="" method="post">
                        <%-- <span>用户名:</span>
                         <div class="recognizedtext">admin-识别到的用户名（可直接修改）</div>
                         <br>
                         <span>性别:</span>
                         <div class="recognizedtext">admin-识别到的性别（可直接修改）</div>
                         <br>
                         <span>电话号码:</span>
                         <div class="recognizedtext">admin-识别到的电话号码（可直接修改）</div>
                         <br>
                         <span>电子邮箱:</span>
                         <div class="recognizedtext">admin-识别到的电子邮箱（可直接修改）</div>--%>
                            <span>用户名:</span>
                            <div class="recognizedtext">
                                <input type="text" class="form-control" name="updatename" placeholder="admin-识别到的用户名（可直接修改）" value="">
                            </div>
                            <br>
                            <span>性别:</span>
                            <div class="recognizedtext form-check form-check-inline">
                               <%-- admin-识别到的性别（可直接修改）--%>
                                <input class="form-check-input" type="radio" name="updatesex" value="man" checked="checked">男
                                <input class="form-check-input" type="radio" name="updatesex" value="woman">女
                            </div>
                            <br>
                            <span>电话号码:</span>
                            <div class="recognizedtext">
                                <input class="form-control" type="tel" name="updatephone" placeholder="admin-识别到的电话号码（可直接修改）" value="">
                            </div>
                            <br>
                            <span>电子邮箱:</span>
                            <div class="recognizedtext">
                                <input class="form-control" type="email" name="updateemail" placeholder="admin-识别到的电子邮箱（可直接修改）" value="">
                            </div>
                 </div>
                 <div class="rightusercontent">
                     <span>用户权限:</span>
                     <div class="recognizedtext">
                         <input class="form-control" name="userlimit" placeholder="admin-识别到的用户权限" readonly value="">
                     </div>
                     <br>
                     <span>发帖数:</span>
                     <div class="recognizedtext">
                         <input class="form-control" name="postcount" placeholder="admin-识别到的发帖数量" readonly value="">
                     </div>
                     <br>
                     <span>注册时间:</span>
                     <div class="recognizedtext">
                         <input class="form-control" name="registertime" placeholder="admin-识别到的注册时间" readonly value="">
                     </div>
                     <br>
                     <span><button id="btsave" class="btn btn-warning" type="submit">保存</button></span>
                 </div>
             </div>
         </div>
         </form>
     </div>
 </div>

 <%--    2.用户名修改界面--%>
<%--    <div class="rightbox">--%>
<%--        <div>--%>
<%--            <span>原用户名：</span>--%>
<%--            <div class="recognizedtext">admin-识别到的原用户名</div>--%>
<%--            <br>--%>
<%--            <span>新用户名：</span>--%>
<%--            <input type="text" size="20">--%>
<%--        </div>--%>
<%--    </div>--%>
</body>
</html>
