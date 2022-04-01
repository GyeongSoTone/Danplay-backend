package com.danplay.server.mail.dto;

import lombok.Getter;

@Getter
public class MailCodeRequest {
    private String mail;
    private String code;
}
