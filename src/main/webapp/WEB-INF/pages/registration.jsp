<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style type="text/css">
        <%@include file="../resources/style.css"%>
        .errors {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div align="center">
    <div style="width: 300px; height: 500px;">
            <div style="width: 300px;">
                <form:form action="/registration" method="post" modelAttribute="user">
                    <div class="container">
                        <div class="require">
                            <input type="text" name="username" placeholder="Username"
                            required="" pattern="[A-Za-z0-9]{2,255}" autofocus="true"
                                        title="Please enter at least 2 characters."/>
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
                             <input type="text" name="firstName" placeholder="First name" required=""/>
                         </div><form:errors path="firstName" cssClass="errors"/>
                         <div class="require">
                             <input type="text" name="lastName" placeholder="Last name" required=""/>
                         </div><form:errors path="lastName" cssClass="errors"/>
                         <div class="require">
                             <input type="email" name="email" placeholder="Email" required=""
                                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                                    title="characters@characters.domain"/>
                         </div><form:errors path="email" cssClass="errors"/>
                         <div>
                             <input type="date" name="dateOfBirth"/>
                         </div>
                         <select name="gender" class="selectionGenderForm" required="" class="require">
                             <option value="" disabled selected>Gender</option>
                             <option value="MALE">Male</option>
                             <option value="FEMALE">Female</option>
                         </select>
                        <p><span style="color: red;">*</span> Required fields</p>
                    </div>
                    <button type="submit">Submit</button>
                    <a href="/login"><button type="button">Back to Login page</button></a>
                </form:form>
            </div>
    </div>
</div>

<script>
var check = function() {
if (document.getElementById('password').value ==
document.getElementById('confirm_password').value) {
document.getElementById('message').style.color = 'green';
document.getElementById('message').innerHTML = 'Passwords are matching';
} else {
document.getElementById('message').style.color = 'red';
document.getElementById('message').innerHTML = 'Passwords are not matching';
}
}
</script>
</body>
</html>
