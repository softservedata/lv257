<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>--%>

<div id="available-props-modal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4>Resource Properties</h4>
      </div>
      <div class="modal-body">
        <div class="container-fluid">
          <div class="form-group">
            <select id="available-properties" name="available-properties" multiple
                    class="form-control scrollable" size="15">
            </select>
            <div class="pull-right">
              <button type="submit" id="add-props-btn" class="btn btn-primary disabled" data-dismiss="modal">Add</button>
              <button type="reset" id="cancel-btn" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<div id="new-property-modal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4>Define new Property</h4>
      </div>
      <div class="modal-body">
        <form class="form" id="property-form">
          <div class="row">
            <div class="col-sm-6">
              <div class="form-group">
                <label for="title">Property title</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Title"
                       pattern="[A-Z][-a-z0-9]+( [A-Z][-a-z0-9]+)*">
              </div>
            </div>
            <div class="col-sm-6">
              <div class="form-group">
                <label for="column-name">Property's column name</label>
                <input type="text" class="form-control" id="column-name" name="columnName" placeholder="Name"
                       pattern="[A-Z][_a-z0-9]+(_[A-Z][_a-z0-9]+)*">
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6">
              <div class="form-group">
                <label for="units">Measuring units</label>
                <input type="text" class="form-control" id="units" name="units" placeholder="e.g. cubical centimeters">
              </div>
            </div>
            <div class="col-sm-3">
              <div class="form-group">
                <label for="units">Units contraction</label>
                <input type="text" class="form-control" id="units-short" name="unitsShort" placeholder="e.g. cc"
                       title="e.g. cc (cubical centimeters)" pattern="[a-zA-Z \.-]{10}">
              </div>
            </div>
            <div class="col-sm-4">
              <div class="form-group">
                <label for="value-type">Value type</label>
                <select id="value-type" name="valueType" class="form-control">
                  <option disabled selected hidden value="">Select value type</option>
                </select>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <div class="form-group">
                <label for="pattern">Value validation pattern</label>
                <input type="text" class="form-control" id="pattern" name="pattern"
                       placeholder="pattern string, e.g. [0-9]+\\.[0-9]*[1-9] for decimal with non-zero last digit">
                <%--placeholder="<se:eval expression="${env}.getProperty('resourceProperty.valuePattern.placeholder')" />">--%>
              </div>
            </div>
          </div>
        </form>
        <div class="col-sm-12">
          <div class="pull-left">
            <div class="row">
              <input type="checkbox" id="reset-on-save"/>
              <label for="reset-on-save" style="font-weight: 100;">Reset fields after Save</label>
            </div>
            <div class="row">
              <input type="checkbox" id="keep-on-save"/>
              <label for="keep-on-save" style="font-weight: 100;">Continuous editing</label>
            </div>
          </div>
          <div class="pull-right">
            <button type="button" id="save-btn" class="btn btn-primary" <%--data-dismiss="modal"--%>>Save</button>
            <button type="button" id="save-add-btn" class="btn btn-primary" <%--data-dismiss="modal"--%>>Save/Add New</button>
          </div>
        </div>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
</div>
<script src="${contextPath}/resources/js/jquery.validate.js"></script>
<script src="${contextPath}/resources/js/properties.js"></script>