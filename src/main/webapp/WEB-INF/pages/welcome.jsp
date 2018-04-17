<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Welcome</title>
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
<h1>Hi ${usernameOfCurrentUser}, Welcome to our super-puper secret page!</h1>
<p>Next, you can see the full list of <a href="/showFemales">girls</a> or <a href="/showMales">boys</a></p>
<form:form method="POST" action="/logout">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>