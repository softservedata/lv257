
var assignedProperties = [];
var initialProperties = assignedProperties.slice();

/**
 * populate Existent Properties dialog with those available properties
 * that aren't in assignedProperties
 */
function updateAvailablePropertiesList() {
	let availableProperties = $.grep(existentProperties, function (property) {
		return $.inArray(property, assignedProperties) < 0;
	}, false);

	$('#available-properties').empty();

	$.each(availableProperties, function (i, property) {
		$('#available-properties').append($('<option>', {
			value: property.id,
			text: property.description
		}).data('property', property));
	});
}

/**
 * refresh table of Assigned Properties
 */
function addAssignedProperties($propeties) {
	let $rowTemplate = $('#assigned-props tbody>tr:first');
	$.each($propeties, function (i, property) {
		let $newPropertyRow = $rowTemplate.clone(true, true);
		$newPropertyRow.data('property', property);
		$newPropertyRow.find('td:first').text(property.description);
		let $removeButton = $newPropertyRow.find('.glyphicon-remove');
		$newPropertyRow.removeClass('hidden');
		$('tbody', '#assigned-props').append($newPropertyRow);
	});
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

	let $addBtn = $('#add-props-btn');

	loadExistentProperties();

	// prevent invoking Add button without select being made
	$('#available-properties').change(function (e) {
		if ($('[value]:checked', '#available-properties').length > 0)
			$addBtn.removeClass('disabled');
		else
			$addBtn.addClass('disabled');
	});

	// set Remove button template handler
	$('.glyphicon-remove', '#assigned-props tbody>tr:first').click(function (e) {
				let $rowToRemove = $(e.target).closest('tr');
				let propertyToRemove = $rowToRemove.data('property');
				assignedProperties = $.grep(assignedProperties, function (property) {
					return property != propertyToRemove;
				}, false);
				updateAvailablePropertiesList();
				$rowToRemove.remove();
			});

	/**
	 * handle Add button click
 	 */
	$addBtn.click(function (e) {
		$addBtn.addClass('disabled');

		let $selected = $('[value]:checked', '#available-properties')
				.map(function (i, option) {
					return $(option).data('property')
				});

		$.merge(assignedProperties, $selected);
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

	let propertyCmp = function (prop1, prop2) {
		let description1 = prop1.description.toUpperCase();
		let description2 = prop2.description.toUpperCase();
		return description1 > description2 ? 1
				: description1 < description2 ? -1 : 0;
	};

	var updateAvailableProperties = function (response, doAssign) {
		let $property = $(response);
		$.merge(existentProperties, $property);
		existentProperties.sort(propertyCmp);
		if (doAssign) {
			$.merge(assignedProperties, $property);
			addAssignedProperties($property);
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