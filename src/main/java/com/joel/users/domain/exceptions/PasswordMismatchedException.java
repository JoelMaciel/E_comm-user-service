package com.joel.users.domain.exceptions;

public class PasswordMismatchedException extends BusinessException {

    public PasswordMismatchedException() {
        super("Mismatched old password");
    }
}