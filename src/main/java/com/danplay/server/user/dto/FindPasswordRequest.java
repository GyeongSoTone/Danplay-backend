package com.danplay.server.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class FindPasswordRequest {

    @Pattern(regexp = "[0-9]{8}@dankook.ac.kr",
            message = "단국대학교 이메일 형식에 맞는 값이어야 합니다")
    private String mail;

    @NotBlank
    private String name;

    @Pattern(regexp="\\d{3}-\\d{4}-\\d{4}",
            message = "전화번호는 '-'를 사이에 두고 3, 4, 4의 길이여야 합니다")
    private String phoneNumber;
}
