package com.joel.users.domain.exceptions;

public class CpfAlreadyExistsException extends EntityInUseException {

    public CpfAlreadyExistsException(String message) {
        super(message);
    }
}
