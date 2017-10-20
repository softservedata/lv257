var isModeCloning;
var resourceType = {};
var initialType = {};

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

function updateResourceType(actualType, isCloned) {
	resourceType = actualType;
	// eliminate id in cloned Resource Type (or it should be set to 0 just as for a newly created definition)
	if (isCloned) {
		delete resourceType['id'];
		$('#type-name').val('');
		$('#table-name').val('');
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
			searchable: constrainedPropertyBrief.searchable
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

function composeResourceType(checkValidity) {
	let $inputs = $('input, select', '#resource-type');
	$inputs.each(function (i, input) {
		if (checkValidity && !input.checkValidity()) {
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
	}

});


$("#define-btn").click(function (e) {
	$('#define-btn, #definition-form').toggleClass('hidden');
});

$('#categories-select').change(function(e) {
	resourceCategorySelect.setSelectedId(resourceCategorySelect.getSelectedId());
});

// prevent saving of ResourceType definition without changes
setInterval(
		function () {
			let $saveBtn = $('#save-type-btn');
			let $discardBtn = $('#discard-btn');
			const currentTypeJSON = JSON.stringify(composeResourceType());
			const initialTypeJSON = JSON.stringify(initialType);
			//todo: localeCompare needs sorted by Id properties or need to implement dedicted type comparator
			if (currentTypeJSON.localeCompare(initialTypeJSON) === 0) {
				$saveBtn.addClass('disabled');
				$discardBtn.addClass('disabled');
			} else {
				$saveBtn.removeClass('disabled');
				$discardBtn.removeClass('disabled');
			}
		}, 1000);

// set Save button handler
$('#save-type-btn').click(function (e) {
	const resourceType = composeResourceType(true);
	if (resourceType === initialType) return;
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: projectPathPrefix + "/api/resource",
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
	resourceType = $.extend(true, {}, initialType);
	updateView();
});

// set input handler trimming leading and tail spaces
$('input[type="text"]').blur(function(e) {
	let input = e.target;
	let trimmedValue = $.trim($(input).val());
	$(input).val(trimmedValue);
	input.checkValidity();
});