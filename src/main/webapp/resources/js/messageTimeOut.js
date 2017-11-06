$(function () {

        var $alert = $('.alert');

        if ($alert.length) {

            setTimeout(function () {
                $alert.fadeOut('slow');
            }, 5000)
        }

        var $requestForm = $('#requestForm');

        if ($requestForm.length) {
            $requestForm.validate({

                rules: {
                    resourceType: {
                        minlength: 4, required: true
                    },
                    description: {
                        minlength: 4, required: true
                    }
                },
                messages: {
                    error: {
                        minlength: 'The request name should not be less than 4 letters'
                    },
                    error: {
                        minlength: 'The request name should not be less than 4 letters'
                    }
                },
                errorElement: 'em',
                errorPlacement: function (error, element) {
                    //add the class of help-block
                    error.addClass('red');

                    //add the error element after the input element
                    error.insertAfter(element);
                }
            });
        }
    }
);
