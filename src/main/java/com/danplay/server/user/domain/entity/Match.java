package com.danplay.server.user.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Match {

    @Id
    @GeneratedValue
    @Column(name = "match_id")
    private Long id;

    private String sports;

    private int maxNumberOfParticipants;

    @OneToMany(mappedBy = "match")
    private List<UserMatch> participants = new ArrayList<>();

    private String place;

    private String content;

    private Date startTime;

    private Date applyTime;
}
