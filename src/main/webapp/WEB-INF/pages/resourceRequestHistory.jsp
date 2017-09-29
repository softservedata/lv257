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
    <script src="//code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/dataTables.foundation.min.js"></script>

</head>

<body>


<jsp:include page="_menu2.jsp"/>

<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${pageContext.request.contextPath}/resources/addType">Add</a></li>
        <li class="active"><a href="#">Requests</a></li>
    </ul>
</div>
<br>
<div class="container">

    <div class="table-responsive">
        <h2>List of processed Resource Type requests</h2>

        <table class="table table-hover table-condensed text-center table-bordered" id="requests">
            <thead>
            <tr>
                <th>
                    <div class="text-center ">
                        RequestedCategory
                    </div>

                </th>
                <th>
                    <div
                            class="text-center ">Who requested
                    </div>
                    <div class="fht-cell"></div>

                </th>
                <th>
                    <div
                            class="text-center ">Date
                    </div>
                    <div class="fht-cell"></div>
                </th>
                <th>
                    <div
                            class="text-center">Who is processing
                    </div>
                    <div class="fht-cell"></div>
                </th>

                <th>
                    <div
                            class="text-center">Status
                    </div>
                    <div class="fht-cell"></div>
                </th>
            </tr>
            <tr>

                <td><input type="text" class="form-control" id="searchReqCat" placeholder="Requested category"></td>
                <td><input type="text" class="form-control" id="searchRegister" placeholder="Register"></td>
                <td><input type="text" class="form-control" id="searchDate" placeholder="Date"></td>
                <td><input type="text" class="form-control" id="searchResAdm" placeholder="Resource Admin"></td>
                <td><input type="text" class="form-control" id="searchStatus" placeholder="Status"></td>

            </tr>
            </thead>
            <tbody>


            <c:forEach items="${resourceRequest}" var="request">
                <tr name="simpleRequest" style="visibility: visible">

                    <td>${request.resourceType}</td>
                    <td>${request.register.username}</td>
                    <td>${request.update}</td>
                    <td>${request.resourcesAdmin.username}</td>
                    <td>${request.status}</td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <br>
    <div align="right">

        <button class="btn btn-primary" onclick="history.back()">See new request</button>

    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        var table = $('#requests').DataTable({
            "order": [[2, "desc"]],
            'dom': 'rt<"bottom"lp><"clear">',
        });
        $("#searchReqCat").on('keyup change', function () {
            table
                .columns(0)
                .search(this.value)
                .draw();
        });
        $("#searchRegister").on('keyup change', function () {
            table
                .columns(1)
                .search(this.value)
                .draw();
        });
        $("#searchDate").on('keyup change', function () {
            table
                .columns(2)
                .search(this.value)
                .draw();
        });
        $("#searchResAdm").on('keyup change', function () {
            table
                .columns(3)
                .search(this.value)
                .draw();
        });
        $("#searchStatus").on('keyup change', function () {
            table
                .columns(4)
                .search(this.value)
                .draw();
        });
    });
</script>
</html>