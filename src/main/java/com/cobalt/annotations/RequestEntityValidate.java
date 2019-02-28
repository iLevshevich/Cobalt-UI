package com.cobalt.annotations;

import com.cobalt.validators.RequestEntityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestEntityValidator.class)
public @interface RequestEntityValidate {
    String message() default "Invalid objects value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
