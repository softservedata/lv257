<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>User Info</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<h2>User Info Page</h2>

<h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>

<b>This is protected page!</b>
</body>
</html>