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
                    class="form-control" title="Resource Properties" size="15">
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
<script>

</script>