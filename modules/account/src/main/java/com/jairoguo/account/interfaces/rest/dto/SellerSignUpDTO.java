package com.jairoguo.account.interfaces.rest.dto;

/**
 * @author Jairo Guo
 */
public record SellerSignUpDTO(
        String phone,
        String smsCode,
        String username,
        String password
) {
}
