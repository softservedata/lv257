package edu.softserve.validator;

import edu.softserve.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserDTO userDTO = (UserDTO) o;

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "confirmPassword.notequal");
        }
    }
}
