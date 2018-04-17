<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mbezaliuc
  Date: 10/10/2017
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
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
<h1>SECRET</h1>
<sec:authorize url="/admin">
    This content will only be visible to users who are authorized to send requests to the "/admin" URL.
</sec:authorize>
<form:form method="POST" action="/logout">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>
