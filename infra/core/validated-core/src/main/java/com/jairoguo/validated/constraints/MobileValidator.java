package com.jairoguo.validated.constraints;

import cn.hutool.core.lang.Validator;
import com.jairoguo.validated.annotation.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Jairo Guo
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Validator.isMobile(s);
    }
}
