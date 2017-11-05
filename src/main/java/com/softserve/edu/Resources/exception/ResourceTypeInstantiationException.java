package com.softserve.edu.Resources.exception;

public class ResourceTypeInstantiationException extends RuntimeException {
    public ResourceTypeInstantiationException() {
    }

    public ResourceTypeInstantiationException(String message) {
        super(message);
    }

    public ResourceTypeInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceTypeInstantiationException(Throwable cause) {
        super(cause);
    }

    public ResourceTypeInstantiationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
