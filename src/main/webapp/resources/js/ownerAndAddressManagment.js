$(document).ready(function () {

    $deletedAddress.hide();
    $updatedAddress.hide();

    $deletedOwner.hide();

    $ownerSearchSelect.on('change', function () {
        const ownerType = this.value;

        if (ownerType == 'company') {
            $searchOwnerFormPlaceholder.empty();

            ownerSearchOptions(searchByOwnerTypeMetadata.optionsForCompanySearch);
            showConcreteOwnerOptions(ownerType);
        }
        if (ownerType == 'person') {
            $searchOwnerFormPlaceholder.empty();

            ownerSearchOptions(searchByOwnerTypeMetadata.optionsForPersonSearch);
            showConcreteOwnerOptions(ownerType);
        }
        if (ownerType == 'absent') {
            $searchOwnerFormPlaceholder.empty();
        }
    })

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
     * Shows resource address form in the popUp window, validates it and send request to the server.
     * Than depending on the response shows error message or address table.
     */
    $addResourceAddressBtn.on('click', function () {
        // clears all inputs in the form
        $('#' + newResourceAddressFormId).trigger('reset');
        //clears all hibernate errors
        clearHibernateErrorClasses(newResourceAddressFormId);
        // clears jQuery Validator error messages
        if (addressValidator) {
            addressValidator.resetForm();
        }
        validateResourceAddressFormAndSaveResult();
    });

    /**
     * Shows search resource address btn in the popUp.
     */
    $('#search_resource_address_btn').on('click', function (e) {
        $('#' + searchResourceAddressFormId).trigger('reset');
        $resourceAddressSearchResultDiv.empty();

        searchAddress();
    })

});


// add resource address button on the main jsp page
const $addResourceAddressBtn = $('#add_resource_address_btn');
// static resource address form id
const newResourceAddressFormId = 'static_address_form';
// submit button id for the resource address
const newResourceAddressSubmitBtnId = 'resource_address_submit_button';
// search resource address btn id
const searchResourceAddressFormId = 'search_resource_address_form';
// button to search resource address
const searchResourceAddressSubmitBtnId = 'search_resource_address_submit_button';
// hidden input for the resource address id (needed to pass it to the generic resource impl)
const $idAddressHiddenInput = $('#resource_address_id');
// results of address search will be appended here
const $resourceAddressSearchResultDiv = $('.resource_address_search_result')
// div contsining three address related buttons
const $addressButtons = $('#address_buttons');
var addressValidator;


var updateAddress = false;
const $deletedAddress = $('#deleted_address');
const $updatedAddress = $('#updated_address');
const $resourceAddressRow = $('#resource_address_row');
const $resourceAddressTbl = $('.resource_address_table');
const $resourceAddressPopUp = $('#resourseAdressPopUp');
const ownerAddressFormId = 'owner_address_form';

const $deletedOwnerVersionTwo = $('#deleted_owner');
const $resourceOwnerTable = $('.resource_owner_table');
const $ownersTbody = $('.owners_tbody');
const $resourceNewOwnerForm = $('#resource_new_owner_form');
const $ownerAddressForm = $('#owner_address_form');
const $resourceOwnersSelect = $('#resource_owners');
const $resourceNewOwnerPopUp = $('#createNewOwnerPopUp');
const ownerFormId = 'owner_form';


// ----------------------------------- Add new resource address -----------------------------------

/**
 * If client-side validation succeed, than send request to the server.
 * Than server-side validation is performed.
 * If server-side validation fall, message with error is shown.
 *
 */
function validateResourceAddressFormAndSaveResult() {
    const $form = $('#' + newResourceAddressFormId);
    const $submitButton = $('#' + newResourceAddressSubmitBtnId);

    // applies rules for the form
    validateResourceAddress($form);

    $submitButton.on('click', function (e) {
        e.preventDefault();

        if ($form.valid()) {
            let json = JSON.stringify(toJSONString(newResourceAddressFormId));
            console.log('json stringified from ' + newResourceAddressFormId + ' : ' + json);

            let url = '/resources/address';
            if (updateAddress) {
                url = '/resources/address/update';
            }
            saveAddressAjax(json, url);
        } else {
            alert("Some fields in the form are empty or invalid. Please, check errors and correct your input.");
        }
    });
}

/**
 * Saves new address or updates existing.
 * @param json - address json
 * @param url - save new address or update existing
 */
