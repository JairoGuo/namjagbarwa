package com.jairoguo.exception.result;

/**
 * @author Jairo Guo
 */
public class ResultException extends RuntimeException{
    private final String code;
    private final String msg;
    private final Boolean success;
    private final transient Object info;

    public <T> ResultException(String code, String msg, T info) {
        this.msg = msg;
        this.code = code;
        this.success = false;
        this.info = info;
    }

    public ResultException(String code, String msg) {
        this.msg = msg;
        this.code = code;
        this.success = false;
        this.info = null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Object getInfo() {
        return info;
    }
}
