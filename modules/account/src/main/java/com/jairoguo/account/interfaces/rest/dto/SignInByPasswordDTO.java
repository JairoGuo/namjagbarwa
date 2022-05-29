package com.jairoguo.account.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Jairo Guo
 */
public record SignInByPasswordDTO(
        @NotNull(message = "用户名不能为null")
        @NotBlank(message = "用户名不能为空格")
        String signInName,
        @NotNull(message = "密码不能为null")
        @NotBlank(message = "密码不能为空格")
        String password) {
}
