package com.cachenote.server.common;

import lombok.Getter;

/**
 * 1 - 99: Success
 * 6xx: Client Error
 * 7xx: Server Error
 */
@Getter
public enum StatusCode {
    OK(1, "OK"),
    BAD_CREDENTIAL(600, "Wrong username or password"),
    /**
     * BAD_NOTE_REQUEST used for NoteAccessDeniedException and NoteNotFoundException.
     */
    BAD_NOTE_REQUEST(600, "Note not exist or unauthorized visit"),
    EXPIRED_JWT_TOKEN(601, "Expired JWT Token"),
    INVALID_JWT_TOKEN(602, "Invalid JWT Token"),
    NO_JWT_TOKEN(603, "No JWT Token Provided"),


    /**
     * Un-authorized endpoints visit.
     */
    ACCESS_DENIED(603, "Not authorized to this resources"),

    INTERNAL_SERVER_ERROR(700, "Internal Server Error");


    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
