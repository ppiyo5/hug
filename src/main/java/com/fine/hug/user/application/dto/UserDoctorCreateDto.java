package com.fine.hug.user.application.dto;

import com.fine.hug.user.domain.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDoctorCreateDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    @Email(regexp = "^(.+)@(.+)$")
    private String email;

    @NotBlank
    private String hospital;

    @NotBlank
    private String major;

    @NotBlank
    private Role role;

}
