<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head><title>Sign Up</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<br/>

<br><br><br><br>
<div class="container-fluid">
    <center>
        <h2>Registration form</h2>
        <form:form modelAttribute="newUser" method="post">
            <div class="form-group" style="width: 400px;">
                <label for="email" style="float: left;">Login/Email:</label>
                <form:input class="form-control" placeholder="Enter login/email" path="email"/>
                <form:errors path="email" cssClass="error" cssStyle="color: #ff0000;"/>
            </div>
            <div class="form-group" style="width: 400px;">
                <label for="password" style="float: left;">Password:</label>
                <form:input type="password" class="form-control" placeholder="Enter password" path="password"/>
                <form:errors path="password" cssClass="error" cssStyle="color: #ff0000;"/>
            </div>
            <div class="form-group" style="width: 400px;">
                <label for="confirmPassword" style="float: left;">Repeat password:</label>
                <form:input type="password" class="form-control" placeholder="Repeat password" path="confirmPassword"/>
                <form:errors path="confirmPassword" cssClass="error" cssStyle="color: #ff0000;"/>
            </div>
            <button type="submit" class="btn btn-default">Create account</button>
        </form:form>
    </center>

    <footer class="footer">
        <div class="container">
            <p class="text-muted">&copy; Lv257_Java</p>
        </div>
    </footer>
</div>
</body>
</html>