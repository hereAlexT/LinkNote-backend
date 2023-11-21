package com.linknote.server.common.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(Long noteId, String message) {
        super("Note " + noteId + " not found." +
                (message != null ? ". Message: " + message : "") + ".");
    }
}

