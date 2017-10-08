<div id="categories-view" class="modal fade" tabindex="-1" data-focus-on="input:first" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="close-managing">&times;</button>
				<h4>Manage categories</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div id="nestable-menu">
						<button type="button" class="btn btn-primary" id="exp-col">Expand/Collapse tree</button>
                        <button class="btn btn-default btn-add pull-right"><span class="glyphicon glyphicon-plus"></span></button>
					</div>

					<div class="dd scrollable" id="nestable"></div>

					<p><strong>Serialised Output</strong></p>
					<div class="form-group" <%--hidden--%>>
						<textarea id="nestable-output" class="form-control"
											rows="5" cols = "30" style="resize: vertical"></textarea>
					</div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save-changes">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true" id="cancel-managing">Cancel</button>
            </div>
        </div>
	</div>
</div>
<jsp:include page="addEditCategory.jsp"/>
<script src="${contextPath}/resources/js/jquery.nestable.js"></script>