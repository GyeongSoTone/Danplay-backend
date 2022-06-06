package com.danplay.server.usermatch.domain.entity;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserMatch {

    @Id
    @GeneratedValue
    @Column(name = "user_match_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "match_id")
    private Match match;

    @Builder
    public UserMatch(User user, Match match) {
        this.user = user;
        this.match = match;
    }
}