function saveAddressAjax(json, url) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        accept: "application/json",
        data: json,
        // if success, show select for address and append option with saved address
        success: function (result) {
            console.log('result from the server: ' + result);

            // sets newly saved address id to the hiden input
            $idAddressHiddenInput.val(result.addressId);

            // if (updateAddress){
            //     console.log('showing message');
            //     $updatedAddress.fadeIn(300).delay(3500).hide(300);
            // }

            // table row will be green (new address)
            $resourceAddressRow.attr('class', 'my_success');

            showAddressTable(result);

            // showOptionsForSelect($resourceAddressSelect, result);
            // closePopUp($placeholder, 'Resource Address was saved', $resourceAddressPopUp);
        },
        // on errors, show messages with explanations
        error: function (result) {
            let parse = JSON.parse(result.responseText);
            console.log('errors in fields: ' + parse);
            $('.my_error_class').empty();

            appendHibernateErrors(parse);
        }
    });
}

/**
 * Builds one row in the address table
 * @param result - address received from the server
 */
function showAddressTable(result) {
    // hides all buttons connected with the address managment
    $addressButtons.hide(1500);
    $resourceAddressTbl.removeClass('display_none');
    $resourceAddressRow.empty();
    let $td;
    for (let attributeValue in result) {
        if (attributeValue == 'addressId') {
            // $resourceAddressRow.attr('id', result[attributeValue]);
        }
        if (result.hasOwnProperty(attributeValue) && attributeValue != 'addressId') {
            $td = $('<td/>', {
                text: result[attributeValue]
            });
            $resourceAddressRow.append($td);
        }
    }

    $resourceAddressPopUp.modal('hide');
    let editDelete = appendEditDeleteOptions();

    // editDelete[0].on('click', function(e) {
    //     e.preventDefault();
    //     updateAddress = true;
    //     $resourceAddressPopUp.modal('show');
    // });

    editDelete.on('click', function (e) {
        e.preventDefault();
        $resourceAddressTbl.addClass('display_none');

        // if it is new address, than delets from db. If it is address from
        // address search - than delets row.
        if ($resourceAddressRow.attr('class') !== 'my_info') {
            $.ajax({
                type: "DELETE",
                contentType: "application/json",
                url: "/resources/address/delete",
                accept: "application/json",
                data: JSON.stringify(toJSONString(newResourceAddressFormId)),
                success: function (result) {
                    console.log('resource address was deleted');

                    $deletedAddress.fadeIn(300).delay(3500).fadeOut(300);

                },
                // on errors, show messages with explanations
                error: function (result) {
                    console.log('some error during deleting resource address');
                }
            });
        }
        $idAddressHiddenInput.val(0);
        $addressButtons.show(1500);
        $resourceAddressRow.empty();
    })
}

/** under each <input> empty <div> is placed to insert there
 * errors that are received from server
 */
function appendHibernateErrors(parse) {
    for (let i = 0; i < parse.fieldErrors.length; i++) {
        let str = parse.fieldErrors[i].field;
        if (str.split(/(?=[A-Z])/).join('_').toLowerCase() == 'address') {
            alert('Address needs to be added!')
            return;
        }
        console.log(str.split(/(?=[A-Z])/).join('_').toLowerCase());
        console.log(str.substring(str.indexOf('.') + 1).split(/(?=[A-Z])/).join('_').toLowerCase());
        var $errorDiv = $('#' + str.substring(str.indexOf('.') + 1).split(/(?=[A-Z])/).join('_').toLowerCase()).next();
        var text = parse.fieldErrors[i].message;
        $errorDiv.text(text);
    }
}

// clears appended hibernate errors
function clearHibernateErrorClasses(resourceAddressFormId) {
    $('#' + resourceAddressFormId).find('div.my_error_class').each(function () {
        $(this).html("");
    });
}


// ----------------------------------- Search resource address -----------------------------------

/**
 * Builds search json and sends it ti the server.
 */
