package com.danplay.server.user.dto;

import com.danplay.server.user.domain.entity.User;
import com.danplay.server.user.domain.enumerations.Gender;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ParticipantResponse {

	private Long id;

	private String mail;

	private String name;

	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	public static ParticipantResponse of(User user) {
		return ParticipantResponse.builder()
			.id(user.getId())
			.mail(user.getMail())
			.name(user.getName())
			.phoneNumber(user.getPhoneNumber())
			.gender(user.getGender())
			.build();
	}
}
