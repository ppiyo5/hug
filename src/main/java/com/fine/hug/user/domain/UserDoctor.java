package com.fine.hug.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("D")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hospital;

    @Column(nullable = false)
    private String access = "0";

    @Embedded
    private User user;

    @Builder
    public UserDoctor(final Long id, final String userId, final String userName, final String password, final String gender, final String birthDate, final String email, final Role role, final String hospital, final String access) {
        this.id = id;
        this.hospital = hospital;
        this.access = access;
        this.user = new User(userId, userName, password, gender, birthDate, email, role);
    }
}