function searchAddress() {
    let searchJson = {};

    $('#' + searchResourceAddressSubmitBtnId).on('click', function (e) {
        e.preventDefault();

        let formJson = toJSONString(searchResourceAddressFormId);
        console.log(formJson);
        searchJson['entityType'] = 'Address';
        searchJson['fieldsAndValues'] = formJson;

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/resources/address/search",
            accept: "application/json",
            data: JSON.stringify(searchJson),
            success: function (result) {
                console.log(result);
                $resourceAddressSearchResultDiv.empty();
                showSearchedAddress(result);
            },
            error: function (result) {
                const error = JSON.parse(result.responseText);
                console.log();
                $resourceAddressSearchResultDiv.empty();
                showErrorMessage(error, $resourceAddressSearchResultDiv);
            }
        });

    })
}

function showSearchedAddress(result) {
    let $formGroup = $('<div/>', {
        class: 'form-group'
    });
    $resourceAddressSearchResultDiv.append($formGroup);

    let $select = $('<select/>', {
        class: 'form-control'
    });
    $formGroup.append($select);

    let $choose = $('<option\>', {
        value: 'none',
        text: 'Please choose address'
    });
    $select.append($choose);

    for (let i = 0; i < result.length; i++) {
        console.log(result[i]);
        // let id;
        let addressStr = "";
        for (let attributeValue in result[i]) {
            if (attributeValue == 'addressId') {
                // id = result[i][attributeValue];
            }
            if (result[i].hasOwnProperty(attributeValue) && attributeValue != 'addressId') {
                addressStr += result[i][attributeValue] + ", ";
            }
        }
        let $option = $('<option\>', {
            value: i,
            text: addressStr
        });
        $select.append($option);
    }

    let $chooseAddressBtn = $('<button/>', {
        // id: 'choose_owner',
        class: 'btn btn-success pull-right',
        text: 'Choose address'
    });
    let $clearfix = $('<div/>', {
        class: 'clearfix'
    });
    $resourceAddressSearchResultDiv.append($chooseAddressBtn);
    $resourceAddressSearchResultDiv.append($clearfix);

    var chosenAddressId = 'none';
    $select.on('change', function () {
        chosenAddressId = this.value;
    });

    $chooseAddressBtn.on('click', function (e) {
        e.preventDefault();

        if (chosenAddressId != 'none') {
            $resourceAddressRow.attr('class', 'my_info');
            showAddressTable(result[chosenAddressId]);
            // insert address id to the hidden input
            $idAddressHiddenInput.val(result[chosenAddressId].addressId);
            $resourceAddressSearchResultDiv.empty();
            showSuccessMessage($resourceAddressSearchResultDiv, 'Resource address was chosen.');
            setTimeout(function () {
                $('#searchResourseAdressPopUp').modal('hide');
            }, 2000);
        }
    })
}


/**
 * Builds owner form with the possibility to add owner's address.
 * Make ajax call to the server to save owner with address.
 */
