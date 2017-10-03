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