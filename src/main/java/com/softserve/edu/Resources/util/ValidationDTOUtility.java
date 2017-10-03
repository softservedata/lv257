package com.softserve.edu.Resources.util;

import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class ValidationDTOUtility {

    private ValidationErrorDTO errorDTO;

    public ValidationDTOUtility(){}

    public ValidationErrorDTO getErrorDTO(BindingResult bindingResult){
        errorDTO = new ValidationErrorDTO();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        fieldErrors.forEach(error -> this.errorDTO.addFieldError(error.getField(), error.getDefaultMessage()));

        return this.errorDTO;
    }
}
