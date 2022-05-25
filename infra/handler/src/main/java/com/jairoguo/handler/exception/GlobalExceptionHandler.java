package com.jairoguo.handler.exception;

import com.jairoguo.common.result.ResultBody;
import com.jairoguo.exception.result.ResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    * 统一数据异常
    */
    @ExceptionHandler(ResultException.class)
    @ResponseStatus(HttpStatus.OK)
    public <T> ResultBody<T> exception(ResultException e) {
        return ResultBody.<T>builder()
                .code(e.getCode())
                .msg(e.getMsg())
                .success(e.getSuccess())
                .info(e.getInfo())
                .build();
    }
}
