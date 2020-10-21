package com.fine.hug.user.infra;

import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.domain.Role;
import com.fine.hug.user.domain.UserBasic;
import com.fine.hug.user.ui.dto.UserBasicResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBasicTranslate {

    public static UserBasic translate(UserBasicCreateDto dto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserBasic.builder()
                .userId(dto.getUserId())
                .password(encoder.encode(dto.getPassword()))
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .visitPath(dto.getVisitPath())
                .role(Collections.singleton(Role.BASIC))
                .build();
    }

    public static UserBasicResponseDto translate(UserBasic userBasic) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserBasicResponseDto.builder()
                .userId(userBasic.getUserId())
                .password(encoder.encode(userBasic.getPassword()))
                .userName(userBasic.getUserName())
                .email(userBasic.getEmail())
                .visitPath(userBasic.getVisitPath())
                .role(Role.BASIC)
                .build();
    }
}
