//load dependency
$.getScript('/resources/js/FormSerializePlugin.js');

var assignedProperties = [];
var initialProperties = assignedProperties.slice();

function updateAssignedPropsTable() {
	let $assignedPropsTable = $('#assigned-props');
	if (assignedProperties.length > 0)
				$assignedPropsTable.removeClass('hidden');
			else
				$assignedPropsTable.addClass('hidden');
}

/**
 * Remove specified property from ResourceType's assignedProperties collection
 * @param propertyToRemove
 */
function removeAssignedProperty(propertyToRemove) {
	assignedProperties = $.grep(assignedProperties, function (property) {
		return property != propertyToRemove;
	}, false);
	updateAssignedPropsTable();
}

/**
 * populate Existent Properties dialog with those available properties
 * that aren't in assignedProperties
 */
function updateAvailablePropertiesList() {
	let unconstrainedProperties = assignedProperties.map(function (i, assignedProperty) {
		return assignedProperty.property;
	});

	let availableProperties = $.grep(existentProperties, function (property) {
		return $.inArray(property, unconstrainedProperties) < 0;
	}, false);

	$('#available-properties').empty();

	function composeDescription(property, short){
		let description = property.title;
		let unitsField = short ? 'unitsShort' : 'units';
		if (property[unitsField])
			description += ', ' + property[unitsField];
		else if (!short) description = '';
		return description;
	}

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
 * and fresh table of Assigned Properties
 * @param $properties to add
 */
function addAssignedProperties($properties) {
	let defaultConstraint = {
		searchable:false,
		required:true
	};
	let constrainedProperties = $.map($properties, function (property, i) {
		let unconstrainedProperty = {'property': property};
		return $.extend(true, defaultConstraint, unconstrainedProperty);
		// return $.extend(true, defaultConstraint, property);
	})

	$.merge(assignedProperties, constrainedProperties);

	let $rowTemplate = $('#assigned-props tbody>tr:first');
	$.each(constrainedProperties, function (i, constrainedProperty) {
		let $newPropertyRow = $rowTemplate.clone(true, true);
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
	updateAssignedPropsTable();
}

function resetNewPropertyForm() {
	$('#new-property')[0].reset();
	$('#new-property select option[value=""]').removeClass('hidden');
}

/**
 * load existent ResourceProperties
 */
function loadExistentProperties() {
	$.get("/api/resources/properties", function (properties) {
		existentProperties = properties;
		updateAvailablePropertiesList();
	}, "json");
}

// configure available properties dialog
(function initAvailablePropertiesDialog() {

	loadExistentProperties();

	// prevent invoking Add button without select being made
	$('#available-properties').change(function (e) {
		if ($('[value]:checked', '#available-properties').length > 0)
			$addBtn.removeClass('disabled');
		else
			$addBtn.addClass('disabled');
	});

	let $propertyRow = $('#assigned-props tbody>tr:first');

	let $removeBtnTemplate = $propertyRow.find('.glyphicon-remove');

	// set Remove button template handler

	$removeBtnTemplate.click(function (e) {
		let $rowToRemove = $(e.target).closest('tr');
		let propertyToRemove = $rowToRemove.data('property');
		assignedProperties = $.grep(assignedProperties, function (property) {
			return property != propertyToRemove;
		}, false);
		updateAvailablePropertiesList();
		$rowToRemove.remove();
		updateAssignedPropsTable();
	});

	/**
	 * returns event handler for particular constraint click event
	 * @param constraint string representing target constraint (generally 'required' or 'searchable')
	 * @returns {Function} input event handler function for specified constraint
	 */
	function constraintClickHandler(constraint) {
		return function (e) {
			let $targetPropertyRow = $(e.target).closest('tr');
			let targetProperty = $targetPropertyRow.data('property');
			targetProperty[constraint] = e.target.checked();
		}
	}

	// set Required template handler
	$propertyRow.find('.required').click(constraintClickHandler('required'));

	// set Searchable template handler
	$propertyRow.find('.required').click(constraintClickHandler('searchable'));

	let $addBtn = $('#add-props-btn');
	/**
	 * handle Add button click
	 */
	$addBtn.click(function (e) {
		$addBtn.addClass('disabled');

		let $selected = $('[value]:checked', '#available-properties')
				.map(function (i, option) {
					return $(option).data('property')
				});

		// $.merge(assignedProperties, $selected);
		addAssignedProperties($selected);
		$(':selected', '#available-properties').remove();
	});

	// handle Cancel button click
	$('#cancel-btn').click(function (e) {
		$.each($(':selected', '#available-properties'), function (i, option) {
			$(option).removeAttr('selected');
		});
	});

})();
// end init

// configure new property dialog
(function initNewPropertyDialog() {

	// load availaple property value types
	$.get("/api/resources/properties/valueTypes", function (valueTypes) {
		$.each(valueTypes, function (i, type) {
			$('#value-type').append($('<option>', {
				value: type.valueType,
				text: type.title,
				"data-pattern": type.defaultPattern
			}));
		});

		// $('#new-property-modal').on('hide', resetNewPropertyForm());
		// $('#existent-props').on('show', updateAvailablePropertiesList());

		/**
		 * value type select change handler
		 */
		$('#value-type').change(function (e) {
			$('[value=""]', e.target).addClass('hidden');
			let valueTypeDefaultPattern = $('option[value='+($(e.target).val())+']', e.target).data('pattern');
			let $patternInput = $('#pattern');
			// let currentPattern = $.trim($patternInput.val());
			// if (currentPattern == "") {
			$patternInput.val(valueTypeDefaultPattern);
			// }
		})
	}, "json");

	function strCmp(str1, str2) {
		if (!str1 && !str2) return 0;
		else if (!str1) return -1;
		else if (!str2) return 1;
		let val1 = str1.toUpperCase();
		let val2 = str2.toUpperCase();
		return val1 > val2 ? 1 : (val1 < val2 ? -1 : 0);
	}

	function propertyCmp(prop1, prop2) {
		let titleCmp = strCmp(prop1.title, prop2.title);
		return titleCmp != 0 ? titleCmp : strCmp(prop1.units, prop2.units);
	}

	var updateAvailableProperties = function (response, doAssign) {
		let $properties = $(response);
		$.merge(existentProperties, $properties);
		existentProperties.sort(propertyCmp);
		if (doAssign) {
			// $.merge(assignedProperties, $properties); //move to addAssignedProperties
			addAssignedProperties($properties);
		}
		updateAvailablePropertiesList();
	};

	function composeProperty() {
		let $inputs = $('input, select', '#new-property-modal');
		let entries = $inputs.serializeArray({checkboxesAsBools: true});
		let property = $(entries).mapToObject();
		return property;
	}

	function requestSaveProperty(property, doAssign) {
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/api/resources/property",
			accept: "application/json",
			data: JSON.stringify(property),
			success: function (response) {
				updateAvailableProperties(response, doAssign);
			},
			error: function (jqxhr, status, exception) {
				console.log(status + ' ' + exception);
			}
		});
	}

	/**
	 * Save new property definition handler
	 */
	$('#save-btn').click(function (e) {
		e.preventDefault();
		let property = composeProperty();
		requestSaveProperty(property, false);
		// resetNewPropertyForm();
	});

	/**
	 * Save and assign new property
	 */
	$('#save-add-btn').click(function (e) {
		e.preventDefault();
		let property = composeProperty();
		requestSaveProperty(property, true);
	});

})();