package com.yb.exception;

import lombok.Data;

/**
 * 运行时异常
 */
@Data
public class BaseException extends RuntimeException {

    private String code;
    private String message;

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseException(String message) {
        this.code = "500";
        this.message = message;
    }
}
