package com.softserve.edu.Resources.util;

import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class ValidationDTOUtility {

    private ValidationDTOUtility(){}

    /**
     * Builds simple dto object, containing info about errors in entity fields.
     *
     * @param bindingResult - spring provided object, containing info about entity constraint violations.
     * @return - dto, with field name and error message.
     */
    public ValidationErrorDTO getErrorDTO(BindingResult bindingResult){
        ValidationErrorDTO errorDTO = new ValidationErrorDTO();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        fieldErrors.forEach(error -> errorDTO.addFieldError(error.getField(), error.getDefaultMessage()));

        return errorDTO;
    }
}
