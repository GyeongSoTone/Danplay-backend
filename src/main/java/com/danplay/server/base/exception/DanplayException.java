package com.danplay.server.base.exception;

import lombok.Getter;

@Getter
public class DanplayException extends RuntimeException {

    private final ExceptionCodeAndDetails codeAndMessage = ExceptionCodeAndDetails
            .findByClass(this.getClass());

    private String code;
    private String message;

    public DanplayException() {
        this.code = codeAndMessage.getCode();
        this.message = codeAndMessage.getMessage();
    }
}
