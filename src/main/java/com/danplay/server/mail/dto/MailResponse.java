package com.danplay.server.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MailResponse {

    private String message;

    private String mail;

    private Boolean confirmStatus;

    public MailResponse(String message, String mail, Boolean confirmStatus) {
        this.message = message;
        this.mail = mail;
        this.confirmStatus = confirmStatus;
    }
}
