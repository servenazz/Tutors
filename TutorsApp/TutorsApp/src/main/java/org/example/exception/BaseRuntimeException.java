package org.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseRuntimeException extends RuntimeException {

    private int status;
    private String error;

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
        this.error = message;
    }

    public BaseRuntimeException(HttpStatus status, String message, String error) {
        super(message);
        this.status = status.value();
        this.error = error;
    }



}