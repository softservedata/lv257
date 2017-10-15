<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
    <title>Logout</title>
    <jsp:include page="metadata.jsp"/>
    <style>

    </style>
</head>
<body>
<jsp:include page="menu.jsp" />
<div class="log-out-message">

    <h2>Logging out was successful</h2>
    <h4>You are welcome to visit us again</h4>

    <img src="${contextPath}/resources/img/welcome.png" width="100%">
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>