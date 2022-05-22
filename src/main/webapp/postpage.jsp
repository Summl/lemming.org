<%@ page import="com.lemming.lemming.bean.Post" %>
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
    <script src="lute/lute.min.js"></script>
    <script src="js/postpage.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="topbar/topbar.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.js"></script>
    <script src="topbar/topbar.js"></script>
    <link rel="stylesheet" href="css/postpage.css">
    <link rel="stylesheet" href="bootstrap-icons/bootstrap-icons.css">

    <script>
        function aa(){
            document.body.scrollTop=0;
        }
    </script>
    <title>旅鼠论坛</title>
</head>
<body>
<div id="topBar"></div>
<div class="container">
<div id="head">
    <% Post post = (Post) request.getAttribute("post"); %>
    <h1><%=post.getTitle()%></h1>
</div>
<div id="view">
</div>
</div>
<div id="jt" ><img src="images/jt.png" onclick="aa()"></div>
<script>
    // 加载页面内容
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'data/posts/<%=post.getPostFilename()%>', true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    httpRequest.onreadystatechange = function (){
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            document.getElementById("view").innerHTML = mdToHtml(httpRequest.responseText)
        }
    };
</script>
</body>
</html>
