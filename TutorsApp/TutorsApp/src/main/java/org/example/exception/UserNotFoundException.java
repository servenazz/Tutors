package org.example.exception;

public class UserNotFoundException extends BaseRuntimeException {

    public UserNotFoundException() {
        super("User is not found");
    }
}

