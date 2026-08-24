package com.daizuongkk.monagement.validator;

import java.util.regex.Pattern;

import com.daizuongkk.monagement.annotation.ValidIdentifier;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentifierValidator
    implements ConstraintValidator<ValidIdentifier, String> {

  private static final Pattern EMAIL_PATTERN = Pattern.compile(
      "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

  private static final Pattern PHONE_PATTERN = Pattern.compile(
      "^(\\+84|0)[35789]\\d{8}$");

  @Override
  public boolean isValid(
      String value,
      ConstraintValidatorContext context) {

    if (value == null || value.isBlank()) {
      return true;
    }

    String identifier = value.trim();

    return EMAIL_PATTERN.matcher(identifier).matches()
        || PHONE_PATTERN.matcher(identifier).matches();
  }
}