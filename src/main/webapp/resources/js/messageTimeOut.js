$(function () {

        var $alert = $('.alert');

        if ($alert.length) {

            setTimeout(function () {
                $alert.fadeOut('slow');
            }, 5000)
        }

        var $requestForm = $('#requestForm');
        var $profileForm = $('#profileForm');

        //$requestForm.fontcolor("red");

        if ($requestForm.length) {
            $requestForm.validate({

                rules: {
                    resourceType: {
                        minlength: 4
                    },
                    description: {
                        minlength: 4
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

        if ($profileForm.length) {
            $profileForm.validate({

                rules: {
                    passportSeries: {
                        minlength: 2
                    },
                    passportNumber: {
                        minlength: 6
                    }
                },
                messages: {
                    error: {
                        minlength: 'The passportSeries should not be less than 2 letters'
                    },
                    error: {
                        minlength: 'The passportNumber should not be less than 2 letters'
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
