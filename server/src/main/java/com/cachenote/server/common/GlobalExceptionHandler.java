package com.cachenote.server.common;


import com.cachenote.server.common.ResponseWrapper.OkWrapper;
import com.cachenote.server.common.ResponseWrapper.ReceptionWrapper;
import com.cachenote.server.common.exception.NoteAccessDeniedException;
import com.cachenote.server.common.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ReceptionWrapper<String> handleNoteNotFoundException(NoteNotFoundException ex) {
        return new ReceptionWrapper<>("Note Not Found");
    }

    @ExceptionHandler(NoteAccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ReceptionWrapper<String> handleNoteAccessDeniedException(NoteAccessDeniedException e) {
        return new ReceptionWrapper<>("You don't have access to the note");
    }

}


