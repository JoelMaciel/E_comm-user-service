package com.joel.users.domain.exceptions;

public class EmailAlreadyExistsException extends EntityInUseException{

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
