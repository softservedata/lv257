<div id="nestable-dialog" class="modal fade" tabindex="-1" data-focus-on="input:first" style="display: none;">
    <div id="nestable-dialog-inner" class="modal-dialog <%--modal-sm--%>">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close close-name-dialog" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <input id="category-name-input" class="form-control" type="text" data-tabindex="2"
                       placeholder="Enter desired name for resource category">
            </div>
            <div class="modal-footer">
                <button id="save-name" type="button" class="btn btn-primary">Ok</button>
                <button type="button" data-dismiss="modal" class="btn btn-default close-name-dialog">Cancel</button>
            </div>
        </div>
    </div>
</div>