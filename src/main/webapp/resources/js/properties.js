var assignedProperties = [];
var existentProperties = [];

//load dependencies
// $.getScript(projectPathPrefix + '/resources/js/jquery.validate.js');
$.getScript(projectPathPrefix + '/resources/js/FormSerializePlugin.js');

function refreshAssignedPropsTable() {

	let $assignedPropsTable = $('#assigned-props');

	if (assignedProperties.length > 0)
		$assignedPropsTable.removeClass('hidden');
	else
		$assignedPropsTable.addClass('hidden');

	$assignedPropsTable.find('.assigned-property').remove();

	let $rowTemplate = $assignedPropsTable.find('tbody>tr:first');

	$.each(assignedProperties, function (i, constrainedProperty) {
		let $newPropertyRow = $rowTemplate.clone(true, true);
		$newPropertyRow.removeClass('assigned-template').addClass('assigned-property');
		$newPropertyRow.data('property', constrainedProperty);
		$newPropertyRow.find('td.title').text(constrainedProperty.property.title);
		let $unitsCell = $newPropertyRow.find('td.units-short');
		$unitsCell.text(constrainedProperty.property.unitsShort);
		$unitsCell.attr('title', constrainedProperty.property.units);
		$newPropertyRow.find('input.searchable').prop('checked', constrainedProperty.searchable);
		$newPropertyRow.find('input.required').prop('checked', constrainedProperty.required);
		// let $removeButton = $newPropertyRow.find('.glyphicon-remove');
		$newPropertyRow.removeClass('hidden');
		$('tbody', '#assigned-props').append($newPropertyRow);
	});
}

/**
 * Remove specified property from ResourceType's assignedProperties collection
 * @param propertyId
 */
function removeAssignedProperty(propertyId) {
	assignedProperties = $.grep(assignedProperties, function (constrainedProperty) {
		return constrainedProperty.property.id !== propertyId;
	});
	refreshAssignedPropsTable();
	updateAvailablePropertiesList();
}

/**
 * populate Existent Properties dialog with those available properties
 * that aren't in assignedProperties
 */
function updateAvailablePropertiesList() {

	function composeDescription(property, short) {
		let description = property.title;
		let unitsField = short ? 'unitsShort' : 'units';
		if (property[unitsField])
			description += ', ' + property[unitsField];
		else if (!short) description = '';
		return description;
	}

	$('#available-properties').empty();

	const propertyIDs = assignedProperties.map(function (assignedProperty, i) {
		return assignedProperty.property.id;
	});

	availableProperties = $.grep(existentProperties, function (property) {
		const pos = $.inArray(property.id, propertyIDs);
		return pos < 0;
	}, false);

	$.each(availableProperties, function (i, property) {
		$('#available-properties').append($('<option>', {
			value: property.id,
			text: composeDescription(property, true),
			title: composeDescription(property, false)
		}).data('property', property));
	});
}

/**
 * Add specified properties to ResourceType's assignedProperties collection
 * and refresh table of Assigned Properties
 * @param $properties to add
 */
function addAssignedProperties($properties) {

	let constrainedProperties = $.map($properties, function (property, i) {
		return {
			'property': property,
			searchable:false,
			required:true
		}
	});

	$.merge(assignedProperties, constrainedProperties);
	refreshAssignedPropsTable();
	updateAvailablePropertiesList();
}

function resetNewPropertyForm() {
	const $form = $('#property-form');
	$form[0].reset();
	$form.find('select option[value=""]').removeClass('hidden');
}

/**
 * load existent ResourceProperties
 */
function loadExistentProperties() {
	$.get(projectPathPrefix + "/api/resources/properties", function (properties) {
		existentProperties = properties;
		updateAvailablePropertiesList();
	}, "json");
}

/**
 * returns event handler for particular constraint click event
 * @param constraint string representing target constraint (generally 'required' or 'searchable')
 * @returns {Function} input event handler function for specified constraint
 */
function constraintClickHandler(constraint) {
	return function (e) {
		let $targetPropertyRow = $(e.target).closest('tr');
		let targetProperty = $targetPropertyRow.data('property');
		targetProperty[constraint] = $(e.target).is(':checked');
	}
}

/**
 * ResourceProperty comparator
 * @param prop1
 * @param prop2
 * @returns -1, 0 or 1 if prop1 is less, equals or greater than prop2 respectively
 */
function propertyCmp(prop1, prop2) {
	let titleCmp = strCmp(prop1.title, prop2.title);
	return titleCmp != 0 ? titleCmp : strCmp(prop1.units, prop2.units);
}

/**
 * String comparator
 * @param str1
 * @param str2
 * @returns -1, 0 or 1 if str1 is less, equals or greater than str2 respectively
 */
