package com.softserve.edu.Resources.exception;

public class RemovingInstantiatedTypeException extends RuntimeException {
    public RemovingInstantiatedTypeException() {
    }

    public RemovingInstantiatedTypeException(String message) {
        super(message);
    }

    public RemovingInstantiatedTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemovingInstantiatedTypeException(Throwable cause) {
        super(cause);
    }

    public RemovingInstantiatedTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
