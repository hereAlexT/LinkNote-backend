package com.linknote.server.common;

import lombok.Getter;

/**
 * 1 - 99: Success
 * 6xx: Client Error
 * 7xx: Server Error
 */
@Getter
public enum StatusCode {
    OK(1, "OK"),
    /**
     * When the email address not linked to any account
     * or the account exists but the password is wrong.
     */
    BAD_CREDENTIAL(600, "Wrong username or password"),
    /**
     * BAD_NOTE_REQUEST used for NoteAccessDeniedException and NoteNotFoundException.
     */
    BAD_NOTE_REQUEST(600, "Note not exist or unauthorized visit"),
    EXPIRED_JWT_TOKEN(601, "Expired JWT TokenCache"),
    INVALID_JWT_TOKEN(602, "Invalid JWT TokenCache"),
    NO_JWT_TOKEN(603, "No JWT TokenCache Provided"),

    /**
     * Un-authorized endpoints visit.
     */
    ACCESS_DENIED(604, "Not authorized to this resources"),
    /**
     * The account exists.
     */
    ACCOUNT_EXIST(605, "Account exist"),

    /**
     * Invalid input. E.g. invalid email address, invalid password.
     */
    BAD_REQUEST(606, "Bad Request"),


    /**
     * Other error that not related to any action in the frontend.
     */
    INTERNAL_SERVER_ERROR(700, "Internal Server Error");


    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
