var resourceType;

function getResourceType(isCloning) {
	$.get("/api/resource/" + resourceTypeID, function (response) {
		resourceType = response;
		if (isCloning)
			delete resourceType['id'];
	}, "json");
}

(function () {

	if (resourceTypeID < 0) {
		resourceTypeID *= -1;
		getResourceType(true);
	} else if (resourceTypeID > 0) {
		getResourceType(false);
	}

})();

(function () {
	$('save-type-btn').click(function (e) {
		$.ajax("/resources", {
			method: 'POST',
			data: {},
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