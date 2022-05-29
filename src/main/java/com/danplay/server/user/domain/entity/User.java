package com.danplay.server.user.domain.entity;

import com.danplay.server.auth.enumerations.Authority;
import com.danplay.server.base.BaseEntity;
import com.danplay.server.user.domain.enumerations.Gender;
import com.danplay.server.user.domain.enumerations.Prefer;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String mail;

    private String password;

    private String name;

    private String phoneNumber;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Enum<Prefer> preferSport;

    @OneToMany(mappedBy = "user")
    private List<UserMatch> matches;

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.USER;
}
