package com.fine.hug.user.domain;

import com.fine.hug.user.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(uniqueConstraints = @UniqueConstraint(name = "UNIQUE_LOGIN_ID", columnNames = {"loginId"}))
@Entity
@Inheritance(strategy =InheritanceType.JOINED)
@DiscriminatorColumn(name="USER_TYPE")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
//    private String gender;
//
//    @Column(nullable = false)
//    private String birthDate;

    @Column(nullable = false)
    private String email;

    //여러개의 enum을 가질 수 있으므로 ElementCollection이라고 맵핑 해줘야 함
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Set<Role> role;
}
