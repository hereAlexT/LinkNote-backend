package com.linknote.server.common;


import com.linknote.server.common.exception.AccountExistException;
import com.linknote.server.common.responseWrapper.ExceptionWrapper;
import com.linknote.server.common.exception.BadUsernamePasswordException;
import com.linknote.server.common.exception.NoteAccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionWrapper<String>> handleRuntimeException(RuntimeException e) {
        HttpStatus status;
        int code;
        String message;

        if (e instanceof NoteAccessDeniedException) {
            status = HttpStatus.NOT_FOUND;
            code = StatusCode.BAD_NOTE_REQUEST.getCode();
            message = "Note not exist or you are not authorized to the note.";
        } else if (e instanceof BadUsernamePasswordException) {
            status = HttpStatus.UNAUTHORIZED;
            code = StatusCode.BAD_CREDENTIAL.getCode();
            message = e.getMessage();
        } else if (e instanceof AccountExistException) {
            status = HttpStatus.CONFLICT;
            code = StatusCode.ACCOUNT_EXIST.getCode();
            message = e.getMessage();
        } else {
            // Default handling for other runtime exceptions
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            code = StatusCode.INTERNAL_SERVER_ERROR.getCode();
            message = "An unexpected error occurred." + e.getMessage();
        }

        logger.debug(e.getMessage());
        // Ensure that the ResponseEntity is parameterized with the correct generic type
        return new ResponseEntity<>(new ExceptionWrapper<>(code, message, null), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionWrapper<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HttpStatus status;
        int code;
        String message;

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        status = HttpStatus.BAD_REQUEST;
        code = StatusCode.BAD_REQUEST.getCode();
        message = "Bad Request";
        return new ResponseEntity<>(new ExceptionWrapper<>(code, errors.toString(), null), status);
    }

}