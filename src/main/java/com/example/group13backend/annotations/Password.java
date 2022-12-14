package com.example.group13backend.annotations;

import static java.lang.annotation.ElementType.FIELD;

import com.example.group13backend.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {

  String message() default "Password is invalid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
