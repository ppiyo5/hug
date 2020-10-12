package com.fine.hug.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBasic {

    @Id
    private String userId;

    @Column(nullable = false)
    private String visitPath;

    @Embedded
    private User user;

    @Builder
    public UserBasic(final String userId, String visitPath, final String userName, final String password, final String gender, final String birthDate, final String email, final Role role) {
        this.userId = userId;
        this.visitPath = visitPath;
        this.user = new User(userName, password, gender, birthDate, email, role);
    }
}
