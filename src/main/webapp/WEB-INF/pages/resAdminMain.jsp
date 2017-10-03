<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.09.2017
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>

</head>

<body>


<jsp:include page="_menu2.jsp"/>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${pageContext.request.contextPath}/resources/addType">Add</a></li>
        <li><a href="${pageContext.request.contextPath}/resources/requests">Request</a></li>
    </ul>
</div>
</body>
</html>