function strCmp(str1, str2) {
	if (!str1 && !str2) return 0;
	else if (!str1) return -1;
	else if (!str2) return 1;
	let val1 = str1.toUpperCase();
	let val2 = str2.toUpperCase();
	return val1 > val2 ? 1 : (val1 < val2 ? -1 : 0);
}

function updatePropertiesIndex() {

}

/**
 * Appends newly created property to the list
 * @param property to be added to available properties list
 * @param doAssign if set to true
 */
function addAvailableProperty(property, doAssign) {
	let $property = $(property);
	$.merge(existentProperties, $property);
	updatePropertiesIndex();
	existentProperties.sort(propertyCmp);
	if (doAssign) {
		addAssignedProperties($property);
	}
	updateAvailablePropertiesList();
}

/**
 * Create JSON ResourceProperty object
 * @returns JSON ResourceProperty object
 */
function composeProperty() {
	let $inputs = $('input, select', '#new-property-modal');
	let entries = $inputs.serializeArray({checkboxesAsBools: true});
	let property = $(entries).mapToObject();
	return property;
}

/**
 * sends ajax request for saving provided ResourceProperty state
 * @param property to be saved
 * @param doAssign if true provided property will be assigned to current ResourceProperty
 */
function saveProperty(property, doAssign) {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: projectPathPrefix + "/api/resources/property",
		accept: "application/json",
		data: JSON.stringify(property),
		success: function (response) {
			addAvailableProperty(response, doAssign);
		},
		error: function (jqxhr, status, exception) {
			console.log(status + ' ' + exception);
		}
	});
}


(function initPropertiesDialogs() {

	loadExistentProperties();

	// prevent invoking Add button without select being made
	$('#available-properties').change(function (e) {
		if ($('[value]:checked', '#available-properties').length > 0)
			$addBtn.removeClass('disabled');
		else
			$addBtn.addClass('disabled');
	});

	let $propertyRow = $('#assigned-props tbody>tr:first');

	// set Remove button template handler
	$propertyRow.find('.glyphicon-remove').click(function (e) {
		let $rowToRemove = $(e.target).closest('tr');
		let propertyToRemove = $rowToRemove.data('property');
		removeAssignedProperty(propertyToRemove.property.id);
		$rowToRemove.remove();
	});

	// set Required template handler
	$propertyRow.find('.required').click(constraintClickHandler('required'));

	// set Searchable template handler
	$propertyRow.find('.searchable').click(constraintClickHandler('searchable'));

	let $addBtn = $('#add-props-btn');

	// handle Add button click
	$addBtn.click(function (e) {
		$addBtn.addClass('disabled');

		let $selected = $('[value]:checked', '#available-properties')
				.map(function (i, option) {
					return $(option).data('property')
				});

		addAssignedProperties($selected);
		$(':selected', '#available-properties').remove();
	});

	// handle Cancel button click
	$('#cancel-btn').click(function (e) {
		$.each($(':selected', '#available-properties'), function (i, option) {
			$(option).removeAttr('selected');
		});
	});

	// load available property value types
	$.get(projectPathPrefix + "/api/resources/properties/valueTypes", function (valueTypes) {
		$.each(valueTypes, function (i, type) {
			$('#value-type').append($('<option>', {
				value: type.valueType,
				text: type.title,
				"data-pattern": type.defaultPattern
			}));
		});
		// type select change handler
		$('#value-type').change(function (e) {
			$('[value=""]', e.target).addClass('hidden');
			let valueTypeDefaultPattern = $('option[value='+($(e.target).val())+']', e.target).data('pattern');
			let $patternInput = $('#pattern');
			$patternInput.val(valueTypeDefaultPattern);
		})
	}, "json");

	// Save new property definition
	$('#save-btn').click(function (e) {
		e.preventDefault();
		let property = composeProperty();
		saveProperty(property, false);
		if (!$('#keep-on-save').is(':checked')) {
			$('#new-property-modal').modal('hide');
		}
		if ($('#reset-on-save').is(':checked')) {
			resetNewPropertyForm();
		}
	});

	// Save new property definition and assign to ResourceType
	$('#save-add-btn').click(function (e) {
		e.preventDefault();
		let property = composeProperty();
		saveProperty(property, true);
		if (!$('#keep-on-save').is(':checked')) {
			$('#new-property-modal').modal('hide');
		}
		if (!$('#keep-on-save').is(':checked')) {
			resetNewPropertyForm();
		}
	});

})();

const $property = $('#property-form');

$property.validate({
	errorClass: "my_error_class",
	rules: {
		title: {
			required: true,
			minlength: 3
		},
		columnName: {
			required: true,
			minlength: 3
		},
		units: {
			required: false,
			minlength: 3
		},
		unitsShort: {
			required: false,
			minlength: 1
		},
		valueType: "required",
		pattern: "required"
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