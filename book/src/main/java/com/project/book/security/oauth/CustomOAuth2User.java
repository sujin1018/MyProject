package com.project.book.security.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.project.book.enums.Role;

import lombok.Getter;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {
	private Long userId;
	private String email;
	private Role role;

	public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
							Map<String, Object> attributes,
							String nameAttributeKey,
							String email,
							Role role,
							Long userId) {

		// 부모인 DefaultOAuth2User 클래스의 생성자 호출
		super(authorities, attributes, nameAttributeKey);
		this.email = email;
		this.role = role;
		this.userId = userId;
	}
}
