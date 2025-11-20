package com.project.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthDto {
	@Getter
	@NoArgsConstructor
	@Schema(name = "AuthDto.TokenRequest", description = "토큰 요청 DTO")
	public static class TokenRequest {
		@Schema(description = "액세스 토큰")
		private String accessToken;
		@Schema(description = "리프레시 토큰")
		private String refreshToken;
	}

	@Getter
	@Builder
	@AllArgsConstructor
	@Schema(name = "AuthDto.TokenInfo", description = "토큰")
	public static class TokenInfo {
		@Schema(description = "토큰 타입")
		private String grantType;
		@Schema(description = "액세스 토큰")
		private String accessToken;
		@Schema(description = "리프레시 토큰")
		private String refreshToken;
		@Schema(description = "액세스 토큰 만료 시간")
		private Long accessTokenExpiresIn;
	}
}
