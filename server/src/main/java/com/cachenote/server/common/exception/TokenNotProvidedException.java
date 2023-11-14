package com.cachenote.server.common.exception;


public class TokenNotProvidedException extends RuntimeException {
    public TokenNotProvidedException(String message) {
        super("Token not provided" +
                (message != null ? ". Message: " + message : "") + ".");
    }

}
