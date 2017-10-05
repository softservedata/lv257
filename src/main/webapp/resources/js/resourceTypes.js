var resourceType;
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
	let $inputs = $('input, select', '#new-property-modal');
	let entries = $inputs.serializeArray({checkboxesAsBools: true});
	let resourceType = entries.mapToObject();
	return resourceType;
}

(function () {

	isModeCloning = resourceTypeID < 0;
	if (isModeCloning) {
		resourceTypeID *= -1;
		getResourceType(isModeCloning);
	} else if (resourceTypeID > 0) {
		getResourceType(isModeCloning);
	}

})();

(function () {
	$('save-type-btn').click(function (e) {
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
	})
})();