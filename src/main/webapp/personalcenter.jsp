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
                <li id="li-1" onclick="page1()">基本信息</li>
                <li id="li-2" onclick="page2()">密码管理</li>
                <li id="li-3" onclick="page3()">头像管理</li>
                <li id="li-4" onclick="page4()">邮箱修改/核验</li>
                <li id="li-5" onclick="page5()">用户注销</li>
            </ul>
        </div>
        <%--右侧内容--%>
        <div class="rightbox col-sm-10">
            <%--1.个人信息界面--%>
            <div id="interface-1" class="rightboxpage">
                <form action="userinfo?type=userBaseInfo" method="post" id="selectBaseinfo">
                    <div class="basic_information form-group">
                        <div class="leftusercontent">
                            <span>用户名:</span>
                            <div class="recognizedtext">
                                <input id="updatename" type="text" class="form-control" name="updatename"
                                       placeholder="识别到的用户名（可直接修改）"
                                       value="">
                            </div>
                            <br>
                            <%--                            单选框--%>
                            <span id="sexBaseInfo">性别:</span>
                            <div id="sexDiv" class="recognizedtext form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="updatesex" id="man" value="男">
                                <label class="form-check-label" for="man">男</label>
                                <input class="form-check-input" type="radio" name="updatesex" id="woman" value="女">
                                <label class="form-check-label" for="woman">女</label>
                                <input class="form-check-input" type="radio" name="updatesex" id="no_sex">
                                <label class="form-check-label" for="no_sex">保密</label>
                            </div>
                            <br>
                            <span id="phone">电话号码:</span>
                            <div class="recognizedtext">
                                <input id="updatephone" class="form-control" type="tel" maxlength="11"
                                       name="updatephone"
                                       placeholder="识别到的电话号码（可直接修改）" value="">
                            </div>
                            <br>
                            <span>电子邮箱:</span>
                            <div class="recognizedtext">
                                <input id="updateemail" class="form-control" type="email" name="updateemail"
                                       placeholder="识别到的电子邮箱（可直接修改）">
                            </div>
                        </div>
                        <%--                        不可编辑--%>
                        <div class="rightusercontent">
                            <span style="margin-top: 10px">用户权限:</span>
                            <div class="recognizedtext">
                                <input id="userlimit" class="form-control" name="userlimit" placeholder="识别到的用户权限"
                                       readonly value="">
                            </div>
                            <br>
                            <span>发帖数:</span>
                            <div class="recognizedtext">
                                <input id="postcount" class="form-control" name="postcount" placeholder="识别到的发帖数量"
                                       readonly value="">
                            </div>
                            <br>
                            <span>注册时间:</span>
                            <div class="recognizedtext">
                                <input id="registertime" class="form-control" name="registertime"
                                       placeholder="识别到的注册时间" readonly
                                       value="">
                            </div>
                            <br>
                            <span>
                                <input id="saveBaseInfo" type="button" onclick="updateBaseInfo()"
                                       class="btn btn-warning" data-dismiss="modal" value="保存">
                            </span>
                        </div>
                    </div>
                </form>
                <div class="modal fade" data-backdrop="static" id="mymodalBaseInfo">
                    <div class="modal-dialog modal-dialog-centered modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5>提示信息：</h5>
                                <button class="close" data-dismiss="modal"><span>&times;</span></button>
                            </div>
                            <div class="modal-body">
                                <p id="textBaseInfo"></p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-warning" data-dismiss="modal">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--2.密码修改界面--%>
            <div id="interface-2" class="rightboxpage">
                <form action="userinfo?type=updatePassword" method="post" id="selectUpdatePassword">
                    <h2>修改密码</h2>
                    <table class="updatepasswordinterface">
                        <tr>
                            <td>
                                <p>原密码：</p>
                                <input id="oldpassword" class="form-check-input updateinputsize" type="password"
                                       name="oldpassword" value="" placeholder="请输入原始密码">
                            </td>
                        </tr>
                        <tr>
                            <td><br></td>
                        </tr>
                        <tr>
                            <td>
                                <p>新密码：</p>
                                <input id="newpassword" class="form-check-input updateinputsize" type="password"
                                       name="newpassword" value="" placeholder="请输入新密码">
                            </td>
                        </tr>
                        <tr>
                            <td><br></td>
                        </tr>
                        <tr>
                            <td>
                                <p>重新输入新密码：</p>
                                <input id="renewpassword" class="form-check-input updateinputsize" type="password"
                                       name="renewpassword" value="" placeholder="请重新输入新密码">
                            </td>
                        </tr>
                        <tr>
                            <td><br></td>
                        </tr>
                    </table>
                    <div class="buttonstyle">
                        <button type="button" onclick="updatePassword()" class="btn btn-warning" id="buttonsave">保存信息
                        </button>
                        <button type="reset" class="btn btn-warning" id="buttonreset">重置信息</button>
                    </div>
                </form>
                <div class="modal fade" data-backdrop="static" id="mymodalUpdatePassword">
                    <div class="modal-dialog modal-dialog-centered modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5>提示信息：</h5>
                                <button class="close" data-dismiss="modal"><span>&times;</span></button>
                            </div>
                            <div class="modal-body">
                                <p id="textUpdatePassword"></p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-warning" data-dismiss="modal">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--3.上传头像界面--%>
            <div id="interface-3" class="rightboxpage">
                <form action="userinfo?type=updateImg" method="post" enctype="multipart/form-data" id="secletImg">
                    <h2>上传头像图片</h2>
                    <div id="uploadimage"  class="col-sm-4">
