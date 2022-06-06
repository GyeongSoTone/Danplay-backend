package com.danplay.server.match.dto;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.user.dto.ParticipantResponse;
import com.danplay.server.usermatch.domain.entity.UserMatch;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MatchResponse {

	private Long id;

	private Long hostId;

	private String sports;

	private int maxNumberOfParticipants;

	private List<ParticipantResponse> participants;

	private String place;

	private String title;

	private String content;

	private String startTime;

	private String applyTime;

	public static MatchResponse of(Match match) {
		List<ParticipantResponse> participantResponses = match.getParticipants().stream()
			.map(UserMatch::getUser)
			.map(ParticipantResponse::of)
			.collect(Collectors.toList());

		return MatchResponse.builder()
			.id(match.getId())
			.hostId(match.getHostId())
			.sports(match.getSports())
			.maxNumberOfParticipants(match.getMaxNumberOfParticipants())
			.participants(participantResponses)
			.place(match.getPlace())
			.title(match.getTitle())
			.content(match.getContent())
			.startTime(match.getStartTime().toString())
			.applyTime(match.getApplyTime().toString())
			.build();
	}
}
