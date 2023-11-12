package com.cachenote.server.common;


import com.cachenote.server.common.ResponseWrapper.ReceptionWrapper;
import com.cachenote.server.common.exception.NoteAccessDeniedException;
import com.cachenote.server.common.exception.NoteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ReceptionWrapper<String> handleNoteNotFoundException(NoteNotFoundException e) {
        logger.debug(e.getMessage());
        return new ReceptionWrapper<>("Note not exist or you are not authorized to the note.");
    }


    @ExceptionHandler(NoteAccessDeniedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // Use "Not Found": Don't reveal the existence of the note to attackers.
    public ReceptionWrapper<String> handleNoteAccessDeniedException(NoteAccessDeniedException e) {
        logger.debug(e.getMessage());
        return new ReceptionWrapper<>("Note not exist or you are not authorized to the note.");
    }


}


