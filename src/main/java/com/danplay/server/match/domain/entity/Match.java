package com.danplay.server.match.domain.entity;

import com.danplay.server.user.domain.entity.UserMatch;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor
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

	private String title;

	private String content;

	private Date startTime;

	@CreationTimestamp
	private Date applyTime;

	@Builder
	public Match(String sports, int maxNumberOfParticipants, String place, String title, String content, Date startTime) {
		this.sports = sports;
		this.maxNumberOfParticipants = maxNumberOfParticipants;
		this.place = place;
		this.title = title;
		this.content = content;
		this.startTime = startTime;
	}
}
