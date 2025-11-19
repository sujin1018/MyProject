package com.project.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.book.enums.SocialType;
import com.project.book.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
