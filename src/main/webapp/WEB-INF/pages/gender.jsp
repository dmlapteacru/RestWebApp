<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Here are all our ${gender}:</h2>
<c:forEach items="${list}" var="user">
    <p>Name: ${user.username} | Password: ${user.password}</p>
</c:forEach>
<form:form method="POST" action="/logout">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>
