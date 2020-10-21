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
public class UserBasic extends User {

    @Column(nullable = false)
    private String visitPath;

    private String userId;

    private String userName;

    private String password;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @Builder
    public UserBasic(String visitPath, String userId, String userName, String password, String email, Set<Role> role) {
        this.visitPath = visitPath;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
