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
        <h2>Sing up/Registration</h2>
        <form action="/signup" method="post">
            <div class="form-group" style="width: 400px;">
                <label for="email" style="float: left;">Login/Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter login/email" name="email">
            </div>
            <div class="form-group" style="width: 400px;">
                <label for="password" style="float: left;">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
            </div>
            <div class="form-group" style="width: 400px;">
                <label for="matchingPassword" style="float: left;">Repeat password:</label>
                <input type="password" class="form-control" id="matchingPassword" placeholder="Repeat password" name="matchingPassword">
            </div>
            <button type="submit" class="btn btn-default">Create account</button>
        </form>
    </center>
</div>
</body>
</html>