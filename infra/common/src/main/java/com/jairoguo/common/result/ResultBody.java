package com.jairoguo.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * @author Jairo Guo
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBody<T> implements Serializable {
    /**
     * 错误代码
     */
    private final String code;
    /**
     * 消息
     */
    private final String msg;
    /**
     * 成功状态
     */
    private final Boolean success;
    /**
     * 返回结构体
     */
    private final transient T data;
    /**
     * 提示信息
     */
    private final transient Object info;

    public ResultBody(Builder<T> builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.success = builder.success;
        this.data = builder.data;
        this.info = builder.info;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<R> {

        private String code;
        private String msg;
        private Boolean success;
        private R data;
        private Object info;

        public Builder() {
            this.code = ResultCodeEnum.OK.getCode();
            this.msg = ResultCodeEnum.OK.getMsg();
            this.success = true;
            this.data = null;
            this.info = null;
        }

        public Builder<R> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<R> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<R> success(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder<R> data(R data) {
            this.data = data;
            return this;
        }

        public Builder<R> info(Object info) {
            this.info = info;
            return this;
        }

        public ResultBody<R> build() {
            return new ResultBody<>(this);

        }

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

    public T getData() {
        return data;
    }

    public Object getInfo() {
        return info;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
