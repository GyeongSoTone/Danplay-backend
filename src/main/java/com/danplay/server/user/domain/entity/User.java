package com.danplay.server.user.domain.entity;

import com.danplay.server.auth.enumerations.Authority;
import com.danplay.server.base.BaseEntity;
import com.danplay.server.user.domain.enumerations.Gender;
import com.danplay.server.user.domain.enumerations.Prefer;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class User extends BaseEntity {

    private String mail;

    private String loginId;

    private String password;

    private String name;

    private String phoneNumber;

    private Date birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

//    @Enumerated(EnumType.STRING)
//    private ArrayList<Prefer> preferSport;

    @OneToMany(mappedBy = "user")
    private List<UserMatch> matches;


    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.USER;
}
