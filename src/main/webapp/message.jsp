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
    String context = (String) request.getAttribute("context");
%>
<div class="page" id="messagePage">
    <h1><%=title%></h1>
    <h1><%=context%></h1>
    <a href="loginPage.jsp" id="return">返回</a>
</div>
</body>
</html>
