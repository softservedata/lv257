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
                <input type="text" class="form-control" id="title" name="title" placeholder="Title">
              </div>
            </div>
            <div class="col-sm-6">
              <div class="form-group">
                <label for="column-name">Property name</label>
                <input type="text" class="form-control" id="column-name" name="columnName" placeholder="Name">
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
                <input type="text" class="form-control" id="units" name="units" placeholder="e.g. cc (cubical centimeters)">
              </div>
            </div>
            <div class="col-sm-4">
              <div class="form-group">
                <label for="value-type">Value type</label>
                <select id="value-type" name="valueType" class="form-control">
                  <option value="">Select value type</option>
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
                <%--placeholder="<se:eval expression="env.getProperty('resourceProperty.valuePattern.placeholder')" />">--%>
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
