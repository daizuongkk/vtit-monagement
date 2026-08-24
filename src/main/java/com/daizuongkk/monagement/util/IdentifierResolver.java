package com.daizuongkk.monagement.util;

import java.util.regex.Pattern;

import com.daizuongkk.monagement.entity.Identifier.IdentifierType;
import com.daizuongkk.monagement.exception.AppException;
import com.daizuongkk.monagement.exception.ErrorCode;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

public class IdentifierResolver {
  private IdentifierResolver() {
    /* This utility class should not be instantiated */
  }

  private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

  public static IdentifierType resolve(String input) {
    if (EMAIL_PATTERN.matcher(input).matches()) {
      return IdentifierType.EMAIL;
    }
    try {
      var number = phoneUtil.parse(input, "VN");
      if (phoneUtil.isValidNumber(number)) {
        return IdentifierType.PHONE;
      }
    } catch (NumberParseException ignored) {
      throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);

    }
    throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
  }

  public static String normalize(IdentifierType type, String input) {
    return switch (type) {
      case EMAIL -> input.trim().toLowerCase();
      case PHONE -> {
        try {
          var number = phoneUtil.parse(input, "VN");
          yield phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
          throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
        }
      }
      default -> throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
    };
  }
}