$(function(){

    var $alert = $('.alert');

    if($alert.length){

        setTimeout(function(){
            $alert.fadeOut('slow');
        }, 3000)
    }

    var $requestForm = $('#requestForm');

    //$requestForm.fontcolor("red");

    if ($requestForm.length) {
        $requestForm.validate({

            rules : {
                theme : {
                    minlength : 4
                },
                details : {
                    minlength : 4
                }
            },
            messages : {
                name : {
                    minlength : 'The request name should not be less than 2 letters'
                },
                description : {
                    minlength : 'The request name should not be less than 2 letters'
                }
            },
            errorElement : 'em',
            errorPlacement : function(error, element) {
                //add the class of help-block
                error.addClass('help-block');


                //add the error element after the input element
                error.insertAfter(element);
            }
        });
    }
});
