<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Add Resource definition</title>
    <jsp:include page="${contextPath}metadata.jsp"/>
</head>
<body>
<jsp:include page="${contextPath}_menu2.jsp"/>
<div class="container-fluid">
    <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">

        <div class="container">
            <ul class="nav nav-tabs">
                <li><a href="ResourcesView.html">View</a></li>
                <li class="active"><a href="#">Add</a></li>
                <li><a href="${pageContext.request.contextPath}/resources/requests">Requests</a></li>
            </ul>
        </div>
        <div class="container">

            <div class="container-fluid col-sd-12">
                <div class="container-fluid">
                    <br>
                    <h3>Choose a way to create definition</h3>
                    <br>
                    <div class="row">
                        <div>
                            <a href="ResourcesView.html" class="btn btn-primary" type="button">Clone existing</a>
                            <button id="addition-btn" class="btn btn-primary" type="button">Define new</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="definition-form" class="container hidden"<%-- style="display: none;"--%>>
                <br>
                <form id="resource-type" method="post">
                    <div class="row">
                        <c:set var="typeSelectLabel" value="Resource Category" scope="request"/>
                        <div id="categories" class="col-sm-6 col-xs-8 form-group">
                            <jsp:include page="components/resourceTypeSelect.jsp"/>
                        </div>
                        <button id="manage-categories" type="button" class="btn btn-primary btn-md"
                                data-toggle="modal" style="margin-top: 25px" title="Manage categories"
                                data-target="#categories-view"><span class="glyphicon glyphicon-cog"></span>
                        </button>
                    </div>

                    <div class="row">
                        <div class="col-sm-6 col-xs-8 form-group">
                            <label for="type-name">Type Name</label>
                            <input id="type-name" name="typeName" type="text" class="form-control" pattern="${typeNamePattern}"
                                   placeholder="Enter the name of new resource type">
                            <%--placeholder="Enter the name of new resource type">--%>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6 col-xs-8 form-group">
                            <label for="table-name">Type's Table Name</label>
                            <input id="table-name" name="tableName" type="text" class="form-control"
                                   pattern="${tableNamePattern}"
                                   placeholder="Enter the name of resource's table">
                        </div>
                    </div>
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
                                .centered { text-align: center;}
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
                    <br>
                    <hr>
                    <button id="save-type-btn" type="submit" class="btn btn-default">Save</button>
                    <button id="discard-btn" type="reset" class="btn btn-default">Discard</button>

                </form>
                <script title="Current ResourceType's variables declaration">
                  var existentProperties;
                  <c:set var="idVal" value="${id}"/>
                  var resourceTypeID = <c:out value="${idVal != 0 ? idVal : 0}"/>;
                </script>
                <jsp:include page="dialogs/properties.jsp"/>
                <jsp:include page="dialogs/categories.jsp"/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="${contextPath}footer.jsp"/>

<script>
    $("#addition-btn").click(function (e) {
        $('#addition-btn, #definition-form').toggleClass('hidden');
    });
</script>
<script src="${contextPath}/resources/js/resourceTypes.js"></script>
</body>
</html>