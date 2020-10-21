package com.fine.hug.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("손님"),
    BASIC("일반 사용자"),
    DOCTOR("의료진 회원"),
    ADMIN("관리자");

    private final String title;
}
