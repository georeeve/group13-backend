package com.example.group13backend.validators;

import com.example.group13backend.annotations.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (value.length() <= 8) {
                return false;
            }
            return true;
        } catch (Exception e) {
//            throw new RuntimeException(e);
            return false;
        }
    }
}
