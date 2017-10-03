<div id="categories-view" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="close-managing">&times;</button>
				<h4>Manage categories</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div id="nestable-menu">
						<button type="button" class="btn btn-primary" data-action="expand-all">Expand All</button>
						<button type="button" class="btn btn-primary" data-action="collapse-all">Collapse All</button>
						<button type="button" class="btn btn-primary" data-action="add-item">Add new item</button>
						<button type="button" class="btn btn-primary" data-action="remove-item">Remove item</button>
					</div>

					<div class="dd scrollable" id="nestable"></div>

					<p><strong>Serialised Output</strong></p>
					<%--<sf:form method="POST" action="${pageContext.request.contextPath}/manageTypes">--%>
					<div class="form-group">
						<textarea <%--path="outputJson" --%>id="nestable-output" class="form-control" rows="5" cols = "30"></textarea>
						<br/>
						<div class="pull-right">
							<button type="button" class="btn btn-primary" <%--data-dismiss="modal" aria-hidden="true" --%>id="save-json">Save</button>
							<button type="button" class="btn" data-dismiss="modal" aria-hidden="true" id="cancel-managing">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>