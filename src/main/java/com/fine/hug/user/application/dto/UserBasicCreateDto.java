package com.fine.hug.user.application.dto;

import com.fine.hug.user.domain.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBasicCreateDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    @NotBlank
    @Email(regexp = "^(.+)@(.+)$")
    private String email;

    @NotBlank
    private String visitPath;

    @NotBlank
    private Role role;

}
