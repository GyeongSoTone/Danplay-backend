package com.danplay.server.usermatch.domain.entity;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    private Long hostId;

    @Builder
    public UserMatch(User user, Match match, Long hostId) {
        this.user = user;
        this.match = match;
        this.hostId = hostId;
    }
}
