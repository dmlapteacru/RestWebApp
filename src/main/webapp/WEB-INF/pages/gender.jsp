<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        <%@include file="../resources/style.css"%>
    </style>
</head>
<body>
<div class="menu_items">
    <a href="/allusers">Home</a>
    <a href="/profile?id=${userId}">Profile</a>
    <a href="/adminpanel">Admin Panel</a>
    <a href="/secret">Secret</a>
</div>
<h2>Here are all our ${gender}:</h2>
<c:forEach items="${list}" var="user">
    <p>Name: ${user.username} | Password: ${user.password}</p>
</c:forEach>
<form:form method="POST" action="/logout">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>
