<%@ page import="com.company.entity.users.User" %><%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 21.05.2019
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main menu</title>
</head>
<body>

<h1>You are logged</h1>
<p>Login: <%=request.getAttribute("login") %></p>
<p>Type:  <%=request.getAttribute("type") %></p>


</body>
</html>
