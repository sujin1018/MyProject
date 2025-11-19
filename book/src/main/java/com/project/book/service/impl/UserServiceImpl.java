package com.project.book.service.impl;

import org.springframework.stereotype.Service;

import com.project.book.entity.UserEntity;
import com.project.book.repository.UserRepository;
import com.project.book.service.UserService;
import com.project.book.common.exception.ApiException;
import com.project.book.common.response.ResponseCode;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserEntity findUser(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new ApiException(ResponseCode.NOT_FOUND, "존재하지 않는 사용자입니다."));
	}
}
