<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add Resource definition</title>
  <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp"/>
<div class="container-fluid">
  <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">

    <div class="container">
      <ul class="nav nav-tabs">
        <li ><a href="ResourcesView.html">View</a></li>
        <li class="active"><a href="#">Add</a></li>
        <li> <a href="${pageContext.request.contextPath}/resources/requests">Requests</a></li>
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
              <button id="additionBtn" class="btn btn-primary" type="button">Define new</button></div>
          </div>
        </div>
      </div>
      <div id="definitionForm" class="container hidden"<%-- style="display: none;"--%>>
        <br>
        <form action="ResourcesView.html">
          <div class="row">
            <div id="categories" class="col-sm-6 form-group">
              <label for="rCategory">Resource Category</label>
              <select id="rCategory" class="form-control">
                <option value="0">Select desired category</option>
                <option value="1">Aircrafts</option>
                <option value="2">Automobiles</option>
                <option value="3">Railroad transport</option>
                <option value="4">Ships</option>
              </select>
            </div>
          </div>
          <div class="row">
            <div id="categoryTypes" class="col-sm-6 form-group">
              <label for="resourceType">Resource Type</label>
              <select id="resourceType" class="form-control">
                <option value="0">Select desired type</option>
                <option>Car</option>
                <option>Bus</option>
                <option>Motorcycle</option>
                <option>Truck</option>
              </select>
            </div>
          </div>
          <div class="container-fluid">
            <br>
            <h4>Resource Type Properties</h4>
            <hr>
            <div class="container">
              <button type="button" class="btn btn-primary" data-toggle="modal"
                      data-target="#existent-props">Add existing</button>
              <button type="button" class="btn btn-primary" data-toggle="modal"
                      data-target="#new-property">Add new</button>
            </div>

            <jsp:include page="dialogs/existentProperties.jsp"/>

            <jsp:include page="dialogs/newProperty.jsp"/>

            <div class="container col-md-8">
              <table class="table table-hover">
                <thead>
                <tr>
                  <th>Characteristic Name</th><th>Details</th><th>Edit</th><th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>Char 1</td><td>Det 1</td>
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

<jsp:include page="footer.jsp"/>
<script>
    $("#additionBtn").on('click', function(e) {
        $('#additionBtn, #definitionForm').toggleClass('hidden');
    });
</script>
</body>
</html>