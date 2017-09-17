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