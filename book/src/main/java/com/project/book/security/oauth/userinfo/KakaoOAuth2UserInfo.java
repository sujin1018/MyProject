package com.project.book.security.oauth.userinfo;

import lombok.extern.slf4j.Slf4j;
import java.util.Map;

@Slf4j
public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

	public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() { // 카카오 소셜로그인 아이디
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getNickname() { // 카카오 소셜로그인 닉네임
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
		if (account == null) {
			return null;
		}

		Map<String, Object> profile = (Map<String, Object>) account.get("profile");
		if (profile == null) {
			return null;
		}

		return (String) profile.get("nickname");
	}
}
