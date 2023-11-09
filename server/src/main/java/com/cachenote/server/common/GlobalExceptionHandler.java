package com.cachenote.server.common;


import com.cachenote.server.common.ResponseWrapper.OkWrapper;
import com.cachenote.server.common.ResponseWrapper.ReceptionWrapper;
import com.cachenote.server.common.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
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

}


