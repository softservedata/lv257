var resourceType = {};
var isModeCloning;

function updateView() {
		//todo: update assigned properties table
}

function updateResourceType(actualType, isCloned) {
	resourceType = actualType;
	// eliminate id in cloned Resource Type (or it should be set to 0 just as for a newly created definition)
	if (isCloned)
		delete resourceType['id'];
	if (resourceType.id) {
		resourceTypeID = resourceType.id;
	}
	initialType = $.extend(true, {}, resourceType);
	let typePropertyIDs = $.map(resourceType.properties, function (constrainedProperty, i) {
		return id;
	})
	assignedProperties = $.grep(availableProperties, function (property) {
		return $.inArray(property.id, typePropertyIDs);
	})
	updateView();
}

function getResourceType(isCloned) {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		accept: "application/json",
		url: projectPathPrefix + "/api/resource/" + resourceTypeID,
		success: function (response) {
			updateResourceType(response, isCloned)
		},
		error: function (jqxhr, status, exception) {
			console.log('Error has occured: ' + status + ' ' + exception);
		}
	});
}

function showSuccessMessage() {

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

	let constrainedPropertiesBrief = $.map(assignedProperties, function (constrainedProperty, i) {
		let propBrief = {
			id: constrainedProperty.property.id,
			searchable: constrainedProperty.searchable,
			required: constrainedProperty.required
		};
		return propBrief;
	});

	resourceType = {
		id:resourceTypeID,
		categoryId: categoryID,
		typeName: $('#type-name').val(),
		tableName: $('#table-name').val(),
		properties: constrainedPropertiesBrief
	}
	return resourceType;
}

/**
 * init model for current view
 */
(function uploadResourceType() {

	isModeCloning = resourceTypeID < 0;
	if (isModeCloning) {
		resourceTypeID *= -1;
		getResourceType(isModeCloning);
	} else if (resourceTypeID > 0) {
		getResourceType(isModeCloning);
	} //else we're creating new ResourceType

})();

//
$('#categories-select').change(function(e) {
	categoryID = $(e.target).data('selectedID');
});

// set Save button handler
$('#save-type-btn').click(function (e) {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: projectPathPrefix + "/api/resource",
		accept: "application/json",
		data: JSON.stringify(composeResourceType()),
		success: function (response, status, jqxhr) {
			updateResourceType(response);
			showSuccessMessage();
		},
		error: function (jqxhr, status, exception) {
			// provide error message
		}
	});

	// set input handler trimming leading and tail spaces
	$('input[type="text"]').blur(function(e) {
		let input = e.target;
		let trimmedValue = $.trim($(input).val());
		$(input).val(trimmedValue);
		input.checkValidity();
	});
});