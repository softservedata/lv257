<%@page session="false" %>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp"/>
<br>
<div class="container">

    <div align="center">
        <h1>${message}</h1>
        <h5>Resources is non-commercial government service for managing and keeping information</br> about resources
            registered and located in Ukraine</h5>
    </div>
    <div>
        <img width="100%" height="auto" src="/resources/img/welcome.png">
    </div>


</div>
<jsp:include page="${contextPath}footer.jsp"/>

</body>
</html>