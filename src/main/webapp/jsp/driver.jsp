<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 21.05.2019
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main menu</title>
</head>
<body>
<h1>Welcome, ${name} ${surname}</h1>

<c:if test="${route < 0}">
    <p>You are not assigned to work place yet</p>
</c:if>
<c:if test="${route > 0}">
    <p>Your work place:</p>
    <P>Bus: ${plate}</P>
    <p>Your route: ${route}</p>
    <c:if test="${not accepted}">
    <form action = "/driver" method="post">
        <input type="submit" value="Approve work place">
    </form>
    </c:if>
</c:if>


</body>
</html>
