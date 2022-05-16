package com.danplay.server.user.domain.entity;

import com.danplay.server.match.domain.entity.Match;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserMatch {

    @Id
    @GeneratedValue
    @Column(name = "user_match_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
