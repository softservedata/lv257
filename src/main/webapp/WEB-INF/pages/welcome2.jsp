<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:if test="${pageContext.request.userPrincipal.name == null}">
    <h1>Name is NULL</h1>

    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name == ''}">
        <h1>Name not NULL</h1>
        <h1>${pageContext.request.userPrincipal.name}</h1>

    </c:if>
    <h1>${pageContext.request.userPrincipal.name}</h1>
    <h1>${pageContext.request.userPrincipal.toString()}</h1>

</div>
</body>
</html>