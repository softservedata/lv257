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
        <li><a href="${pageContext.request.contextPath}/resources/addResource">Add</a></li>
        <li class="active"><a href="#">Request</a></li>
    </ul>
</div>
<br>
<div class="container">


    <div class="table-responsive">
        <h2>List of requests</h2>
        <div align="right">
            <label for="search">Search by resource Admin:</label>
            <input type="text" class="form-control" id="search" placeholder="Resource Administrator"
                   style="width: 300px">
        </div>
        <br>
        <table class="table table-hover table-condensed text-center table-bordered display" id="requests">
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
                    <div class="text-center">Information</div>
                </th>
                <th>
                    <div
                            class="text-center ">Date
                    </div>
                    <div class="fht-cell"></div>
                </th>
                <th name="resourceAdm">
                    <div
                            class="text-center">Who is processing
                    </div>
                    <div class="fht-cell"></div>
                </th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${resourceRequest}" var="request">

                <tr>
                    <td>${request.theme}</td>
                    <td>${request.register.username}</td>
                    <td><a href="/resources/details/${request.id}">details</a></td>
                    <td>${request.update}</td>
                    <td>${request.resourcesAdmin.username}</td>

                    <c:choose>
                        <c:when test="${request.resourcesAdmin!=null}">
                            <td data-order="1" name="action">
                                <c:choose>
                                    <c:when test="${request.resourcesAdmin.username==resourceAdmin}">

                                        <a href="/add">
                                            <button class="btn btn-primary">Process</button>
                                        </a>
                                        <button class="btn btn-primary" type="button" data-toggle="modal"
                                                data-target="#myModal">Responce
                                        </button>
                                        <div id="myModal" class="modal fade">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">&times;
                                                        </button>
                                                        <h3 class="modal-title " style="text-align: left">
                                                            Response</h3>
                                                        <br>
                                                        <h5 class="modal-title" style="text-align: left">
                                                            Theme: ${request.theme}</h5>
                                                        <br>


                                                    </div>
                                                    <div class="modal-body">
                                                        <form>
                                                            <h6 class="modal-title" style="text-align: left">
                                                                Comment:</h6>
                                                            <div class="form-group">

                                                                    <textarea class="form-control" rows="5"
                                                                              style=" resize: vertical"></textarea>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">

                                                        <div style="text-align: left">
                                                            <h6 class="modal-title">Purpose:</h6>
                                                            <form name="Purpose">
                                                                    <input type="radio" name="radioName" value="Refinement" checked /> Refinement
                                                                    <br />
                                                                    <input type="radio" name="radioName" value="Accept" /> Accept <br />
                                                                    <input type="radio" name="radioName" value="Decline" /> Decline <br />
                                                            </form>
                                                        </div>

                                                        <button type="button" class="btn btn-primary send"
                                                                data-id=${request.id}
                                                                        data-dismiss="modal">Send
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/add">
                                            <button class="btn btn-primary" disabled>Process</button>
                                        </a>
                                        <button class="btn btn-primary"
                                                disabled> Responce
                                        </button>

                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </c:when>
                        <c:otherwise>
                            <td data-order="0" name="action">

                                <button class="btn btn-primary assign" data-id=${request.id}>Assign to me</button>

                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>
    <div align="right">
        <a href="/resources/history">
            <button class="btn btn-primary">See processed request</button>
        </a>
    </div>

</div>
</div>
</body>
<script>
    $.extend(true, $.fn.dataTable.defaults, {
        "searching": true,
        "sPageButton": "paginate_button"
    });

    $(document).ready(function () {
        var table = $('#requests').DataTable({
            'dom': 'rt<"bottom"lp><"clear">',
            stateSave: true
        });

        table
            .order([[5, 'asc'], [3, 'desc']])
            .draw();

        $("#search").on('keyup change', function () {
            table
                .columns(4)
                .search(this.value)
                .draw();
        });


        $('.assign').click(function () {
            var id = $(this).attr('data-id');
            $.ajax(
                {
                    type: "POST",
                    url: "assignRequest",
                    data: {id: id},
                    success: function (obj) {
                        alert(obj)
                    }
                })
        })

        $('.send').click(function () {
            var row= table
                .row( $(this).parents('tr') );
            var id = $(this).attr('data-id');
            var form =
                alert(id);
            $.ajax(
                {
                    type: "POST",
                    url: "sendResponce",
                    data: {id: id},
                    success: function (obj) {
                        alert(obj)
                        row.remove().draw();
                        alert(obj)
                    }
                })
        })
    });


</script>
</html>
