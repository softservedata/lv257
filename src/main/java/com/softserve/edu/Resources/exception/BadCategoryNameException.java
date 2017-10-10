package com.softserve.edu.Resources.exception;

public class BadCategoryNameException extends RuntimeException {
    public BadCategoryNameException() {
    }

    public BadCategoryNameException(String message) {
        super(message);
    }

    public BadCategoryNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCategoryNameException(Throwable cause) {
        super(cause);
    }

    public BadCategoryNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

