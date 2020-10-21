package com.fine.hug.user.infra;

import com.fine.hug.user.application.dto.UserDoctorCreateDto;
import com.fine.hug.user.domain.Role;
import com.fine.hug.user.domain.UserDoctor;
import com.fine.hug.user.ui.dto.UserDoctorResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDoctorTranslate {

    public static UserDoctor translate(UserDoctorCreateDto dto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserDoctor.builder()
                .userId(dto.getUserId())
                .password(encoder.encode(dto.getPassword()))
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .hospital(dto.getHospital())
                .major(dto.getMajor())
//                .role(Role.DOCTOR)
                .build();
    }

    public static UserDoctorResponseDto translate(UserDoctor userDoctor) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserDoctorResponseDto.builder()
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
