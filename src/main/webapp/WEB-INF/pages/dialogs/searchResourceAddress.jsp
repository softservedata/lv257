<div id="search_resource_address_form_placeholder">
    <form class="form" id="search_resource_address_form">
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="search_region">Region</label>
                    <input type="text" class="form-control" name="region" id="search_region" placeholder="Region" required>
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="search_district">District</label>
                    <input type="text" class="form-control" name="district" id="search_district" placeholder="District">
                    <div class="my_error_class"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="search_postal_index">Postal Index</label>
                    <input type="text" class="form-control" name="postal_index" id="search_postal_index" placeholder="79060">
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="search_locality">Locality</label>
                    <input type="text" class="form-control" name="locality" id="search_locality" placeholder="Locality">
                    <div class="my_error_class"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="search_street">Street</label>
                    <input type="text" class="form-control" name="street" id="search_street" placeholder="Shevchenka street">
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="search_building">Building</label>
                    <input type="text" class="form-control" name="building" id="search_building" placeholder="35">
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="search_apartment">Apartment</label>
                    <input type="text" class="form-control" name="apartment" id="search_apartment" placeholder="14">
                    <div class="my_error_class"></div>
                </div>
            </div>
        </div>
        <button class="btn pull-left btn-primary " id="search_resource_address_submit_button">
            Find Address
        </button>
        <div class="clearfix"></div>
    </form>
    <div class="resource_address_search_result">

    </div>
</div>