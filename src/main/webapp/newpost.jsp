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
    <link rel="stylesheet" href="topbar/topbar.css">

    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.js"></script>
    <script src="topbar/topbar.js"></script>
<%-- 编辑器需要导入以下文件 --%>
    <link rel="stylesheet" href="vditor/dist/index.css" />
    <script src="vditor/dist/index.min.js"></script>
<%--  --%>
    <script src="js/newpost.js"></script>
    <link rel="stylesheet" href="css/newpost.css">
</head>
<body>
    <div id="topBar"></div>

    <div class="container">
        <form class="container" action="post?type=post" method="post" enctype="multipart/form-data">
            <div class="row">
                <input id="post_title" class="col-xs-12" type="text" name="title" placeholder="请输入标题">
                <h2 class="label_tip col-sm-1">简介：</h2>
                <textarea id="post_brief" class="col-xs-5" name="brief" placeholder="请输入文章简介"></textarea>
                <h2 class="label_tip col-sm-1">封面图：</h2>
                <input type="file" name="cover" accept="image/*">
                <input id="content" name="content" type="hidden">
            </div>
            <div id="editor" class="row vditor"></div>
            <div class="row">
            </div>
            <div class="row submitBox">
                <div class="draftBox">
                    <button type="button" value="存草稿" class="col-lg-1 btn-post col-md-2 col-xs-3  " >存草稿</button>
                </div>
                <input id="submit_btn" type="submit" onclick="return checkSubmit()" class="col-lg-1 btn-post col-md-2 col-md-offset-10 col-xs-3 col-xs-offset-0" value="发布">
            </div>
        </form>
    </div>
</body>
</html>
