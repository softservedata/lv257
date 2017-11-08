<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <%--&lt;%&ndash;<jsp:include page="metadata.jsp"/>&ndash;%&gt;--%>
    <%--<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" rel="stylesheet">--%>
    <meta charset="UTF-8">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../pages/menu.jsp" />

<br>
<br>
<br>
<div class="container">

    <ul class="nav nav-tabs">
        <li class="active"><a href="${pageContext.request.contextPath}/users">Users</a></li>
        <li><a href="${pageContext.request.contextPath}/roles">Roles</a></li>
        <li><a href="${pageContext.request.contextPath}/privileges">Privileges</a></li>
    </ul>
    <br>

<%--    <ul class="nav nav-pills">
        <li class="active"><a href="#NeedMoreDetails" data-toggle="pill">Need more details</a></li>
        <li><a href="#NotReviewed" data-toggle="pill">Not reviewed</a></li>
        <li><a href="#Accepted" data-toggle="pill">Accepted</a></li>
        <li><a href="#Declined" data-toggle="pill">Declined</a></li>
    </ul>--%>

    <div class="tab-content" id="TabContent">

        <div class="tab-pane fade in active" id="NeedMoreDetails">

            <h3>List of users</h3>
            <br>
            <table data-toggle="table" id="refinementRequest"
                   class="table table-striped table-bordered text-center" cellspacing="0" width="100%">
                <br>
                <thead>
                <tr>
                    <th>Id</th>
                    <%--<th>Full name</th>--%>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Enabled</th>
                    <%--<th>Last visit</th>--%>
                    <th>Edit</th>
                    <th>Clone</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="listValue" items="${users}">
                    <tr>
                        <td>${listValue.id}</td>
                        <%--<td>Full name</td>--%>
                        <td>${listValue.username}</td>
                        <td><c:forEach var="role" items="${listValue.roles}">
                            ${role.name}
                        </c:forEach>
                        </td>
                        <c:if test="${listValue.enabled=='true'}">
                            <td>
                                <div class="checkbox" >
                                    <label><input type="checkbox" value="" checked disabled></label>
                                </div>
                            </td>
                        </c:if>
                        <c:if test="${listValue.enabled=='false'}">
                            <td>
                                <div class="checkbox">
                                    <label><input type="checkbox" value="" disabled></label>
                                </div>
                            </td>
                        </c:if>
                        <%--<td>${listValue.password}</td>--%>
                        <td ><a href="${pageContext.request.contextPath}/userEdit?uid=${listValue.id}">
                            <span class="glyphicon glyphicon-edit"></span>
                        </a></td>
                        <td ><a href="#">
                            <span class="glyphicon glyphicon-copy"></span>
                        </a></td>
                            <%--<td ><a href="#">
                                <span class="glyphicon glyphicon-remove"></span>
                            </a></td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            </tbody>


        </div>

        <div class="tab-pane fade" id="Accepted">

<%--3 vkladka--%>
            <h3>List of processed requests 3</h3>
            <br>
            <table data-toggle="table" id="acceptedRequest"
                   class="table table-striped table-bordered text-center" cellspacing="0" width="100%">
                <br>
                <thead>
                <tr>
                    <th data-field="requestedCategory">
                        <div
                                class="text-center">RequestedCategory
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="register">
                        <div
                                class="text-center">Who processed
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="resourcesAdmin">
                        <div
                                class="text-center">Details
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="status">
                        <div
                                class="text-center">Date
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th data-field="requestedCategory">
                        <div
                                class="text-center">RequestedCategory
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="register">
                        <div
                                class="text-center">Who processed
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="resourcesAdmin">
                        <div
                                class="text-center">Details
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="status">
                        <div
                                class="text-center">Date
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                </tr>
                </tfoot>


                <tbody>
                <c:forEach items="${acceptedRequest}" var="request">
                    <tr>
                        <td>${request.resourceType}</td>
                        <td>${request.resourcesAdmin.username}</td>
                        <td><a href="${pageContext.request.contextPath}/resources/info/${request.id}">Info about
                            request</a></td>
                        <td>${request.update.toString().split('\\.')[0]}</td>
                    </tr>
                </c:forEach>
                </tbody>

                </tbody>
            </table>


        </div>

        <div class="tab-pane fade" id="Declined">


            <h3>List of processed requests 4</h3>

            <br>
            <table data-toggle="table" id="declinedRequest"
                   class="table table-striped table-bordered text-center" cellspacing="0" width="100%">
                <br>
                <thead>
                <tr>
                    <th data-field="requestedCategory">
                        <div
                                class="text-center">RequestedCategory
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="register">
                        <div
                                class="text-center">Who processed
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="resourcesAdmin">
                        <div
                                class="text-center">Details
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="status">
                        <div
                                class="text-center">Date
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th data-field="requestedCategory">
                        <div
                                class="text-center">RequestedCategory
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="register">
                        <div
                                class="text-center">Who processed
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="resourcesAdmin">
                        <div
                                class="text-center">Details
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="status">
                        <div
                                class="text-center">Date
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                </tr>
                </tfoot>

                <tbody>

                <c:forEach items="${declinedRequest}" var="request">
                    <tr>
                        <td>${request.resourceType}</td>
                        <td>${request.resourcesAdmin.username}</td>
                        <td><a href="${pageContext.request.contextPath}/resources/info/${request.id}">Info about
                            request</a></td>
                        <td>${request.update.toString().split('\\.')[0]}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>

        <div class="tab-pane fade" id="NotReviewed">

            <h3>List of requests 2</h3>
            <br>
            <table data-toggle="table" id="newRequest"
                   class="table table-striped table-bordered text-center" cellspacing="0" width="100%">
                <br>
                <thead>
                <tr>
                    <th data-field="requestedCategory">
                        <div
                                class="text-center">RequestedCategory
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="register">
                        <div
                                class="text-center">Who processed
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="resourcesAdmin">
                        <div
                                class="text-center">Details
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="status">
                        <div
                                class="text-center">Updated
                        </div>
                        <div class="fht-cell"></div>
                    </th>

                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th data-field="requestedCategory">
                        <div
                                class="text-center">RequestedCategory
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="register">
                        <div
                                class="text-center">Who processed
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="resourcesAdmin">
                        <div
                                class="text-center">Details
                        </div>
                        <div class="fht-cell"></div>
                    </th>
                    <th data-field="status">
                        <div
                                class="text-center">Updated
                        </div>
                        <div class="fht-cell"></div>
                    </th>

                </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${newRequest}" var="request">
                    <tr>
                        <td>${request.resourceType}</td>
                        <td>${request.resourcesAdmin.username}</td>
                        <td><a href="${pageContext.request.contextPath}/resources/info/${request.id}">Info about
                            request</a></td>
                        <td>${request.update.toString().split('\\.')[0]}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<br>
<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy; Lv257_Java</p>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>

<script>

    $(".fixed-table-container").remove();
    $(".fixed-table-body").remove();

    $(document).ready(function () {
        $('#newRequest').DataTable();
    });
    $(document).ready(function () {
        $('#declinedRequest').DataTable();
    });
    $(document).ready(function () {
        $('#acceptedRequest').DataTable();
    });
    $(document).ready(function () {
        $('#refinementRequest').DataTable();
    });

</script>

</body>
</html>
