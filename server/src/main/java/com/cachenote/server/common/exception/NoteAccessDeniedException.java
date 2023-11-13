package com.cachenote.server.common.exception;

import jakarta.annotation.Nullable;

public class NoteAccessDeniedException extends RuntimeException {
    public NoteAccessDeniedException(Long userId, Long noteId, @Nullable String message) {
        super("User " + userId + " is not authorized to access note " + noteId +
                (message != null ? ". Message: " + message : "") + ".");
    }

}
