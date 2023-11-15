package com.cachenote.server.common.exception;

import jakarta.annotation.Nullable;

public class AccountExistException extends RuntimeException {
    public AccountExistException(String email, @Nullable String message) {
        super("Email " + email + " already exist " +
                (message != null ? ". Message: " + message : "") + ".");

    }

    public AccountExistException(String email) {
        super("Email " + email + " already exist ");

    }
}
