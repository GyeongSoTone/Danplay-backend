package com.danplay.server.user.dto;

import com.danplay.server.user.domain.enumerations.Gender;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParticipantResponse {

	private Long id;

	private String mail;

	private String name;

	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private Boolean isHost;
}
