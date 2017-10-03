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
                    <li> <a href="${pageContext.request.contextPath}/users">Users</a></li>
                    <li> <a href="${pageContext.request.contextPath}/roles">Roles</a></li>
                    <li> <a href="${pageContext.request.contextPath}/privileges">Privileges</a></li>
                    <li class="active"> <a href="${pageContext.request.contextPath}/privileges">Add privileges</a></li>
                </ul>
            </div>
            <br/>
            <div class="container">
                <h2>Adding new Privilege</h2>
            </div>
            <div class="container">

            </div>

        </div>
    </div>
</div>
</body>
</html>