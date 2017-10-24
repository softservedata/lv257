<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<head>
    <title>View resource types</title>
    <jsp:include page="metadata.jsp"/>
    <script src="${contextPath}/resources/js/bootstrap-select.min.js"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="container-fluid">
    <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">

        <div class="container">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#">View</a></li>
                <li><a href="/resources/editType">Add</a></li>
                <li><a href="/resources/requests">Requests</a></li>
            </ul>
        </div>

        <div class="container">
            <br>
            <h3>View resource types</h3>
            <br>
            <table class="table table-striped table-condensed text-center table-bordered display" id="types-table">
                <thead>
                <tr>
                    <th>
                        <div class="text-center ">Resource category</div>
                    </th>
                    <th>
                        <div class="text-center ">Resource type</div>
                    </th>
                    <th>
                        <div class="text-center">Created by</div>
                    </th>
                    <th>
                        <div class="text-center ">Instantiated</div>
                    </th>
                    <th>
                        <div class="text-center">Edit</div>
                    </th>
                    <th>
                        <div class="text-center">Clone</div>
                    </th>
                    <th>
                        <div class="text-center">Delete</div>
                    </th>
                </tr>
                </thead>

                <thead>
                <th>
                    <div class="theader-with-select" id="categories">
                        <script> var disableAncestorSelecting = false;
                        var showAllCategoriesInSelect = true;</script>
                        <jsp:include page="components/resourceTypeSelect.jsp"/>
                    </div>
                </th>
                <th>
                    <select class="selectpicker" data-live-search="true" data-width="100%" id="types">
                        <option>All resource types</option>
                        <option>Burger, Shake and a Smile</option>
                        <option>Sugar, Spice and all things nice</option>
                    </select>
                </th>
                <th>
                    <select class="selectpicker" data-live-search="true" data-width="100%" id="admins">
                        <option>All administrators</option>
                        <option>Myself</option>
                    </select>
                </th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                </thead>

                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
