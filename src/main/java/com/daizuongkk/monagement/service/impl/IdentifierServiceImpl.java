package com.daizuongkk.monagement.service.impl;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.Identifier.IdentifierType;
import com.daizuongkk.monagement.entity.User;
import com.daizuongkk.monagement.exception.AppException;
import com.daizuongkk.monagement.exception.ErrorCode;
import com.daizuongkk.monagement.repository.IdentifierRepository;
import com.daizuongkk.monagement.service.IdentifierService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdentifierServiceImpl implements IdentifierService {
  private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
  private final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

  private final IdentifierRepository identifierRepository;

  @Override
  public IdentifierType resolve(String value) {
    if (EMAIL_PATTERN.matcher(value).matches()) {
      return IdentifierType.EMAIL;
    }
    try {
      var number = phoneUtil.parse(value, "VN");
      if (phoneUtil.isValidNumber(number)) {
        return IdentifierType.PHONE;
      }
      throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
    } catch (NumberParseException e) {
      throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
    }
  }

  @Override
  public Identifier create(User user, RegisterRequest registerRequest) {

    boolean isFirst = !identifierRepository.existsByUserId(user.getId());

    IdentifierType identifierType = resolve(registerRequest.getIdentifier());
    String identifierValue = normalize(identifierType, registerRequest.getIdentifier());

    Identifier toSave = Identifier.builder()
        .user(user)
        .type(identifierType)
        .value(identifierValue)
        .primary(isFirst)
        .verified(false)
        .build();

    return identifierRepository.save(toSave);
  }

  @Override
  public String normalize(IdentifierType type, String input) {
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
