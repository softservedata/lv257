<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>${title}</title>

    <jsp:include page="metadata.jsp"/>
</head>

<body>

<jsp:include page="_menu2.jsp"/>
<br/>

<div class="wrapper">
    <div class="container-fluid">
        <div class="col-sm-12 col-md-12">

            <div class="container">

                <ul class="nav nav-tabs">
                    <li class="active"><a href="#">Register resource</a></li>
                    <li><a href="/resources/request">Send request</a></li>
                    <li><a href="/resources/history">History</a></li>
                </ul>
                <br>
                <br>

                <!-- MAIN FORM FOR REGISTRATING RESOURCE -->

                <form role="form-horizontal" enctype="multipart/form-data">

                    <div class="form-group">
                        <label for="resource_type">Resource type</label>
                        <%--<select name="resource type" id="resource type">--%>
                        <select type="resource_type" id="resource_type" class="form-control selectpicker"
                                data-live-search="true"
                                title="Choose one of the following">
                            <%--<c:forEach items="${resourceTypes}" var="resource">--%>
                            <%--<option>${resource.typeName}</option>--%>
                            <%--</c:forEach>--%>

                            <option disabled style="background-color: lightgray; text-indent: 0px;">Capital</option>
                            <option disabled style="background-color: lightgray; text-indent: 10px;">Real Estate
                            </option>
                            <option style="text-indent: 20px;">Cottages</option>
                            <option style="text-indent: 20px;">Apartment Buildings</option>
                            <option disabled style="background-color: lightgray; text-indent: 10px;">Transport</option>
                            <option style="text-indent: 20px;">Vehicles</option>
                            <option style="text-indent: 20px;">Trucks</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="owners">Resource Owners</label>
                        <select type="owners" id="owners" class="form-control" multiple>
                            <option>Owner 1</option>
                            <option>Owner 2</option>
                            <option>Owner 3</option>
                            <option>Owner 4</option>
                        </select>
                    </div>
                    <div class="padding_bottom_15">
                        <button class="btn btn-primary"
                                type="button" data-toggle="modal"
                                data-target="#myModal">Add from existing owners
                        </button>
                        <button class="btn btn-primary"
                                type="button" data-toggle="modal"
                                data-target="#createNewOwnerPopUp">Add new Owner
                        </button>
                    </div>

                    <div id="myModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4>Find owner</h4>
                                </div>

                                <div class="modal-body">

                                    <label for="owner_search">Select type of Owner:</label>
                                    <select id="owner_search" class="form-control">
                                        <option value="1">Choose type here</option>
                                        <option value="2">Company</option>
                                        <option value="3">Person</option>
                                    </select>

                                    <div class="find_company display_none">

                                        <form class="form">
                                            <div class="form-group">
                                                <label for="search_company_by">Search by:</label>
                                                <select id="search_company_by" class="form-control">
                                                    <option value="1">Choose</option>
                                                    <option value="2">Name of the company</option>
                                                    <option value="3">IPN number</option>
                                                </select>
                                            </div>

                                            <div class="search_company_name display_none">
                                                <div class="form-group">
                                                    <label for="searchcompanyname">Name of the company</label>
                                                    <input type="text" class="form-control" id="searchcompanyname"
                                                           placeholder="Put name of the company: e.g. 'Sunshine'">
                                                </div>
                                                <div class="padding_bottom_15">
                                                    <button type="button" class="btn btn-primary">Find Owner<a
                                                            href="LookUpResources4.html">/</a></button>
                                                </div>
                                            </div>
                                            <div class="search_ipn_number display_none">
                                                <div class="form-group">
                                                    <label for="ipn_number">IPN number</label>
                                                    <input type="text" class="form-control" id="ipn_number"
                                                           placeholder="IPn number">
                                                </div>
                                                <div class="padding_bottom_15">
                                                    <button type="button" class="btn btn-primary">Find Owner<a
                                                            href="LookUpResources4.html">/</a></button>
                                                </div>
                                            </div>

                                            <div id="owners_search_result" class="form-group">
                                                <label for="exampleFormControlSelect1">Result of Owner research. Please,
                                                    choose special ownery to make Search Resource
                                                </label>
                                                <select multiple class="form-control" id="exampleFormControlSelect1">
                                                    <option>Company 1</option>
                                                    <option>Company 2</option>
                                                    <option>Company 3</option>
                                                    <option>Company 4</option>
                                                    <option>Company 5</option>
                                                </select>
                                            </div>
                                            <div>
                                                <button type="button" class="btn btn-success pull-right">Choose</button>
                                            </div>
                                            <div class="clearfix"></div>
                                        </form>

                                    </div>

                                    <div class="find_person display_none">

                                        <form class="form">
                                            <div class="form-group">
                                                <label for="search_person_by">Search by:</label>
                                                <select id="search_person_by" class="form-control">
                                                    <option value="1">Choose</option>
                                                    <option value="2">Person's name</option>
                                                    <option value="3">Pasport series</option>
                                                </select>
                                            </div>
                                            <div class="persons_name_search display_none">
                                                <div class="form-group">
                                                    <label for="owner_first_name">Person's first name</label>
                                                    <input type="text" class="form-control" id="owner_first_name"
                                                           placeholder="Put name of the person here: e.g. 'Ivan'">
                                                </div>
                                                <div class="form-group">
                                                    <label for="owner_last_name">Person's last name</label>
                                                    <input type="text" class="form-control" id="owner_last_name"
                                                           placeholder="Put name of the person here: e.g. 'Ivanov'">
                                                </div>
                                                <div class="padding_bottom_15">
                                                    <button type="button" class="btn btn-primary">Find Owner<a
                                                            href="LookUpResources4.html">/</a></button>
                                                </div>
                                            </div>
                                            <div class="persons_passport_search display_none">
                                                <div class="row">
                                                    <div class="col-sm-4">
                                                        <div class="form-group">
                                                            <label for="passport_series">Person's passport
                                                                series</label>
                                                            <input type="text" class="form-control" id="passport_series"
                                                                   placeholder="KC">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-8">
                                                        <div class="form-group">
                                                            <label for="passport_number">Person's passport
                                                                number</label>
                                                            <input type="text" class="form-control" id="passport_number"
                                                                   placeholder="789315">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="padding_bottom_15">
                                                    <button type="button" class="btn btn-primary">Find Owner<a
                                                            href="LookUpResources4.html">/</a></button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="exampleFormControlSelect1">Result of Owner research. Please,
                                                    choose special ownery to make Search Resource</label>
                                                <select multiple class="form-control" id="exampleFormControlSelect1">
                                                    <option>Person 1</option>
                                                    <option>Person 2</option>
                                                    <option>Person 3</option>
                                                    <option>Person 4</option>
                                                    <option>Person 5</option>
                                                </select>
                                            </div>
                                            <div class="padding_bottom_15">
                                                <button type="button" class="btn btn-success pull-right">Choose</button>
                                            </div>
                                            <div class="clearfix"></div>
                                        </form>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="createNewOwnerPopUp" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4>Add new Owner</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="changable_form">

                                        <div class="select_ownertype padding_bottom_15">
                                            <label for="owner_type">Please specify what type of owner you want to
                                                add:</label>
                                            <select id="owner_type" class="form-control">
                                                <option value="1" selected>Choose type here</option>
                                                <option value="2">Company</option>
                                                <option value="3">Person</option>
                                            </select>
                                        </div>

                                        <div id="owner_form">


                                            <div id="resource_owner_form">

                                                <%--Here owner form will be rendered--%>

                                            </div>

                                            <div id="owner_address_form">

                                                <%--Here owner address form will be rendered--%>

                                            </div>

                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address">Resource Address</label>
                        <select type="address" id="address" class="form-control">
                            <option>Address 1</option>
                            <option>Address 2</option>
                            <option>Address 3</option>
                            <option>Address 4</option>
                        </select>
                    </div>
                    <div class="padding_bottom_15">
                        <button id="add_resource_address_btn"
                                class="btn btn-primary"
                                type="button" data-toggle="modal"
                                data-target="#resourseAdressPopUp">
                            Add Resource Address
                        </button>
                    </div>


                    <div id="resourseAdressPopUp" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4>Resource Address</h4>
                                </div>
                                <div class="modal-body">

                                    <div id="resource_address_form_placeholder">

                                        <%--Here will be generated resource adress form--%>


                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>

                    <div id="resource_address_id_input">

                   <%--May be I will rendder hidder input here with resource address id.--%>

                    </div>

                    <%--CONCRETE RESOURCE TYPE CHARACTERISTICS--%>

                    <div class="rP_chars">


                    </div>

                    <div class="form-group">
                        <label for="files">Registration basis</label>
                        <input type="file" id="files" multiple="true">
                    </div>
                    <div class="padding_bottom_15">
                        <button type="submit" class="btn btn-success">Register new resource</button>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy; Lv257_Java</p>
    </div>
</footer>

<script src="../../resources/js/registerResource.js"></script>
<script>

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
    function addAddressFormWithoutBtn(addressFor, formPlaceholder, rows) {
        // clears div element
        formPlaceholder.empty();

        // id for the form
        var formId = addressFor + '_address_form';
        var form = $('<form/>', {
            class: 'form',
            id: formId
        });

        formPlaceholder.append(form);
        appendRows(form, addressFor, rows);
        return formId;
    }

</script>

</body>
</html>