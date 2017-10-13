package com.softserve.edu.Resources.exception;

public class RemovingCategoriesWithTypesException extends RuntimeException {
    public RemovingCategoriesWithTypesException() {
    }

    public RemovingCategoriesWithTypesException(String message) {
        super(message);
    }

    public RemovingCategoriesWithTypesException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemovingCategoriesWithTypesException(Throwable cause) {
        super(cause);
    }

    public RemovingCategoriesWithTypesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
