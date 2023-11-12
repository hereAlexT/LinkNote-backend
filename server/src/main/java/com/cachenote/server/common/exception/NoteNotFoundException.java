package com.cachenote.server.common.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(Long id) {
        super("Note not found or you do not have permission to access it.");
    }
}

