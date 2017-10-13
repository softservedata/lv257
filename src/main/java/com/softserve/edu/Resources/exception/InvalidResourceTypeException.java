package com.softserve.edu.Resources.exception;

public class InvalidResourceTypeException extends RuntimeException {
    private String message;

    public InvalidResourceTypeException(String message) {
        this.message = message;
    }
}
