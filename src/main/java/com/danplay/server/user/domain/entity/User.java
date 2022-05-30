package com.danplay.server.user.domain.entity;

import com.danplay.server.auth.enumerations.Authority;
import com.danplay.server.user.domain.enumerations.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Setter
    @OneToMany(mappedBy = "user")
    private List<PreferSport> preferSports;

    @OneToMany(mappedBy = "user")
    private List<UserMatch> matches;

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.USER;
}
