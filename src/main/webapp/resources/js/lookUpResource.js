$(document).ready(function(){



    $('#div-for-types').hide();
    $('#form-for-properties').hide();
    $('#lookup-result-by-owner-grouped').hide();
    $('#result-search').hide();
    $('#new-search').hide();
    $('#no-inputs-error').hide();
    $('#div-for-owners').hide();
    var table = $('#result-search');

    // populating select with resource types depends on look Up type chosen
    $('#lookup_type').on('change', function(){
        var lookUpType = $('#lookup_type').val();
        if (lookUpType == 'by-type'){

		
            $('#div-for-types').show();
            $('#div-for-owners').hide();

		
        } else if (lookUpType == 'by-owner') {

        	$('#div-for-owners').show();
        	
            $('#div-for-types').hide();
            $('#form-for-properties').hide();
            $('#lookup-result-by-owner-grouped').hide();
            $('#new-search').hide();
            $('#result-search').empty();
            $('#no-inputs-error').empty();
            $('#no-inputs-error').hide();
            


            // here will be implemented next steps for looking up by owner
        } else {
        	$('#div-for-owners').hide();
            $('#div-for-types').hide();
            $('#form-for-properties').hide();
            $('#lookup-result-by-owner-grouped').hide();
            $('#new-search').hide();
            $('#result-search').empty();
            $('#no-inputs-error').empty();
            $('#no-inputs-error').hide();
        }
    });

    var resourceTypeId;
    // populating form with properties to fill in depends on resourceType chosen
    $('#div-for-types').on('change',function(e){
        $('#result-search').empty();
        $('#new-search').hide();
        $('#no-inputs-error').hide();
        resourceTypeId = $(e.target).data('selectedID');
        if (typeof resourceTypeId != 'undefined')
            $.ajax({
                type: 'GET',
                url: projectPathPrefix +'/api/resources/lookup/resourcetypes/'+ resourceTypeId,
                contentType: 'application/json; charset=UTF-8',
                dataType: 'json',
                success: function(result){
                    var form = $('#form-for-properties');
                    form.empty();
                    form.append("<p> Look up can be done only if there is at least one property is filled in</p>")
                    for (var j = 0; j < result.length; j++){
                        console.log(result[j].columnName + "--" + result[j].id);
                        form.append("<div class=\"form-group row\">" +
                            "<label for='"+result[j].title+"' class=\"col-sm-2 control-label\">" + result[j].title+ "</label>" +
                            "<div class=\"col-sm-10\">" +
                            "<input type=\"text\" name= '" +result[j].columnName+ "' oninvalid=\"this.setCustomValidity('"+result[j].hint+"')\" oninput=\"this.setCustomValidity('')\"  pattern= '"+result[j].pattern+"' class=\"form-control\" id='" +result[j].title+ "' placeholder='" + result[j].title +"'>" +
                            "</div>"+
                            "</div>");
                        
//                        $("#"+result[j].title).on("invalid", function(event) {
//                       	 event.target.setCustomValidity(result[j].hint);
//                       });
                    }
                    form.append("<div class=\"form-group row\">" +
                        "<div class=\"col-sm-offset-2 col-sm-10\">" +
                        "<button type=\"submit\" class=\"btn btn-primary\">Look Up</button>" +
                        "</div>" +
                        "</div>");
                    $('#form-for-properties').show();
                },
                error: function (result) {
//	                var responce = JSON.parse(result);
                    console.log(result.responseJSON.message);
                    $('#no-inputs-error').empty();
                    $('#no-inputs-error').show();
                    $('#no-inputs-error').html(result.responseJSON.message).css( "color", "red" );

                }

            });

    });


    $('#form-for-properties').submit(function(e) {
        $('#result-search').empty();
        $('#no-inputs-error').empty();
        $('#no-inputs-error').hide();
        var empty = true;
        $('.form-group input', $(this)).each(function() {
            let value = $(this).val().trim();
            if (value.length > 0){
                empty = false;
            }
        });

        if (empty){
            console.log("no input");
            $('#no-inputs-error').empty();
            $('#no-inputs-error').show();
            $('#no-inputs-error').html("Please, enter data at least in one field to make look up").css( "color", "red" );
            e.preventDefault();
        } else {
            $('#no-inputs-error').hide();
            var objMap = $('#form-for-properties').serializeArray().filter(function(k) {
                return $.trim(k.value) != "";
            });
            $.each(objMap, function(i, field) {
                objMap[i].value = $.trim(field.value);
            });
            var mapToJson = objectifyForm(objMap);
            console.log(mapToJson);
            console.log(JSON.stringify(mapToJson));
            var GenericResourceDTO = {
                id: $('#categories-select').data('selectedID'),
                resourcePropertyValue: mapToJson
            };
            console.log(JSON.stringify(GenericResourceDTO));
            e.preventDefault();
            $.ajax({
                type: 'POST',
                url: projectPathPrefix +'/api/resources/lookup/inputedvalues/foundresources',
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(GenericResourceDTO),
                dataType: 'json',
                success: function(result){
                    console.log(result);
                    // populating table
                    table.empty();
                    var tableTag = $("<table id=\"dt\" class=\"table table-striped table-condensed text-center display\" width=\"100%\" ></table>").appendTo(table);
                    var header = $("<thead></thead>").appendTo(tableTag);
                    var rowHeader = $("<tr></tr>").appendTo(header);
                    for(var j = 0; j < result[0].propertyValues.length; j++) {
                        $("<th class='text-center'>" + result[0].propertyValues[j].type.property.title +"</th>").appendTo(rowHeader);
                    }

                    $("<th class='text-center'>More Info</th>").appendTo(rowHeader);
                    var tableBody = $("<tbody></tbody>").appendTo(tableTag);

                    for(var i = 0; i < result.length; i++) {
                        var bodyRow =  $("<tr></tr>").appendTo(tableBody);
                        for(var j = 0; j < result[i].propertyValues.length; j++) {
                            $(bodyRow).append("<td>"+ result[i].propertyValues[j].value +"</td>");
                        }
                        $(bodyRow).append("<td><a href=\"/resource/type/"+resourceTypeId+"/id/"+result[i].id+"\" target=\"_blank\">Details</a></td>");
                    }
                    $("<br/>").appendTo(tableTag);
                    //DataTables plug-in
                    $('#dt').DataTable({
                    	"dom": '<"up"f>rt<"bottom"lp><"clear">',
                    	"processing": true,
                        stateSave: true
                    });

                    $('#result-search').show();
                    $('#new-search').show();

                },
                error: function (result) {

                    $('#no-inputs-error').empty();
                    $('#no-inputs-error').show();
                    $('#no-inputs-error').html(result.responseJSON.message).css( "color", "red" );

                }

            });


        }

    });

    

});
function objectifyForm(formArray) {//serialize array to json

    var returnArray = {};
    for (var i = 0; i < formArray.length; i++){
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray;
}
