<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form class="form" id="new-property">
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
            <div class="col-sm-4">
              <div class="form-group">
                <label for="searchable">Searchable</label>
                <input type="checkbox" id="searchable" name="searchable">
              </div>
            </div>
            <div class="col-sm-4">
              <div class="form-group">
                <label for="required">Required</label>
                <input type="checkbox" id="required" name="required">
              </div>
            </div>
            <div class="col-sm-4">
              <div class="form-group">
                <label for="multivalued">Multivalued</label>
                <input type="checkbox" id="multivalued" name="multivalued">
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
                  <option disabled selected hidden>Select value type</option>
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
          <div class="pull-right">
            <button type="button" id="save-btn" class="btn btn-primary" data-dismiss="modal">Save</button>
            <button type="button" id="save-add-btn" class="btn btn-primary" data-dismiss="modal">Save/Add New</button>
          </div>
          <div class="clearfix"></div>
        </form>
      </div>
    </div>
  </div>
</div>

<%--<script src="${contextPath}/resources/js/FormSerializePlugin.js"></script>--%>
<script src="${contextPath}/resources/js/properties.js"></script>