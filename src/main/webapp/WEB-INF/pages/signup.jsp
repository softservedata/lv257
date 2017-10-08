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



<br>
<div class="container-fluid">
    <div class="row" style="text-align: left;">

        <div class="col-md-8" style="display: inline-block; text-align: center;float: left">
            <br>
            <br>
            <h1>Welcome to Resources</h1>
            <h5>Resources is non-commercial government service for managing and keeping information</br> about resources registered and located in Ukraine</h5>
<br>


            <img width="100%" height="auto" src="/resources/img/welcome.png">
        </div>


        <div class="col-md-4" style="float: left;">
            <center>
                <h3>Registration form</h3>
        <form:form modelAttribute="newUser" method="post" style="border: 1px groove gray; border-radius: 5px; padding: 5px; width: 280px" >
<br>

            <div class="form-group" style="width: 250px;">
                <label for="email" style="float: left;">Login/Email:</label>
                <form:input class="form-control" placeholder="Enter login/email" path="email"/>
                <form:errors path="email" cssClass="error" cssStyle="color: #ff0000;"/>
            </div>
            <div class="form-group" style="width: 250px;">
                <label for="password" style="float: left;">Password:</label>
                <form:input type="password" class="form-control" placeholder="Enter password" path="password"/>
                <form:errors path="password" cssClass="error" cssStyle="color: #ff0000;"/>
            </div>
            <div class="form-group" style="width: 250px;">
                <label for="confirmPassword" style="float: left;">Repeat password:</label>
                <form:input type="password" class="form-control" placeholder="Repeat password" path="confirmPassword"/>
                <form:errors path="confirmPassword" cssClass="error" cssStyle="color: #ff0000;"/>
            </div>
            <button type="submit" class="btn btn-default">Create account</button>
            <br>
            <br>

        </form:form>
            </center>

    </div>
    </div>


    <%--<footer class="footer">--%>
        <%--<div class="container">--%>
            <%--<p class="text-muted">&copy; Lv257_Java</p>--%>
        <%--</div>--%>
    <%--</footer>--%>
</div>
<jsp:include page="${contextPath}footer.jsp"/>

</body>
</html>