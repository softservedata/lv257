<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head><title>Sign Up</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp" />

<br/>

<br><br><br><br>
<div class="container-fluid">
    <center>
        <div style="border: 1px groove gray; border-radius: 5px; padding: 5px; width: 400px;">
            <h3>${message}</h3>
        </div>
    </center>
</div>
</body>
</html>