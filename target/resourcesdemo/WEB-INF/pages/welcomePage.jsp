<%@page session="false"%>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />
<br>
<br>
<br>
<div class="container-fluid">
    <h1>${message}</h1>
</div>
</body>
</html>