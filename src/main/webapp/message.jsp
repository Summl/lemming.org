<%--
  Created by IntelliJ IDEA.
  User: Summ
  Date: 2022/5/18
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>message</title>
</head>
<body>
<%
    String title = (String) request.getAttribute("title");
    String content = (String) request.getAttribute("content");
%>
<div class="page" id="messagePage">
    <h1><%=title%></h1>
    <h1><%=content%></h1>
    <a href="index.jsp" id="return">返回首页</a>
</div>
</body>
</html>
