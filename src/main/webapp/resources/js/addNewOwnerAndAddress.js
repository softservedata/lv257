
$(document).ready(function(){

    /* Generate owner form depending on owner type */
    $('#owner_type').on('change', function () {
        var ownerType = this.value;
        // in this div will be rendered form for the owner registration
        var $ownerForm = $('#resource_owner_form');
        // in this div will be rendered form for the owner address
        var $ownerAddressForm = $('#owner_address_form');

        // if selected value is 1 ("Choose owner type"), these two divs are cleared
        if (ownerType == 1) {
            $ownerForm.empty();
            $ownerAddressForm.empty();
        } else if (ownerType == 2) {
            // company form will be rendered
            addOwnerForm($ownerForm, $ownerAddressForm, "company", fieldsMetadata.rowsForCompany, ownerType);
        } else if (ownerType == 3) {
            // person form will be rendered
            addOwnerForm($ownerForm, $ownerAddressForm, "person", fieldsMetadata.rowsForPerson, ownerType);
        }
    });

    /**
     * Builds owner form with the possibility to add owner's address.
     * Make ajax call to the server to save owner with address.
     */
    function addOwnerForm($ownerForm, $ownerAddressForm, forWhat, rows, ownerType) {
        $ownerForm.empty();
        $ownerAddressForm.empty();

        // here will be stored json string
        // 0 index - owner address json string
        // 1 index - owner json string
        // 2 index - owner type, just number
        var ownerAddressFormAndOwnerForm = [];

        // id for the form
        var ownerFormId = 'register_owner_' + forWhat;
        var form = $('<form/>', {
            class: 'form',
            id: ownerFormId
        });
        // append form for the owner and append all rows needed by this form
        $ownerForm.append(form);
        appendRows(form, forWhat, rows);

        var $clearfix = $('<div/>', {
            class: 'clearfix'
        });
        var $registerOwnerBtn = $('<button/>', {
            text: "Register Owner",
            class: 'btn pull-right btn-success'
        });
        var $addOwnerAddresBtn = $('<button/>', {
            text: "Add Owner Address",
            class: 'btn pull-left btn-primary'
        });

        // append button to register owner
        $ownerForm.append($registerOwnerBtn);
        $ownerForm.append($clearfix);
        // append button to add owner's address
        $ownerForm.append($addOwnerAddresBtn);
        $ownerForm.append($clearfix);

        // another div where I place form for the owner's address and button
        // before user press 'Add Owner Address'
        // I do it right now, because I want to add listener to 'Register Address' button.
        var $addressDiv = $('<div/>', {
            id: 'ghostDiv',
            class: 'padding_top_40'
        });

        // add address form iside $addressDiv and returns form id
        var addressFormId = addAddressFormWithoutBtn(forWhat, $addressDiv, fieldsMetadata.rowsForAddress);

        // 'Register Address' button, I attach listener to it to retrieve all inputs filled by user
        var $registerOwnerAddressBtn = $('<button/>', {
            text: "Register Address",
            class: 'btn pull-right btn-success'
        });
        $addressDiv.append($registerOwnerAddressBtn);

        // when press 'Add owner's address', $addressDiv with already appended form for address and button
        // is appended to ownerAddress div placehlder
        $addOwnerAddresBtn.on('click', function (e) {
            e.preventDefault();
            $registerOwnerBtn.prop('disabled', true);
            $(this).remove();
            $ownerAddressForm.append($addressDiv);
            $ownerAddressForm.append($clearfix);
        });

        // here I retrieve all inputs for the owner address form
        // add push this json into the array
        $registerOwnerAddressBtn.on('click', function (e) {
            e.preventDefault();
            $registerOwnerBtn.prop('disabled', false);
            var json = toJSONString(addressFormId);
            ownerAddressFormAndOwnerForm.push(json);
            console.log(ownerAddressFormAndOwnerForm);
            $ownerAddressForm.empty();
            $ownerForm.append($clearfix);
        });

        // here I retrieve all inputs for the owner form,
        // push this json into the array, also push ownerType
        // and call ajax function to register owner
        $registerOwnerBtn.on('click', function (e) {
            e.preventDefault();
            var json = toJSONString(ownerFormId);
            ownerAddressFormAndOwnerForm.push(json);
            ownerAddressFormAndOwnerForm.push(ownerType);

            registerOwner(ownerAddressFormAndOwnerForm, $ownerForm);
        });
    };

    /**
     * Takes:
     * addressAndOwnerJson - data to be send to the server
     * $ownerForm - element to be cleared, when owner is registered
     */
    function registerOwner(addressAndOwnerJson, $ownerForm) {
        // Building string than contains addressJson, ownerJson and owner type with "|" delimiter
        // In the controller this string will be splited by "\\|" regexp
        // and I can parse owner and address json separately
        var jsonToSend = addressAndOwnerJson[0] + "|" + addressAndOwnerJson[1] + "|" + addressAndOwnerJson[2];
        console.log(jsonToSend);

        $.ajax({
            type: "POST",
            contentType: "text/plain",
            url: "/resources/owner",
            accept: "text/plain",
            data: jsonToSend,
            success: function (result) {
                console.log(result);
                insertHiddenInput('#resource_owner_id_input', 'resource_owner_id', result);
                closePopUp($ownerForm, 'Owner was saved.', '#createNewOwnerPopUp');
            }
        })

    }

    /**
     *  Dynamic adding address form
     * Takes:
     * string   - addressFor - purpose of this address form, needed to generate form id
     * element  - formPlaceholder - div element, in which form will be placed
     *
     * Returns:
     * id - id of generated form
     *
     */
    function addAddressFormWithoutBtn(formPlaceholder, rows) {
        // clears div element
        formPlaceholder.empty();

        // id for the form
        const formId = 'address_form';
        var form = $('<form/>', {
            class: 'form',
            id: formId
        });

        formPlaceholder.append(form);
        appendRows(form, addressFor, rows);
        return formId;
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

                        appendHibernateErrors(parse);
                    }
                });
                alert("form valid");
            } else {
                alert("form invalid");
            }
        })
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
                    maxlength: 20
                },
                region: {
                    required : true,
                    minlength : 3,
                    maxlength: 20
                },
                district: {
                    required : false,
                    minlength : 3,
                    maxlength: 20
                },
                postal_index: {
                    required : false
                },
                locality: {
                    required : true,
                    minlength : 3,
                    maxlength: 20
                },
                street: {
                    required : true,
                    minlength : 3,
                    maxlength: 20
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

    //show select for address and append option with saved address
    function showOptionsForSelect($placeholder, result) {
        $placeholder.removeClass('display_none');
        $placeholder.prev().removeClass('display_none');

        let $option = $('<option\>', {
            value: result.objectId,
            text: result.message
        });
        $resourceAddressSelect.append($option);
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
            console.log(parse.fieldErrors[i].field.split(/(?=[A-Z])/).join('_').toLowerCase());
            var $errorDiv = $('#' + parse.fieldErrors[i].field.split(/(?=[A-Z])/).join('_').toLowerCase()).next();
            var text = parse.fieldErrors[i].message;
            $errorDiv.text(text);
        }
    }

    /**
     * This function gets all inputs of a particular form.
     * Takes as a parameter form id.
     * Returns string containing json representation ('field': 'value' ...).
     */
    function toJSONString(form) {
        var obj = {};
        var elements = $('#' + form + ' input');
        for (var i = 0; i < elements.length; i++) {
            var element = elements[i];
            var name = element.name;
            var value = element.value;

            if (name) {
                obj[name] = value;
            }
        }
        return obj;
    }

});

const $resourceAddressFormPlaceholder = $('#resource_address_form_placeholder');
const $resourceAddressPopUp = $('#resourseAdressPopUp');
const $resourceAddressSelect = $('#resource_address');
const addressFormId = 'address_form';
const customButtonId = 'custom_button';

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