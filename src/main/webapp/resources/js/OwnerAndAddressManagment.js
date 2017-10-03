$(document).ready(function(){

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
        if (ownerType == 'absent'){
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
     * Renders resource address form in the popUp window
     */
    $addResourceAddressBtn.on('click', function () {
        $resourceNewOwnerForm.empty();
        $ownerAddressForm.empty();
        addAddressForm($resourceAddressFormPlaceholder, fieldsMetadata.rowsForAddress);
        validateFormAndSaveResult($resourceAddressFormPlaceholder, addressFormId, customButtonId);
    });

});

var updateAddress = false;
var $idAddressInput;
const $deletedAddress = $('#deleted_address');
const $updatedAddress = $('#updated_address');
const $addResourceAddressBtn = $('#add_resource_address_btn');
const $resourceAddressRow = $('#resource_address_row');
const $resourceAddressTbl = $('.resource_address_table');
const $resourceAddressFormPlaceholder = $('#resource_address_form_placeholder');
const $resourceAddressPopUp = $('#resourseAdressPopUp');
const $resourceAddressSelect = $('#resource_address');
const addressFormId = 'address_form';
const ownerAddressFormId = 'owner_address_form';
const customButtonId = 'custom_button';

const $deletedOwnerVersionTwo = $('#deleted_owner');
const $resourceOwnerTable = $('.resource_owner_table');
const $ownersTbody = $('.owners_tbody');
const $resourceNewOwnerForm =  $('#resource_new_owner_form');
const $ownerAddressForm =  $('#owner_address_form');
const $resourceOwnersSelect = $('#resource_owners');
const $resourceNewOwnerPopUp = $('#createNewOwnerPopUp');
const ownerFormId = 'owner_form';

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

function showOwnersTable(result){
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
        if ( $(this).parent().attr('owner_type') == 'new_owner'){
            console.log($(this).parent().attr('id'));
            deleteOwner($(this).parent().attr('id'));
            $tr.remove();
            checkIfOneOwner();
            $deletedOwnerVersionTwo.fadeIn(300).delay(3500).fadeOut(300);
        }
    })

}

function checkIfOneOwner(){
    if ($('.owners_tbody tr').length == 0){
        console.log($('.owners_tbody tr').length);
        $resourceOwnerTable.addClass('display_none');
    }
}

function deleteOwner(ownerId){
    $.ajax({
        type: "DELETE",
        url: "owner/" + ownerId + "/delete",
        success: function (result) {
            console.log("owner was deleted from db");
            console.log(result);
        }
    })
}

function appendDeleteOption($tr){
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
 * If client-side validation succeed, than send request to the server.
 * Than server-side validation is performed.
 * If server-side validation fall, message with error is shown
 *
 * @param $placeholder
 * @param formId
 * @param customButtonId
 */
function validateFormAndSaveResult($placeholder, formId, customButtonId){
    const $form = $('#' + formId);
    const $submitButton = $('#' + customButtonId);

    validateAddress($form);

    $submitButton.on('click', function (e) {
        e.preventDefault();
        if($form.valid()){
            console.log(formId + ' is valid');
            let json = JSON.stringify(toJSONString(formId));
            console.log('json from form ' + formId + ' : ' + json);

            let url = '/resources/address';
            if (updateAddress){
                url = '/resources/address/update';
            }
            saveAddressAjax(json, url);
        } else {
            alert("form invalid");
        }
    })
}

/**
 * Saves new address or updates existing.
 * @param json - address json
 * @param url - save new address or update existing
 */
function saveAddressAjax(json, url){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        accept: "application/json",
        data: json,
        // if success, show select for address and append option with saved address
        success: function (result) {
            console.log('result from the server: ' + result.addressId + " " + result.country);
            $idAddressInput.val(result.addressId);
            console.log('result length: ' + result.length);

            if (updateAddress){
                console.log('showing message');
                $updatedAddress.fadeIn(300).delay(3500).hide(300);
            }
            showAddressTable(result);

            // showOptionsForSelect($resourceAddressSelect, result);
            // closePopUp($placeholder, 'Resource Address was saved', $resourceAddressPopUp);
        },
        // on errors, show messages with explanations
        error: function (result) {
            var parse = JSON.parse(result.responseText);
            console.log('errors in fields: ' + parse);
            $('.my_error_class').empty();

            appendHibernateErrors(parse);
        }
    });
}

