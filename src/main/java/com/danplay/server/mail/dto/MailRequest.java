package com.danplay.server.mail.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class MailRequest {

    @Pattern(regexp = "[0-9]{8}@dankook.ac.kr",
            message = "단국대학교 이메일 형식에 맞는 값이어야 합니다")
    private String mail;
}
