<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>

<body>

<jsp:include page="menu.jsp"/>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${pageContext.request.contextPath}/resources/view">View</a></li>
        <li><a href="${pageContext.request.contextPath}/resources/addType">Add</a></li>
        <li class="active"><a href="#">Requests</a></li>
    </ul>
</div>
<br>
<div class="container">

    <div class="table-responsive">
        <h3>Requests for resource type</h3>
        <div align="right">
            <label for="search">Search by resource Admin:</label>
            <input type="text" class="form-control" id="search" placeholder="Resource Administrator"
                   style="width: 300px">
        </div>
        <br>
        <table class="table table-striped table-condensed text-center table-bordered display" id="requests">
            <thead>
            <tr>
                <th>
                    <div class="text-center ">Resource Type</div>
                </th>
                <th>
                    <div class="text-center ">Requester</div>
                    <div class="fht-cell"></div>
                </th>
                <th>
                    <div class="text-center">Information</div>
                </th>
                <th>
                    <div class="text-center ">Date</div>
                    <div class="fht-cell"></div>
                </th>
                <th name="resourceAdm">
                    <div class="text-center">Who is processing</div>
                    <div class="fht-cell"></div>
                </th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${resourceRequest}" var="request">

                <tr class="active">
                    <td>${request.resourceType}</td>
                    <td>${request.requesterName}</td>
                    <td><a href="/resources/info/${request.id}">details</a></td>
                    <td>${request.update.toString().split('\\.')[0]}</td>
                    <td>${request.assignerName}</td>

                    <c:choose>
                        <c:when test="${request.assignerName!=null}">
                            <td data-order="1" data-id=${request.id}>
                                <c:choose>
                                    <c:when test="${request.assignerName==resourceAdmin}">
                                        <a href="${pageContext.request.contextPath}/resources/addType">
                                            <button class="btn btn-primary">Process</button>
                                        </a>
                                        <button class="btn btn-primary responce" type="button"
                                                data-toggle="modal"
                                                data-target="#myModal">Responce
                                        </button>

                                    </c:when>
                                    <c:otherwise>
                                        <a href="/add">
                                            <button class="btn btn-primary" disabled>Process</button>
                                        </a>
                                        <button class="btn btn-primary" disabled> Responce</button>

                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </c:when>
                        <c:otherwise>
                            <td data-order="0" data-id=${request.id}>

                                <button class="btn btn-primary assign">Assign to me</button>

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
            <button class="btn btn-primary">See processed requests</button>
        </a>
    </div>

</div>
</div>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h3 class="modal-title left-align">Response</h3>
                <br>
                <h5 class="modal-title" id="idRequest" hidden></h5>
                <br>
            </div>
            <div class="modal-body">
                <form>
                    <h6 class="modal-title left-align">Comment:</h6>
                    <div class="form-group">

                        <textarea class="form-control" id="comment" rows="5"
                                  style=" resize: vertical"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">

                <div class="left-align">
                    <h6 class="modal-title">Purpose:</h6>
                    <form id="Purpose">
                        <input type="radio" name="radioName" value="Refinement" checked/> Refinement<br/>
                        <input type="radio" name="radioName" value="Accept"/> Accept <br/>
                        <input type="radio" name="radioName" value="Decline"/> Decline <br/>
                    </form>
                </div>

                <button type="button" class="btn btn-primary send"
                        data-dismiss="modal">Send
                </button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.foundation.min.js"></script>
<script>

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

        var currentRow;
        $(document).on('click', '.responce', function () {
            currentRow = table.row($(this).parents('tr'));
            $('#idRequest').text($(this).parents('td').attr('data-id'));
        })

        $('.assign').on('click', function () {
            var cell = $(this).parents('td');
            var id = cell.attr('data-id');
            $.ajax(
                {
                    type: "POST",
                    url: "assignRequest",
                    accept: "application/json",
                    data: {id: id},
                    success: function (responceRequest) {
                        table.cell(cell.closest('tr'), 3).data(responceRequest.update);
                        table.cell(cell.closest('tr'), 4).data(responceRequest.assignerName);
//
                        cell.replaceWith(" <td data-order=\"1\" data-id=" + id + ">\n" +
                            "                       <a href=\"/resources/addType\">\n" +
                            "                           <button class=\"btn btn-primary\">Process</button>\n" +
                            "                       </a>\n" +
                            "                       <button class=\"btn btn-primary responce\"  type=\"button\"\n" +
                            "                                  data-toggle=\"modal\" data-target=\"#myModal\">Responce\n" +
                            "                       </button>" +
                            "              </td>"
                        );
                        table.destroy();
                        table = $('#requests').DataTable({
                            'dom': 'rt<"bottom"lp><"clear">',
                            stateSave: true
                        });

                        table.order([[5, 'asc'], [3, 'desc']]).draw();
                    },
                    error: function (result) {
                        alert("This request has already assigned.\n" +
                            "Please, reload page.");
                    }
                })

        })


        $('.send').click(function () {

            var comment = $('#comment').val();
            var purpose = ($('input[name=radioName]:checked', "#Purpose").val());
            var id_request = $("#idRequest").text();
            var message = {
                id_request: id_request,
                purpose: purpose,
                comment: comment
            }
            $.ajax(
                {
                    type: "POST",
                    contentType: "application/json",
                    url: "/resources/sendResponce",
                    accept: "text/plain",
                    data: JSON.stringify(message),
                    success: function (obj) {
                        alert("Your mail has already sent.")
                        $("#comment").val('');
                        currentRow.remove().draw();
                    }
                })
        })
    });
</script>
</html>