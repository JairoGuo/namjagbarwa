package com.jairoguo.account.application.api.dto;

import com.jairoguo.validated.annotation.Mobile;

import javax.validation.constraints.NotNull;

/**
 * @author Jairo Guo
 */
public record CodeDTO(
        @Mobile
        @NotNull
        String phone, String use) {
}
