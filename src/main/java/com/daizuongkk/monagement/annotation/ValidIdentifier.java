package com.daizuongkk.monagement.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.daizuongkk.monagement.validator.IdentifierValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IdentifierValidator.class)
public @interface ValidIdentifier {

  String message() default "Invalid email or phone number";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}