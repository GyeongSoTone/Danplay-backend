package com.danplay.server.mail.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class MailCodeRequest {

    @Pattern(regexp = "[0-9]{8}@dankook.ac.kr")
    private String mail;

    @Pattern(regexp = "\\d{6}")
    private String code;
}
