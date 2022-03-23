package com.danplay.server.user.dto;

import com.danplay.server.user.domain.enumerations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpRequest {

    private String email;

    private String password;

    private String name;

    private Date birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
