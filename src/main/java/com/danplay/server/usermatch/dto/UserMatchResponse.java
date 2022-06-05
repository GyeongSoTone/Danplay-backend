package com.danplay.server.usermatch.dto;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserMatchResponse {

	private Long id;

	private Match match;

	private User user;
}
