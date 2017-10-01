<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                <form action="ResourcesView.html">
                    <div class="row">
                        <div id="categories" class="col-sm-6 col-xs-8 form-group">
                            <label for="rCategory">Resource Category</label>
                            <select type="resource type" id="rCategory" class="form-control selectpicker"
                                    data-live-search="true" title="Select desired category">
                                <option style="text-indent: 0px;">Capital</option>
                                <option style="text-indent: 10px;">Real Estate</option>
                                <option style="text-indent: 20px;">Cottages</option>
                                <option style="text-indent: 20px;">Apartment Buildings</option>
                                <option style="text-indent: 10px;">Transport</option>
                                <option style="text-indent: 20px;">Vehicles</option>
                                <option style="text-indent: 20px;">Trucks</option>
                            </select></div>
                        <button id="categories-manager" type="button" class="btn btn-primary" data-toggle="modal"
                                style="margin-top: 25px"
                                data-target="#categories-view">Manage
                        </button>
                    </div>

                    <div class="row">
                        <div id="categoryTypes" class="col-sm-6 col-xs-8 form-group">
                            <label for="resourceType">Resource Type Name</label>
                            <input id="resourceType" type="text" class="form-control"
                                   placeholder="Enter the name of new resource type">
                        </div>
                    </div>
                    <div class="container-fluid">
                        <br>
                        <h4>Resource Type Properties</h4>
                        <hr>
                        <div class="container">
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#existent-props">Add existing
                            </button>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#new-property">Add new
                            </button>
                        </div>

                        <jsp:include page="dialogs/existentProperties.jsp"/>
                        <jsp:include page="dialogs/newProperty.jsp"/>
                        <jsp:include page="dialogs/categories.jsp"/>

                        <div class="container col-md-8">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Characteristic Name</th>
                                    <th>Details</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Char 1</td>
                                    <td>Det 1</td>
                                    <td><a href="#"><i class="glyphicon glyphicon-edit"></i></a></td>
                                    <td><a href="#"><i class="glyphicon glyphicon-remove"></i></a></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-default">Save</button>

                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="${contextPath}footer.jsp"/>
<script>
    $("#addition-btn").on('click', function (e) {
        $('#addition-btn, #definition-form').toggleClass('hidden');
    });

    <%--inputJson = ${inputJson};--%>
</script>
<script src="${contextPath}/resources/js/categoriesManagement.js"></script>
</body>
</html>