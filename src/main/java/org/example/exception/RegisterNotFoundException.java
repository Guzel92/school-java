package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegisterNotFoundException extends RuntimeException {
    public RegisterNotFoundException() {
    }

    public RegisterNotFoundException(String message) {
        super(message);
    }

    public RegisterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterNotFoundException(Throwable cause) {
        super(cause);
    }

    public RegisterNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
