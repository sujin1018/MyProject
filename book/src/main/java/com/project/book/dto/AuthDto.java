package com.project.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthDto {
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TokenInfo {
		private String grantType;
		private String accessToken;
		private Long accessTokenExpiresIn;
		private String refreshToken;
	}
}
