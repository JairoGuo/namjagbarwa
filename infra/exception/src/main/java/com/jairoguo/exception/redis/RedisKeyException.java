package com.jairoguo.exception.redis;

/**
 * @author Jairo Guo
 */
public class RedisKeyException extends RuntimeException{

    private final String msg;

    public RedisKeyException(String msg) {
        super(msg);
        this.msg = msg;

    }

    public String getMsg() {
        return msg;
    }
}

