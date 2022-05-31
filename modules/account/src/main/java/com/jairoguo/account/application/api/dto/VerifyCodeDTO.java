package com.jairoguo.account.application.api.dto;


public record VerifyCodeDTO(
        String phone,
        String use,
        String code
) {
}
