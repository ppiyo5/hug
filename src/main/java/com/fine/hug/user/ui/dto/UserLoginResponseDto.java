package com.fine.hug.user.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginResponseDto {

    private String userId;

    private String password;

    @Builder
    public UserLoginResponseDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
