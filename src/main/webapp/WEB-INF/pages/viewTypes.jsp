<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<script>
    currentAdmin = "<c:out value="${currentAdmin}"/>"
</script>
<html>
<head>
    <title>View resource types</title>
    <jsp:include page="metadata.jsp"/>

</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="container-fluid">
    <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">

        <div class="container">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#">View</a></li>
                <li><a href="${contextPath}/resources/addType">Add</a></li>
                <li><a href="${contextPath}/resources/requests">Requests</a></li>
            </ul>
        </div>

        <div class="container">
            <br>
            <div class="container">
                <span class="h3">View resource types</span>
                <a class="btn btn-primary pull-right" href="${contextPath}/resources/addType" target="_blank">Add new
                    resource type</a>
            </div>
            <br>
            <table class="table table-striped table-condensed text-center display" width="100%" id="types-table">
                <thead>
                <tr>
                    <th class="text-center">#</th>
                    <th class="text-center">Resource category</th>
                    <th class="text-center">Resource type</th>
                    <th class="text-center">Created by</th>
                    <th></th>
                    <th></th>
                    <th class="text-center">Actions</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <thead>
                <tr>
                    <th></th>
                    <th>
                        <div class="theader-with-select" id="categories">
                            <script> var disableAncestorSelecting = false;
                            var showAllCategoriesInSelect = true;</script>
                            <jsp:include page="components/resourceTypeSelect.jsp"/>
                        </div>
                    </th>
                    <th>
                        <select class="selectpicker" data-live-search="true" data-width="100%" id="types">
                            <%--<option value="all">All resource types</option>--%>
                        </select>
                    </th>
                    <th>
                        <select class="selectpicker" data-live-search="true" data-width="100%" id="admins">
                            <option value="all">All administrators</option>
                            <option value="${currentAdmin}">Me</option>
                            <c:forEach items="${administrators}" var="adminName">
                                <c:if test="${adminName != currentAdmin}">
                                    <option value="${adminName}">${adminName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th class="text-center">#</th>
                    <th class="text-center">Resource category</th>
                    <th class="text-center">Resource type</th>
                    <th class="text-center">Created by</th>
                    <th></th>
                    <th></th>
                    <th class="text-center">Actions</th>
                    <th></th>
                    <th></th>
                </tr>
                </tfoot>

                <tbody></tbody>
            </table>
            <br/>
        </div>
    </div>

    <div class="modal fade" id="confirm-dialog" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" id="confirm-dialog-inner">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 id="confirm-title" class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <p id="confirm-body"></p>
                </div>
                <div class="modal-footer">
                    <button id="confirm-button" class="btn btn-primary btn-ok">Yes</button>
                    <button id="dismiss-button" type="button" class="btn btn-default" data-dismiss="modal">No</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="type-info-dialog" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg" id="type-info-dialog-inner">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close close-name-dialog" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title">Resource type information</h4>
                </div>
                <div id="type-info-body" class="modal-body">
                    <div id="type-name"></div>
                    <br>
                    <div id="category-name"></div>
                    <br>
                    <div id="is-instantiated"><span class="font-bold"></span></div>
                    <p class="caption text-center font-bold" style="font-size: 12pt">Properties of the resource type</p>
                    <table class="table table-striped table-condensed table-bordered text-center display" width="100%"
                           id="props-table">
                        <thead>
                        <tr>
                            <th class="text-center">#</th>
                            <th class="text-center">Name</th>
                            <th class="text-center">Units</th>
                            <th class="text-center">Validation RegEx pattern</th>
                            <th class="text-center">Value type</th>
                            <th class="text-center">Searchable</th>
                            <th class="text-center">Required</th>
                        </tr>
                        </thead>
                        <tbody id="props-tbody"></tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
<script src="${contextPath}/resources/js/bootstrap-select.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/viewTypes.js"></script>
</html>