/**
 * Builds one row in the address table
 * @param result - address receiver from the server
 */
function showAddressTable(result){
    updateAddress = false;
    $addResourceAddressBtn.hide(1500);
    $resourceAddressTbl.removeClass('display_none');
    $resourceAddressRow.empty();
    let $td;
    for (let attributeValue in result) {
        if (result.hasOwnProperty(attributeValue) && attributeValue != 'addressId') {
            $td = $('<td/>', {
                text: result[attributeValue]
            });
            $resourceAddressRow.append($td);
        }
    }

    $resourceAddressPopUp.modal('hide');
    let editDelete = appendEditDeleteOptions();

    editDelete[0].on('click', function(e) {
        e.preventDefault();
        updateAddress = true;
        $resourceAddressPopUp.modal('show');
    });

    editDelete[1].on('click', function (e) {
        e.preventDefault();
        $resourceAddressTbl.addClass('display_none');
        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: "/resources/address/delete",
            accept: "application/json",
            data: JSON.stringify(toJSONString(addressFormId)),
            success: function (result) {
                console.log('resource address was deleted');

                $deletedAddress.fadeIn(300).delay(3500).fadeOut(300);

            },
            // on errors, show messages with explanations
            error: function (result) {
                console.log('some error during deleting resource address');
            }
        });
        $addResourceAddressBtn.show(1500);
        $resourceAddressRow.empty();
    })
}

/**
 * Appends edit and delete Options to the table and returns that elements.
 */
