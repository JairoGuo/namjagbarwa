package com.jairoguo.sms.domain.model.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Jairo Guo
 */
@Getter
@ToString
public class Code {
    private String code;

    private Code() {
    }

    public static Code create() {
        return new Code();
    }

    public void generateCode() {
        this.code = RandomUtil.randomNumbers(4);
    }

    public void setCode(String code) {
        this.code = code;
    }




}
