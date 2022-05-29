package com.jairoguo.account.domain.model.value;

import com.jairoguo.common.base.ValueObject;

public enum OpenCodeTypeEnum implements ValueObject {
    /**
     * 用户名.
     */
    USERNAME,
    /**
     * 邮箱.
     */
    EMAIL,
    /**
     * 手机号.
     */
    PHONE,
    /**
     * 授权.
     */
    AUTHORIZE
}
