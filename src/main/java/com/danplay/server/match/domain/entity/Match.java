package com.danplay.server.match.domain.entity;

import com.danplay.server.match.dto.MatchRequest;
import com.danplay.server.usermatch.domain.entity.UserMatch;
import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
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
	public Match(String sports, int maxNumberOfParticipants, String place, String title,
		String content, Date startTime) {
		this.sports = sports;
		this.maxNumberOfParticipants = maxNumberOfParticipants;
		this.place = place;
		this.title = title;
		this.content = content;
		this.startTime = startTime;
	}

	public void updateMatch(MatchRequest matchRequest) {
		this.sports = matchRequest.getSports();
		this.maxNumberOfParticipants = matchRequest.getMaxNumberOfParticipants();
		this.place = matchRequest.getPlace();
		this.startTime = matchRequest.getStartTime();
		this.title = matchRequest.getTitle();
		this.content = matchRequest.getContent();
	}

	public void addUser(UserMatch userMatch) {
		this.participants.add(userMatch);
		if (userMatch.getMatch() != this) {
			userMatch.setMatch(this);
		}
	}

	@Override
	public String toString() {
		return "Match{" +
			"id=" + id +
			", sports='" + sports + '\'' +
			", maxNumberOfParticipants=" + maxNumberOfParticipants +
			", participants=" + participants +
			", place='" + place + '\'' +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", startTime=" + startTime +
			", applyTime=" + applyTime +
			'}';
	}
}
