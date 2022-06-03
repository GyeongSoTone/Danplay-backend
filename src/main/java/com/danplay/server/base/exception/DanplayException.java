package com.danplay.server.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DanplayException extends RuntimeException {

    private final ExceptionCodeAndDetails codeAndMessage = ExceptionCodeAndDetails
            .findByClass(this.getClass());

    private HttpStatus status;
    private String code;
    private String message;

    public DanplayException() {
        this.status = codeAndMessage.getStatus();
        this.code = codeAndMessage.getCode();
        this.message = codeAndMessage.getMessage();
    }
}
