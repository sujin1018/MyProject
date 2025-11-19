package com.project.book.service;

import com.project.book.entity.UserEntity;

public interface UserService {
	UserEntity findUser(Long userId);
}
