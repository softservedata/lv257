package com.softserve.edu.Resources.validator;

import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UploadFileValidator implements Validator{


    @Override
    public boolean supports(Class<?> clazz) {
        return ResourceRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ResourceRequest resourceRequest = (ResourceRequest)target;

        if(resourceRequest.getFile() == null ||
                resourceRequest.getFile().getOriginalFilename().equals("")){
           errors.rejectValue("file", null, "Please select an file to upload!");
           return;
        }
        if(!(resourceRequest.getFile().getContentType().equals("application/pdf") ||
                resourceRequest.getFile().getContentType().equals("image/jpeg") ||
                   resourceRequest.getFile().getContentType().equals("image/png"))){
            errors.rejectValue("file", null, "Please choose only pdf, jpeg or png files for upload!");
            return;
        }
    }
}
