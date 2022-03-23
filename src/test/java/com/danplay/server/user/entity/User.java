package com.danplay.server.user.entity;

import com.danplay.server.usermatch.entity.UserMatch;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String email;

	private String password;

	private String name;

	private Date birth;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@OneToMany(mappedBy = "user")
	private List<UserMatch> matches = new ArrayList<>();
}
