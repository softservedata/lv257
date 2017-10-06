<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Register Resource</title>

    <jsp:include page="metadata.jsp"/>
</head>

<body>

<jsp:include page="_menu2.jsp"/>
<br/>

<div id="bootstrap-overrides" class="wrapper">
    <div class="container-fluid">
        <div class="col-sm-12 col-md-12">

            <div class="container">

                <ul class="nav nav-tabs">
                    <li class="active"><a href="${pageContext.request.contextPath}/resources/registration">Register
                        resource</a></li>
                    <li><a href="${pageContext.request.contextPath}/resources/request">Send request</a></li>
                    <li><a href="${pageContext.request.contextPath}/resources/story">History</a></li>
                </ul>
                <br>
                <br>

                <!-- MAIN FORM FOR REGISTRATING RESOURCE -->

                <%--<form role="form-horizontal" enctype="multipart/form-data">--%>

                <c:set var="typeSelectLabel" value="Resource Category" scope="request"/>
                <div class="row">
                    <div id="categories" class="col-sm-12 col-xs-8 form-group">
                        <jsp:include page="components/resourceTypeSelect.jsp"/>
                    </div>
                </div>

                <div id="deleted_owner" class="my_error_class">
                    <h4> Owner was deleted. </h4>
                </div>

                <div class="resource_owner_table display_none">
                    <hr class="my_hr">
                    <label for="owner_table">Resource Owners</label>
                    <div class="table_message">
                        Created owner are marked <span>green</span>, already existing - <span>blue</span>.
                    </div>
                    <table id="owner_table" class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Owner Type</th>
                            <th>Owner Info</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody class="owners_tbody">
                        </tbody>
                    </table>
                </div>

                <div class="form-group">
                    <button class="btn btn-primary width_13em"
                            type="button" data-toggle="modal"
                            data-target="#createNewOwnerPopUp">Add new owner
                    </button>
                    <button class="btn btn-primary width_13em"
                            type="button" data-toggle="modal"
                            data-target="#search_existing_owner">Add existing owner
                    </button>
                </div>

                <div id="search_existing_owner" class="modal fade">
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
                                    <option value="absent">Choose type here</option>
                                    <option value="company">Company</option>
                                    <option value="person">Person</option>
                                </select>


                                <div id="search_owner_form">


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
                                            <option value="absent">Choose type here</option>
                                            <option value="company">Company</option>
                                            <option value="person">Person</option>
                                        </select>
                                    </div>

                                    <div id="owner_form">


                                        <div id="resource_new_owner_form">

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


                <div id="deleted_address" class="my_error_class">
                    <h4> Address was deleted. </h4>
                </div>

                <div id="updated_address" class="my_success_class">
                    <h4> Address was updated. </h4>
                </div>

                <div class="resource_address_table display_none">
                    <hr class="my_hr">
                    <label for="address_table">Resource Address</label>
                    <div class="table_message">
                        Created address are marked <span>green</span>, already existing - <span>blue</span>.
                    </div>
                    <table id="address_table" class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Country</th>
                            <th>Region</th>
                            <th>District</th>
                            <th>Postal Index</th>
                            <th>Locality</th>
                            <th>Street</th>
                            <th>Building</th>
                            <th>Block</th>
                            <th>Apartment</th>
                            <%--<th>Edit</th>--%>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr id="resource_address_row"
                            class="my_success">
                        </tr>
                        </tbody>
                    </table>
                </div>


                <div id="address_buttons" class="padding_bottom_15">
                    <button id="add_resource_address_btn"
                            class="btn btn-primary width_13em"
                            type="button" data-toggle="modal"
                            data-target="#resourseAdressPopUp">
                        Add resource address
                    </button>
                    <button id="search_resource_address_btn"
                            class="btn btn-primary width_13em"
                            type="button" data-toggle="modal"
                            data-target="#searchResourseAdressPopUp">
                        Add existing address
                    </button>
                    <button id="add_resource_address_from_owner_btn"
                            class="btn btn-primary width_13em"
                            type="button" data-toggle="modal"
                            data-target="#chooseResourceAddressFromOwnerPopUp">
                        Choose owner's address
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

                                <jsp:include page="components/resourceAddressForm.jsp"/>

                            </div>

                        </div>
                    </div>
                </div>

                <div id="searchResourseAdressPopUp" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4>Find Address</h4>
                            </div>
                            <div class="modal-body">

                                <jsp:include page="components/searchResourceAddress.jsp"/>

                            </div>

                        </div>
                    </div>
                </div>

                <div id="chooseResourceAddressFromOwnerPopUp" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4>Choose Address</h4>
                            </div>
                            <div class="modal-body">

                                TODO
                                <%--<jsp:include page="components/searchResourceAddress.jsp"/>--%>

                            </div>

                        </div>
                    </div>
                </div>

                <div class="rP_chars">

                    <%--CONCRETE RESOURCE TYPE CHARACTERISTICS--%>

                </div>

                <div class="form-group">
                    <label for="files">Registration basis</label>
                    <input type="file" id="files" multiple="true">
                </div>
                <div class="padding_bottom_15">
                    <button type="submit" class="btn btn-success width_13em">Register resource</button>
                </div>

                <%--</form>--%>

            </div>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy; Lv257_Java</p>
    </div>
</footer>

<script src="../../resources/js/ownerAndAddressManagment.js"></script>
<script src="../../resources/js/categories.js"></script>
<script src="../../resources/js/hierarchy-select.js"></script>
<script src="../../resources/js/jquery.validate.js"></script>
<script>

</script>

</body>
</html>