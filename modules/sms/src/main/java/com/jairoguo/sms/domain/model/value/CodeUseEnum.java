package com.jairoguo.sms.domain.model.value;

import java.util.Objects;

/**
 * 验证码用途.
 *
 * @author Jairo Guo
 */
public enum CodeUseEnum {


    /**
     * 注册.
     */
    REGISTER("REGISTER"),

    LOG_IN("LOG_IN"),

    /**
     * 验证.
     */
    VALID("VALID");

    private final String type;

    CodeUseEnum(String type) {
        this.type = type;
    }

    public static CodeUseEnum get(String type) {
        for (CodeUseEnum value : values()) {
            if ((Objects.equals(type, value.type))) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