<%--                        <img src="" id="showimg">--%>
                    </div>
                    <input  id="inputimage" type="file" name="cover" accept="image/*">
                    <button type="submit" class="btn btn-warning" onclick="updateImg()" id="uploadbuttonsave">保存头像</button>
                </form>
           </div>
           <%--4.邮箱修改界面   --%>
            <div id="interface-4" class="rightboxpage">
                <form action="" method="post">
                    <h2>邮箱修改/核验</h2>
                    <table class="updateemailinterface">
                        <tr>
                            <td>
                                <p>原邮箱地址：</p>
                                <input id="oldemail" class="form-check-input updateinputsize" type="text"
                                       name="oldemail" value="" placeholder="显示原本邮箱地址">
                            </td>
                        </tr>
                        <tr>
                            <td><br></td>
                        </tr>
                        <tr>
                            <td>
                                <p>新邮箱地址：</p>
                                <input id="newemail" class="form-check-input updateinputsize" type="text"
                                       name="newemail" value="" placeholder="请输入新密码">
                                <button id="emailbtn" type="reset" class="btn btn-warning">重置</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>向新邮箱发送验证码：</p>
                                <input class="form-check-input updateinputsize" type="text" name="emailcode"
                                       id="emailcode" value="" placeholder="请输入邮箱验证码">
                                <button id="emailcodebtn" type="submit" class="btn btn-warning">发送邮箱验证码</button>
                            </td>
                        </tr>
                    </table>
                    <div id="buttonstyle">
                        <button id="emailbtnsave" type="submit" class="btn btn-warning">保存信息</button>
                    </div>
                </form>
            </div>
            <%--5.注销账号--%>
            <div id="interface-5" class="rightboxpage">
                <form action="userinfo?type=revokeUserAccount" method="post" id="selectrevokebutton">
                    <h2>注销账号</h2>
                    <div class="RevokeInterface">
                        <br>
                        <p>账号注销则视为您主动放弃以下资产和权益，且同意以下规则: </p>
                        <p>&nbsp;&nbsp;&nbsp;1.账号注销后,您将无法登录、使用旅鼠论坛账号,且不支持找回任何个人资料。</p>
                        <p>&nbsp;&nbsp;&nbsp;2.该账号将解除与其他产品的绑定或授权关系，不支持找回。</p>
                        <p>&nbsp;&nbsp;&nbsp;3.将不再支持使用已注销账号的用户ID注册新的账号。</p>
                        <p>&nbsp;&nbsp;&nbsp;4.注销旅鼠论坛账号并不代表您注销前的账号行为和相关责任得到豁免或减轻。</p>
                        <br>
                    </div>
                    <div id="revokepasswordpart">
                        <p>此账号密码</p>
                        <input id="revokepassword" class="form-check-input updateinputsize" type="password"
                               name="newpassword" value="" placeholder="请输入密码">
                    </div>
                    <div id="RevokeButton">
                        <button id="rbtn" type="button" onclick="buttonrevoke()" class="btn btn-warning" >我已知悉，确认注销账号</button>
                    </div>
                </form>
            </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
