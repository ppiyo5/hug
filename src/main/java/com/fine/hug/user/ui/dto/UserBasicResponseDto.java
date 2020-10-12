package com.fine.hug.user.ui.dto;

import com.fine.hug.user.domain.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBasicResponseDto {

    private String userId;

    private String password;

    private String userName;

    private String birthDate;

    private String gender;

    private String email;

    private String visitPath;

    private Role role;

    @Builder
    public UserBasicResponseDto(String userId, String password, String userName, String birthDate, String gender, String email, String visitPath, Role role) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.visitPath = visitPath;
        this.role = role;
    }
}
