/* Generates resource addres form in the pop-up */
$('#add_resource_address_btn').on('click', function () {
    var $placeholder = $('#resource_address_form_placeholder');
    var addressFormAndBtn = addAddressForm("resource", $placeholder, fieldsMetadata.rowsForAddress);

    parseFormAndSaveResource($placeholder, addressFormAndBtn);

});

function parseFormAndSaveResource(placeholder, addressFormAndBtn) {
    var addressFormId = addressFormAndBtn[0];
    var submitButtonId = addressFormAndBtn[1];

    var button = $('#' + submitButtonId);
    var returnStatus;
    button.on('click', function (e) {
        e.preventDefault();
        var json = toJSONString(addressFormId);
        alert(json);


        $.ajax({
            type: "POST",
            contentType: "text/plain",
            url: "/resources/address",
            accept: "text/plain",
            data: json,
            success: function (result) {
                closePopUp(placeholder);
            }
        })
    });
};

function closePopUp(placeholder) {

    placeholder.empty();

    var result = $('<div/>', {
        text: 'Resource Address was saved'
    });
    var closePopUp = $('<button/>', {
        text: 'Close',
        class: 'btn btn-default pull-right clearfix'
    });
    var clear = $('<div/>', {
        class: 'clearfix'
    });

    placeholder.append(result);
    placeholder.append(closePopUp);
    placeholder.append(clear);

    closePopUp.on('click', function () {
        $('#resourseAdressPopUp').modal('hide');
    });
}

/**
 * Dynamic adding address form
 * Takes:
 * string   - forWhat - purpose of this form, needed to generate button id
 * element  - formPlaceholder - div element, in which form will be placed
 *
 * Returns:
 * array, where first element - id of generated form
 *              second element - id of submit button
 */
function addAddressForm(addressFor, $formPlaceholder, rows) {
    // clears div element
    $formPlaceholder.empty();

    // id for the form
    var formId = addressFor + '_address_form';
    var form = $('<form/>', {
        class: 'form',
        id: formId
    });

    $formPlaceholder.append(form);
    appendRows(form, addressFor, rows);
    var addressSubmitButtonId = appendButton(form, addressFor, "Register new Address", true, true);
    return [formId, addressSubmitButtonId];
}

/** Appending rows.
 * Each row in the array is the <div class="row"> and may have one
 * or more input tages.
 * This method genertes all this element and append them to each other
 * and to the giveb form.
 */
function appendRows(form, forWhat, rows) {
    for (var i = 0; i < rows.length; i++) {
        var row = $('<div/>', {
            class: 'row'
        });
        form.append(row);

        for (var j = 0; j < rows[i].length; j++) {
            var col = $('<div/>', {
                class: 'col-sm-' + rows[i][j].size
            });
            row.append(col);

            var formGroup = $('<div/>', {
                class: 'form-group',
            });
            col.append(formGroup);
            var label = $('<label/>', {
                for: rows[i][j].userFriendlyName + "_" + forWhat,
                text: rows[i][j].userFriendlyName
            });
            formGroup.append(label);
            var input = $('<input/>', {
                type: 'text',
                class: 'form-control',
                name : rows[i][j].userFriendlyName.toLowerCase(),
                id: rows[i][j].userFriendlyName + "_" + forWhat,
                placeholder: rows[i][j].placeholder
            });
            formGroup.append(input);
        }
    }
}

/** Appending submit button to the form.
 * Takes:
 * element  - form itself,
 * string   - forWhat - purpose of this button, needed to generate button id
 * string   - text - text that will be placed on button
 * boolean  - right - if true, btn will be placed in the right corner, if not - left
 * boolean  - success - if true, btn will green , if not - blue
 *
 */
function appendButton(form, forWhat, text, right, success) {
    var successButton = $('<button/>', {
        class: 'btn' +
        (right ? ' pull-right ' : ' pull-left ') +
        (success ? ' btn-success ' : ' btn-primary '),
        type: 'submit',
        text: text,
        id: forWhat + '_custom_btn'
    });
    var clearfix = $('<div/>', {
        class: 'clearfix'
    });
    form.append(successButton);
    form.append(clearfix);
    var tempId = forWhat + '_custom_btn';
    return tempId;
}

/**
 * This function gets all inputs of a particular form.
 * Takes as a parameter form id.
 * Returns string containing json representation ('field': 'value' ...).
 *
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
    return JSON.stringify(obj);
}

/**
 * Custom object for dynamic form building.
 * Holds uer friendly name, size of column, placeholder for the input and exactrly field name
 *
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
        [new FieldAndSize('Short Name', 6, 'Short Name'), new FieldAndSize('Orgsnization Form', 6, 'TzOV')],
        [new FieldAndSize('CEO', 12, 'CEO')],
        [new FieldAndSize('Phone', 12, '+380679365998')]
    ],

    rowsForPerson: [
        [new FieldAndSize('First Name', 6, 'First Name'), new FieldAndSize('Last Name', 6, 'Last Name')],
        [new FieldAndSize('Middle Name', 12, 'Middle Name')],
        [new FieldAndSize('Passport Series', 3, 'KC'), new FieldAndSize('Passport Number', 4, '149875'), new FieldAndSize('Personal Phone', 5, '+30679365998')]
    ]
}