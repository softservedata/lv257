$(document).ready(function(){

    /* Generate owner form depending on owner type */
    $('#owner_type').on('change', function () {
        const ownerType = this.value;

        if (ownerType == 'absent') {
            $resourceNewOwnerForm.empty();
            $ownerAddressForm.empty();
        } else if (ownerType == 'company') {
            addOwnerFormAndSaveResult(fieldsMetadata.rowsForCompany, ownerType);
        } else if (ownerType == 'person') {
            addOwnerFormAndSaveResult(fieldsMetadata.rowsForPerson, ownerType);
        }
    });

    /**
     * Builds owner form with the possibility to add owner's address.
     * Make ajax call to the server to save owner with address.
     */
    function addOwnerFormAndSaveResult(rows, ownerType) {
        $resourceNewOwnerForm.empty();
        $ownerAddressForm.empty();

        var $form = $('<form/>', {
            class: 'form',
            id: ownerFormId
        });
        // append form for the owner and append all rows needed by this form
        $resourceNewOwnerForm.append($form);
        appendRows($form, rows);

        var $clearfix = $('<div/>', {
            class: 'clearfix'
        });
        var $registerOwnerBtn = $('<button/>', {
            text: "Register owner",
            class: 'btn pull-right btn-success'
        });
        var $addOwnerAddresBtn = $('<button/>', {
            text: "Add owner address",
            class: 'btn pull-left btn-primary'
        });

        // append button to register owner
        $resourceNewOwnerForm.append($registerOwnerBtn);
        $resourceNewOwnerForm.append($clearfix);
        // append button to add owner's address
        $resourceNewOwnerForm.append($addOwnerAddresBtn);
        $resourceNewOwnerForm.append($clearfix);

        // another div where I place form for the owner's address and button
        // before user press 'Add Owner Address'
        // I do it right now, because I want to add listener to 'Register Address' button.
        var $addressDiv = $('<div/>', {
            id: 'ghostDiv',
            class: 'padding_top_40'
        });

        // add address form inside $addressDiv and returns form id
        let $addressForm = addAddressFormWithoutBtn($addressDiv, fieldsMetadata.rowsForAddress);

        // 'Register Address' button, I attach listener to it to retrieve all inputs filled by user
        var $registerOwnerAddressBtn = $('<button/>', {
            text: "Register address",
            class: 'btn pull-right btn-success'
        });
        $addressDiv.append($registerOwnerAddressBtn);

        // when press 'Add owner's address', $addressDiv with already appended form for address and button
        // is appended to ownerAddress div placeholder
        $addOwnerAddresBtn.on('click', function (e) {
            e.preventDefault();
            $registerOwnerBtn.prop('disabled', true);
            $(this).remove();
            $ownerAddressForm.append($addressDiv);
            $ownerAddressForm.append($clearfix);
        });

        //validating address form
        validateAddress($addressForm);

        if(ownerType == 'company'){
            validateCompany($form);
        } else {
            validatePerson($form);
        }

        let ownerAddressJson;
        // here I retrieve all inputs for the owner address form
        // add push this json into the array
        $registerOwnerAddressBtn.on('click', function (e) {
            e.preventDefault();

            if ($addressForm.valid()){
                $registerOwnerBtn.prop('disabled', false);
                ownerAddressJson = toJSONString(addressFormId);
                console.log('owner address form: ' + JSON.stringify(ownerAddressJson));
                $ownerAddressForm.hide(1500);
                $resourceNewOwnerForm.append($clearfix);
            } else {
                console.log('owner address form is invalid!!');
            }
        });

        // here I retrieve all inputs for the owner form,
        // push this json into the array, also push ownerType
        // and call ajax function to register owner
        $registerOwnerBtn.on('click', function (e) {
            e.preventDefault();

            if($form.valid()){
                console.log('owner form is valid!!');

                let ownerFormJson = toJSONString(ownerFormId);

                console.log('owner form: ' + JSON.stringify(ownerFormJson));
                ownerFormJson["type"] = ownerType;
                ownerFormJson["address"] = ownerAddressJson;

                let ownerWithAddress = JSON.stringify(ownerFormJson);

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/resources/owner",
                    accept: "application/json",
                    data: ownerWithAddress,
                    success: function (result) {
                        console.log('result from the server: ' + result.objectId + " " + result.message);
                        $ownerAddressForm.empty();
                        $ownerAddressForm.show();

                        showOptionsForSelect($resourceOwnersSelect, result);
                        closePopUp($resourceNewOwnerForm, 'Resource owner was saved', $resourceNewOwnerPopUp);
                    },
                    // on errors, show messages with explanations
                    error: function (result) {
                        var parse = JSON.parse(result.responseText);
                        console.log('errors in fields: ' + parse);

                        $('.my_error_class').empty();
                        $ownerAddressForm.show(1500);
                        $ownerAddressForm.append($clearfix);
                        appendHibernateErrors(parse);
                    }
                })
            } else {
                console.log('owner form is invalid!!');
            }
        });
    }

    /**
     *  Dynamic adding address form
     * Takes:
     * element  - formPlaceholder - div element, in which form will be placed
     */
    function addAddressFormWithoutBtn($placeholder, rows) {
        // clears div element
        $placeholder.empty();

        var $addressForm = $('<form/>', {
            class: 'form',
            id: addressFormId
        });
        $placeholder.append($addressForm);
        appendRows($addressForm, rows);
        return $addressForm;
    }

    /**
     * Renders resource address form in the popUp window
     */
    $('#add_resource_address_btn').on('click', function () {
        addAddressForm($resourceAddressFormPlaceholder, fieldsMetadata.rowsForAddress);
        validateFormAndSaveResult($resourceAddressFormPlaceholder, addressFormId, customButtonId);

    });

    /**
     * Dynamic adding address form
     * Takes:
     * element  - formPlaceholder - div element, in which form will be placed
     *
     * Returns:
     * array, where first element - id of generated form
     *              second element - id of submit button
     */
    function addAddressForm($formPlaceholder, rows) {
        // clears div element
        $formPlaceholder.empty();

        var $form = $('<form/>', {
            class: 'form',
            id: addressFormId
        });

        $formPlaceholder.append($form);
        appendRows($form, rows);
        appendButton($form, "Register new Address", true, true);
    }

    /** Appending rows.
     * Each row in the array is the <div class="row"> and may have one
     * or more input tags.
     * This method generates all this element and append them to each other
     * and to the given form.
     */
    function appendRows($form, rows) {
        for (var i = 0; i < rows.length; i++) {
            var $row = $('<div/>', {
                class: 'row'
            });
            $form.append($row);

            for (var j = 0; j < rows[i].length; j++) {
                var $col = $('<div/>', {
                    class: 'col-sm-' + rows[i][j].size
                });
                $row.append($col);

                var $formGroup = $('<div/>', {
                    class: 'form-group'
                });
                $col.append($formGroup);

                var $label = $('<label/>', {
                    for: rows[i][j].userFriendlyName.toLowerCase().replace(" ", "_"),
                    text: rows[i][j].userFriendlyName
                });
                $formGroup.append($label);

                var $input = $('<input/>', {
                    type: 'text',
                    class: 'form-control',
                    name : rows[i][j].userFriendlyName.toLowerCase().replace(" ", "_"),
                    id: rows[i][j].userFriendlyName.toLowerCase().replace(" ", "_"),
                    placeholder: rows[i][j].placeholder
                });
                $formGroup.append($input);

                var $error = $('<div/>', {
                    class: 'my_error_class'
                });
                $formGroup.append($error);
            }
        }
    }

    /** Appending submit button to the form.
     * Takes:
     * element  - form itself,
     * string   - text - text that will be placed on button
     * boolean  - right - if true, btn will be placed in the right corner, if not - left
     * boolean  - success - if true, btn will green , if not - blue
     */
    function appendButton(form, text, right, success) {
        var $successButton = $('<button/>', {
            class: 'btn' +
            (right ? ' pull-right ' : ' pull-left ') +
            (success ? ' btn-success ' : ' btn-primary '),
            text: text,
            id: customButtonId
        });
        var $clearfix = $('<div/>', {
            class: 'clearfix'
        });
        form.append($successButton);
        form.append($clearfix);
    }

    /**
     * If client-side validation succeed, than send request to the server.
     * Than server-side validation is performed.
     * If server-side validation fall, message with error is shown
     *
     * @param $placeholder
     * @param formId
     * @param customButtonId
     */
    function validateFormAndSaveResult($placeholder, formId, customButtonId){
        var $form = $('#' + formId);
        var $submitButton = $('#' + customButtonId);

        validateAddress($form);

        $submitButton.on('click', function (e) {
            e.preventDefault();
            if($form.valid()){
                console.log(formId + ' is valid');

                var json = JSON.stringify(toJSONString(formId));
                console.log('json from form ' + formId + ' : ' + json);

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/resources/address",
                    accept: "application/json",
                    data: json,
                    // if success, show select for address and append option with saved address
                    success: function (result) {
                        console.log('result from the server: ' + result.objectId + " " + result.message);

                        showOptionsForSelect($resourceAddressSelect, result);
                        closePopUp($placeholder, 'Resource Address was saved', $resourceAddressPopUp);
                    },
                    // on errors, show messages with explanations
                    error: function (result) {
                        var parse = JSON.parse(result.responseText);
                        console.log('errors in fields: ' + parse);
                        $('.my_error_class').empty();

                        appendHibernateErrors(parse);
                    }
                });
                alert("form valid");
            } else {
                alert("form invalid");
            }
        })
    }

    //show select for address and append option with saved address
    function showOptionsForSelect($placeholder, result) {
        $placeholder.removeClass('display_none');
        $placeholder.prev().removeClass('display_none');

        let $option = $('<option\>', {
            value: result.objectId,
            text: result.message
        });
        $placeholder.append($option);
    }

    function closePopUp($placeholder, text, $modal) {
        $placeholder.empty();

        var $result = $('<div/>', {
            text: text
        });
        var $closePopUp = $('<button/>', {
            text: 'Close',
            class: 'btn btn-default pull-right clearfix'
        });
        var $clear = $('<div/>', {
            class: 'clearfix'
        });

        $placeholder.append($result);
        $placeholder.append($closePopUp);
        $placeholder.append($clear);

        $closePopUp.on('click', function () {
            $placeholder.empty();
            $modal.modal('hide');
        });
    }

    // under each <input> empty <div> is placed to insert there
    // errors that are recieved from server
    function appendHibernateErrors(parse){
        for(let i = 0; i < parse.fieldErrors.length; i++){
            let str = parse.fieldErrors[i].field;
            console.log(str.split(/(?=[A-Z])/).join('_').toLowerCase());
            console.log(str.substring(str.indexOf('.') + 1).split(/(?=[A-Z])/).join('_').toLowerCase());
            var $errorDiv = $('#' + str.substring(str.indexOf('.') + 1).split(/(?=[A-Z])/).join('_').toLowerCase()).next();
            var text = parse.fieldErrors[i].message;
            $errorDiv.text(text);
        }
    }



});

