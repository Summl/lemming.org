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
    <% Post post = (Post) request.getAttribute("post"); %>
    <title><%=post.getTitle()%></title>
    <script src="lute/lute.min.js"></script>
    <script src="js/postpage.js"></script>
</head>
<body>
<div id="view">

</div>
<script>
    let httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
    httpRequest.open('GET', 'data/posts/<%=post.getPostFilename()%>', true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
    httpRequest.send();//第三步：发送请求  将请求参数写在URL中
    httpRequest.onreadystatechange = function () {
        document.getElementById("view").innerHTML = mdToHtml(httpRequest.responseText)
    };
</script>
</body>
</html>
