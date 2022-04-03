package com.danplay.server.user.domain.entity;

import com.danplay.server.user.domain.enumerations.Gender;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    private Date birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Nullable
    @OneToMany(mappedBy = "user")
    private List<UserMatch> matches = new ArrayList<>();

    @Setter
    private Boolean emailAuthentication;
}
