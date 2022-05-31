package com.danplay.server.user.dto;

import com.danplay.server.user.domain.enumerations.Gender;
import com.danplay.server.user.domain.enumerations.Prefer;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    @Pattern(regexp = "[0-9]{8}@dankook.ac.kr",
            message = "단국대학교 이메일 형식에 맞는 값이어야 합니다")
    private String mail;

    @Setter
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 알파벳, 숫자, 특수기호가 최소 1개 이상 포함해야 하며, 8자 ~ 20자의 길이여야 합니다")
    private String password;

    @NotBlank
    private String name;

    @Pattern(regexp="\\d{3}-\\d{4}-\\d{4}",
            message = "전화번호는 '-'를 사이에 두고 3, 4, 4의 길이여야 합니다")
    private String phoneNumber;

    private Date birth; // 정규표현식 요구

    @Enumerated(EnumType.STRING)
    private Gender gender; // 정규표현식 요구

    @Enumerated(EnumType.STRING)
    private List<Prefer> preferSport; // 정규표현식 요구
}
