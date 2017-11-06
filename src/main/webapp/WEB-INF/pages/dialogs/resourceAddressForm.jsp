<div id="resource_address_form_placeholder">

    <div class="address_uniqueness_exception display_none">
        <div class="alert alert-danger alert-dismissible fade in">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            Address's city, street and building and apartment number already exists.
        </div>
    </div>

    <form class="form" id="static_address_form">
        <input type="hidden" id="resource_address_id" name="addressId">
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="country">Country</label>
                    <input type="text" class="form-control" name="country" id="country" placeholder="Ukraine">
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="region">Region</label>
                    <input type="text" class="form-control" name="region" id="region" placeholder="Region">
                    <div class="my_error_class"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group"><label for="district">District</label>
                    <input type="text" class="form-control" name="district" id="district" placeholder="District">
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group"><label for="postal_index">Postal Index</label>
                    <input type="text" class="form-control" name="postal_index" id="postal_index" placeholder="79060">
                    <div class="my_error_class"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group"><label for="locality">Locality</label>
                    <input type="text" class="form-control" name="locality" id="locality" placeholder="Locality">
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group"><label for="street">Street</label>
                    <input type="text" class="form-control" name="street" id="street" placeholder="Shevchenka street">
                    <div class="my_error_class"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4">
                <div class="form-group"><label for="building">Building</label>
                    <input type="text" class="form-control" name="building" id="building" placeholder="35">
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group"><label for="block">Block</label>
                    <input type="text" class="form-control" name="block" id="block" placeholder="A">
                    <div class="my_error_class"></div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group"><label for="apartment">Apartment</label>
                    <input type="text" class="form-control" name="apartment" id="apartment" placeholder="14">
                    <div class="my_error_class"></div>
                </div>
            </div>
        </div>
        <button class="btn pull-right btn-success " id="resource_address_submit_button">
            Add new Address
        </button>
        <div class="clearfix"></div>
    </form>
</div>