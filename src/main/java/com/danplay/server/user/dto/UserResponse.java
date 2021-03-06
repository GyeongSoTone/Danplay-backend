package com.danplay.server.user.dto;

import com.danplay.server.auth.dto.TokenResponse;
import com.danplay.server.user.domain.enumerations.Gender;
import com.danplay.server.user.domain.enumerations.Prefer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponse {

    private String mail;

    private String name;

    private String phoneNumber;

    private Date birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Setter
    private List<Prefer> preferSport;

    @Setter
    private TokenResponse tokenResponse;
}
