package com.jairoguo.sms.interfaces.rest.dto;

import com.jairoguo.sms.domain.model.value.CodeUseEnum;
import com.jairoguo.validated.annotation.Mobile;

import javax.validation.constraints.NotNull;

/**
 * @author Jairo Guo
 */
public record CodeDTO(
        @Mobile
        @NotNull
        String phone, CodeUseEnum use) {
}
