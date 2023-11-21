package com.linknote.server.common.exception;


public class TokenNotProvidedException extends RuntimeException {
    public TokenNotProvidedException(String message) {
        super("TokenCache not provided" +
                (message != null ? ". Message: " + message : "") + ".");
    }

}
