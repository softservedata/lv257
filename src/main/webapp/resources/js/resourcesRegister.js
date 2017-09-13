$(function(){
    switch(menu){

        case 'Register resource':
            $('#registerResources').addClass('active');
            break;

        case 'History':
            $('#requestHistory').addClass('active');
            break;

        default:
            if(menu == "Send Request") break;
            $('#sendRequest').addClass('active');
            break;
    }

    //dismissing the alert after 3 seconds

    var $alert = $('.alert');

    if($alert.length){

        setTimeout(function(){
          $alert.fadeOut('slow');
        }, 3000)

    }


});

