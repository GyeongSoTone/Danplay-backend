package com.danplay.server.user.dto;

import com.danplay.server.user.domain.enumerations.Gender;
import com.danplay.server.user.domain.enumerations.Prefer;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Date;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    private String mail;

    private String loginId;

    @Setter
    private String password;

    private String name;

    private String phoneNumber;

    private Date birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private ArrayList<Prefer> preferSport;
}
