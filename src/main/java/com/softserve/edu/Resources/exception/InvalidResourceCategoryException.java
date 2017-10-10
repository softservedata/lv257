package com.softserve.edu.Resources.exception;


public class InvalidResourceCategoryException extends RuntimeException {
    private String message;

    public InvalidResourceCategoryException(String message) {
        this.message = message;
    }
}
