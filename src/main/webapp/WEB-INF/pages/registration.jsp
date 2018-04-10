<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style type="text/css">
        <%@include file="../resources/style.css"%>
    </style>
</head>
<body>
<div align="center">
    <div style="width: 300px; height: 500px;">
        <form:form method="POST" modelAttribute="userForm">
            <h2 align="center" style="color:maroon;">Create your account</h2>
            <div class="container" align="left">

                    <input type="text" name="username" placeholder="Username" pattern="[A-Za-z]{5,10}"
                           autofocus="true" title="Please enter 5-10 characters. Only letters."/>

                    <input type="password" name="password" placeholder="Password" required=""/>

                    <input type="password" name="confirmPassword"
                            placeholder="Confirm your password" required=""/>
                    <h3 style="color: red;">${messageErrorPassConfirm}</h3>

                    <select name="gender" class="selForm">
                        <option value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                    </select>


                    <button type="submit">Submit</button>

                <a href="/login"><button type="button">Back to Login page</button></a>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
