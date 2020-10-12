package com.fine.hug.user.infra;

import com.fine.hug.user.application.dto.UserDoctorCreateDto;
import com.fine.hug.user.domain.Role;
import com.fine.hug.user.domain.UserDoctor;
import com.fine.hug.user.ui.dto.UserDoctorResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDoctorTranslate {

    public static UserDoctor translate(UserDoctorCreateDto dto) {
        return UserDoctor.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .userName(dto.getUserName())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .hospital(dto.getHospital())
                .access(dto.getAccess())
                .role(Role.DOCTOR)
                .build();
    }

    public static UserDoctorResponseDto translate(UserDoctor userDoctor) {
        return UserDoctorResponseDto.builder()
                .userId(userDoctor.getUserId())
                .password(userDoctor.getUser().getPassword())
                .userName(userDoctor.getUser().getUserName())
                .birthDate(userDoctor.getUser().getBirthDate())
                .gender(userDoctor.getUser().getGender())
                .email(userDoctor.getUser().getEmail())
                .hospital(userDoctor.getHospital())
                .access(userDoctor.getAccess())
                .role(Role.DOCTOR)
                .build();
    }
}
