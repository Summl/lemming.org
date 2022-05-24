<%@ page import="com.lemming.lemming.bean.Post" %>
<%@ page import="com.lemming.lemming.dao.UserDao" %>
<%@ page import="com.lemming.lemming.bean.User" %>
<%--
  Created by IntelliJ IDEA.
  User: maicss
  Date: 2022/5/19
  Time: 下午5:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Post post = (Post) request.getAttribute("post");
        User user = UserDao.getUserById(post.getUserId());

        assert user != null;
    %>
    <script src="lute/lute.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="topbar/topbar.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.js"></script>
    <script src="topbar/topbar.js"></script>
    <link rel="stylesheet" href="css/postpage.css">
    <link rel="stylesheet" href="bootstrap-icons/bootstrap-icons.css">
    <script src="js/postpage.js"></script>
    <title><%=post.getTitle()%> - 旅鼠论坛</title>
</head>
<body>
<div id="topBar"></div>
<div class="container">

    <div class="content_left col-sm-9">
        <div id="head">
            <h1><%=post.getTitle()%></h1>
            <p><%=post.getReadNum()%> 次浏览 · <span id="like_number"><%=post.getLikeNum()%></span> 次点赞</p>
        </div>
        <div id="view">
        </div>

        <div class="toolbar">
            <button id="btn_like"><i class="bi bi-heart-fill"></i>点赞</button>
            <button id="btn_delete">删除</button>
        </div>
    </div>

    <div class="col-sm-3">
        <div id="user_show" >
            <h3><%=user.getUserName()%></h3>
            <p>Email：<%=user.getEmail()%></p>
            <p>注册时间：<%=user.getRegistrationDate()%></p>
        </div>
    </div>
</div>
<script>
    let postID = <%=post.getId()%>
    // 加载页面内容
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'data/posts/<%=post.getPostFilename()%>', true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    httpRequest.onreadystatechange = function (){
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            document.getElementById("view").innerHTML = mdToHtml(httpRequest.responseText)
        }else if(httpRequest.status === 404){
            window.location.replace("notfound.jsp")
        }
    };
</script>
</body>
</html>
