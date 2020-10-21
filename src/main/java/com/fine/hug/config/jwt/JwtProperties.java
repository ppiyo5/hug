package com.fine.hug.config.jwt;

public interface JwtProperties {
    String SECRET = "hug"; //우리 서버만 알고 있는 비밀값
    Long EXPIRATION_TIME = 30 * 60 * 1000L; //30분
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
