<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
    <%--<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="_menu2.jsp"/>

            <div class="container">

                <ul class="nav nav-tabs">
                    <li><a href="${pageContext.request.contextPath}/resources/registration">Register
                        resource</a></li>
                    <li><a href="${pageContext.request.contextPath}/resources/request">Send request</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/resources/story">History</a></li>
                </ul>
                <br>

                <ul class="nav nav-pills">
                    <li class="active"><a href="#NeedMoreDetails" data-toggle="pill">Need more details</a></li>
                    <li ><a href="#NotReviewed" data-toggle="pill">Not reviewed</a></li>
                    <li><a href="#Accepted" data-toggle="pill">Accepted</a></li>
                    <li><a href="#Declined" data-toggle="pill">Declined</a></li>
                </ul>

                <div class="tab-content" id="TabContent">

                    <div class="tab-pane fade in active" id="NeedMoreDetails">
                        <div class="table-responsive">
                            <h3>List of requests</h3>
                            <br>
                            <table data-toggle="table" id="refinementRequest"
                                   class="table table-hover table-condensed text-center table-bordered display">
                                <br>
                                <thead>
                                <tr>

                                    <th data-field="requestedCategory">
                                        <div class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register">
                                        <div class="text-center">Who reviewed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="documentLink">
                                        <div class="text-center">Information</div></th>
                                    <th data-field="date">
                                        <div class="text-center">Date</div></th>
                                    <th ></th>
                                </tr>
                                </thead>


                                <tbody>
                                 <c:forEach items="${refinementRequest}" var="request">

                                <tr>

                                    <td>${request.resourceType}</td>
                                    <td>${request.resourcesAdmin.username}</td>
                                    <td><a href="/resources/info/${request.id}">Info about request</a></td>
                                    <td>${request.update.toString().split('\\.')[0]}</td>
                                    <td>
                                        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal">Responce</button>
                                        <div id="myModal" class="modal fade">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        <h4 class="modal-title " style="text-align: left">Response</h4>
                                                        <h6 class="modal-title"  style="text-align: left">Comment:</h6>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form>
                                                            <div class="form-group">

                                                                <textarea class="form-control" rows="5"></textarea>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer" >

                                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Send</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                 </c:forEach>
                                </tbody>
                            </table>

                                </tbody>

                        </div>

                    </div>

                    <div class="tab-pane fade" id="Accepted">

                        <div class="table-responsive">
                            <h3>List of processed requests</h3>
                            <br>
                            <table data-toggle="table" id="acceptedRequest"
                                   class="table table-hover table-condensed text-center table-bordered display">
                                <br>
                                <thead>
                                <tr>
                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Details</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Date</div>
                                        <div class="fht-cell"></div></th>
                                </tr>
                                </thead>
                                <tbody>

                                <tbody>
                                <c:forEach items="${acceptedRequest}" var="request">
                                    <tr>
                                        <td>${request.resourceType}</td>
                                        <td>${request.resourcesAdmin.username}</td>
                                        <td><a href="/resources/info/${request.id}">Info about request</a></td>
                                        <td>${request.update.toString().split('\\.')[0]}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                                </tbody>
                            </table>
                        </div>

                    </div>

                    <div class="tab-pane fade" id="Declined">

                        <div class="table-responsive">
                            <h3>List of processed requests</h3>

                            <br>
                            <table data-toggle="table" id="declinedRequest"
                                   class="table table-hover table-condensed text-center table-bordered display">
                                <br>
                                <thead>
                                <tr>

                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Details</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Date</div>
                                        <div class="fht-cell"></div></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${declinedRequest}" var="request">
                                    <tr>
                                        <td>${request.resourceType}</td>
                                        <td>${request.resourcesAdmin.username}</td>
                                        <td><a href="/resources/info/${request.id}">Info about request</a></td>
                                        <td>${request.update.toString().split('\\.')[0]}</td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="NotReviewed">

                            <h3>List of requests</h3>
                        <br>
                            <table data-toggle="table" id="newRequest"
                                   class="table table-hover table-condensed text-center table-bordered display">
                                <br>
                                <thead>
                                <tr>

                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Details</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Updated</div>
                                        <div class="fht-cell"></div></th>

                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${newRequest}" var="request">
                                    <tr>
                                        <td>${request.resourceType}</td>
                                        <td>${request.resourcesAdmin.username}</td>
                                        <td><a href="/resources/info/${request.id}">Info about request</a></td>
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

    $(document).ready(function() {
        $('#newRequest').DataTable();
    } );
    $(document).ready(function() {
        $('#declinedRequest').DataTable();
    } );
    $(document).ready(function() {
        $('#acceptedRequest').DataTable();
    } );
    $(document).ready(function() {
        $('#refinementRequest').DataTable();
    } );

</script>

</body>
</html>
