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
                <li onclick="page1()">基本信息</li>
                <li onclick="page2()">密码管理</li>
                <li onclick="page3()">头像管理</li>
                <li onclick="page4()">邮箱修改/核验</li>
                <li onclick="page5()">用户注销</li>
            </ul>
        </div>
        <%--右侧内容--%>
        <div class="rightbox col-sm-10">
            <%--1.个人信息界面--%>
            <div id="interface-1" class="rightboxpage">
                <form action="" method="post">
                    <div class="basic_information form-group">
                        <div class="leftusercontent">
                            <span>用户名:</span>
                            <div class="recognizedtext">
                                <input id="updatename" type="text" class="form-control" name="updatename" placeholder="admin-识别到的用户名（可直接修改）"
                                       value="">
                            </div>
                            <br>
                            <span>性别:</span>
                            <div class="recognizedtext form-check form-check-inline">
                                <%-- admin-识别到的性别（可直接修改）--%>
                                <input id="updatesex" class="form-check-input" type="radio" name="updatesex" id="man" value="man"
                                       checked="checked">
                                <label class="form-check-label" for="man">男</label>
                                <input class="form-check-input" type="radio" name="updatesex" id="woman" value="woman">
                                <label class="form-check-label" for="man">女</label>
                            </div>
                            <br>
                            <span id="phone">电话号码:</span>
                            <div class="recognizedtext">
                                <input id="updatephone" class="form-control" type="tel" name="updatephone"
                                       placeholder="admin-识别到的电话号码（可直接修改）" value="">
                            </div>
                            <br>
                            <span>电子邮箱:</span>
                            <div class="recognizedtext">
                                <input id="updateemail" class="form-control" type="email" name="updateemail"
                                       placeholder="admin-识别到的电子邮箱（可直接修改）" value="">
                            </div>
                        </div>
                        <div class="rightusercontent">
                            <span>用户权限:</span>
                            <div class="recognizedtext">
                                <input id="userlimit" class="form-control" name="userlimit" placeholder="admin-识别到的用户权限" readonly value="">
                            </div>
                            <br>
                            <span>发帖数:</span>
                            <div class="recognizedtext">
                                <input id="postcount" class="form-control" name="postcount" placeholder="admin-识别到的发帖数量" readonly value="">
                            </div>
                            <br>
                            <span>注册时间:</span>
                            <div class="recognizedtext">
                                <input id="registertime" class="form-control" name="registertime" placeholder="admin-识别到的注册时间" readonly
                                       value="">
                            </div>
                            <br>
                            <span><button id="btsave" class="btn btn-warning" type="submit">保存</button></span>
                        </div>
                    </div>
                </form>
            </div>
            <%--      2.密码修改界面--%>
            <div id="interface-2" class="rightboxpage">
                    <form action="" method="post">
                        <div class="updatepasswordinterface">
                            <span>原密码：</span>
                            <input class="form-check-input" type="text" name="oldpassword" value="" placeholder="请输入原始密码">
                            <br>
                            <span>新密码：</span>
                            <input class="form-check-input" type="text" name="newpassword" value="" placeholder="请输入新密码">
                            <br>
                            <span>重新输入新密码：</span>
                            <input class="form-check-input" type="text" name="newpassword" value="" placeholder="请输入新密码">
                            <br>
                            <button type="submit" class="btn btn-warning">保存信息</button>
                            <button type="submit" class="btn btn-warning">重置信息</button>
                        </div>
                    </form>
            </div>
            <div id="interface-3" class="rightboxpage">
                <div>第三个界面内容</div>
            </div>
            <div id="interface-4" class="rightboxpage">
                <div>第四个界面内容</div>
            </div>
            <div id="interface-5" class="rightboxpage">
                <div>第五个界面内容</div>
            </div>

        </div>
    </div>
</div>


</body>
</html>
