package com.project.book.security.jwt;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter  {
	public static final String AUTHORIZATION= "Authorization";
	public static final String BEARER_PREFIX = "Bearer ";

	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String token = getToken(request);

		if(StringUtils.hasText(token) && tokenProvider.isExpiredToken(token) == true) {
			throw new JwtException("토큰 만료");
		}

		if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
			Authentication authentication = tokenProvider.getAuthentication(token); // 사용자 인증
			SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContentHolder에 인증정보 설정
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Authorization 헤더에서 Bearer 토큰을 추출
	 */
	private String getToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(7); // "Bearer " 제외한 실제 토큰 문자열 반환
		}
		return null;
	}
}
