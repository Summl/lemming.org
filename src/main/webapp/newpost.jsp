<%--
  Created by IntelliJ IDEA.
  User: maicss
  Date: 2022/5/19
  Time: 下午1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发表新文章</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.js"></script>
<%-- 编辑器需要导入以下文件 --%>
    <link rel="stylesheet" href="vditor/index.css" />
    <script src="vditor/index.min.js"></script>
<%--  --%>
    <script src="js/newpost.js"></script>
    <link rel="stylesheet" href="css/newpost.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <h1 class="col-xs-12">发表文章</h1>
        </div>
        <form class="container" action="post?type=post" method="post">
            <div class="row">
                <input id="title" class="col-xs-12" type="text" name="title" placeholder="请输入标题">
                <input id="content" name="content" type="hidden">
            </div>
            <div id="editor" class="row vditor"></div>
            <div class="row submitBox">
                <input type="submit" onclick="return updateContent()" class="col-lg-1 btn-post col-lg-offset-11 col-md-2 col-md-offset-10 col-xs-12 col-xs-offset-0" value="发布">
            </div>
        </form>
    </div>
</body>
</html>