const $resourceAddressFormPlaceholder = $('#resource_address_form_placeholder');
const $resourceAddressPopUp = $('#resourseAdressPopUp');
const $resourceAddressSelect = $('#resource_address');
const addressFormId = 'address_form';
const customButtonId = 'custom_button';

const $resourceNewOwnerForm =  $('#resource_new_owner_form');
const $ownerAddressForm =  $('#owner_address_form');
const $resourceOwnersSelect = $('#resource_owners');
const $resourceNewOwnerPopUp = $('#createNewOwnerPopUp');
const ownerFormId = 'owner_form';

/**
 * This function gets all inputs of a particular form.
 * Takes as a parameter form id.
 * Returns string containing json representation ('field': 'value' ...).
 */
function toJSONString(form) {
    let obj = {};
    let elements = $('#' + form + ' input');
    for (let i = 0; i < elements.length; i++) {
        let element = elements[i];
        let name = element.name;
        let value = element.value;

        if (name) {
            obj[name] = value;
        }
    }
    return obj;
}

/**
 * @param $form - form element to ba validated by JQuery validator plugin
 */
function validateAddress($form){
    $form.validate({
        errorClass: "my_error_class",
        rules: {
            country: {
                required : true,
                minlength : 3,
                maxlength: 30
            },
            region: {
                required : true,
                minlength : 3,
                maxlength: 30
            },
            district: {
                required : false,
                minlength : 3,
                maxlength: 30
            },
            postal_index: {
                required : false
            },
            locality: {
                required : true,
                minlength : 3,
                maxlength: 30
            },
            street: {
                required : true,
                minlength : 3,
                maxlength: 30
            },
            building: {
                required : true,
                number: true,
                max: 500
            },
            block: {
                required : false
            },
            apartment: {
                required : true,
                number: true,
                max: 500
            },
        }
    });
}

