package com.danplay.server.base.exception;

import static com.danplay.server.base.exception.ExceptionCodeAndDetails.INVALID_REQUEST_TYPE;
import static com.danplay.server.base.exception.ExceptionCodeAndDetails.NOT_FOUND_API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DanplayException.class)
    public ResponseEntity<DanplayExceptionDto> ExceptionHandler(DanplayException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new DanplayExceptionDto(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<DanplayExceptionDto> handleError404() {
        String code = NOT_FOUND_API.getCode();
        String message = NOT_FOUND_API.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DanplayExceptionDto(code, message));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<DanplayExceptionDto> validException() {
        String code = INVALID_REQUEST_TYPE.getCode();
        String message = INVALID_REQUEST_TYPE.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new DanplayExceptionDto(code, message));
    }
}
