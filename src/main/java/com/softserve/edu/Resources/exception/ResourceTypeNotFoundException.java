package com.softserve.edu.Resources.exception;


public class ResourceTypeNotFoundException extends RuntimeException {
    private final String message;

    public ResourceTypeNotFoundException(String message) {
        this.message = message;
    }
}
