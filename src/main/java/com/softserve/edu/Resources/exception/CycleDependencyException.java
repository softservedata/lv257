package com.softserve.edu.Resources.exception;

public class CycleDependencyException extends RuntimeException {
    public CycleDependencyException() {
    }

    public CycleDependencyException(String message) {
        super(message);
    }

    public CycleDependencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CycleDependencyException(Throwable cause) {
        super(cause);
    }

    public CycleDependencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
