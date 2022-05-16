package com.danplay.server.match.dto;

import com.danplay.server.match.domain.entity.Match;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchRequest {

	private String sports;

	private int maxNumberOfParticipants;

	private String place;

	private String title;

	private String content;

	private Date startTime;

	public Match toEntity() {
		return Match.builder()
			.sports(this.sports)
			.maxNumberOfParticipants(this.maxNumberOfParticipants)
			.place(this.place)
			.title(this.title)
			.content(this.content)
			.startTime(this.startTime)
			.build();
	}
}
