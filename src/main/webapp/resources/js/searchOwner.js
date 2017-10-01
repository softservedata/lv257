$(document).ready(function () {

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
});

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

            appendOwnerToTable(success, choosenOwnerId);

            // $resourceOwnersMultySelect.append($searchedOwner);
            showSuccessMessage($resultDiv);
        }
    })
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
        class: 'existing_owner',
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

    let $deleteOption = appendDeleteOptionVersionTwo($tr);

    $deleteOption.on('click', function (e) {
        e.preventDefault();
        if ( $(this).parent().attr('class') == 'existing_owner'){
            console.log($(this).parent().attr('id'));
            $tr.remove();
            $deletedOwner.fadeIn(300).delay(3500).fadeOut(300);
            checkIfOneOwnerVersionTwo();
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

function appendDeleteOptionVersionTwo($tr){
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


function checkIfOneOwnerVersionTwo(){
    if ($('.owners_tbody tr').length == 0){
        console.log($('.owners_tbody tr').length);
        $resourceOwnerTable.addClass('display_none');
    }
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