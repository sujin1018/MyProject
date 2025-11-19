package com.project.book.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.project.book.enums.Role;
import com.project.book.enums.SocialType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class UserEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "social_id")
	private String socialId;

	@Column(name = "social_type")
	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "refresh_token")
	private String refreshToken;

	@Column(name = "created_dt", insertable = false, updatable = false)
	private LocalDateTime createdDt;

	@Builder(builderMethodName = "UserBuilder")
	public UserEntity(String email, String nickname, String socialId, SocialType socialType) {
		this.email = email;
		this.nickname = nickname;
		this.socialId = socialId;
		this.socialType = socialType;
		this.role = Role.ROLE_USER;
	}

	public void updateUser(String nickname) {
		if (nickname != null) {
			this.nickname = nickname;
		}
	}

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
