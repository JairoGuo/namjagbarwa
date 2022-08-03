package com.jairoguo.handler.exception;

import com.jairoguo.common.FeignUtil;
import com.jairoguo.common.result.ResultBody;
import com.jairoguo.common.result.ResultCodeEnum;
import com.jairoguo.exception.result.ResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ResultBody<?>> exception(Exception e, WebRequest request) {


        if (e instanceof ResultException resultException) {
            return handleResultException(resultException, request);
        }

        return handleException(e);
    }

    private ResponseEntity<ResultBody<?>> handleResultException(ResultException e, WebRequest request) {
        log.info("返回异常: {}", e.getMsg());

        ResultBody<?> resultBody = ResultBody.builder()
                .code(e.getCode())
                .msg(e.getMsg())
                .success(e.getSuccess())
                .info(e.getInfo())
                .build();
        /*
        如果通过feign调用，当前服务请求头中有feign_id返回带有500错误的结果体；否则为直接服务调用，返回200
         */
        if (request.getHeader(FeignUtil.FEIGN_ID) != null) {
            return new ResponseEntity<>(resultBody, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(resultBody, HttpStatus.OK);
        }
    }

    private ResponseEntity<ResultBody<?>> handleException(Exception e) {
        log.info("系统异常: {}", e.getMessage());

        e.printStackTrace();
        ResultBody<?> resultBody = ResultBody.builder()
                .code(ResultCodeEnum.ERROR.getCode())
                .msg("服务端出现异常")
                .success(false)
                .info(e.getMessage())
                .build();
        return new ResponseEntity<>(resultBody, HttpStatus.INTERNAL_SERVER_ERROR);
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
