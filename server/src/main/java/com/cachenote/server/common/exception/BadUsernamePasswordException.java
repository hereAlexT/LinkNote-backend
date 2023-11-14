package com.cachenote.server.common.exception;

public class BadUsernamePasswordException extends RuntimeException {
    public BadUsernamePasswordException(String message) {
        super(message);
    }


}
