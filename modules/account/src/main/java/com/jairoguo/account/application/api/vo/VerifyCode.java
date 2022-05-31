package com.jairoguo.account.application.api.vo;

public record VerifyCode(
        String phone,
        Boolean status
) {
}
