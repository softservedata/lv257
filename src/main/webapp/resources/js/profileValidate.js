$(function () {

        var $profileForm = $('#profileForm');

        jQuery.validator.addMethod(
            'fName',
            function (value, element, regexp) {
                alert(value + " \n " + element + "  \n " + regexp);
                var re = new RegExp(regexp);
                return this.optional(element) || re.test(value);
            },
            "Please check your input. " +
            "This field should starts from Capital Letter and contains only Letters of English alphabet or dash"
        );

        jQuery.validator.addMethod(
            'fPassportSeries',
            function (value, element, regexp) {
                var re = new RegExp(regexp);
                return this.optional(element) || re.test(value);
            },
            "Please check your input. " +
            "Passport Series should contains two Capital Letters of English alphabet"
        );

        jQuery.validator.addMethod(
            'fPhoneNumber',
            function (value, element, regexp) {
                var re = new RegExp(regexp);
                return this.optional(element) || re.test(value);
            },
            "Please check your input. " +
            " Phone number should contains 8 digits"
        );

    jQuery.validator.addMethod(
        "notnumbers", function (value, element, regexp) {
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
        },
        "Please don't insert numbers.")

        if ($profileForm.length) {
            $profileForm.validate({
                // onfocusout: true,
                // onkeyup: true,
                // onclick: true,
                // onsubmit: true,

                rules: {
                    firstName: {
                        pattern: '!/[0-9]*/'
                    },
                    fPhoneNumber: {
                        rangelength: [2, 2],
                        fPassportSeries: '[A-Z]{2}'
                    },
                    passportSeries: {
                        rangelength: [2, 2],
                        fPassportSeries: '[A-Z]{2}'
                    },
                    passportNumber: {
                        rangelength: [6, 6],
                        digits: true
                    },
                    phone: {
                        // required: true,
                        minlength: 4,
                        maxlength: 20,
                        // rangelength: [4, 20],
                        pattern: '[0-9]+'
                        // pattern: '(/+)?[0-9]+$'
                    }
                },
                messages: {
                    firstName: {
                        pattern: 'Only letters'
                    },
                    passportSeries: {
                        rangelength: 'The passportSeries should not be less than 02 letters',
                        fPassportSeries: 'SAD'
                    },
                    passportNumber: {
                        rangelength: 'The passportNumber should not be less than 2 letters',
                        digits: 'Only digits'
                    },
                    phone: {
                        minlength: "Length of phone number should be more 4 digits",
                        maxlength: "Length of phone number should be less 20 digits",
                        pattern: "Input phone number as template"
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

/*                    firstName: {
 fName: "([A-Z -])\w+"
 // fName: '[A-Z]{1} [A-Za-z]{1}' //\w'//'^[A-Z]{1}[a-z]{1,14} [A-Z]{1}[a-z]{1,14}$'
 // rangelength: [2, 40],
 // fName: '^[A-Za-z]{1-40}'
 // fName: '/^[а-яё\-]{4,40}+$/iu'
 },
 secondName: {
 rangelength: [2, 2],
 fName: '[A-Z]{2}'
 },
 middleName: {
 rangelength: [2, 2],
 fName: '[A-Z]{2}'
 },*/