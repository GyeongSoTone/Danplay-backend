package com.danplay.server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class SignInRequest {

    @Pattern(regexp = "[0-9]{8}@dankook.ac.kr",
            message = "단국대학교 이메일 형식에 맞는 값이어야 합니다")
    private String mail;

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 알파벳, 숫자, 특수기호가 최소 1개 이상 포함해야 하며, 8자 ~ 20자의 길이여야 합니다")
    private String password;
}
