package com.fine.hug.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDoctorRepository extends JpaRepository<UserDoctor, Long> {
    Optional<UserDoctor> findByUserId(String userId);
}
