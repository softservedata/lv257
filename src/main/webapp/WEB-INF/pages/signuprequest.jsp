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
        <div style="border: 1px groove gray; border-radius: 5px; padding: 5px; width: 400px;">
            <h3>You registered successfully!</h3>
            <h3>We will send you a confirmation message to your email account.</h3>
            <%--<button type="submit" class="btn btn-default">Ok</button><br><br>--%>
        </div>
    </center>
</div>
</body>
</html>