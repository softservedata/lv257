
$(document).ready(function(){
    $('#formregister').validate({
        rules:{
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            confirmPassword: {
                required: true,
                equalTo: "#password"
            }
        },
        messages: {
            email: {
                required: "Email should not be empty",
                email: "Invalid email address!",
                remote: "Email address already in use. Please use other email."
            },
            password: {
                required: "Password should not be empty"
            },
            confirmPassword: {
                required: "Confirm Password should not be empty",
                equalTo: "Confirm Password should be equal to password"
            }
        }
    });

    $('#email').blur(function(){
        check_email_exist();
    })
});

function check_email_exist(){

    $('#email_error').hide();
    let email = $('#email').val();

    $.ajax({
        type: "POST",
        contentType: "text/plain",
        url: projectPathPrefix + "/checkemail",
        accept: "text/plain",
        data: email,
        success: function(request){
            if(request == true){
                $('#email_error').show().html('Email address already in use. Please use other email.');
            }
        },
        error: function(res){
        }
    })
}
