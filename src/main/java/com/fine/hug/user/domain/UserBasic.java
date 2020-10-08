package com.fine.hug.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("B")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBasic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String visitPath;

    @Embedded
    private User user;

    @Builder
    public UserBasic(final Long id, final String userId, final String userName, final String password, final String gender, final String birthDate, final String email, final Role role, final String visitPath) {
        this.id = id;
        this.visitPath = visitPath;
        this.user = new User(userId, userName, password, gender, birthDate, email, role);
    }
}
