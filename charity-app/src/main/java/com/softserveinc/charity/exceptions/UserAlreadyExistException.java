package com.softserveinc.charity.exceptions;

public final class UserAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    private static final String MESSAGE_TEMPLATE = "User with email { %s } already exist.";

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(final String email) {
        super(String.format(MESSAGE_TEMPLATE, email));
    }
}
