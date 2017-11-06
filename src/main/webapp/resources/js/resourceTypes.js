var isModeCloning;
var resourceType = {};
var initialType = {};
var changeWatchdog;

function updateView() {
	$('#type-name').val(resourceType.typeName);
	$('#table-name').val(resourceType.tableName);
	updateAvailablePropertiesList();
	refreshAssignedPropsTable();
	// console.log('resource.categoryID = '+	resourceType.categoryId);
	resourceCategorySelect.selectItem(resourceType.categoryId);
	const $definitionForm = $('#definition-form');
	if ($definitionForm.is(':hidden'))
		$definitionForm.removeClass('hidden');
}

// prevent saving of ResourceType definition without changes
function resetWatchdog() {
	if (changeWatchdog) clearTimeout(changeWatchdog);
	changeWatchdog = setInterval(
			function () {
				let $btnBlock = $('#buttons-block');
				$btnBlock[isTypeDefinitionChanged() ? 'addClass' : 'removeClass']('hidden')
			}, 500);
}

function updateResourceType(actualType, isCloned) {
	resourceType = actualType;
	// eliminate id in cloned Resource Type (or it should be set to 0 just as for a newly created definition)
	if (isCloned) {
		resourceTypeID = 0;
		resourceType['id'] = 0;
		resourceType['typeName'] = '';
		resourceType['tableName'] = '';
	}

	if (resourceType.id) {
		resourceTypeID = resourceType.id;
	}
	if (resourceType.categoryId) {
		categoryID = resourceType.categoryId;
	}
	initialType = $.extend(true, {}, resourceType);

	let constrainedPropertiesBrief = {};
	$.each(resourceType.properties, function (i, constrainedPropertyBrief) {
		constrainedPropertiesBrief[constrainedPropertyBrief.id] = {
			required: constrainedPropertyBrief.required,
			searchable: constrainedPropertyBrief.searchable,
			unique: constrainedPropertyBrief.unique
		};
	});
	$.each(existentProperties, function (i, property) {
		const id = property.id;
		if (constrainedPropertiesBrief[id]) {
			let prop = {property: property};
			constrainedPropertiesBrief[id] = $.extend(true, constrainedPropertiesBrief[id], prop);
		}
	});

	assignedProperties = $.map(constrainedPropertiesBrief, function (constrainedProperty, i) {
		return constrainedProperty;
	});

	updateView();

	resetWatchdog();
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
	$('#successMsg').removeClass('hidden');
	setTimeout(function () {
		$('#successMsg').addClass('hidden');
	}, 3000);
}

function showErrorMessage(message) {
	$('#errorMsg').removeClass('hidden');
	setTimeout(function () {
		$('#successMsg').addClass('hidden');
	}, 3000);
}

function composeResourceType(checkValidity) {
	// if (resourceCategorySelect.getSelectedId() === 0)
	// 	return undefined;
	let $inputs = $('input, select', '#resource-type');
	$inputs.each(function (i, input) {
		if (checkValidity && !input.checkValidity()) {
			input.blur();
			// let label = $(input).prev('label');
			// showErrorMessage(label.text() + ' value is invalid');
			return initialType;
		}
	});

	let constrainedPropertiesBrief = $.map(assignedProperties, function (constrainedProperty, i) {
		let propBrief = {
			id: constrainedProperty.property.id,
			searchable: constrainedProperty.searchable,
			required: constrainedProperty.required,
			unique: constrainedProperty.unique
		};
		return propBrief;
	});

	resourceType = {
		id:resourceTypeID,
		categoryId: resourceCategorySelect.getSelectedId(),
		typeName: $('#type-name').val(),
		tableName: $('#table-name').val(),
		properties: constrainedPropertiesBrief
	}
	return resourceType;
}

/**
 * init model for current view
 */
$('#categories-select').load(function () {
	isModeCloning = resourceTypeID < 0;
	if (isModeCloning) {
		resourceTypeID *= -1;
		getResourceType(isModeCloning);
	} else if (resourceTypeID > 0) {
		getResourceType(isModeCloning);
	} else {
		//else we're creating new ResourceType
		initialType = composeResourceType();
		resetWatchdog();
	}

});

$("#define-btn").click(function (e) {
	$('#define-btn, #definition-form').toggleClass('hidden');
});

$('#categories-select').change(function(e) {
	resourceCategorySelect.setSelectedId(resourceCategorySelect.getSelectedId());
});

function isTypeDefinitionChanged() {
	const currentTypeCopy = $.extend(true, {}, composeResourceType());
	sortByProperty(currentTypeCopy.properties, 'id');
	const currentTypeJSON = JSON.stringify(currentTypeCopy);
	const initialTypeCopy = $.extend(true, {}, initialType);
	sortByProperty(initialTypeCopy.properties, 'id');
	const initialTypeJSON = JSON.stringify(initialTypeCopy);
	console.log(currentTypeJSON);
	console.log(initialTypeJSON);
	return currentTypeJSON.localeCompare(initialTypeJSON) === 0;
}

// set Save button handler
$('#save-type-btn').click(function (e) {
	const resourceType = composeResourceType(true);
	if (!resourceType || resourceType === initialType) return;
	let requestReference = '';
	if (resourceRequestID !== 0) {
		requestReference += '/requestId/' + resourceRequestID;
	}
	const requestURL = projectPathPrefix + "/api/resource" + requestReference;
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: requestURL,
		accept: "application/json",
		data: JSON.stringify(resourceType),
		success: function (response, status, jqxhr) {
			updateResourceType(response);
			showSuccessMessage();

		},
		error: function (jqxhr, status, exception) {
			// provide error message
		}
	});
});

$('#discard-btn').click(function (e) {
	updateResourceType(initialType, true);
	// resourceType = $.extend(true, {}, initialType);
	// updateView();
});

// set input handler trimming leading and tail spaces
$('input[type="text"]').blur(function(e) {
	let input = e.target;
	let trimmedValue = $.trim($(input).val());
	$(input).val(trimmedValue);
	input.checkValidity();
});

$.validator.addMethod('categoryPresent', function () {
	return resourceCategorySelect.getSelectedId() !== 0;
}, "Please, select a category, the Resource Type belongs to");

$('#resource-type').validate({
	errorClass: "my_error_class",
	rules: {
		typeName: {
			required: true,
			minlength: 3
		},
		tableName: {
			required: true,
			minlength: 3
		}
	},
	messages: {
		title: {
			required: "Please, provide a name for a property",
			minlength: "Name of a property should be at least 3 characters"
		},
		columnName: {
			required: "Please, provide a name for a property's column name",
			minlength: "Name of a column name should be at least 3 characters"
		},
		units: {
			required: false,
			minlength: "Units name should be at least 2 characters"
		},
		unitsShort: {
			required: false,
			minlength: "Units name's contraction should be at least 1 character"
		},
		valueType: "Please, select type of a propoerty's value",
		pattern: "Please, provide a pattern regex for property's value validation"
	}
});