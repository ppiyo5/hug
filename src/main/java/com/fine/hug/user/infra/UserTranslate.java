package com.fine.hug.user.infra;

import com.fine.hug.user.application.dto.UserCreateDto;
import com.fine.hug.user.domain.User;
import com.fine.hug.user.ui.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTranslate {

    public static User translate(UserCreateDto dto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return User.builder()
                .userId(dto.getUserId())
                .password(encoder.encode(dto.getPassword()))
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .build();
    }

    public static UserResponseDto translate(User user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return UserResponseDto.builder()
                .userId(user.getUserId())
                .password(encoder.encode(user.getPassword()))
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }

}
