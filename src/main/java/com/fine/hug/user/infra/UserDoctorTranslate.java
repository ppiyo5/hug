package com.fine.hug.user.infra;

import com.fine.hug.user.application.dto.UserDoctorCreateDto;
import com.fine.hug.user.domain.Role;
import com.fine.hug.user.domain.UserDoctor;
import com.fine.hug.user.ui.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDoctorTranslate {

    public static UserDoctor translate(UserDoctorCreateDto dto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserDoctor.createDoctor()
                .userId(dto.getUserId())
                .password(encoder.encode(dto.getPassword()))
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .hospital(dto.getHospital())
                .major(dto.getMajor())
                .role(Collections.singleton(Role.DOCTOR))
                .build();
    }

    public static UserResponseDto translate(UserDoctor userDoctor) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserResponseDto.builder()
                .userId(userDoctor.getUserId())
                .password(encoder.encode(userDoctor.getPassword()))
                .userName(userDoctor.getUserName())
                .email(userDoctor.getEmail())
                .hospital(userDoctor.getHospital())
                .major(userDoctor.getMajor())
                .role(Role.DOCTOR)
                .build();
    }
}
