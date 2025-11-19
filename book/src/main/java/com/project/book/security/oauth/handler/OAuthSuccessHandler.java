package com.project.book.security.oauth.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.book.dto.AuthDto;
import com.project.book.service.AuthService;
import com.project.book.enums.Role;
import com.project.common.exception.ApiException;
import com.project.common.response.ResponseCode;
import com.project.book.security.oauth.CustomOAuth2User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final String MAIN_URL = "http://localhost:8000/success"; // 임시

	private final AuthService authService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		log.info("소셜 로그인 성공");

		try {
			CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

			Long userId = oauthUser.getUserId();
			Role role = oauthUser.getRole();

			AuthDto.TokenInfo tokenInfo = authService.login(userId, role);

			String redirectUrl = returnRedirectUrl(tokenInfo);
			getRedirectStrategy().sendRedirect(request, response, redirectUrl);
		}
		catch (Exception e) {
			throw new ApiException(ResponseCode.NOT_FOUND);
		}
	}

	public String returnRedirectUrl(AuthDto.TokenInfo tokenResponse) {
		return UriComponentsBuilder.fromUriString(MAIN_URL)
			.queryParam("accessToken", tokenResponse.getAccessToken())
			.queryParam("refreshToken", tokenResponse.getRefreshToken())
			.build().toUriString();
	}
}
