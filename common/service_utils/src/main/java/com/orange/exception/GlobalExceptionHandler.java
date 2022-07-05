package com.orange.exception;

import com.orange.ajaxresult.AjaxResult;
import com.orange.errorcode.ErrorCode;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package : com.orange.exception
 * @Author : yilantingfeng
 * @Date : 2022/7/2 4:43 PM
 * @Version : V1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AjaxResult error(Exception e) {
        e.printStackTrace();
        return AjaxResult.error();
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public AjaxResult error(CustomException e) {
        e.printStackTrace();
        return AjaxResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public AjaxResult error(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return AjaxResult.error(ErrorCode.REQUEST_METHOD_NOT_SUPPORT,ErrorCode.REQUEST_METHOD_NOT_SUPPORT_MSG);
    }
}
