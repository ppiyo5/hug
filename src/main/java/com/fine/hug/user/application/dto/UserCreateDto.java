package com.fine.hug.user.application.dto;

import com.fine.hug.user.domain.UserBasic;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    @NotBlank
    private String gender;

    @NotBlank
    private String birthDate;

    @Email(regexp = "^(.+)@(.+)$")
    private String email;

    @NotBlank
    private String visitPath;

    @Builder
    public UserCreateDto(@NotBlank String userId, @NotBlank String password, @NotBlank String userName, @NotBlank String gender, @NotBlank String birthDate, @Email(regexp = "^(.+)@(.+)$") String email, @NotBlank String visitPath) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.visitPath = visitPath;
    }

    public UserBasic toEntity() {
        return UserBasic.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .gender(gender)
                .birthDate(birthDate)
                .email(email)
                .visitPath(visitPath)
                .build();
    }
}
