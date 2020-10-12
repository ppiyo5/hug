package com.fine.hug.user.ui.dto;

import com.fine.hug.user.domain.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDoctorResponseDto {

    private String userId;

    private String password;

    private String userName;

    private String birthDate;

    private String gender;

    private String email;

    private String hospital;

    private String access;

    private Role role;

    @Builder
    public UserDoctorResponseDto(String userId, String password, String userName, String birthDate, String gender, String email, String hospital, String access, Role role) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.hospital = hospital;
        this.access = access;
        this.role = role;
    }
}
