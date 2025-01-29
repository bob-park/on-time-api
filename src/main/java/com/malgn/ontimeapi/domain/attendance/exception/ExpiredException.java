package com.malgn.ontimeapi.domain.attendance.exception;

public class ExpiredException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Expired.";

    public ExpiredException() {
        super(DEFAULT_MESSAGE);
    }

    public ExpiredException(String message) {
        super(DEFAULT_MESSAGE + ": " + message);
    }

    public ExpiredException(String message, Throwable cause) {
        super(DEFAULT_MESSAGE + ": " + message, cause);
    }

    public ExpiredException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(DEFAULT_MESSAGE + ": " + message, cause, enableSuppression, writableStackTrace);
    }
}
