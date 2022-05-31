package com.jairoguo.account.interfaces.rest.dto;

import com.jairoguo.validated.annotation.Mobile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jairo Guo
 */
public record SignUpByPhoneDTO(
        @Mobile
        @NotNull(message = "手机号不能为空")
        String phone,

        @Size(min = 4, max = 6, message = "验证码格式错误")
        String code,

        @Size(min = 8, max = 20, message = "密码长度必须保证8到20位")
        @NotNull(message = "密码不能为null")
        @NotBlank(message = "密码不能为空格")
        String password
) {
}
