package com.danplay.server.base.exception;

import static com.danplay.server.base.exception.ExceptionCodeAndDetails.NOT_FOUND_API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DanplayException.class)
    public ResponseEntity<DanplayExceptionDto> ExceptionHandler(DanplayException exception) {
        return ResponseEntity.badRequest()
                .body(new DanplayExceptionDto(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<DanplayExceptionDto> handleError404() {
        String code = NOT_FOUND_API.getCode();
        String message = NOT_FOUND_API.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DanplayExceptionDto(code, message));
    }
}
