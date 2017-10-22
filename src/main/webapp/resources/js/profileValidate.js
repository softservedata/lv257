$(function () {

        /*var $alert = $('.alert');

        if ($alert.length) {

            setTimeout(function () {
                $alert.fadeOut('slow');
            }, 5000)
        }*/

        var $profileForm = $('#profileForm');

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
                        minlength: 'The passportSeries should not be less than 02 letters'
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
