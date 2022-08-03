package com.jairoguo.auth.dto;

/**
 * @author Jairo Guo
 */
public record LogInDTO(
        String openCode,
        String password,
        String smsCode

) {
}
