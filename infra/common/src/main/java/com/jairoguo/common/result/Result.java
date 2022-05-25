package com.jairoguo.common.result;

import com.jairoguo.exception.result.ResultException;

/**
 * @author Jairo Guo
 */
public class Result {

    public static <T> ResultBody<T> success() {
        return ResultBody.<T>builder().build();
    }

    public static <T> ResultBody<T> success(T result) {
        return ResultBody.<T>builder().data(result).build();
    }

    public static <T> ResultBody<T> fail(ResultCodeEnum resultCode) {
        throw new ResultException(resultCode.getCode(), resultCode.getMsg());
    }

    public static <T> void fail(ResultCodeEnum resultCode, T info) {
        throw new ResultException(resultCode.getCode(), resultCode.getMsg(), info);
    }

    public static void fail(String msg) {
        throw new ResultException(ResultCodeEnum.ERROR.getCode(), msg);
    }

    public static <T> void fail(String msg, T info) {
        throw new ResultException(ResultCodeEnum.ERROR.getCode(), msg, info);
    }

    public static void fail(String code, String msg) {
        throw new ResultException(code, msg);
    }

    public static <T> void fail(String code, String msg, T info) {
        throw new ResultException(code, msg, info);
    }

    public static <T> ResultBody<T> info(ResultCodeEnum resultCode) {
        return ResultBody.<T>builder()
                .code(resultCode.getCode())
                .msg(resultCode.getMsg())
                .success(false)
                .build();
    }

    public static <T> ResultBody<T> info(ResultCodeEnum resultCode, String msg) {
        return ResultBody.<T>builder()
                .code(resultCode.getCode())
                .msg(msg)
                .success(false)
                .build();
    }

    public static <T> ResultBody<T> info(ResultCodeEnum resultCode, Object info) {
        return ResultBody.<T>builder()
                .code(resultCode.getCode())
                .msg(resultCode.getMsg())
                .success(false)
                .info(info)
                .build();
    }

    public static <T> ResultBody<T> info(ResultCodeEnum resultCode, String msg, Object info) {
        return ResultBody.<T>builder()
                .code(resultCode.getCode())
                .msg(msg)
                .success(false)
                .info(info)
                .build();
    }

    public static <T> ResultBody<T> info(String msg) {
        return ResultBody.<T>builder()
                .code(ResultCodeEnum.INFO.getCode())
                .msg(msg)
                .success(false)
                .build();
    }

    public static <T> ResultBody<T> info(String code, String msg, Object info) {
        return ResultBody.<T>builder()
                .code(code)
                .msg(msg)
                .success(false)
                .info(info)
                .build();
    }

    public static <T> ResultBody<T> info(Object info) {
        return ResultBody.<T>builder()
                .code(ResultCodeEnum.INFO.getCode())
                .msg(ResultCodeEnum.INFO.getMsg())
                .success(false)
                .info(info)
                .build();
    }

    public static <T> ResultBody<T> info(String code, String msg) {
        return ResultBody.<T>builder()
                .code(code)
                .msg(msg)
                .success(false)
                .build();
    }
    private Result() {
    }
}
