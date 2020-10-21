package com.fine.hug.exception;

import com.fine.hug.error.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(ErrorCode.ENTITY_NOT_FOUND, message);
    }
}
