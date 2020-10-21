package com.fine.hug.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@DiscriminatorValue("BASIC")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDoctor extends User {

    @Column(nullable = false)
    private String hospital;

    @Column(nullable = false)
    private String major;

    private String userId;

    private String userName;

    private String password;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @Builder
    public UserDoctor(String hospital, String major, String userId, String userName, String password, String email, Set<Role> role) {
        this.hospital = hospital;
        this.major = major;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
