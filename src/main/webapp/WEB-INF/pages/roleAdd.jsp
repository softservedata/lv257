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
                    <li class="active"> <a href="${pageContext.request.contextPath}/roles">Roles</a></li>
                    <li> <a href="${pageContext.request.contextPath}/privileges">Privileges</a></li>
                </ul>
            </div>
            <br/>
            <div class="container">
                <h2>Adding new Role</h2>
            </div>
            <div class="container">
                <h1>My test Table</h1>

            </div>

            <div class="container">
                <div class="table-responsive">
                    <form>
                        <div class="form-group">
                            <label for="usr">Name:</label>
                            <input type="text" class="form-control" id="usr">
                        </div>

                        <table class="table table-striped">
                            <tbody>
                            <c:if test="${not empty list}">
                                <c:forEach var="listValue" items="${list}">
                                    <tr>
                                        <td>${listValue}</td>
                                        <td><label class="checkbox-inline"><input type="checkbox" value="">Give access</label></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <button type="button" class="btn btn-primary btn-md">Add Role</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>