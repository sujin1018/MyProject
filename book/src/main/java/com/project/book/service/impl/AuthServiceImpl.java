package com.project.book.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.book.dto.AuthDto;
import com.project.book.service.AuthService;
import com.project.book.enums.Role;
import com.project.book.security.jwt.TokenProvider;
import com.project.book.entity.UserEntity;
import com.project.book.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserService userService;
	private final TokenProvider tokenProvider;

	@Override
	@Transactional
	public AuthDto.TokenInfo login(Long userId, Role role){
		UserEntity user = userService.findUser(userId);
		String refreshToken = user.getRefreshToken();
		AuthDto.TokenInfo tokenInfo;

		// DB에 RefreshToken이 아직 등록되지 않았거나, 만료 또는 잘못된 토큰인 경우
		if(refreshToken == null || tokenProvider.validateToken(refreshToken) == false) {
			tokenInfo = tokenProvider.generateToken(userId, role);  // Access Token, RefreshToken 재발급
			user.updateRefreshToken(tokenInfo.getRefreshToken());  // DB RefreshToken 업데이트
		}
		else {
			tokenInfo = tokenProvider.generateAccessTokenByRefreshToken(userId, role, refreshToken);  // Access Token 재발급
		}
		return tokenInfo;
	}
}
