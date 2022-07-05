package com.orange.exception;

import java.io.Serial;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.exception
 * @className : CustomException
 * @description:
 * @date : 2022/7/5 13:22
 */
public class CustomException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomException(Integer code) {
        super();
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
