package com.project.book.service;

import com.project.book.dto.AuthDto;
import com.project.book.enums.Role;

public interface AuthService {
	AuthDto.TokenInfo login(Long userId, Role role);

	AuthDto.TokenInfo reissue(AuthDto.TokenRequest tokenRequest);
}
