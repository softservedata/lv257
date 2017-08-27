$('#show_owner_person_address_form').click(function(){
    $('.owner_person_address_form').slideToggle(450);
    return false;
});

$('#show_owner_company_address_form').click(function(){
    $('.owner_company_address_form').slideToggle(450);
    return false;
});

$(document).ready(function(){

    $("#owner_search").change(function(){
        var valOpt2 = $(this).find('option:selected').val();
        if(valOpt2==1){
            $('.find_person').slideUp(400);
            $('.find_company').slideUp(400);
        }
        if(valOpt2==2){
            $('.find_company').delay(450).slideToggle(450);
            $('.find_person').slideUp(450);
        }
        if(valOpt2==3){
            $('.find_person').delay(450).slideToggle(450);
            $('.find_company').slideUp(450);
        }

    });

    $("#owner_type").change(function(){
        var valOpt = $(this).find('option:selected').val();
        if(valOpt==1){
            $('.add_person').slideUp(400);
            $('.add_company').slideUp(400);
        }
        if(valOpt==2){
            $('.add_company').delay(450).slideToggle(450);
            $('.add_person').slideUp(450);
        }
        if(valOpt==3){
            $('.add_person').delay(450).slideToggle(450);
            $('.add_company').slideUp(450);
        }

    });

    $("#search_company_by").change(function(){
        var valOpt = $(this).find('option:selected').val();
        if(valOpt==1){
            $('.search_company_name').slideUp(400);
            $('.search_ipn_number').slideUp(400);
        }
        if(valOpt==2){
            $('.search_company_name').delay(450).slideToggle(450);
            $('.search_ipn_number').slideUp(450);
        }
        if(valOpt==3){
            $('.search_ipn_number').delay(450).slideToggle(450);
            $('.search_company_name').slideUp(450);
        }

    });

    $("#search_person_by").change(function(){
        var valOpt = $(this).find('option:selected').val();
        if(valOpt==1){
            $('.persons_name_search').slideUp(400);
            $('.persons_passport_search').slideUp(400);
        }
        if(valOpt==2){
            $('.persons_name_search').delay(450).slideToggle(450);
            $('.persons_passport_search').slideUp(450);
        }
        if(valOpt==3){
            $('.persons_passport_search').delay(450).slideToggle(450);
            $('.persons_name_search').slideUp(450);
        }

    });

});