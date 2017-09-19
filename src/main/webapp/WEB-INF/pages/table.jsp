<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.09.2017
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    //            $(document).ready(
    //                function () {
    //
    //                    $('.responceButton').click(function () {
    //                        // Get the record's ID via attribute
    //                        var id = $(this).attr('data-id');
    //                        alert(id);
    //
    //                        $.ajax({
    //                            url: '' + id,
    //                            method: 'GET'
    //                        }).success(function (response) {
    //                            // Populate the form fields with the data returned from server
    //                            $('#responceForm')
    //                                .find('[name="theme"]').val(response.theme).end()
    //
    //
    //                        });
    //                    });
    //                });


    /////////////////////////////




    $('#myRequest').on( 'click', function () {
    ////                    var checkboxValues = JSON.parse(localStorage.getItem('checkboxValues')) || {};
    ////                    checkboxValues=$(this).checked;
    ////                    localStorage.setItem("checkboxValues", JSON.stringify(checkboxValues));
    //                    if ($(this).is(":checked")) {
    //                        table
    //                            .columns(4)
    //                            .search('Iryna')
    //                            .draw();
    //                    }else{
    //                        table
    //                            .columns(4)
    //                            .search('')
    //                            .draw();
    //                    }
    //                } );

</head>
<body>
<c:forEach items="${resourceRequest}" var="request" varStatus="counter">
    <tr name="simpleRequest" style="visibility: visible">
        <td>${counter.count}</td>
        <td>${request.theme}</td>
        <td>${request.register.username}</td>
        <td><a href="/details-${request.id}">details</a></td>
        <td>${request.update}</td>
        <td>${request.resourcesAdmin.username}</td>
        <td>
            <c:choose>
                <c:when test="${request.resourcesAdmin!=null}">
                    <c:choose>
                        <c:when test="${request.resourcesAdmin.username.equals('Iryna')}">
                            <a href="/add">
                                <button class="btn btn-primary">Process</button>
                            </a>
                            <button class="btn btn-primary responceButton" type="button"
                                    data-id=${counter.count}>Responce
                            </button>
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
                </c:when>
                <c:otherwise>

                    <button class="btn btn-primary assign" data-id=${request.id}>Assign to me</button>

                </c:otherwise>

            </c:choose>
        </td>
    </tr>
</c:forEach>


</body>
</html>
