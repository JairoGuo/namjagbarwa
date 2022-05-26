package com.jairoguo.validated.annotation;

import com.jairoguo.validated.constraints.MobileValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Jairo Guo
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
@Documented
public @interface Mobile {
    String message() default "不是合法的移动手机号码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
