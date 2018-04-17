<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: dlapteacru
  Date: 4/13/2018
  Time: 12:21 AM
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

<div align="center">
    <div style="width: 300px; height: 500px;">
        <div style="width: 300px;">
            <form:form action="/editUser" method="post" modelAttribute="user">
                <div class="container">
                    <div class="require">
                        <input type="text" name="username" value="${user.username}" readonly/>
                    </div><form:errors path="username" cssClass="errors"/>
                    <div class="require">
                        <input id="password" type="password" name="password" placeholder="Password"
                               required="" pattern="(?=.*\d)(?=.*[A-Z])(?=.*[!$^&*()_|={}\[\]:;'?.\/]).{8,}"
                               title="Password must contains at least 8 chars, that includes at least one number,
                                                upper case character and symbol (?=.*[!$^&*()_|={}\[\]:;'?.\/])"/>
                    </div><form:errors path="password" cssClass="errors"/>
                    <div class="require">
                        <input id="confirm_password" type="password" name="confirmPassword" placeholder="Confirm Password"
                               required="" onkeyup="check()"/>
                    </div>
                    <span id="message"></span>
                    <form:errors path="confirmPassword" cssClass="errors"/>
                    <div class="require">
                        <input type="text" name="firstName" placeholder="First name" required="" value="${user.firstName}"/>
                    </div><form:errors path="firstName" cssClass="errors"/>
                    <div class="require">
                        <input type="text" name="lastName" placeholder="Last name" required="" value="${user.lastName}"/>
                    </div><form:errors path="lastName" cssClass="errors"/>
                    <div class="require">
                        <input type="email" name="email" placeholder="Email" required=""
                               pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                               title="characters@characters.domain"
                                value="${user.email}"/>
                    </div><form:errors path="email" cssClass="errors"/>
                    <div>
                        <input type="date" name="dateOfBirth" value="${user.dateOfBirth}"/>
                    </div>
                    <select name="gender" class="selectionGenderForm" required="" class="require">
                        <option value="${user.gender}" selected>${user.gender}</option>
                        <option value="MALE">MALE</option>
                        <option value="FEMALE">FEMALE</option>
                    </select>
                    <p><span style="color: red;">*</span> Required fields</p>
                </div>
                <button type="submit">Save changes</button>
                <a href="/profile"><button type="button">Back to Profile</button></a>
            </form:form>

        </div>
    </div>
</div>


</body>
</html>
