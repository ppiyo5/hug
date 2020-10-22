package com.fine.hug.user.infra;

import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.domain.Role;
import com.fine.hug.user.domain.UserBasic;
import com.fine.hug.user.ui.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBasicTranslate {

    public static UserBasic translate(UserBasicCreateDto dto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserBasic.createBasic()
                .visitPath(dto.getVisitPath())
                .userId(dto.getUserId())
                .password(encoder.encode(dto.getPassword()))
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .role(Collections.singleton(Role.BASIC))
                .build();
    }

    public static UserResponseDto translate(UserBasic userBasic) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserResponseDto.builder()
                .visitPath(userBasic.getVisitPath())
                .userId(userBasic.getUserId())
                .password(encoder.encode(userBasic.getPassword()))
                .userName(userBasic.getUserName())
                .email(userBasic.getEmail())
                .role(Role.BASIC)
                .build();
    }
}
