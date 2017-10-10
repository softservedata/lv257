$(document).ready(function(){
	
//	var dataObject = [{"name":"yura", "model":"mazda", "year":"2005"}, {"name":"george", "model":"toyota", "year":"2009"}];
//	$('#result-search').append('<thead> <tr></tr> </thead>');
//	$('#result-search').append('<tfoot> <tr></tr> </tfoot>');
//	
//	for(var key in dataObject[0]) { 
//		 $('#result-search thead tr').append('<th>' + key + '</th>');
//	}
//	 
//	var dtColumns = [];
//	var c = 0;
//	for(var key in dataObject[0]) { 
//		dtColumns.push({data:key}); 
//	} 
//	
//	$('#result-search').DataTable({
//		"data": dataObject,
//		"columns": dtColumns 
//	});
	
	$('#div-for-types').hide();
	$('#form-for-properties').hide();
	$('#lookup-result').hide();
	$('#result-search').hide();
	$('#new-search').hide();
    $('#no-inputs-error').hide();
	var table = $('#result-search');
	
	// populating select with resource types depends on look Up type chosen
	$('#lookup_type').on('change', function(){    
	    var lookUpType = $('#lookup_type').val();    
	    if (lookUpType == 'by-type'){
	    	
		  /*  $.ajax({    
		        type: 'GET',
		        url: '/lookup/resourceTypes',
		        contentType: 'application/json; charset=UTF-8',
		        dataType: 'json',
		        success: function(result){    
		        	var select = $('#sel1-resource-types'); 
		        	select.empty().append('<option>select type</option>');
		            for (var j = 0; j < result.length; j++){                 
		                console.log(result[j].tableName + "--" + result[j].id);
		                select.append("<option value='" + result[j].id + "'>" +result[j].typeName+ "</option>");    
		            }*/
		            $('#div-for-types').show();
		            
		      /*  },
		        error: function (result) {
	                
	                console.log('error');
	              
	            }
		    });   */ 
	    } else if (lookUpType == 'by-owner') {
	    	
	    	$('#div-for-types').hide();
	    	$('#form-for-properties').hide();
	    	$('#lookup-result').hide();
	    	$('#result-search').empty();
	    	$('#no-inputs-error').empty();
            $('#no-inputs-error').show();
	
	    	
	    	// here will be implemented next steps for looking up by owner
	    } else {
	    	$('#div-for-types').hide();
	    	$('#form-for-properties').hide();
	    	$('#lookup-result').hide();
	    	$('#result-search').empty();
	    	$('#no-inputs-error').empty();
            $('#no-inputs-error').show();
	    }
	});
	
	var resourceTypeId;
	// populating form with properties to fill in depends on resourceType chosen
	$('#div-for-types').on('change',function(e){    
	    resourceTypeId = $(e.target).data('selectedID');
	    if (typeof resourceTypeId != 'undefined')
		    $.ajax({    
		        type: 'GET',
		        url: '/lookUp/resourceProperties/'+ resourceTypeId,
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
				                            "<input type=\"text\" name= '" +result[j].columnName+ "' pattern= '"+result[j].pattern+"' class=\"form-control\" id='" +result[j].title+ "' placeholder='" + result[j].title +"'>" +
				                        "</div>"+
				                     "</div>");
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
			        url: '/lookUp/inputValues',
			        contentType: 'application/json; charset=UTF-8',
			        data: JSON.stringify(GenericResourceDTO),
			        dataType: 'json',
			        success: function(result){    
			        	console.log(result);
//			        	$('#lookup-result').html(JSON.stringify(result));
//			        	var divResult = $('#lookup-result'); 
//			        	divResult.append("<p> id= "+  result[0].id + "; columnName " + result[0].propertyValues[0].type.columnName + 
//			        		"; value " +result[0].propertyValues[0].value+	"</p>");
			        	
			        	// populating table 
			        	table.empty();
			        	var tableTag = $("<table id=\"dt\"></table>").appendTo(table);
			        	var header = $("<thead></thead>").appendTo(tableTag);
			        	var rowHeader = $("<tr></tr>").appendTo(header);
			        	for(var j = 0; j < result[0].propertyValues.length; j++) { 
			       		 	$("<th>'" + result[0].propertyValues[j].type.title +"'</th>").appendTo(rowHeader);
			        	}
			        	
			        	$("<th>More Info</th>").appendTo(rowHeader);
			        	var tableBody = $("<tbody></tbody>").appendTo(tableTag);
			        	
			        	for(var i = 0; i < result.length; i++) { 
				       		var bodyRow =  $("<tr></tr>").appendTo(tableBody);
				       		 	for(var j = 0; j < result[i].propertyValues.length; j++) {
				       		 		$(bodyRow).append("<td>"+ result[i].propertyValues[j].value +"</td>");
				       		 	}
				       		 $(bodyRow).append("<td><a href=\"/resource/type/"+resourceTypeId+"/id/"+result[i].id+"\" target=\"_blank\">Details</a></td>");
				        }
			        	//DataTables plug-in
			        	$('#dt').DataTable({
			        		"processing": true,
			        		stateSave: true
			        	});
			        	
			        	$('#result-search').show();
			        	$('#new-search').show();

			        	},
			        error: function (result) {
		                
		                console.log('error');
		              
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