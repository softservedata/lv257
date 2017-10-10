<%@page session="false"%>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="../metadata.jsp"/>
</head>
<body>
<jsp:include page="../menu.jsp" />
<br>
<br>
<br>
<div class="container-fluid">
    ${activeResType.toString()}
    ${email}

</div>
</body>
</html>