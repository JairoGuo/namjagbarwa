package com.jairoguo.sms.domain.model.entity.id;

import cn.hutool.core.lang.Validator;
import com.jairoguo.common.base.Id;
import com.jairoguo.common.result.Result;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Phone implements Id {
    private String phone;

    private Phone() {
    }

    public static Phone create() {
        return new Phone();
    }

    public void setPhone(String phone) {
        if (!Validator.isMobile(phone)) {
            Result.fail("非法手机号");
        }
        this.phone = phone;
    }
}
