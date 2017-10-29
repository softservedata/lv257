<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<c:set var="idVal" value="${param.id}"/>
<c:if test="${idVal == null}"><c:set var="titlePlaceholder" value="Add"/><c:set var="idVal" value="0"/></c:if>
<c:if test="${idVal > 0}"><c:set var="titlePlaceholder" value="Edit"/></c:if>
<c:if test="${idVal < 0}"><c:set var="titlePlaceholder" value="Clone"/></c:if>

<!DOCTYPE html>
<html>
<head>
    <title>${titlePlaceholder} Resource definition</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="container-fluid">
    <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">

        <div class="container">
            <ul class="nav nav-tabs">
                <li><a href="${contextPath}/resources/viewTypes">View</a></li>
                <li class="active"><a href="#">${titlePlaceholder}</a></li>
                <li><a href="${contextPath}/resources/requests">Requests</a></li>
            </ul>
        </div>
        <div class="container">

            <div class="container-fluid col-sd-12">
                <div class="container-fluid">
                    <br>
                    <h3>Define Resource Type</h3>
                    <br>
                    <div class="row">
                        <div>
                            <a href="${contextPath}/resources/viewTypes" class="btn btn-primary" type="button">Clone
                                existing</a>
                            <button id="define-btn"
                                    class="btn btn-primary <c:if test="${idVal != 0}"><c:out value="hidden"/></c:if>"
                                    type="button">Define new
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <%--<div id="definition-form" class="container <c:if test="${idVal == 0}"><c:out value="hidden"/></c:if>">--%>
            <div id="definition-form" class="container hidden">
                <br>
                <form id="resource-type" method="post">
                    <div class="row">
                        <c:set var="typeSelectLabel" value="Resource Category" scope="request"/>
                        <button id="manage-categories" type="button" class="btn btn-primary btn-md"
                                data-toggle="modal" style="margin-top: 25px" title="Manage categories"
                                data-target="#categories-view"><span class="glyphicon glyphicon-cog"></span>
                        </button>
                        <div id="categories" class="col-sm-6 col-xs-8 form-group">
                            <jsp:include page="dialogs/categories.jsp"/>
                            <jsp:include page="components/resourceTypeSelect.jsp"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6 col-xs-8 form-group">
                            <label for="type-name">Type Name</label>
                            <input id="type-name" name="typeName" type="text" class="form-control" required
                                   minlength="3"
                                   pattern="[A-Z][a-zA-Z -]+"
                                   title="Alphabetical characters with dash or space as separator"
                            <%--pattern="${typeNamePattern}"--%>
                                   placeholder="Enter the name of new resource type">
                            <%--placeholder="Enter the name of new resource type">--%>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6 col-xs-8 form-group">
                            <label for="table-name">Type's Table Name</label>
                            <input id="table-name" name="tableName" type="text" class="form-control" required
                                   minlength="3"
                                   pattern="[A-Z][a-z]+(_[A-Z][a-z]+)*"
                                   title="Type in Camel case names separated with underscores"
                            <%--pattern="${tableNamePattern}"--%>
                                   placeholder="Enter the name of resource's table">
                        </div>
                    </div>
                </form>
                <div class="container-fluid">
                    <br>
                    <h4>Resource Type Properties</h4>
                    <hr>
                    <div class="container">
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#available-props-modal">Add existing
                        </button>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#new-property-modal">Add new
                        </button>
                    </div>
                    <div class="container col-md-8">
                        <style>
                            .centered {
                                text-align: center;
                            }
                        </style>
                        <table id="assigned-props" class="table table-hover hidden">
                            <thead>
                            <tr>
                                <th>Property</th>
                                <th class="centered">Units</th>
                                <th class="centered">Searchable</th>
                                <th class="centered">Required</th>
                                <th><%--Remove--%></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="assigned-template hidden">
                                <td class="title"></td>
                                <td class="units-short centered"></td>
                                <td class="centered"><input type="checkbox" class="searchable"/></td>
                                <td class="centered"><input type="checkbox" class="required"/></td>
                                <td><a title="Remove"><i class="glyphicon glyphicon-remove"></i></a></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <hr>
                <div id="buttons-block" class="pull-left hidden">
                    <button id="save-type-btn" type="submit" class="btn btn-default">Save</button>
                    <button id="discard-btn" type="reset" class="btn btn-default">Discard</button>
                </div>
                <br>

                <script title="Current ResourceType's variables declaration">
                    var resourceTypeID = <c:out value="${idVal != 0 ? idVal : 0}"/>;
                    var categoryID;
                </script>
                <jsp:include page="dialogs/properties.jsp"/>
                <script src="${contextPath}/resources/js/resourceTypes.js"></script>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>