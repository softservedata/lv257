<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>

</head>
<body>

<jsp:include page="resources.jsp" />

<div class="wrapper">
    <div class="container-fluid">
        <div class="col-sm-12 col-md-12">
            <div class="container">

                <ul class="nav nav-pills">
                    <li class="active"><a href="#NeedMoreDetails" data-toggle="pill">Not reviewed</a></li>
                    <li ><a href="#NotReviewed" data-toggle="pill">Need more details</a></li>
                    <li><a href="#Accepted" data-toggle="pill">Accepted</a></li>
                    <li><a href="#Declined" data-toggle="pill">Declined</a></li>
                </ul>

                <div class="tab-content" id="TabContent">

                    <div class="tab-pane fade in active" id="NeedMoreDetails">
                        <div class="table-responsive">
                            <h3>List of requests</h3>
                            <br>
                            <br>
                            <%----%>
                            <table data-toggle="table"
                                    id="listRequests"class="table table-striped table-condensed text-center">
                                <thead>
                                <tr>
                                    <th class="text-center">Status</th>
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


                                </tr>
                                </thead>


                                <tbody>
                                 <c:forEach items="${gRequest}" var="request">

                                <tr>
                                    <td>${request.status}</td>
                                    <td>${request.resourceType}</td>
                                    <td>Not yet reviewed</td>
                                    <td><a href="/resources/info/${request.id}">documentLink1</a></td>
                                    <td>${request.update}</td>
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
                            <table data-toggle="table"
                                   class="table table-striped table-condensed text-center">
                                <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who requested</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Date</div>
                                        <div class="fht-cell"></div></th>

                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Квартира</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>24.07.2017</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Особняк </td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin3</td>
                                    <td>20.03.2017</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Одяг </td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>08.01.2017</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>


                    </div>

                    <div class="tab-pane fade" id="Declined">

                        <div class="table-responsive">
                            <h3>List of processed requests</h3>
                            <br>
                            <br>
                            <br>
                            <table data-toggle="table"
                                   class="table table-striped table-condensed text-center">
                                <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who requested</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Date</div>
                                        <div class="fht-cell"></div></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Вода</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>01.07.2017</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Повітряний простір</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin3</td>
                                    <td>06.06.2017</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Теща</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>19.02.2017</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>


                    </div>
                    <div class="tab-pane fade" id="NotReviewed">

                        <div class="table-responsive">
                            <h3>List of requests</h3>
                            <table data-toggle="table"
                                   class="table table-striped table-condensed text-center">
                                <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who requested</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Date</div>
                                        <div class="fht-cell"></div></th>


                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Велосипед</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>02.08.2017</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Самокат</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin3</td>
                                    <td>31.06.2017</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Надра землі</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>05.06.2017</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>



<%--<script src="//code.jquery.com/jquery-1.12.4.js"></script>--%>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<%--<script src="https://cdn.datatables.net/1.10.16/js/dataTables.foundation.min.js"></script>--%>

<script>
    $(function() {
        $("#listRequests").dataTable({
            lengthMenu : [
            [ 3, 5, 10, -1 ],
            [ '3 records', '5 records', '10 records',
            'All records' ] ],
            pageLength:5,
        });
    });
</script>

<%--<script>--%>
    <%--$.extend(true, $.fn.dataTable.defaults, {--%>
        <%--"searching": true,--%>
        <%--"sPageButton": "paginate_button"--%>
    <%--});--%>
    <%--$(document).ready(function () {--%>
        <%--var table = $('#listRequests').DataTable({--%>
            <%--'dom': 'rt<"bottom"lp><"clear">',--%>
            <%--stateSave: true--%>
        <%--});--%>
        <%--table--%>
            <%--.order([[5, 'asc'], [3, 'desc']])--%>
            <%--.draw();--%>
        <%--$("#search").on('keyup change', function () {--%>
            <%--table--%>
                <%--.columns(5)--%>
                <%--.search(this.value)--%>
                <%--.draw();--%>
        <%--});--%>

        <%--var currentRow;--%>
        <%--$('.responce').click(function () {--%>
            <%--$('#idRequest').text($(this).attr('data-id'))--%>
            <%--currentRow = table--%>
                <%--.row($(this).parents('tr'));--%>
        <%--})--%>
        <%--$('.assign').click(function () {--%>
            <%--var id = $(this).attr('data-id');--%>
            <%--$.ajax(--%>
                <%--{--%>
                    <%--type: "POST",--%>
                    <%--url: "assignRequest",--%>
                    <%--data: {id: id},--%>
                    <%--success: function (obj) {--%>
                        <%--alert(obj)--%>
                    <%--}--%>
                <%--})--%>
        <%--})--%>


        <%--$('.send').click(function () {--%>

            <%--var comment = $('#comment').val();--%>
            <%--var purpose = ($('input[name=radioName]:checked', "#Purpose").val());--%>
            <%--var id_request = $("#idRequest").text();--%>
            <%--var message = {--%>
                <%--id_request: id_request,--%>
                <%--purpose: purpose,--%>
                <%--comment: comment--%>
            <%--}--%>
            <%--$.ajax(--%>
                <%--{--%>
                    <%--type: "POST",--%>
                    <%--contentType: "text/plain",--%>
                    <%--url: "/resources/sendResponce",--%>
                    <%--accept: "text/plain",--%>
                    <%--data: JSON.stringify(message),--%>

                    <%--success: function (obj) {--%>
                        <%--$("#comment").val('');--%>
                        <%--currentRow.remove().draw();--%>
                    <%--}--%>
                <%--})--%>
        <%--})--%>
    <%--});--%>
<%--</script>--%>
<%--<script>--%>

    <%--var $table = $('#listRequests');--%>

    <%--// execute the below code only where we have this table--%>
    <%--if ($table.length) {--%>

        <%--$table.Datatable({--%>
            <%--lengthMenu : [--%>
                <%--[ 3, 5, 10, -1 ],--%>
                <%--[ '3 records', '5 records', '10 records',--%>
                    <%--'All records' ] ],--%>
            <%--pageLength:5,--%>
            <%--// data: requests--%>
        <%--});--%>
    <%--}--%>

<%--</script>--%>

</body>
</html>
