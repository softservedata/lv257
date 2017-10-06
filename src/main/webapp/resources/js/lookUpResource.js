$(document).ready(function(){
	
	$('#div-for-types').hide();
	$('#form-for-properties').hide();
	
	// populating select with resource types depends on look Up type chosen
	$('#lookup_type').on('change',function(){    
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
	    	
	    	// here will be implemented next steps for looking up by owner
	    } else {
	    	
	    }
	});
	
	
	// populating form with properties to fill in depends on resourceType chosen
	$('#div-for-types').on('change',function(e){    
	    var resourceTypeId = $(e.target).data('selectedID');
	    if (typeof resourceTypeId != 'undefined')
		    $.ajax({    
		        type: 'GET',
		        url: '/lookUp/resourceProperties/'+ resourceTypeId,
		        contentType: 'application/json; charset=UTF-8',
		        dataType: 'json',
		        success: function(result){    
		        	var form = $('#form-for-properties'); 
		        	form.empty();
		            for (var j = 0; j < result.length; j++){                 
		                console.log(result[j].columnName + "--" + result[j].id);
		                form.append("<div class=\"form-group row\">" +
		                				"<label for='"+result[j].title+"' class=\"col-sm-2 control-label\">" + result[j].title+ "</label>" +
				                        "<div class=\"col-sm-10\">" +
				                            "<input type=\"text\" name= '" +result[j].id+ "' class=\"form-control\" id='" +result[j].title+ "' placeholder='" + result[j].title +"'>" +
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
	                
	                console.log('error');
	              
	            }
		    
		    });    
	    
	});
	
	
	$('#form-for-properties').submit(function(e) {
		var empty = true;
		$('.form-group input', $(this)).each(function() {
			let value = $(this).val();
			if (value.length > 0){
				empty = false;
			}
		});
		
		if (empty){
			console.log("no input");
			$('#no-inputs-error').html("Please, enter data at least in one field to make look up").css( "color", "red" );
			e.preventDefault();
		} else {
			var objMap = $('#form-for-properties').serializeArray();
			var json = objectifyForm(objMap);
			console.log(JSON.stringify(json));
			e.preventDefault();
		}
		
	});
	
	
	
});
function objectifyForm(formArray) {//serialize array to json function

	  var returnArray = {};
	  for (var i = 0; i < formArray.length; i++){
	    returnArray[formArray[i]['name']] = formArray[i]['value'];
	  }
	  return returnArray;
	}