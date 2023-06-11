package com.example.dev.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {

    private ErrorCode errorCode;
    private Object data;

    public CommonException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
