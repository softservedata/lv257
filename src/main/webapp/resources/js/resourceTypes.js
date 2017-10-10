var resourceType = {};
var isModeCloning;

function getResourceType(isCloning) {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		accept: "application/json",
		url: "/api/resource/" + resourceTypeID,
		success: function (response) {
			resourceType = response;
			if (isCloning)
				delete resourceType['id'];
		},
		error: function (jqxhr, status, exception) {
			console.log(status + ' ' + exception);
		}
	});
}

function composeResourceType() {
	let $inputs = $('input, select', '#resource-type');
	$inputs.each(function (i, input) {
		if (!input.checkValidity()) {
			input.blur();
			let label = $(input).prev('label');
			alert(label.text() + ' value is invalid');
			throw	new Error();
		}
	});
	resourceType = {
		categoryID: categoryID,
		typeName: $('#type-name').val(),
		tableName: $('#table-name').val(),
		properties: assignedProperties
	}
	return resourceType;
}

(function uploadResourceType() {

	isModeCloning = resourceTypeID < 0;
	if (isModeCloning) {
		resourceTypeID *= -1;
		getResourceType(isModeCloning);
	} else if (resourceTypeID > 0) {
		getResourceType(isModeCloning);
	} //else we're creating new ResourceType

})();

$('#categories-select').change(function(e) {
	categoryID = $(e.target).data('selectedID');
});

// set Save button handler
$('#save-type-btn').click(function (e) {
	let restourceType = composeResourceType();
	$.ajax("/api/resources", {
		method: 'POST',
		data: JSON.stringify(restourceType),
		dataType: 'json',
		contentType: 'application/json',
		success: function (response, status, jqxhr) {

		},
		error: function (jqxhr, status, exception) {

		},
		beforeSend: function () {
			return true;
		}
	});
});