function appendEditDeleteOptions(){
    $tdEdit = $('<td/>');
    $aEdit = $('<a/>');
    $iEdit = $('<i/>', {
        class: 'glyphicon glyphicon-edit',
        'aria-hidden': "true"
    });
    $tdEdit.append($aEdit);
    $aEdit.append($iEdit);

    $tdDelete = $('<td/>');
    $aDelete = $('<a/>');
    $iDelete = $('<i/>', {
        class: 'glyphicon glyphicon-remove',
        'aria-hidden': "true"
    });
    $tdDelete.append($aDelete);
    $aDelete.append($iDelete);

    $resourceAddressRow.append($tdEdit);
    $resourceAddressRow.append($tdDelete);
    return [$tdEdit, $tdDelete];
}

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
    updateAddress = false;
    // clears div element
    $formPlaceholder.empty();

    const $form = $('<form/>', {
        class: 'form',
        id: addressFormId
    });

    $idAddressInput = $('<input/>',{
        type: 'hidden',
        id: 'id',
        name: 'addressId'
    });
    $form.append($idAddressInput);

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
        if(str.split(/(?=[A-Z])/).join('_').toLowerCase() == 'address'){
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
function showConcreteOwnerOptions(ownerType){
    let $concreteOwnerSearchDiv = $('<div/>');
    $searchOwnerFormPlaceholder.append($concreteOwnerSearchDiv);
    let $resultDiv = $('<div/>', {
        class: 'result_div'
    });

    if (ownerType == 'company'){
        handleCompanyOptions($concreteOwnerSearchDiv, $resultDiv, ownerType);
    }
    if (ownerType == 'person'){
        handlePersonOptions($concreteOwnerSearchDiv, $resultDiv, ownerType);
    }
}

/**
 * Depending on particular owner select, renders form and makes ajax call
 * @param $concreteOwnerSearchDiv - placeholder for owner search form
 * @param $resultDiv - placeholder, where results will be put
 * @param ownerType - concrete owner type
 */
function handleCompanyOptions($concreteOwnerSearchDiv, $resultDiv, ownerType){
    $('#' + concreteOwnerOption).on('change', function () {
        const companySearchOption = this.value;

        if(companySearchOption == 'name'){
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();

            let $findOwnerButton = buildOwnerSearchForm($concreteOwnerSearchDiv, searchByCompanyCharacteristicsMetadata.companyName);
            $concreteOwnerSearchDiv.append($resultDiv);
            makeAjaxCall($findOwnerButton, ownerType, $resultDiv);
        }
        if(companySearchOption == 'absent'){
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();
        }
    })
}

function handlePersonOptions($concreteOwnerSearchDiv, $resultDiv, ownerType){
    $('#' + concreteOwnerOption).on('change', function () {
        const personSearchOption = this.value;
        let $findOwnerButton;

        if(personSearchOption == 'name'){
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();

            $findOwnerButton = buildOwnerSearchForm($concreteOwnerSearchDiv, searchByPersonCharacteristicsMetadata.personsName)
            $concreteOwnerSearchDiv.append($resultDiv);
            makeAjaxCall($findOwnerButton, ownerType, $resultDiv);
        }
        if(personSearchOption == 'passport'){
            $concreteOwnerSearchDiv.empty();
            $resultDiv.empty();

            $findOwnerButton = buildOwnerSearchForm($concreteOwnerSearchDiv, searchByPersonCharacteristicsMetadata.personsPassport)
            $concreteOwnerSearchDiv.append($resultDiv);
            makeAjaxCall($findOwnerButton, ownerType, $resultDiv);
        }
        if(personSearchOption == 'absent'){
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
function makeAjaxCall($findOwnerButton, ownerType, $resultDiv){
    let searchQuery = {};
    $findOwnerButton.on('click', function (e) {
        e.preventDefault();

        let jsonString = toJSONString(searchOwnerFormId);
        searchQuery["ownerType"] = capitalizeFirstLetter(ownerType);
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

function showErrorMessage(error, $resultDiv){
    let $h4 = $('<h4/>', {
        class: 'my_error_class',
        text: error.message + " Sorry :("
    });
    $resultDiv.append($h4);
    $h4.delay(3500).hide(2000);

}

function showSuccessMessage($resultDiv){
    $resultDiv.empty();
    let $h4 = $('<h4/>', {
        class: 'my_success_class',
        text: 'Owner was chosen.'
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
function showResults(success, $resultDiv){
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

    let choosenOwnerId;
    let choosenOwnerInfo;

    $select.on('change', function () {
        choosenOwnerId = this.value;
        choosenOwnerInfo = $(this).find('option:selected').text();
        console.log(choosenOwnerInfo);
    });

    $chooseOwnerBtn.on('click', function (e) {
        e.preventDefault();

        if (choosenOwnerId != 'none'){

            if(!checkIfOwnerAlreadyInTable(success)){
                appendOwnerToTable(success, choosenOwnerId);
                showSuccessMessage($resultDiv);
            } else {
             alert("Owner already added in the table!!!")
            }
            // $resourceOwnersMultySelect.append($searchedOwner);
        }
    })
}

function checkIfOwnerAlreadyInTable(owners){
    // let t = $ownersTbody.find('tr');
    for(let i = 0; i < owners.length; i++) {
        let children = $ownersTbody.children("[id='" + owners[i]['ownerId'] + "']");
        console.log('finded owner with id in the table: ' + children.attr('id'));
        if (children.attr('id') == owners[i]['ownerId']){
            console.log('there already is such an owner!!! ');
            return true;
        }
    }
    return false;
}

function appendOwnerToTable(result, choosenOwnerId){
    let concreteOwner;
    for(let i = 0; i < result.length; i++){
        if (result[i]['ownerId'] == choosenOwnerId){
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
        if ( $(this).parent().attr('owner_type') == 'existing_owner'){
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