function addOwnerFormAndSaveResult(rows, ownerType) {
    $resourceNewOwnerForm.empty();
    $ownerAddressForm.empty();

    let $form = $('<form/>', {
        class: 'form',
        id: ownerFormId
    });
    // append form for the owner and append all rows needed by this form
    $resourceNewOwnerForm.append($form);
    appendRows($form, rows);

    let $clearfix = $('<div/>', {
        class: 'clearfix'
    });
    let $registerOwnerBtn = $('<button/>', {
        text: "Register owner",
        class: 'btn pull-right btn-success'
    });
    let $addOwnerAddresBtn = $('<button/>', {
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
    let $addressDiv = $('<div/>', {
        id: 'ghostDiv',
        class: 'padding_top_40'
    });

    // add address form inside $addressDiv and returns form id
    let $addressForm = addAddressFormWithoutBtn($addressDiv, fieldsMetadata.rowsForAddress);

    // 'Register Address' button, I attach listener to it to retrieve all inputs filled by user
    let $registerOwnerAddressBtn = $('<button/>', {
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
    validateResourceAddress($addressForm);

    if (ownerType == 'company') {
        validateCompany($form);
    } else {
        validatePerson($form);
    }

    let ownerAddressJson;
    // here I retrieve all inputs for the owner address form
    // add push this json into the array
    $registerOwnerAddressBtn.on('click', function (e) {
        e.preventDefault();

        if ($addressForm.valid()) {
            $registerOwnerBtn.prop('disabled', false);
            ownerAddressJson = toJSONString(ownerAddressFormId);
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

        if ($form.valid()) {
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
                    $ownerAddressForm.empty();
                    $ownerAddressForm.show();

                    showOwnersTable(result);

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

function showOwnersTable(result) {
    $resourceOwnerTable.removeClass('display_none');
    let $tr = $('<tr/>', {
        ['owner_type']: 'new_owner',
        class: 'my_success',
        id: result['ownerId']
    });
    $ownersTbody.append($tr);
    for (let attributeValue in result) {
        if (result.hasOwnProperty(attributeValue) && attributeValue != 'ownerId') {
            console.log(result[attributeValue]);
            let $td = $('<td/>', {
                text: result[attributeValue]
            });
            $tr.append($td);
        }
    }

    let $deleteOption = appendDeleteOption($tr);

    $deleteOption.on('click', function (e) {
        e.preventDefault();
        if ($(this).parent().attr('owner_type') == 'new_owner') {
            console.log($(this).parent().attr('id'));
            deleteOwner($(this).parent().attr('id'));
            $tr.remove();
            checkIfOneOwner();
            $deletedOwnerVersionTwo.fadeIn(300).delay(3500).fadeOut(300);
        }
    })

}

function checkIfOneOwner() {
    if ($('.owners_tbody tr').length == 0) {
        console.log($('.owners_tbody tr').length);
        $resourceOwnerTable.addClass('display_none');
    }
}

function deleteOwner(ownerId) {
    $.ajax({
        type: "DELETE",
        url: "owner/" + ownerId + "/delete",
        success: function (result) {
            console.log("owner was deleted from db");
            console.log(result);
        }
    })
}

function appendDeleteOption($tr) {
    $tdDelete = $('<td/>');
    $aDelete = $('<a/>');
    $iDelete = $('<i/>', {
        class: 'glyphicon glyphicon-remove',
        'aria-hidden': "true"
    });
    $tdDelete.append($aDelete);
    $aDelete.append($iDelete);
    $tr.append($tdDelete);
    return $tdDelete;
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
        id: ownerAddressFormId
    });
    $placeholder.append($addressForm);
    appendRows($addressForm, rows);
    return $addressForm;
}

/**
 * Appends edit and delete Options to the table and returns that elements.
 */
function appendEditDeleteOptions() {
    // $tdEdit = $('<td/>');
    // $aEdit = $('<a/>');
    // $iEdit = $('<i/>', {
    //     class: 'glyphicon glyphicon-edit',
    //     'aria-hidden': "true"
    // });
    // $tdEdit.append($aEdit);
    // $aEdit.append($iEdit);

    $tdDelete = $('<td/>');
    $aDelete = $('<a/>');
    $iDelete = $('<i/>', {
        class: 'glyphicon glyphicon-remove',
        'aria-hidden': "true"
    });
    $tdDelete.append($aDelete);
    $aDelete.append($iDelete);

    // $resourceAddressRow.append($tdEdit);
    $resourceAddressRow.append($tdDelete);
    return $tdDelete;
}

/** Appending rows.
 * Each row in the array is the <div class="row"> and may have one
 * or more input tags.
 * This method generates all this element and append them to each other
 * and to the given form.
 */
function appendRows($form, rows) {
    for (let i = 0; i < rows.length; i++) {
        let $row = $('<div/>', {
            class: 'row'
        });
        $form.append($row);

        for (let j = 0; j < rows[i].length; j++) {
            let $col = $('<div/>', {
                class: 'col-sm-' + rows[i][j].size
            });
            $row.append($col);

            let $formGroup = $('<div/>', {
                class: 'form-group'
            });
            $col.append($formGroup);

            let $label = $('<label/>', {
                for: rows[i][j].userFriendlyName.toLowerCase().replace(" ", "_"),
                text: rows[i][j].userFriendlyName
            });
            $formGroup.append($label);

            let $input = $('<input/>', {
                type: 'text',
                class: 'form-control',
                name: rows[i][j].userFriendlyName.toLowerCase().replace(" ", "_"),
                id: rows[i][j].userFriendlyName.toLowerCase().replace(" ", "_"),
                placeholder: rows[i][j].placeholder
            });
            $formGroup.append($input);

            let $error = $('<div/>', {
                class: 'my_error_class'
            });
            $formGroup.append($error);
        }
    }

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

// ----------------------------------- Validation rules for address, owners forms -----------------------------------

/**
 * @param $form - form element to be validated by JQuery validator plugin
 */
function validateResourceAddress($form) {
    let validator = $form.validate({
        errorClass: "my_error_class",
        rules: {
            country: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            region: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            district: {
                required: false,
                minlength: 3,
                maxlength: 30
            },
            postal_index: {
                required: true,
                digits: true,
                minlength: 4,
                maxlength: 6
            },
            locality: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            street: {
                required: false,
                minlength: 3,
                maxlength: 30
            },
            building: {
                required: false,
                digits: true,
                max: 1500
            },
            block: {
                required: false
            },
            apartment: {
                required: false,
                digits: true,
                max: 1500
            },
        }
    });
    addressValidator = validator;
}

/**
 * @param $form - form element to be validated by JQuery validator plugin
 */
function validateCompany($form) {
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

/**
 * @param $form - form element to be validated by JQuery validator plugin
 */
function validatePerson($form) {
    $form.validate({
        errorClass: "my_error_class",
        rules: {
            first_name: {
                required: true,
                minlength: 3,
                maxlength: 30
            },
            last_name: {
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

// ----------------------------------- Custom object and metadata to dynamic form building -----------------------------------

/**
 * Custom object for dynamic form building.
 * Holds uer friendly name, size of column and placeholder for the input
 */
function FieldAndSize(userFriendlyName, size, placeholder) {
    this.userFriendlyName = userFriendlyName;
    this.size = size;
    this.placeholder = placeholder;
}

const fieldsMetadata = {
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

//  ----------------------------------- Search owner part -----------------------------------


/**
 * Appends to the placeholder select with concrete options
 * for chosen owner
 *
 * @param optionsMetadata - on wich fields owner can be searched
 */
function ownerSearchOptions(optionsMetadata) {
    let $formGroup = $('<div/>', {
        class: 'form-group'
    });
    $searchOwnerFormPlaceholder.append($formGroup);

    let $label = $('<label/>', {
        for: concreteOwnerOption,
        text: 'Search by:'
    });
    $formGroup.append($label);

    let $select = $('<select/>', {
        id: concreteOwnerOption,
        class: 'form-control'
    });
    $formGroup.append($select);

    for (let i = 0; i < optionsMetadata.length; i++) {
        let $option = $('<option\>', {
            value: optionsMetadata[i].value,
            text: optionsMetadata[i].text
        });
        $select.append($option);
    }
}

/**
 * Builds select with concrete owner search options.
 * @param ownerType - concrete owner type
 */
function showConcreteOwnerOptions(ownerType) {
    let $concreteOwnerSearchDiv = $('<div/>');
    $searchOwnerFormPlaceholder.append($concreteOwnerSearchDiv);
    let $resultDiv = $('<div/>', {
        class: 'result_div'
    });

    if (ownerType == 'company') {
        handleCompanyOptions($concreteOwnerSearchDiv, $resultDiv, ownerType);
    }
    if (ownerType == 'person') {
        handlePersonOptions($concreteOwnerSearchDiv, $resultDiv, ownerType);
    }
}

/**
 * Depending on particular owner select, renders form and makes ajax call
 * @param $concreteOwnerSearchDiv - placeholder for owner search form
 * @param $resultDiv - placeholder, where results will be put
 * @param ownerType - concrete owner type
 */
function handleCompanyOptions($concreteOwnerSearchDiv, $resultDiv, ownerType) {
    $('#' + concreteOwnerOption).on('change', function () {
        const companySearchOption = this.value;

        if (companySearchOption == 'name') {
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();

            let $findOwnerButton = buildOwnerSearchForm($concreteOwnerSearchDiv, searchByCompanyCharacteristicsMetadata.companyName);
            $concreteOwnerSearchDiv.append($resultDiv);
            makeAjaxCall($findOwnerButton, ownerType, $resultDiv);
        }
        if (companySearchOption == 'absent') {
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();
        }
    })
}

function handlePersonOptions($concreteOwnerSearchDiv, $resultDiv, ownerType) {
    $('#' + concreteOwnerOption).on('change', function () {
        const personSearchOption = this.value;
        let $findOwnerButton;

        if (personSearchOption == 'name') {
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();

            $findOwnerButton = buildOwnerSearchForm($concreteOwnerSearchDiv, searchByPersonCharacteristicsMetadata.personsName)
            $concreteOwnerSearchDiv.append($resultDiv);
            makeAjaxCall($findOwnerButton, ownerType, $resultDiv);
        }
        if (personSearchOption == 'passport') {
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();

            $findOwnerButton = buildOwnerSearchForm($concreteOwnerSearchDiv, searchByPersonCharacteristicsMetadata.personsPassport)
            $concreteOwnerSearchDiv.append($resultDiv);
            makeAjaxCall($findOwnerButton, ownerType, $resultDiv);
        }
        if (personSearchOption == 'absent') {
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();
        }

    })
}

/**
 * Makes request to the server with search values typed by user.
 * If success, renders select with received info.
 * If error, shows error message.
 *
 * @param $findOwnerButton - button to handle submit.
 * @param ownerType - concrete owner type.
 * @param $resultDiv - placeholder where result will be put .
 */
function makeAjaxCall($findOwnerButton, ownerType, $resultDiv) {
    let searchQuery = {};
    $findOwnerButton.on('click', function (e) {
        e.preventDefault();

        let jsonString = toJSONString(searchOwnerFormId);
        searchQuery["entityType"] = capitalizeFirstLetter(ownerType);
        searchQuery["fieldsAndValues"] = jsonString;
        console.log(JSON.stringify(searchQuery));

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/resources/owner/search",
            accept: "application/json",
            data: JSON.stringify(searchQuery),
            success: function (result) {
                console.log(result);
                $resultDiv.empty();
                showResults(result, $resultDiv);
            },
            error: function (result) {
                const error = JSON.parse(result.responseText);
                console.log();
                $resultDiv.empty();
                showErrorMessage(error, $resultDiv);
            }
        });


    })
}

/**
 * Dynamically builds search owner form.
 *
 * @param $concreteOwnerSearchDiv - inside it element will be placed form
 * @param rows - metadata about form rows
 * @returns {jQuery|HTMLElement} - submit button
 */
function buildOwnerSearchForm($concreteOwnerSearchDiv, rows) {
    let $form = $('<form/>', {
        class: 'form',
        id: searchOwnerFormId
    });
    $concreteOwnerSearchDiv.append($form);

    let $row = $('<div/>', {
        class: 'row'
    });
    $form.append($row);

    for (let i = 0; i < rows.length; i++) {
        let $col = $('<div/>', {
            class: 'col-sm-' + rows[i].size
        });
        $row.append($col);

        let $formGroup = $('<div/>', {
            class: 'form-group',
        });
        $col.append($formGroup);

        let $label = $('<label/>', {
            for: rows[i].userFriendlyName,
            text: rows[i].userFriendlyName
        });
        $formGroup.append($label);

        let $input = $('<input/>', {
            type: 'text',
            class: 'form-control',
            name: rows[i].userFriendlyName.toLowerCase().replace(" ", "_"),
            id: rows[i].userFriendlyName,
            placeholder: rows[i].placeholder
        });
        $formGroup.append($input);
    }

    let $findOwnerBtn = $('<button/>', {
        class: 'btn btn-primary pull-left',
        text: 'Find owner'
    });
    $form.append($findOwnerBtn);

    var $clearfix = $('<div/>', {
        class: 'clearfix'
    });
    $form.append($clearfix);
    return $findOwnerBtn;
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function showErrorMessage(error, $resultDiv) {
    let $h4 = $('<h4/>', {
        class: 'my_error_class',
        text: error.message
    });
    $resultDiv.append($h4);
    $h4.delay(3500).hide(2000);

}

function showSuccessMessage($resultDiv, message) {
    $resultDiv.empty();
    let $h4 = $('<h4/>', {
        class: 'my_success_class',
        text: message
    });
    $resultDiv.append($h4);
    $h4.delay(3500).hide(2000);

}

/**
 * Builds select, with result info
 *
 * @param success - info, received from server
 * @param $resultDiv - placeholder
 */
function showResults(success, $resultDiv) {
    let $formGroup = $('<div/>', {
        class: 'form-group'
    });
    $resultDiv.append($formGroup);

    let $select = $('<select/>', {
        class: 'form-control'
    });
    $formGroup.append($select);

    console.log('parse owners length  ' + success.length);

    buildOptions(success, $select);

    let $chooseOwnerBtn = $('<button/>', {
        id: 'choose_owner',
        class: 'btn btn-success pull-right',
        text: 'Choose owner'
    });
    let $clearfix = $('<div/>', {
        class: 'clearfix'
    });
    $resultDiv.append($chooseOwnerBtn);
    $resultDiv.append($clearfix);

    let choosenOwnerId = 'none';
    let choosenOwnerInfo;

    $select.on('change', function () {
        choosenOwnerId = this.value;
        choosenOwnerInfo = $(this).find('option:selected').text();
        console.log(choosenOwnerInfo);
    });

    $chooseOwnerBtn.on('click', function (e) {
        e.preventDefault();

        if (choosenOwnerId != 'none') {

            if (!checkIfOwnerAlreadyInTable(success)) {
                appendOwnerToTable(success, choosenOwnerId);
                showSuccessMessage($resultDiv, 'Owner was chosen.');
            } else {
                alert("Owner already added in the table!!!")
            }
            // $resourceOwnersMultySelect.append($searchedOwner);
        }
    })
}

function checkIfOwnerAlreadyInTable(owners) {
    // let t = $ownersTbody.find('tr');
    for (let i = 0; i < owners.length; i++) {
        let children = $ownersTbody.children("[id='" + owners[i]['ownerId'] + "']");
        console.log('finded owner with id in the table: ' + children.attr('id'));
        if (children.attr('id') == owners[i]['ownerId']) {
            console.log('there already is such an owner!!! ');
            return true;
        }
    }
    return false;
}

function appendOwnerToTable(result, choosenOwnerId) {
    let concreteOwner;
    for (let i = 0; i < result.length; i++) {
        if (result[i]['ownerId'] == choosenOwnerId) {
            console.log(result[i]);
            concreteOwner = result[i];
            break;
        }
    }
    $resourceOwnerTable.removeClass('display_none');
    let $tr = $('<tr/>', {
        ['owner_type']: 'existing_owner',
        class: 'my_info',
        id: concreteOwner['ownerId']
    });

    $ownersTbody.append($tr);
    for (let attributeValue in concreteOwner) {
        if (concreteOwner.hasOwnProperty(attributeValue) && attributeValue != 'ownerId') {
            console.log(concreteOwner[attributeValue]);
            let $td = $('<td/>', {
                text: concreteOwner[attributeValue]
            });
            $tr.append($td);
        }
    }

    let $deleteOption = appendDeleteOption($tr);

    $deleteOption.on('click', function (e) {
        e.preventDefault();
        if ($(this).parent().attr('owner_type') == 'existing_owner') {
            console.log($(this).parent().attr('id'));
            $tr.remove();
            $deletedOwner.fadeIn(300).delay(3500).fadeOut(300);
            checkIfOneOwner();
        }
    })
}

function buildOptions(success, $select) {
    let $choose = $('<option\>', {
        value: 'none',
        text: 'Please choose owner'
    });
    $select.append($choose);
    for (let i = 0; i < success.length; i++) {
        let $option = $('<option\>', {
            value: success[i].ownerId,
            text: success[i].personalInfo
        });
        $select.append($option);
    }
}

function SearchByOwnerType(value, text) {
    this.value = value;
    this.text = text;
}

const concreteOwnerOption = 'concrete_owner_select';
const searchOwnerFormId = 'owner_search_form';

const $deletedOwner = $('#deleted_owner');
const $ownerSearchSelect = $('#owner_search');
const $resourceOwnersMultySelect = $('#resource_owners');
const $searchOwnerFormPlaceholder = $('#search_owner_form');

const searchByOwnerTypeMetadata = {
    optionsForPersonSearch: [
        new SearchByOwnerType('absent', 'Choose'),
        new SearchByOwnerType('name', 'Person\'s name'),
        new SearchByOwnerType('passport', 'Passport series')
    ],
    optionsForCompanySearch: [
        new SearchByOwnerType('absent', 'Choose'),
        new SearchByOwnerType('name', 'Company\'s full and sort name'),
    ]
};

const searchByPersonCharacteristicsMetadata = {
    personsName: [
        new FieldAndSize('First name', 6, 'Ivan'),
        new FieldAndSize('Last name', 6, 'Ivanov')
    ],
    personsPassport: [
        new FieldAndSize('Passport series', 5, 'KC'),
        new FieldAndSize('Passport number', 7, '147896')
    ]
};

const searchByCompanyCharacteristicsMetadata = {
    companyName: [
        new FieldAndSize('Organization form', 4, 'TzOV'),
        new FieldAndSize('Full name', 4, 'Gromadske Tovarystvo Prosvita'),
        new FieldAndSize('Short name', 4, 'Prosvita')
    ]
};