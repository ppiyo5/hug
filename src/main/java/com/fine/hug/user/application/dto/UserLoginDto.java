package com.fine.hug.user.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

}
