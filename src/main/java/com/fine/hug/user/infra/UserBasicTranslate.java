package com.fine.hug.user.infra;

import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.domain.Role;
import com.fine.hug.user.domain.UserBasic;
import com.fine.hug.user.ui.dto.UserBasicResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBasicTranslate {

    public static UserBasic translate(UserBasicCreateDto dto) {
        return UserBasic.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .userName(dto.getUserName())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .visitPath(dto.getEmail())
                .role(Role.BASIC)
                .build();
    }

    public static UserBasicResponseDto translate(UserBasic userBasic) {
        return UserBasicResponseDto.builder()
                .userId(userBasic.getUserId())
                .password(userBasic.getUser().getPassword())
                .userName(userBasic.getUser().getUserName())
                .birthDate(userBasic.getUser().getBirthDate())
                .gender(userBasic.getUser().getGender())
                .email(userBasic.getUser().getEmail())
                .visitPath(userBasic.getUser().getEmail())
                .role(Role.BASIC)
                .build();
    }
}
