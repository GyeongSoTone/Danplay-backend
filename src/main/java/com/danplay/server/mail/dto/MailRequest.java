package com.danplay.server.mail.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class MailRequest {

    @Pattern(regexp = "[0-9]{8}@dankook.ac.kr")
    private String mail;

}
