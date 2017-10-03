<html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
    <title>Bad User</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />


<h3><spring:message code="auth.message.expired"/></h3>
<h3><spring:message code="auth.message.invalidToken"/></h3>
<h1>badUser</h1>
</body>
</html>