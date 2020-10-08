package com.fine.hug.exception;

import com.fine.hug.error.ErrorCode;

public class AlreadyIdException extends BusinessException {

    public AlreadyIdException(String message) {
        super(ErrorCode.ALREADY_ID, message);
    }

    public AlreadyIdException() {
        super(ErrorCode.ALREADY_ID, ErrorCode.ALREADY_ID.getMessage());
    }
}
