package com.softserve.edu.Resources.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

        @ExceptionHandler(MultipartException.class)
        public ModelAndView handleMaxSizeException() {
            ModelAndView modelAndView = new ModelAndView("403");
            modelAndView.getModel().put("message", "You tried uploading too big a file!\n" +
                    "Maximum file size is 5 MB.\n" +
                    "Next time be careful!");
            return modelAndView;

    }

}