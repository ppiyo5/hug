package com.fine.hug.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    BASIC("ROLE_BASIC", "일반 사용자"),
    DOCTOR("ROLE_DOCTOR", "의료진 회원"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
