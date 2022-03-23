package com.danplay.server.match.entity;

import com.danplay.server.usermatch.entity.UserMatch;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

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
