package com.danplay.server.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class DanplayExceptionDto {

    private String code;

    private String message;
}
