package com.jairoguo.handler.exception;

import com.jairoguo.common.result.ResultBody;
import com.jairoguo.common.result.ResultCodeEnum;
import com.jairoguo.exception.result.ResultException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public <T> ResultBody<T> bindExceptionHandler(BindException e) {
        String message = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());

        return ResultBody.<T>builder()
                .code(ResultCodeEnum.INFO.getCode())
                .msg("参数校验错误")
                .info(message)
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public <T> ResultBody<T> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResultBody.<T>builder()
                .code(ResultCodeEnum.INFO.getCode())
                .msg("参数校验错误")
                .info(errors)
                .build();


    }
}
