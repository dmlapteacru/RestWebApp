<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dlapteacru
  Date: 4/13/2018
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>
    <style type="text/css">
        <%@include file="../resources/style.css"%>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
</head>
<body>
<div class="menu_items">
    <a href="/allusers">Home</a>
    <a href="/profile?id=${userId}">Profile</a>
    <a href="">Admin Panel</a>
    <a href="/secret">Secret</a>
</div>
<div class="wrapper">
    <div class="container">
        <table class="table">
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th><a href="/newuser"><img src="../resources/add_blue.png" class="editable"></a></th>
            <c:forEach items="${list}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="/editUser/${user.id}"><img src="../resources/edit_icon.png" class="editable"></a>
                        <a href="/deleteUser?id=${user.id}"><img src="../resources/Delete_Icon.png" class="editable"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
