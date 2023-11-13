package com.cachenote.server.common.exception;

import jakarta.annotation.Nullable;

public class BadUsernamePasswordException extends RuntimeException {
    public BadUsernamePasswordException(String message) {
        super(message);
    }


}
