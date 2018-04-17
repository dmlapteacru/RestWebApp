<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dlapteacru
  Date: 4/13/2018
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
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
<div class="wrapper">
    <div class="profile">
        <form:form action="/profile" method="get" modelAttribute="user">
            <div class="container">
                <p><b>Your personal info:</b></p>
                <p><b>Username:</b> ${user.username}</p>
                <p><b>First Name:</b> ${user.firstName}</p>
                <p><b>Last Name:</b> ${user.lastName}</p>
                <p><b>Email: </b> ${user.email}</p>
                <p><b>Date of birth:</b> ${user.dateOfBirth}</p>
                <p><b>Gender:</b> ${user.gender}</p>
            </div>
        </form:form>

        <a href="/editUser/${user.id}"><button>EDIT PROFILE</button></a>
    </div>
</div>

</body>
</html>
