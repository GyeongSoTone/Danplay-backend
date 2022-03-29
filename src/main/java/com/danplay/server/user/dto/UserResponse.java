package com.danplay.server.user.dto;

import com.danplay.server.user.domain.enumerations.Gender;
import com.danplay.server.user.domain.enumerations.Sports;
import java.util.Date;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

	private String email;

	private String password;

	private String name;

	private Date birth;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private List<Sports> favorites;
}
