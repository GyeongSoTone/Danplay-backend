package com.danplay.server.match.dto;

import com.danplay.server.user.domain.entity.UserMatch;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;
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

	private String sports;

	private int maxNumberOfParticipants;

	@OneToMany(mappedBy = "match")
	private List<UserMatch> participants;

	private String place;

	private String title;

	private String content;

	private Date startTime;

	private Date applyTime;
}
