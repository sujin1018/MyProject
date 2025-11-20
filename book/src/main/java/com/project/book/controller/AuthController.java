package com.project.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.book.common.response.ResponseCode;
import com.project.book.common.response.ResponseData;
import com.project.book.dto.AuthDto;
import com.project.book.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "로그인 관련")
public class AuthController {

	private final AuthService authService;

	@Operation(
		summary = "JWT 토큰 재발급 API",
		description = "JWT 토큰 재발급 (Refresh Token으로 Access Token 재발급)",
		tags = {"AuthController"}
	)
	@PostMapping("/reissue")
	public ResponseEntity<ResponseData<AuthDto.TokenInfo>> reissue(@RequestBody AuthDto.TokenRequest tokenRequest) {
		AuthDto.TokenInfo response = authService.reissue(tokenRequest);
		return ResponseData.toResponseEntity(ResponseCode.SUCCESS, response);
	}

}
