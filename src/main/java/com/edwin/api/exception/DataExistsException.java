package com.edwin.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
public class DataExistsException extends RuntimeException{
    public DataExistsException() {
    }

    public DataExistsException(String message) {
        super(message);
    }

    public DataExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataExistsException(Throwable cause) {
        super(cause);
    }

    public DataExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
