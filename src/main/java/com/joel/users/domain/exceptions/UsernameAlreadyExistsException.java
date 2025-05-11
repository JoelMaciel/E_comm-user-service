package com.joel.users.domain.exceptions;

public class UsernameAlreadyExistsException extends EntityInUseException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
