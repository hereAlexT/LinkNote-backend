package com.cachenote.server.common.exception;

public class NoteAccessDeniedException extends RuntimeException {
    public NoteAccessDeniedException(Long id) {
        super("Access to note " + id + " is not allowed");
    }

}
