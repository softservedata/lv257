
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
                email: "Invalid email address!"
            },
            password: {
                required: "Password should not be empty"
            },
            confirmPassword: {
                required: "Confirm Password should not be empty",
                equalTo: "Confirm Password should be equal to password"
            }
        }
    })
})
