package com.danplay.server.user.domain.entity;

import com.danplay.server.user.domain.enumerations.Prefer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreferSport {

    @Id
    @GeneratedValue
    @Column(name = "preferSport_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Prefer prefer;
}
