package com.joel.users.domain.exceptions;

public class UserNotFoundException extends EntityNotFoundException{

    public UserNotFoundException() {
        super("User not registered in the database");
    }
}
