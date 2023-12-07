package com.assessment.hotelbooking.validation;

import com.assessment.hotelbooking.validation.validator.UUIDFormatValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UUIDFormatValidator.class})
public @interface UUIDFormat {
  Class<?>[] groups() default {};

  String message() default "";

  Class<? extends Payload>[] payload() default {};
}