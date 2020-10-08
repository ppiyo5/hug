package com.fine.hug.error;

import com.fine.hug.response.ApiResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(ApiResponseCode.BAD_PARAMETER, "Invalid Input Value"),
    NOT_REQUEST_PARAMETER(ApiResponseCode.BAD_PARAMETER, "파라미터가 존재하지 않습니다."),
    METHOD_NOT_ALLOWED(ApiResponseCode.BAD_PARAMETER, "Invalid Input Value"),
    ENTITY_NOT_FOUND(ApiResponseCode.NOT_FOUND, "Entity Not Found"),
    INTERNAL_SERVER_ERROR(ApiResponseCode.SERVER_ERROR, "Server Error"),
    INVALID_TYPE_VALUE(ApiResponseCode.BAD_PARAMETER, "타입이 잘못된 요청이 있습니다."),
    HANDLE_ACCESS_DENIED(ApiResponseCode.UNAUTHORIZED, "Access is Denied"),

    //user
    ALREADY_ID(ApiResponseCode.BAD_PARAMETER, "이미 존재하는 아이디입니다.");

    private final ApiResponseCode code;
    private final String message;
}
