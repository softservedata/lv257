package com.softserve.edu.Resources.util;

import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationDTOUtilityTest {

    private static FieldError fieldError;
    private static ValidationErrorDTO validationErrorDTO;
    private static String defaultField = "default field";
    private static String defaultMessage = "default message";

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ValidationDTOUtility validationDTOUtility;

    @BeforeClass
    public static void setUpt(){
        fieldError = new FieldError("object", defaultField,
                new Object(), false,
                new String[]{}, new Object[]{},
                defaultMessage);

        validationErrorDTO = new ValidationErrorDTO();
        validationErrorDTO.addFieldError(defaultField, defaultMessage);
    }

    @Test
    public void getErrorSTOTest(){
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError));

        ValidationErrorDTO errorDTO = validationDTOUtility.getErrorDTO(bindingResult);

        assertEquals(errorDTO.getFieldErrors().size(), validationErrorDTO.getFieldErrors().size());
        assertEquals(errorDTO.getFieldErrors().get(0).getMessage(), validationErrorDTO.getFieldErrors().get(0).getMessage());
        verify(bindingResult, times(1)).getFieldErrors();
    }

}
