<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<br />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
            <div class="container">
                <ul class="nav nav-tabs">
                    <li class="active"> <a href="${pageContext.request.contextPath}/users">Users</a></li>
                    <li > <a href="${pageContext.request.contextPath}/roles">Roles</a></li>
                    <li> <a href="${pageContext.request.contextPath}/privileges">Privileges</a></li>
                    <li> <a href="UsersRequests.html">Request <span class="badge">3</span></a></li>
                </ul>
            </div>
            <br/>
            <div class="container">
                <h2>Edditing user</h2>
            </div>
            <div class="container">
                <p>${uid}</p>
                <p>${user.getId()}</p>
                <p>${user.getUsername()}</p>
                <p>${user.getPassword()}</p>

            </div>
        </div>
    </div>
</div>
</body>
</html>