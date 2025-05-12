package com.joel.users.domain.exceptions;

public abstract class EntityNotFoundException extends BusinessException {

    protected EntityNotFoundException(String message) {
        super(message);
    }

}