package com.fine.hug.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDoctor {

    @Id
    private String userId;

    @Column(nullable = false)
    private String hospital;

    @Column(nullable = false)
    private String access = "0";

    @Embedded
    private User user;

    @Builder
    public UserDoctor(final String userId, final String hospital, final String access, final String userName, final String password, final String gender, final String birthDate, final String email, final Role role) {
        this.userId = userId;
        this.hospital = hospital;
        this.access = access;
        this.user = new User(userName, password, gender, birthDate, email, role);
    }
}
