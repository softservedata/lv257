<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="new-property" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4>Define new Property</h4>
      </div>
      <div class="modal-body">
        <div class="container-fluid">
          <form class="form">
            <div class="row">
              <div class="col-sm-6">
                <div class="form-group">
                  <label for="title">Property title</label>
                  <input type="text" class="form-control" id="title" placeholder="Title">
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <label for="name">Property name</label>
                  <input type="text" class="form-control" id="name" placeholder="Name">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-5">
                <div class="form-group">
                  <label for="units">Measuring units</label>
                  <input type="text" class="form-control" id="units" placeholder="e.g. cc (cubical centimeters)">
                </div>
              </div>
              <div class="col-sm-3">
                <div class="form-group">
                  <label for="valueTypeName">Value type</label>
                  <select class="form-control" id="valueTypeName">
                    <option value="0">Select value type</option>
                    <option value="1">integer</option>
                    <option value="2">decimal</option>
                    <option value="3">string</option>
                    <option value="4">boolean</option>
                    <option value="5">timestamp</option>
                    <option value="6">resource property</option>
                  </select>
                </div>
              </div>
              <div class="col-sm-3">
                <div class="form-group">
                  <label for="default">Default value</label>
                  <input type="text" class="form-control" id="default" placeholder="0">
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="pattern">Validation pattern</label>
              <input type="text" class="form-control" id="pattern"
                     placeholder="pattern string, e.g. [0-9]+\.[0-9]*[1-9] for decimal with non-zero last digit">
            </div>
            <div class="checkbox">
              <label><input type="checkbox" id="searchable"><span class="font-bold">Essential</span></label>
            </div>
            <div class="checkbox">
              <label><input type="checkbox" id="multi"><span class="font-bold">Multivalued</span></label>
            </div>
            <div class="checkbox">
              <label><input type="checkbox" id="req"><span class="font-bold">Required</span></label>
            </div>
            <div class="checkbox">
              <label><input type="checkbox" id="secured"><span class="font-bold">Secured</span></label>
            </div>

            <div class="container-fluid pull-right">
              <button type="reset" class="btn btn-primary" data-dismiss="modal">Save</button>
              <button type="reset" class="btn btn-primary" data-dismiss="modal">Save/Add New</button>
            </div>
          </form>
        </div>
      </div>
    </div>

  </div>
</div>
