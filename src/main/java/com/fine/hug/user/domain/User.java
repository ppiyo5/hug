package com.fine.hug.user.domain;

import com.fine.hug.user.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "userType")
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String userId, String userName, String password, String gender, String birthDate, String email, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.role = role;
    }
}
