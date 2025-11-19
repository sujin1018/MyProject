package com.project.book.security.oauth.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final String REDIRECT_URL = "http://localhost:3000/login"; // 임시

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
		log.info("소셜 로그인 실패. 에러 메시지 : {}", exception.getMessage());

		String redirectUrl = UriComponentsBuilder.fromUriString(REDIRECT_URL)
			.build()
			.toUriString();

		// 로그인 페이지로 리다이렉트
		getRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}
}
