package com.fine.hug.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBasicRepository extends JpaRepository<UserBasic, Long> {
    Optional<UserBasic> findByUserId(String userId);
}
