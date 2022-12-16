package com.example.group13backend.validators;

import com.example.group13backend.annotations.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    // password must contain one or more lowercase characters
    Pattern lowercasePattern = Pattern.compile(".*[a-z].*");
    Matcher lowercaseMatcher = lowercasePattern.matcher(value);

    // password must contain one or more uppercase characters
    Pattern uppercasePattern = Pattern.compile(".*[A-Z].*");
    Matcher uppercaseMatcher = uppercasePattern.matcher(value);

    // password must contain one or more special characters
    Pattern symbolPattern = Pattern.compile(".*[!@#$%^&*].*");
    Matcher symbolMatcher = symbolPattern.matcher(value);

    // password must contain one or more numeric values
    Pattern numberPattern = Pattern.compile(".*[0-9].*");
    Matcher numberMatcher = numberPattern.matcher(value);
    try {
      if (value.length() > 8
      // TODO: password validator uncomment for live app
      //                            && lowercaseMatcher.matches()
      //                            && uppercaseMatcher.matches()
      //                            && symbolMatcher.matches()
      //                            && numberMatcher.matches()
      ) {
        return true;
      }
      return false;
    } catch (Exception e) {
      //            throw new RuntimeException(e);
      return false;
    }
  }
}
