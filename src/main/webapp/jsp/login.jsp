<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<c:set var="language" value="${not empty param.language ? param.language : 'en'}" scope="session"/>--%>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="TextBundle"/>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="" method="post">
    <input type="hidden" name="action" value="lang">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
    </select>
</form>


<c:if test="${notfound == 1}">
    <p style="color: red">Not found</p>
</c:if>
<form action="" method="post">
    <input type="hidden" name="action" value="login">
    <table style="with: 50%">
        <tr>
            <td><fmt:message key="login"/></td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td><fmt:message key="password"/></td>
            <td><input type="password" name="password"/></td>
        </tr>
    </table>
    <input type="submit" value=<fmt:message key="enter"/>>
</form>

</body>
</html>
