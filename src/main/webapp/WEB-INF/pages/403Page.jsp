<%@page session="false"%>
<html>
<head>
    <title>Access Denied</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp"/>

<h3 style="color:red;">${message}</h3>
</body>
</html>