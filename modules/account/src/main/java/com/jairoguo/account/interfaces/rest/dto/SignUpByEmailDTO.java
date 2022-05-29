package com.jairoguo.account.interfaces.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * @author Jairo Guo
 */
public record SignUpByEmailDTO(
        @Email(message = "非邮箱类型")
        String email,

        @Size(min = 8, max = 20, message = "密码长度必须保证8到20位")
        String password
) {
}
