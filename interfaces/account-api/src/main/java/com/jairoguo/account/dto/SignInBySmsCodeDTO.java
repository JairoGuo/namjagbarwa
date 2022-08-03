package com.jairoguo.account.dto;

import com.jairoguo.validated.annotation.Mobile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jairo Guo
 */
public record SignInBySmsCodeDTO(
        @Mobile
        @NotNull(message = "手机号不能为null")
        @NotBlank(message = "手机号不能为空格")
        String phone,
        @NotNull(message = "短信验证码不能为null")
        @NotBlank(message = "短信验证码不能为空格")
        String code) {
}