function validateCompany($form){
    $form.validate({
        errorClass: "my_error_class",
        rules: {
            full_name: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            short_name: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            organization_form: {
                required: false,
                minlength: 3,
                maxlength: 30
            },
            ceo: {
                required: false,
                minlength: 3,
                maxlength: 30
            },
            phone: {
                required: true,
                minlength: 11,
                maxlength: 15,
                digits: true
            }
        }
    });
}

function validatePerson($form){
    $form.validate({
        errorClass: "my_error_class",
        rules: {
            first_name: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            last_name   : {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            middle_name: {
                required: false,
                minlength: 3,
                maxlength: 30
            },
            // passport_series: {
            //     required: true,
            //     maxlength: 2,
            //     minlength: 2
            // },
            passport_number: {
                required: true,
                digits: true,
                maxlength: 6,
                minlength: 6
            },
            phone: {
                required: true,
                digits: true,
                minlength: 11,
                maxlength: 15
            }
        }
    });
}

/**
 * Custom object for dynamic form building.
 * Holds uer friendly name, size of column and placeholder for the input
 */
function FieldAndSize(userFriendlyName, size, placeholder) {
    this.userFriendlyName = userFriendlyName;
    this.size = size;
    this.placeholder = placeholder;
}

var fieldsMetadata = {
    rowsForAddress: [
        [new FieldAndSize('Country', 6, 'Ukraine'), new FieldAndSize('Region', 6, 'Region')],
        [new FieldAndSize('District', 6, 'District'), new FieldAndSize('Postal Index', 6, '79060')],
        [new FieldAndSize('Locality', 6, 'Locality'), new FieldAndSize('Street', 6, 'Shevchenka street')],
        [new FieldAndSize('Building', 4, '35'), new FieldAndSize('Block', 4, 'A'), new FieldAndSize('Apartment', 4, '14')]
    ],

    rowsForCompany: [
        [new FieldAndSize('Full Name', 12, 'Full Name')],
        [new FieldAndSize('Short Name', 6, 'Short Name'), new FieldAndSize('Organization Form', 6, 'TzOV')],
        [new FieldAndSize('CEO', 12, 'CEO')],
        [new FieldAndSize('Phone', 12, '+380679365998')]
    ],

    rowsForPerson: [
        [new FieldAndSize('First Name', 6, 'First Name'), new FieldAndSize('Last Name', 6, 'Last Name')],
        [new FieldAndSize('Middle Name', 12, 'Middle Name')],
        [new FieldAndSize('Passport Series', 3, 'KC'), new FieldAndSize('Passport Number', 4, '149875'), new FieldAndSize('Phone', 5, '+30679365998')]
    ]
};