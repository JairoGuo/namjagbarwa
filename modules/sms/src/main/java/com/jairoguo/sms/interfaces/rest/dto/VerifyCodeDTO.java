package com.jairoguo.sms.interfaces.rest.dto;

import com.jairoguo.sms.domain.model.value.CodeUseEnum;

public record VerifyCodeDTO(
        String phone,
        CodeUseEnum use,
        String code
) {
}
