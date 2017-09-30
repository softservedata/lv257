 <%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />
<br>
<div class="container-fluid">
    <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
        <div class="container">
            <ul class="nav nav-tabs">
                <li> <a href="${pageContext.request.contextPath}/users">Users</a></li>
                <li> <a href="${pageContext.request.contextPath}/roles">Roles</a></li>
                <li class="active"> <a href="${pageContext.request.contextPath}/privileges">Privileges</a></li>
                <%--<li> <a href="UsersRequests.html">Request <span class="badge">3</span></a></li>--%>
            </ul>
        </div>

        <div class="container">
            <div class="table-responsive">
                <%--<h2>Privileges list</h2>--%>
                    <h2>System privileges</h2>
                    <div class="container">
                        <table class="table">
                            <tbody>
                        <c:forEach var="systemPrivilege" items="${list}">
                            <c:if test="${systemPrivilege.privilegeType=='SYSTEM'}">
                                <tr>
                                    <th>${systemPrivilege.name}</th>
                                    <th><input type="checkbox"></th>
                                </tr>
                            </c:if>
                        </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <h2>Resource types privileges</h2>
                    <div class="container">
                        <table class="table">
                            <thead>
                                <th></th>
                                <th>Read</th>
                                <th>Create</th>
                                <th>Update</th>
                                <th>Delete</th>
                                <th>All actions</th>
                            </thead>
                            <tr>
                                <th>All resource types*</th>
                                <th><input type="checkbox"></th>
                                <th><input type="checkbox"></th>
                                <th><input type="checkbox"></th>
                                <th><input type="checkbox"></th>
                                <th><input type="checkbox"></th>
                            </tr>
                            <tbody>
                            <c:forEach var="systemPrivilege" items="${list}">
                                <c:if test="${systemPrivilege.privilegeType=='RESOURCE_TYPE'}">
                                    <tr>
                                        <th>${systemPrivilege.name}</th>
                                        <th><input type="checkbox"></th>
                                        <th><input type="checkbox"></th>
                                        <th><input type="checkbox"></th>
                                        <th><input type="checkbox"></th>
                                        <th><input type="checkbox"></th>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <h2>Resource properties privileges</h2>
                    <div class="container">
                        <table class="table">
                            <thead>
                            <th></th>
                            <th>Read</th>
                            <th>Update</th>
                            </thead>
                            <tr>
                                <th>All resource properties*</th>
                                <th><input type="checkbox"></th>
                                <th><input type="checkbox"></th>
                            </tr>
                            <tbody>
                            <c:forEach var="systemPrivilege" items="${list}">
                                <c:if test="${systemPrivilege.privilegeType=='PROPERTY'}">
                                    <tr>
                                        <th>${systemPrivilege.name}</th>
                                        <th><input type="checkbox"></th>
                                        <th><input type="checkbox"></th>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Edit</th>
                        <th>Clone</th>
                        <th>Delete</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty list}">

                        <ul>
                            <c:forEach var="listValue" items="${list}">
                                <tr>
                                    <td><a href="">${listValue}</a></td>
                                    <td><a href="">${listValue.privilegeType}</a></td>
                                    <td ><a href="">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </a></td>
                                    <td ><a href="#">
                                        <span class="glyphicon glyphicon-copy"></span>
                                    </a></td>
                                    <td ><a href="#">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </a></td>
                                </tr>
                            </c:forEach>
                        </ul>

                    </c:if>
                    </tbody>
                </table>
            </div>

        </div>

    </div>
</div>
</body>
</html>